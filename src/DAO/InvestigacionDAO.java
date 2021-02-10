package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TPFinal.modelo.Explicacion;
import TPFinal.modelo.Investigacion;



public class InvestigacionDAO {
	String u = "root";
	String p = "123456";
	String url = "jdbc:mysql://localhost:3306/chekeado?useSSL=false";
	
	
	
	public ArrayList<Investigacion> obtenerTodos(){

		ArrayList<Investigacion> lista = new ArrayList<Investigacion>();
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT investigacion_id, investigacion_tema, investigacion_palabraclave, investigacion_titulo, investigacion_epigrafe, investigacion_contenido,investigacion_fecha_creacion, investigacion_categoria FROM investigaciones";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Investigacion inv= new Investigacion();
				inv.setId(rs.getInt(1));
				inv.setTema_investigacion(rs.getString(2));
				inv.setPalabra_clave(rs.getString(3));
				inv.setTitulo(rs.getString(4));
				inv.setEpigrafe(rs.getString(5));
				inv.setContenido(rs.getString(6));
				inv.setFecha(rs.getDate(7).toLocalDate());
				inv.setCategoria(rs.getInt(8));
				
				lista.add(inv);
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
	
	public void eliminarConTituloPalabraclave(String titulo, String palabraclave) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			String sql = "DELETE FROM investigaciones WHERE investigacion_titulo=? AND investigacion_palabraclave = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, titulo);
			pStmt.setString(2, palabraclave);
			
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
	
	public void updateConId(Investigacion inv, int id) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "UPDATE investigaciones SET investigacion_tema=?, investigacion_palabraclave=?, investigacion_titulo=?, investigacion_epigrafe=?, investigacion_contenido=?, investigacion_categoria=? WHERE investigacion_id = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, inv.getTema_investigacion());
			pStmt.setString(2, inv.getPalabra_clave());
			pStmt.setString(3, inv.getTitulo());
			pStmt.setString(4, inv.getEpigrafe());
			pStmt.setString(5, inv.getContenido());
			pStmt.setInt(6, inv.getCategoria());
			pStmt.setInt(7, id);
			
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
	
	
	
	public ArrayList<Investigacion> obtenerConTema(String tema){
		
		tema.toUpperCase();
		
		ArrayList<Investigacion> lista = new ArrayList<Investigacion>();
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT investigacion_id, investigacion_tema, investigacion_palabraclave, investigacion_titulo, investigacion_epigrafe, investigacion_contenido,investigacion_fecha_creacion, investigacion_categoria FROM investigaciones WHERE UPPER(investigacion_tema) LIKE ?";
			
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, "%" + tema +"%");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Investigacion inv= new Investigacion();
				inv.setId(rs.getInt(1));
				inv.setTema_investigacion(rs.getString(2));
				inv.setPalabra_clave(rs.getString(3));
				inv.setTitulo(rs.getString(4));
				inv.setEpigrafe(rs.getString(5));
				inv.setContenido(rs.getString(6));
				inv.setFecha(rs.getDate(7).toLocalDate());
				inv.setCategoria(rs.getInt(8));
				
				lista.add(inv);
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
	
	public void guardarnuevo(Investigacion inv) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,u,p);
			String sql = "INSERT INTO investigaciones (investigacion_tema, investigacion_palabraclave, investigacion_titulo, investigacion_epigrafe, investigacion_contenido, investigacion_fecha_creacion, investigacion_categoria) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, inv.getTema_investigacion());
			pStmt.setString(2, inv.getPalabra_clave());
			pStmt.setString(3, inv.getTitulo());
			pStmt.setString(4, inv.getEpigrafe());
			pStmt.setString(5, inv.getContenido());
			pStmt.setDate(6, java.sql.Date.valueOf(inv.getFecha()));
			pStmt.setInt(7, inv.getCategoria());
			
			pStmt.executeUpdate();
			
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
	
	public Investigacion obtenerConTituloPalabraClave (String titulo, String palabraclave) {
		Connection conn = null;
		Investigacion inv = new Investigacion();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT investigacion_id, investigacion_tema, investigacion_palabraclave, investigacion_titulo, investigacion_epigrafe, investigacion_contenido, investigacion_fecha_creacion, investigacion_categoria FROM investigaciones WHERE investigacion_titulo = ? AND investigacion_palabraclave = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, titulo);
			pstmt.setString(2, palabraclave);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				inv.setId(rs.getInt(1));
				inv.setTema_investigacion(rs.getString(2));
				inv.setPalabra_clave(rs.getString(3));
				inv.setTitulo(rs.getString(4));
				inv.setEpigrafe(rs.getString(5));
				inv.setContenido(rs.getString(6));
				inv.setFecha(rs.getDate(7).toLocalDate());
				inv.setCategoria(rs.getInt(8));
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
		return inv;
	}
	
}


