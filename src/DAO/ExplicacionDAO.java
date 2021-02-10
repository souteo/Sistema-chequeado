package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TPFinal.modelo.Chequeo;
import TPFinal.modelo.Explicacion;



public class ExplicacionDAO {
	String u = "root";
	String p = "123456";
	String url = "jdbc:mysql://localhost:3306/chekeado?useSSL=false";
	
	
	
	public ArrayList<Explicacion> obtenerTodos(){

		ArrayList<Explicacion> lista = new ArrayList<Explicacion>();
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT explicacion_id, explicacion_nombre, explicacion_epigrafe, explicacion_contenido, explicacion_fecha_creacion, explicacion_enlace FROM explicaciones";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Explicacion ex = new Explicacion();
				ex.setId(rs.getInt(1));
				ex.setTitulo(rs.getString(2));
				ex.setEpigrafe(rs.getString(3));
				ex.setContenido(rs.getString(4));
				ex.setFecha(rs.getDate(5).toLocalDate());
				ex.setEnlace(rs.getString(6));
				
				lista.add(ex);
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
	
	
	
	public void eliminarConId(int id) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			String sql = "DELETE FROM explicaciones WHERE explicacion_id=?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			int filasAfectadas = pStmt.executeUpdate();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateConId(Explicacion ex, int id) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "UPDATE explicaciones SET explicacion_nombre=?, explicacion_epigrafe=?, explicacion_contenido=? WHERE explicacion_id = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, ex.getTitulo());
			pStmt.setString(2, ex.getEpigrafe());
			pStmt.setString(3, ex.getContenido());
			pStmt.setInt(4, id);
			
			int filasAfectadas = pStmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();			
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public Explicacion obtenerConTitulo (String titulo) {
		Connection conn = null;
		Explicacion ex = new Explicacion();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT explicacion_id, explicacion_nombre, explicacion_epigrafe, explicacion_contenido FROM explicaciones WHERE explicacion_nombre = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, titulo);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ex.setId(rs.getInt(1));
				ex.setTitulo(rs.getString(2));
				ex.setEpigrafe(rs.getString(3));
				ex.setContenido(rs.getString(4));

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
		return ex;
	}
	
	public Explicacion obtenerConId (int id) {
		Connection conn = null;
		Explicacion ex = new Explicacion();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT explicacion_id, explicacion_nombre, explicacion_epigrafe, explicacion_contenido, explicacion_fecha_creacion FROM explicaciones WHERE explicacion_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ex.setId(rs.getInt(1));
				ex.setTitulo(rs.getString(2));
				ex.setEpigrafe(rs.getString(3));
				ex.setContenido(rs.getString(4));
				ex.setCategoria(rs.getInt(5));

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
		return ex;
	}
	
	public ArrayList<Explicacion> obtenerConNombre(String nombre){
		nombre.toUpperCase();
		ArrayList<Explicacion> lista = new ArrayList<Explicacion>();
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT explicacion_id, explicacion_nombre, explicacion_epigrafe, explicacion_contenido, explicacion_fecha_creacion FROM explicaciones WHERE UPPER(explicacion_nombre) LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + nombre +"%");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Explicacion ex = new Explicacion();
				ex.setId(rs.getInt(1));
				ex.setTitulo(rs.getString(2));
				ex.setEpigrafe(rs.getString(3));
				ex.setContenido(rs.getString(4));
				ex.setFecha(rs.getDate(5).toLocalDate());
				
				lista.add(ex);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	
	public void insert(Explicacion exp) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,u,p);
			String sql = "INSERT INTO explicaciones (explicacion_nombre, explicacion_epigrafe, explicacion_contenido, explicacion_fecha_creacion, explicacion_enlace) VALUES (?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exp.getTitulo());
			pstmt.setString(2, exp.getEpigrafe());
			pstmt.setString(3, exp.getContenido());
			pstmt.setDate(4, java.sql.Date.valueOf(exp.getFecha()));
			pstmt.setString(5, exp.getEnlace());
			
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void guardarExCheq(Explicacion exp, Chequeo c) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,u,p);
			String sql = "INSERT INTO explicaciones_chequeos (explicacion_id, chequeo_id) VALUES (?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, exp.getId());
			pstmt.setInt(2, c.getId());
			
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

