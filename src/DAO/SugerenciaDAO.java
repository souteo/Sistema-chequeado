package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import TPFinal.modelo.Chequeo;
import TPFinal.modelo.Sugerencia;

public class SugerenciaDAO {
	private String u="root";
	private String p="123456";
	private String url="jdbc:mysql://localhost:3306/chekeado?useSSL=false";
	
 public ArrayList<Sugerencia> obtenerTodos(){
	 ArrayList<Sugerencia> lista = new ArrayList<Sugerencia>();
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String select = "SELECT frase, sugerencia_autor_frase, sugerencia_medio_frase, sugerencia_enlace, sugerencia_fecha_frase FROM sugerencia";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(select);
			
			while(rs.next()) {
				Sugerencia su = new Sugerencia();
				su.setFrase(rs.getString(1));
				su.setAutor_frase(rs.getString(2));
				su.setMedio_frase(rs.getString(3));
				su.setEnlace(rs.getString(4));
				su.setFecha_frase(rs.getDate(5).toLocalDate());
				
				lista.add(su);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null) {
					conexion.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
 
 	public void eliminarconFraseAutor(String frase, String autor) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, u, p);
			String delete = "DELETE FROM sugerencia WHERE frase = ? AND sugerencia_autor_frase = ?";
			PreparedStatement pStmt = con.prepareStatement(delete);
			pStmt.setString(1, frase);
			pStmt.setString(2, autor);
			
			int filasAfectadas = pStmt.executeUpdate();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null)
					con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 	
 	public Sugerencia obtenerConFraseAutor (String frase, String autor) {
		Connection conn = null;
		Sugerencia sug = new Sugerencia();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT frase, sugerencia_autor_frase, sugerencia_medio_frase, sugerencia_enlace, sugerencia_fecha_frase FROM sugerencia WHERE frase = ? AND sugerencia_autor_frase =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, frase);
			pstmt.setString(2, autor);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sug.setFrase(rs.getString(1));
				sug.setAutor_frase(rs.getString(2));
				sug.setMedio_frase(rs.getString(3));
				sug.setEnlace(rs.getString(4));
				sug.setFecha_frase(rs.getDate(5).toLocalDate());
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sug;
	}
 	
 	public void eliminarconFraseFecha(String frase, LocalDate fecha) {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, u, p);
			String delete = "DELETE FROM sugerencia WHERE frase = ? AND sugerencia_fecha_frase = ?";
			PreparedStatement pStmt = con.prepareStatement(delete);
			pStmt.setString(1, frase);
			pStmt.setDate(2, java.sql.Date.valueOf(fecha));
			
			int filasAfectadas = pStmt.executeUpdate();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null)
					con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 	
}
