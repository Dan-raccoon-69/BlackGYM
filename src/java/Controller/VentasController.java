/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Modelo.Ventas;
import dao.VentasDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VentasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String mensaje = "";
        switch (action) {
            case "agregarVentas":
                // Redirigir a la página de agregar
                rd = request.getRequestDispatcher("/ventas.jsp");
                rd.forward(request, response);
                break;
            case "verReporte":
                verTodasLasVentas(request, response);
                // Redirigir a la página de agregar
                rd = request.getRequestDispatcher("/reportes.jsp");
                rd.forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insertar".equals(action)) {

            String cantidadProductosParametro = request.getParameter("CanP");
            String descripcionVentaParametro = request.getParameter("DesV");
            String costoVentaParametro = request.getParameter("CosV");
            String fechaVentaParametro = request.getParameter("FecV");
            //String horaVentaParametro = request.getParameter("Hor");
            String formaPagoParametro = request.getParameter("ForP");

            // Realizar la conversión de tipos según sea necesario
            int cantidadProductos = Integer.parseInt(cantidadProductosParametro);
            double costoVenta = Double.parseDouble(costoVentaParametro);
            LocalTime horaActual = LocalTime.now();

            // Crear un objeto Ventas y establecer los valores
            Ventas venta = new Ventas(0);
            venta.setCanP(cantidadProductos);
            venta.setDesV(descripcionVentaParametro);
            venta.setCosV(costoVenta);
            venta.setFecV(java.sql.Date.valueOf(fechaVentaParametro));
            venta.setHor(horaActual);
            venta.setForP(formaPagoParametro);

            VentasDao ventaDao = new VentasDao();
            boolean resultado = ventaDao.insertar(venta);

            String mensaje = "";
            if (resultado) {
                mensaje = "La venta fue agregada correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, la venta no fue agregada.";
                System.out.println(mensaje);
            }
            verTodasLasVentas(request, response);
        }
        /*
        else if ("modificar".equals(action)) {

            int Fol = Integer.parseInt(request.getParameter("fol"));
            String nombreParametro = request.getParameter("Nom");
            String edadParametro = request.getParameter("Eda");
            String TelParametro = request.getParameter("Tel");
            String CorElecParametro = request.getParameter("CorElec");
            int numPlanParametro = Integer.parseInt(request.getParameter("NumPlan"));
            //int NumParametro = Integer.parseInt(request.getParameter("Num"));
            String CalParametro = request.getParameter("Cal");
            String ColParametro = request.getParameter("Col");
            String CpParametro = request.getParameter("Cp");
            String EntParametro = request.getParameter("Ent");
            String EstParametro = request.getParameter("Est");

            // Obtener los parámetros como String desde la solicitud
            String fechaString = request.getParameter("fecha");
            String fechaOutString = request.getParameter("fechaOut");

            // Convertir los String a objetos Date
            Date fecha = null;
            Date fechaOut = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                if (fechaString != null && !fechaString.isEmpty()) {
                    fecha = dateFormat.parse(fechaString);
                }

                if (fechaOutString != null && !fechaOutString.isEmpty()) {
                    fechaOut = dateFormat.parse(fechaOutString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                // Manejar la excepción de análisis aquí
            }

            Socio socioModificado = new Socio(Fol);
            socioModificado.setNom(nombreParametro);
            socioModificado.setEda(edadParametro);
            socioModificado.setTel(TelParametro);
            socioModificado.setCorElec(CorElecParametro);
            socioModificado.setNumPlan(numPlanParametro);
            socioModificado.setCol(ColParametro);
            socioModificado.setCp(CpParametro);
            socioModificado.setCal(CalParametro);
            socioModificado.setEnt(EntParametro);
            socioModificado.setEst(EstParametro);
            socioModificado.setFip(fechaOut);
            socioModificado.setInp(fecha);

            SociosDao socioDao = new SociosDao();
            boolean resultado = socioDao.actualizarSocio(socioModificado);

            String mensaje = "";
            if (resultado) {
                mensaje = "El socio fue modificado correctamente.";
                System.out.println(mensaje);
            } else {
                mensaje = "Ocurrió un error, el socio no fue modificado.";
                System.out.println(mensaje);
            }
            verTodosLosSocios(request, response);
         */
    }
    
    protected void verTodasLasVentas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ventas> todas = new LinkedList<>();
        VentasDao venta = new VentasDao();
        todas = venta.obtenerTodasLasVentas();
        System.out.println("VENTAS DESDE CONTROLLER");
        for (Ventas toda : todas) {
            System.out.println(toda.toString());
        }
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/reportes.jsp");
        rd.forward(request, response);
    }

}
