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
import TPFinal.modelo.Investigacion;




public class ChequeoDAO {
	String u = "root";
	String p = "123456";
	String url = "jdbc:mysql://localhost:3306/chekeado?useSSL=false";
	
	
	
	public ArrayList<Chequeo> obtenerTodos(){

		ArrayList<Chequeo> lista = new ArrayList<Chequeo>();
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT chequeo_id, chequeo_categoria, chequeo_palabraclave, chequeo_frase, chequeo_medio_origen, chequeo_enlace, chequeo_fecha, chequeo_verificacion FROM chequeos";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Chequeo ch = new Chequeo();
				ch.setId(rs.getInt(1));
				ch.setCategoria(rs.getInt(2));
				ch.setPalabra_clave(rs.getString(3));
				ch.setFrase(rs.getString(4));
				ch.setMedio_origen(rs.getString(5));
				ch.setEnlace(rs.getString(6));
				ch.setFecha(rs.getDate(7).toLocalDate());
				ch.setVerificacion(rs.getBoolean(8));
				
				lista.add(ch);
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
	
	public void eliminarConPalabraClaveFrase(String palabraclave, String frase) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			String sql = "DELETE FROM chequeos WHERE chequeo_palabraclave=? AND chequeo_frase = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, palabraclave);
			pStmt.setString(2, frase);
			
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
	
	public void updateConEnlaceFrase(Chequeo chequeo, String enlace, String frase) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "UPDATE chequeos SET chequeo_categoria=?, chequeo_palabraclave=?, chequeo_verificacion=? WHERE chequeo_enlace = ? AND chequeo_frase = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setInt(1, chequeo.getCategoria());
			pStmt.setString(2, chequeo.getPalabra_clave());
			pStmt.setBoolean(3, chequeo.isVerificacion());
			pStmt.setString(4, enlace);
			pStmt.setString(5, frase);
			
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
	
	public Chequeo obtenerConPalabraClaveFrase (String palabraClave, String frase) {
		Connection conn = null;
		Chequeo chequeo = new Chequeo();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT chequeo_id, chequeo_categoria, chequeo_palabraclave, chequeo_frase, chequeo_medio_origen, chequeo_enlace, chequeo_fecha, chequeo_verificacion FROM chequeos WHERE chequeo_palabraclave = ? AND chequeo_frase=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, palabraClave);
			pstmt.setString(2, frase);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				chequeo.setId(rs.getInt(1));
				chequeo.setCategoria(rs.getInt(2));
				chequeo.setPalabra_clave(rs.getString(3));
				chequeo.setFrase(rs.getString(4));
				chequeo.setMedio_origen(rs.getString(5));
				chequeo.setEnlace(rs.getString(6));
				chequeo.setFecha(rs.getDate(7).toLocalDate());
				chequeo.setVerificacion(rs.getBoolean(8));
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
		return chequeo;
	}
	
	public ArrayList<Chequeo> obtenerPalabraclave(String palabraclave){
		palabraclave.toUpperCase();
		ArrayList<Chequeo> lista = new ArrayList<Chequeo>();
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT chequeo_categoria, chequeo_palabraclave, chequeo_frase, chequeo_medio_origen, chequeo_enlace, chequeo_fecha, chequeo_verificacion FROM chequeos WHERE UPPER(chequeo_palabraclave) LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + palabraclave +"%");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Chequeo ch = new Chequeo();
				ch.setCategoria(rs.getInt(1));
				ch.setPalabra_clave(rs.getString(2));
				ch.setFrase(rs.getString(3));
				ch.setMedio_origen(rs.getString(4));
				ch.setEnlace(rs.getString(5));
				ch.setFecha(rs.getDate(6).toLocalDate());
				ch.setVerificacion(rs.getBoolean(7));
				
				lista.add(ch);
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
	
	public void insert(Chequeo nuevo_chequeo) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,u,p);
			String sql = "INSERT INTO chequeos (chequeo_categoria, chequeo_palabraclave, chequeo_frase, chequeo_medio_origen, chequeo_enlace, chequeo_fecha, chequeo_verificacion) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, nuevo_chequeo.getCategoria());
			pStmt.setString(2, nuevo_chequeo.getPalabra_clave());
			pStmt.setString(3, nuevo_chequeo.getFrase());
			pStmt.setString(4, nuevo_chequeo.getMedio_origen());
			pStmt.setString(5, nuevo_chequeo.getEnlace());
			pStmt.setDate(6, java.sql.Date.valueOf(nuevo_chequeo.getFecha()));
			pStmt.setBoolean(7, nuevo_chequeo.isVerificacion());
			
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
	
	public ArrayList<Chequeo> select_categoria (int categoria) {
		Connection conn = null;
		ArrayList<Chequeo> lista = new ArrayList<Chequeo>();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT chequeo_id, chequeo_categoria, chequeo_palabraclave, chequeo_frase, chequeo_medio_origen, chequeo_enlace, chequeo_fecha, chequeo_verificacion FROM chequeos WHERE chequeo_categoria = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoria);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				Chequeo chequeo = new Chequeo();
				chequeo.setId(rs.getInt(1));
				chequeo.setCategoria(rs.getInt(2));
				chequeo.setPalabra_clave(rs.getString(3));
				chequeo.setFrase(rs.getString(4));
				chequeo.setMedio_origen(rs.getString(5));
				chequeo.setEnlace(rs.getString(6));
				chequeo.setFecha(rs.getDate(7).toLocalDate());
				chequeo.setVerificacion(rs.getBoolean(8));
				
				lista.add(chequeo);
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
		return lista;
	}
	
	
	
	public ArrayList<Chequeo> ibt(String palabraclave){
		palabraclave.toUpperCase();
		ArrayList<Chequeo> lista = new ArrayList<Chequeo>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT chequeo_categoria, chequeo_palabraclave, chequeo_frase, chequeo_medio_origen, chequeo_enlace, chequeo_fecha, chequeo_verificacion FROM chequeos WHERE UPPER(chequeo_palabraclave) LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + palabraclave +"%");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Chequeo ch = new Chequeo();
				ch.setCategoria(rs.getInt(1));
				ch.setPalabra_clave(rs.getString(2));
				ch.setFrase(rs.getString(3));
				ch.setMedio_origen(rs.getString(4));
				ch.setEnlace(rs.getString(5));
				ch.setFecha(rs.getDate(6).toLocalDate());
				ch.setVerificacion(rs.getBoolean(7));
				
				lista.add(ch);
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
	
	public ArrayList<Chequeo> filtrarChequeoXExplicacion(Explicacion exp){

		ArrayList<Chequeo> lista = new ArrayList<Chequeo>();
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT * FROM chequeos JOIN explicaciones_chequeos ON explicaciones_chequeos.chequeo_id = chequeos.chequeo_id WHERE explicaciones_chequeos.explicacion_id=?";
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, exp.getId());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Chequeo ch = new Chequeo();
				ch.setId(rs.getInt(1));
				ch.setCategoria(rs.getInt(2));
				ch.setPalabra_clave(rs.getString(3));
				ch.setFrase(rs.getString(4));
				ch.setMedio_origen(rs.getString(5));
				ch.setEnlace(rs.getString(6));
				ch.setFecha(rs.getDate(7).toLocalDate());
				ch.setVerificacion(rs.getBoolean(8));
				
				lista.add(ch);
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
	
}
