/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Planes;
import dao.PlanesDao;
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
public class PlanesController extends HttpServlet {


    
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
            case "verPlanes":
                this.verTodosLosPlanes(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    protected void verTodosLosPlanes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Planes> todas = new LinkedList<>();
        PlanesDao plan = new PlanesDao();
        todas = plan.obtenerTodosLosPlanes();
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/planes.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreParametro = request.getParameter("nombre");
        String precioParametro = request.getParameter("precio");
        int precio = Integer.parseInt(precioParametro);

        Planes plan1 = new Planes(0);
        plan1.setNom(nombreParametro);
        plan1.setP(precio);

        PlanesDao pla = new PlanesDao();
        boolean resultado = pla.insertar(plan1);

        String mensaje = "";
        if (resultado) {
            mensaje = "La vacante fue guardada correctamente.";
            System.out.println(mensaje);
        } else {
            mensaje = "Ocurrio un error, El Plan no fue grabado.";
            System.out.println(mensaje);
        }
        verTodosLosPlanes(request, response);
    }


}
