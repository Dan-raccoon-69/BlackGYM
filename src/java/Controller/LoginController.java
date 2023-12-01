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

        // Verificar si ya hay una sesión activa
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("u1") != null) {
            // Si hay una sesión activa, puede realizar otras acciones aquí si es necesario
            String action = request.getParameter("action");

            if ("irAdministracion".equals(action)) {
                // Si el parámetro "action" es "irAdministracion", abrir admin.jsp
                RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
                rd.forward(request, response);
            } 
            /*
            else if ("logout".equals(action)){
                // Si no hay una acción específica, mostrar una página predeterminada
                RequestDispatcher rd = request.getRequestDispatcher("/default.jsp");
                rd.forward(request, response);
            }
            */
        } else {
            // Si no hay una sesión activa, mostrar index.jsp
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // recibiendo parametros
        String user = request.getParameter("usuario");
        String password = request.getParameter("contrasena");
        String mensaje = "";

        System.out.println("user: " + user);
        System.out.println("password: " + password);

        // Recuperamos una instancia del objeto HttpSession
        HttpSession session = request.getSession();
        UsuarioDao usuario = new UsuarioDao();
        // se verifica si existe un usuario con sus respectivos datos
        Usuario u1 = usuario.login(user, password);
        RequestDispatcher rd;
        //RequestDispatcher rd;
        if (u1.getId() > 0) {
            // creamos una variable de session, con el registro de usuario (Bean)
            session.setAttribute("u1", u1);
            rd = request.getRequestDispatcher("/homePage.jsp");
            rd.forward(request, response);
        } else {
            mensaje = "Usuario y/o Contraseña incorrectos";
            request.setAttribute("mensaje", mensaje);
            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }
}
