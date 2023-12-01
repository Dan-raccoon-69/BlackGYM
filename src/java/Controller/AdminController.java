/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        // se recupera la session activa que viene junto con el request
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String mensaje = "";

        switch (action) {
            case "inicio":
                // significa que si hay un usuario y simplmente se redirige a admin.jsp
                rd = request.getRequestDispatcher("/homePage.jsp");
                rd.forward(request, response);
                break;
            case "logout":
                // Salir, cerramos la session
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;
            default:
                throw new AssertionError();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
