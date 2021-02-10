package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TPFinal.modelo.Categoria;
import TPFinal.modelo.Investigacion;

public class CategoriaDAO {
	String u = "root";
	String p = "123456";
	String url = "jdbc:mysql://localhost:3306/chekeado?useSSL=false";
	
	public Categoria obtenerConId(int id) {
		Connection conn = null;
		Categoria cat = new Categoria();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT categoria_id, categoria_nombre FROM categorias WHERE categoria_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cat.setId(rs.getInt(1));
				cat.setNombre(rs.getString(2));
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
		return cat;
	}
	
	public ArrayList<Categoria> obtenerTodos(){
		
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT categoria_id, categoria_nombre FROM categorias";
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Categoria cat = new Categoria();
				cat.setId(rs.getInt(1));
				cat.setNombre(rs.getString(2));
				lista.add(cat);
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
	
	public Categoria obtenerConNombre(String categoria) {
		Connection conn = null;
		Categoria cat = new Categoria();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT categoria_id, categoria_nombre FROM categorias WHERE categoria_nombre = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoria);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cat.setId(rs.getInt(1));
				cat.setNombre(rs.getString(2));
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
		return cat;
	}
}
