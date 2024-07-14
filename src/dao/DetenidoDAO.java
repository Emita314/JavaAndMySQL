package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.CCDTyE;
import modelo.Identificado;
import modelo.Testigo;

public class DetenidoDAO {
	public boolean guardar(Identificado identificado) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement
					("INSERT INTO `detenido_identificado` (`Det_Nombre`, `Det_Apellido`,`DNIdetenido`, `LugarSecuestro`, `ultfechavisto`, `Biografia`,`MaterialAudiovisual`,`DNItestigo` ) VALUES (?,?,?,?,?,?,?,?)");
			pStmt.setString(1, identificado.getDet_Nombre());
			pStmt.setString(2, identificado.getDet_Apellido());
			pStmt.setInt(3, identificado.getDNIdetenido());
			pStmt.setInt(4, identificado.getLugarSecuestro());
			Date fuv = Date.valueOf(identificado.getUltFechaVisto());
			pStmt.setDate(5, fuv);
			pStmt.setString(6, identificado.getBiografia());
			pStmt.setString(7, identificado.getMaterialAudivisual());
			pStmt.setInt(8, identificado.getDniTes());
			filasAfectas = pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filasAfectas == 1;
	}
	
	public boolean actualizar(Identificado identificado, int DNIdetenido ) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn
		.prepareStatement("UPDATE detenido_identificado SET `Det_Nombre` = ?,`Det_Apellido` = ?,`DNIdetenido`=?,`LugarSecuestro`=?,`ultfechavisto`=?, `Biografia`=?,`MaterialAudiovisual`=?,`DNItestigo`  = ? WHERE `DNIdetenido` = ?;");
			pStmt.setString(1, identificado.getDet_Nombre());
			pStmt.setString(2, identificado.getDet_Apellido());
			pStmt.setInt(3, identificado.getDNIdetenido());
			pStmt.setInt(4, identificado.getLugarSecuestro()); 
			Date fuv = Date.valueOf(identificado.getUltFechaVisto()); 
			pStmt.setDate(5, fuv);
			pStmt.setString(6, identificado.getBiografia());
			pStmt.setString(7, identificado.getMaterialAudivisual());
			pStmt.setInt(8, identificado.getDniTes());

			pStmt.setInt(9,DNIdetenido );
			filasAfectas = pStmt.executeUpdate(); 

		} catch (SQLException e) { 
			e.printStackTrace(); 
		} finally { 
			if (conn != null) { 
				try { 
					conn.close(); 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			} 
		} 
		return filasAfectas == 1; 
	} 
	
	public boolean eliminar(int DNIdetenido) { 
		String url ="jdbc:mysql://localhost:3306/olimpo3";   
		String usuario  = "root";
		String contrasenia = "marifabi26"; 
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement  pStmt = conn.prepareStatement("DELETE FROM `detenido_identificado` WHERE DNIdetenido = ?;");
			pStmt.setInt (1, DNIdetenido);

			filasAfectas  = pStmt.executeUpdate();

		} catch  (SQLException e) {
			 e.printStackTrace();
		} finally  {
			if (conn  != null) {
				try  {
					conn.close();
				} catch (SQLException e) {
					 e.printStackTrace();
				} 
			} 
		} 
		return filasAfectas == 1; 
	} 

	
	public ArrayList<Identificado>  traerTodos() {
		ArrayList<Identificado>  detenidos = new ArrayList<Identificado>();
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT Det_Nombre, Det_Apellido,DNIdetenido,LugarSecuestro, ultfechavisto, Biografia, MaterialAudiovisual, DNItestigo FROM detenido_identificado");

			while (rs.next()) {
				String Det_Nombre = rs.getString(1);
				String Det_Apellido = rs.getString(2);
				int DNIdetenido = rs.getInt(3);
				int LugarSecuestro = rs.getInt(4);
				LocalDate ultfechavisto = rs.getDate(5).toLocalDate();
				String Biografia = rs.getString(6);
				String Audiovisual = rs.getString(7);
				int DNItes = rs.getInt(8);
				Identificado e = new Identificado(Det_Nombre, Det_Apellido,DNIdetenido,LugarSecuestro,ultfechavisto,Biografia,Audiovisual,DNItes);
				detenidos.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return detenidos;
	}
	public Identificado traersolouno(int DNIdete) {
		Identificado detenido = null;
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			//ResultSet rs = stmt.executeQuery("SELECT CCDTyENombre, Ubicacion,FechaApertura,FechaCierre FROM ccdtye");

			PreparedStatement pStmt = conn.prepareStatement(
					"SELECT Det_Nombre, Det_Apellido,DNIdetenido,LugarSecuestro, ultfechavisto, Biografia, MaterialAudiovisual, DNItestigo FROM detenido_identificado WHERE DNIdetenido = ?;");
			pStmt.setInt(1, DNIdete);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String Det_Nombre = rs.getString(1);
				String Det_Apellido = rs.getString(2);
				int DNIdetenido = rs.getInt(3);
				int LugarSecuestro = rs.getInt(4);
				LocalDate ultfechavisto = rs.getDate(5).toLocalDate();
				String Biografia = rs.getString(6);
				String Audiovisual = rs.getString(7);
				int DNItes = rs.getInt(8);
				
				detenido = new Identificado(Det_Nombre, Det_Apellido,DNIdetenido,LugarSecuestro,ultfechavisto,Biografia,Audiovisual,DNItes);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return detenido;
	}
}
