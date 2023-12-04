package Controller;

import Modelo.Socio;
import dao.SociosDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
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
public class SociosController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        
        switch (action) {
            case "inicio":
                rd = request.getRequestDispatcher("/homePage.jsp");
                rd.forward(request, response);
                break;
            case "verSocios":
                this.verTodosLosSocios(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
    }
    
    protected void verTodosLosSocios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Socio> todas = new LinkedList<>();
        SociosDao socio = new SociosDao();
        todas = socio.obtenerTodosLosSocios();
        System.out.println("SOCIOS DESDE CONTROLLER");
        for (Socio toda : todas) {
            System.out.println(toda.toString());
        }
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/VerSocios.jsp");
        rd.forward(request, response);
    }
}
