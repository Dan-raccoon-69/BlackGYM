/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Planes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class PlanesDao {
     public static final String url = "jdbc:mysql://localhost:3306/gym";
    public static final String usuario = "root";
    public static final String contraseña = "616263646566676869";

    PreparedStatement ps;
    ResultSet rs;

    // Método para obtener la conexión
    private Connection getConnection() {
        try {
            // Cargar el controlador JDBC (Driver)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            return conexion;
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar la excepción (imprimir o lanzar una nueva)
            e.printStackTrace();
            return null;
        }
    }

    public List<Planes> obtenerTodosLosPlanes() {
        try {
            Connection conn = getConnection();
            String sql = "select * from Planes order by NumPlan asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Planes> list = new LinkedList<>();
            Planes plan;
            while (rs.next()) {
                plan = new Planes(rs.getInt("NumPlan"));
                plan.setNom(rs.getString("Nom"));
                plan.setP(rs.getInt("P"));
                list.add(plan);
            }
            System.out.println("DAO");
            for (Planes planes : list) {
                System.out.println(planes.toString() + " ");
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<Planes> buscarPlan(String planAbuscar) {
        try {
            Connection conn = getConnection();
            String sql = "select * from Planes where Nom like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+planAbuscar+"%");
            rs = ps.executeQuery();
            List<Planes> planEncontrado = new LinkedList<>();
            Planes plan;
            while (rs.next()) {     
                plan = new Planes(rs.getInt("NumPlan"));
                plan.setNom(rs.getString("Nom"));
                plan.setP(rs.getInt("P"));
                planEncontrado.add(plan);
            }
            return planEncontrado;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
