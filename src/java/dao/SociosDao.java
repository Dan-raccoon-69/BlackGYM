/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Socio;
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
public class SociosDao {
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
    
    public List<Socio> obtenerTodosLosSocios() {
        try {
            Connection conn = getConnection();
            String sql = "select * from socios order by fol asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Socio> list = new LinkedList<>();
            Socio socio;
            while (rs.next()) {
                socio = new Socio(rs.getInt("fol"));
                socio.setNom(rs.getString("Nom"));
                socio.setEda(rs.getString("Eda"));
                socio.setTel(rs.getString("Tel"));
                socio.setCorElec(rs.getString("CorElec"));
                socio.setCal(rs.getString("Cal"));
                socio.setNum(rs.getInt("Num"));
                socio.setCol(rs.getString("Col"));
                socio.setCp(rs.getString("Cp"));
                socio.setEnt(rs.getString("Ent"));
                socio.setEst(rs.getString("Est"));
                socio.setNumPlan(rs.getInt("NumPlan"));
                socio.setInp(rs.getDate("Inp"));
                socio.setFip(rs.getDate("FiP"));
                
                list.add(socio);
            }
            System.out.println("SOCIOS DAO");
            for (Socio socio1 : list) {
                System.out.println(socio1.toString() + " ");
            }
            return list;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<Socio> buscarSocio(String socioAbuscar) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM socios WHERE Nom LIKE ? ORDER BY fol ASC";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+socioAbuscar+"%");
            rs = ps.executeQuery();
            List<Socio> socioEncontrado = new LinkedList<>();
            Socio socio;
            while (rs.next()) {     
                socio = new Socio(rs.getInt("fol"));
                socio.setNom(rs.getString("Nom"));
                socio.setEda(rs.getString("Eda"));
                socio.setTel(rs.getString("Tel"));
                socio.setCorElec(rs.getString("CorElec"));
                socio.setCal(rs.getString("Cal"));
                socio.setNum(rs.getInt("Num"));
                socio.setCol(rs.getString("Col"));
                socio.setCp(rs.getString("Cp"));
                socio.setEnt(rs.getString("Ent"));
                socio.setEst(rs.getString("Est"));
                socio.setNumPlan(rs.getInt("NumPlan"));
                socio.setInp(rs.getDate("Inp"));
                socio.setFip(rs.getDate("FiP"));
                socioEncontrado.add(socio);
            }
            return socioEncontrado;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
}
