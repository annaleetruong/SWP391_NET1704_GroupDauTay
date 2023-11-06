/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Staff;

import Daos.BirdNestDetail_TrackingDAO;
import Daos.Bird_Nest_TrackingDAO;
import Models.BirdNestDetail_TrackingDTO;
import Utils.MyAppConstants;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "addTrackingNote", urlPatterns = {"/addTrackingNote"})
public class addTrackingNote extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = MyAppConstants.StaffFeatures.VIEW_ALL_ORDER_CONTROLLER;
        String orderId = request.getParameter("orderId");
        String action = request.getParameter("action");

        try {
            if (!action.equals("Cancel")) {
                String birdNestId = request.getParameter("birdNestId");
                String eggs = request.getParameter("numberOfEggs");
                String males = request.getParameter("maleBirds");
                String females = request.getParameter("femaleBirds");
                String note = request.getParameter("note");
                String status = request.getParameter("progress");
                int numOfEggs = Integer.parseInt(eggs);
                int numOfMales = Integer.parseInt(males);
                int numOfFemales = Integer.parseInt(females);
                long millis = System.currentTimeMillis();
                Date currentDate = new Date(millis);
                BirdNestDetail_TrackingDAO trackingDetail = new BirdNestDetail_TrackingDAO();
                Bird_Nest_TrackingDAO tracking = new Bird_Nest_TrackingDAO();
                boolean addNote = trackingDetail.createBirdNestDetailTracking(new BirdNestDetail_TrackingDTO(birdNestId, note, currentDate));
                boolean updateTracking = tracking.updateStatusBirdNestTracking(birdNestId, numOfEggs, numOfMales, numOfFemales, currentDate, status);
            }
            url = MyAppConstants.StaffFeatures.VIEW_DETAIL_ORDER_CONTROLLER + "?OrderID=" + orderId;
        } catch (Exception e) {
            log(e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}