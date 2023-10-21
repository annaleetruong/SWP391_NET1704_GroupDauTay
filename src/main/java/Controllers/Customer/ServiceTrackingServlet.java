/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Customer;

import Daos.BirdNestDetail_TrackingDAO;
import Daos.Bird_Nest_TrackingDAO;
import Daos.CustomerDAO;
import Models.AccountDTO;
import Models.BirdNestDetail_TrackingDTO;
import Models.Bird_Nest_TrackingDTO;
import Utils.MyAppConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hj
 */
@WebServlet(name = "Service_Tracking", urlPatterns = {"/Service_Tracking"})
public class ServiceTrackingServlet extends HttpServlet {

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
        String url = MyAppConstants.CustomerFeatures.SERVICE_TRACKING_PAGE;
        String orderID = request.getParameter("txtOrderID");
        String page = request.getParameter("page");
        HttpSession session = request.getSession();
        try {
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
            if (account == null) {
                url = MyAppConstants.PublicFeatures.HOME_CONTROLLER;
                response.sendRedirect(url);
                return;
            }
            if (orderID != null) {
                request.setAttribute("ORDER_ID", orderID);
            }
            Bird_Nest_TrackingDAO bntdao = new Bird_Nest_TrackingDAO();
         
            Bird_Nest_TrackingDTO bntdto = bntdao.getBNTrackingByOrderID(orderID);
            session.setAttribute("BIRD_NEST_TRACKING", bntdto);
            if (session.getAttribute("CUSTOMER") == null) {
                CustomerDAO cusdao = new CustomerDAO();
                session.setAttribute("CUSTOMER", cusdao.getCustomerByAccountID(account.getAccountID()));
            }

            if (page == null) {
                page = "1";
            }
            int indexPage = Integer.parseInt(page);

            BirdNestDetail_TrackingDAO bndetaildao = new BirdNestDetail_TrackingDAO();
            int endPage = bndetaildao.getNumberPage(bntdto.getBird_Nest_ID());
            List<BirdNestDetail_TrackingDTO> result = bndetaildao.getPagingByUpdateDateDesc(indexPage, bntdto.getBird_Nest_ID());
            session.setAttribute("BN_DETAIL_TRACKING_LIST", result);
            int start = 1;
            int distance = 3;

            int end;
            if (endPage < distance) {
                end = endPage;
            } else {
                end = start + distance;
            }
            
            if (indexPage >= 3) {
                start = indexPage - 2;
                end = indexPage + 2;
                if (indexPage + distance >= endPage) {
                    start = endPage - distance;
                    end = endPage;
                }
            }
            request.setAttribute("BEGIN", start);
            request.setAttribute("FINISH", end);
            request.setAttribute("pageCurrent", indexPage);
            request.setAttribute("endPage", endPage);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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