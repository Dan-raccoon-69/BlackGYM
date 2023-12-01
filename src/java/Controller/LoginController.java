/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Usuario;
import dao.UsuarioDao;
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
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // recibiendo parametros

        // recibiendo parametros
        String user = request.getParameter("usuario");
        String password = request.getParameter("contrasena");
        
        System.out.println("user: " + user);
        System.out.println("password: " + password);

        // Recuperamos una instancia del objeto HttpSession
        HttpSession session = request.getSession();
        UsuarioDao usuario = new UsuarioDao();
        // se verifica si existe un usuario con sus respectivos datos
        Usuario u1 = usuario.login(user, password);

        RequestDispatcher rd;
        if (u1.getId() > 0) {
            // creamos una variable de session, con el registro de usuario (Bean)
            session.setAttribute("u1", u1);
            rd = request.getRequestDispatcher("/homePage.jsp");
            rd.forward(request, response);
        } else {
            System.out.println("Contrasenia o usuario incorecto");
        }
    }
}
