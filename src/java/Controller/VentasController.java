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
import java.time.LocalDate;
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
        String action2 = request.getParameter("action");
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
            case "fechas":
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
        } else if ("Ver".equals(action)) {
            RequestDispatcher rd;
            String fechaIni = request.getParameter("fechaIni");
            String fechaFin = request.getParameter("fechaFin");

            LocalDate fechaI = LocalDate.parse(fechaIni);
            LocalDate fechaF = LocalDate.parse(fechaFin);

            List<Ventas> ventasTotales = new LinkedList<>();
            List<Ventas> ventasFiltradas = new LinkedList<>();
            VentasDao venta = new VentasDao();
            ventasTotales = venta.obtenerTodasLasVentas();
            Double costosEfe = 0.0;
            Double costosTar = 0.0;
            Double costosTot = 0.0;
            for (Ventas ventas : ventasTotales) {
                // Asegúrate de que toda.getFecV() devuelve un java.sql.Date
                java.sql.Date fechaVenta = (java.sql.Date) ventas.getFecV();
                // Convertir la fecha de la venta a LocalDate
                LocalDate fechaVentaLocalDate = fechaVenta.toLocalDate();

                if (fechaVentaLocalDate.isAfter(fechaI) && fechaVentaLocalDate.isBefore(fechaF)) {
                    ventasFiltradas.add(ventas);
                    if (ventas.getForP().equals("Efectivo")) {
                        costosEfe += ventas.getCosV();
                    } else if (ventas.getForP().equals("Tarjeta")) {
                        costosTar += ventas.getCosV();
                    }
                }
            }

            costosTot = costosEfe + costosTar;
            
            // compartimos la variable ultimas, para poder acceder la vista con Expression Language
            request.setAttribute("todas", ventasFiltradas);
            request.setAttribute("costosEfe", costosEfe);
            request.setAttribute("costosTar", costosTar);
            request.setAttribute("costosTot", costosTot);
            request.setAttribute("fechaI", fechaI);
            request.setAttribute("fechaF", fechaF);
            // enviamos respuesta, se renderiza a la vista "index.jsp"
            rd = request.getRequestDispatcher("/reportes.jsp");
            rd.forward(request, response);
        }

    }

    protected void verTodasLasVentas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Ventas> todas = new LinkedList<>();
        VentasDao venta = new VentasDao();
        todas = venta.obtenerTodasLasVentas();
        Double costosEfe = 0.0;
        Double costosTar = 0.0;
        Double costosTot = 0.0;
        for (Ventas toda : todas) {
            if (toda.getForP().equals("Efectivo")) {
                costosEfe += toda.getCosV();
            } else if (toda.getForP().equals("Tarjeta")) {
                costosTar += toda.getCosV();
            }
        }
        costosTot = costosEfe + costosTar;
        RequestDispatcher rd;
        // compartimos la variable ultimas, para poder acceder la vista con Expression Language
        request.setAttribute("todas", todas);
        request.setAttribute("costosEfe", costosEfe);
        request.setAttribute("costosTar", costosTar);
        request.setAttribute("costosTot", costosTot);
        // enviamos respuesta, se renderiza a la vista "index.jsp"
        rd = request.getRequestDispatcher("/reportes.jsp");
        rd.forward(request, response);
    }

}
