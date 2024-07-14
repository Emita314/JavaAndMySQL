package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.CCDTyE;
import modelo.Fuerza;

public class FuerzaDAO {
	
	public ArrayList<Fuerza> traerTodos() {
		ArrayList<Fuerza> Fuerzas = new ArrayList<Fuerza>();
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT Fuerza_Nombre FROM fuerza");

			while (rs.next()) {
				String FNombre = rs.getString(1);
				Fuerza e = new Fuerza(FNombre);
				Fuerzas.add(e);
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
		return Fuerzas;
	}
	
	public ArrayList<Fuerza> traerVinculados() {
		ArrayList<Fuerza> FuerzasV = new ArrayList<Fuerza>();
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT FuerzaNombre,CCDTyENombre FROM operaba");

			while (rs.next()) {
				String FNombre = rs.getString(1);
				String CNombre = rs.getString(2);
				Fuerza e = new Fuerza(FNombre,CNombre);
				FuerzasV.add(e);
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
		return FuerzasV;
	}
	public boolean guardar(Fuerza vinculacion) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement(
			"INSERT INTO `operaba` (`CCDTyENombre`, `FuerzaNombre`) VALUES (?,?)");
			pStmt.setString(1, vinculacion.getFuerza_Npmbre());
			pStmt.setString(2, vinculacion.getNcentro());
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
	public boolean eliminarFV(String CCDTyENombre) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM `operaba` WHERE CCDTyENombre = ?;");
			pStmt.setString(1, CCDTyENombre);

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
	public boolean actualizar(Fuerza FVActu, String CCDTyENombreOriginal) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement(
					"UPDATE operaba SET `FuerzaNombre` = ? WHERE `CCDTyENombre` = ?;");
			pStmt.setString(1, FVActu.getFuerza_Npmbre());
			pStmt.setString(2, CCDTyENombreOriginal);

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


}
