/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Public;

import Daos.CustomerDAO;
import Models.AccountDTO;
import Models.CustomerDTO;
import Utils.MyAppConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "HandlePaymentServlet", urlPatterns = {"/HandlePaymentServlet"})
public class HandlePaymentServlet extends HttpServlet {

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
        String url = MyAppConstants.PublicFeatures.PAYMENT_PAGE;
        String button = request.getParameter("btAction");
        HttpSession session = request.getSession();

        try {
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
            String serviceID = (String) session.getAttribute("SERVICE_ID");

            CustomerDAO dao = new CustomerDAO();
            CustomerDTO customer = null;
            customer = dao.getCustomerByAccountID(account.getAccountID());

            if (customer.getAddress() == null && customer.getCity() == null && customer.getPhone_Number() == null) {
                request.setAttribute("FULLNAME", customer.getFullName());
                url = MyAppConstants.CustomerFeatures.RECEIVING_INFO_PAGE;
            } else if (serviceID.equals("1")) {
                url = MyAppConstants.PublicFeatures.PAYMENT_PAGE;
                session.setAttribute("CUSTOMER", customer);

            } else if (serviceID.equals("2")) {
                url = MyAppConstants.PublicFeatures.BIRD_NEST_AVAILABLE_SERVICE_CONTROLLER;
                session.setAttribute("CUSTOMER", customer);
            } else if (serviceID.equals("3") ) {
                url = MyAppConstants.PublicFeatures.MATCH_BIRD_AVAILABLE_SERVICE_CONTROLLER;
                session.setAttribute("CUSTOMER", customer);
            } else if (serviceID.equals("4") ) {
                url = MyAppConstants.PublicFeatures.MATCH_BIRD_CUSTOMER_SERVICE_CONTROLLER;
                session.setAttribute("CUSTOMER", customer);
            }
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