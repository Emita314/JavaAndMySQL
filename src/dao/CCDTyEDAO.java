package dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.sql.Date;

import modelo.CCDTyE;
import java.sql.Statement;
import java.time.LocalDate;

public class CCDTyEDAO {
	public boolean guardar(CCDTyE CCDTyE) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement(
			"INSERT INTO `ccdtye` (`CCDTyENombre`, `Ubicacion`,`FechaApertura`, `FechaCierre`) VALUES (?,?,?,?)");
			pStmt.setString(1, CCDTyE.getCCDTyENombre());
			pStmt.setInt(2, CCDTyE.getUbicacion());
			Date fa = Date.valueOf(CCDTyE.getFechaApertura());
			Date fc = Date.valueOf(CCDTyE.getFechaCierre());
			pStmt.setDate(3, fa);
			pStmt.setDate(4, fc);

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

	public boolean actualizar(CCDTyE centroActu, String CCDTyENombreOriginal) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement(
					"UPDATE ccdtye SET `Ubicacion` = ?,`FechaApertura` = ?,`FechaCierre` = ? WHERE `CCDTyENombre` = ?;");
			pStmt.setInt(1, centroActu.getUbicacion());

			Date fa = Date.valueOf(centroActu.getFechaApertura());
			Date fc = Date.valueOf(centroActu.getFechaCierre());

			pStmt.setDate(2, fa);
			pStmt.setDate(3, fc);
			pStmt.setString(4, CCDTyENombreOriginal);

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

	public boolean eliminar(String CCDTyENombre) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM `ccdtye` WHERE CCDTyENombre = ?;");
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

	public ArrayList<CCDTyE> traerTodos() {
		ArrayList<CCDTyE> centros = new ArrayList<CCDTyE>();
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery
		("SELECT ccdtye.CCDTyENombre,ccdtye.Ubicacion,ccdtye.FechaApertura,ccdtye.FechaCierre, operaba.FuerzaNombre FROM ccdtye INNER JOIN operaba ON operaba.CCDTyENombre = ccdtye.CCDTyENombre;");

			while (rs.next()) {
				String CCDTyENombre = rs.getString(1);
				int Ubicacion = rs.getInt(2);
				LocalDate FechaApertura = rs.getDate(3).toLocalDate();
				LocalDate FechaCierre = rs.getDate(4).toLocalDate();
				String Fnombre=rs.getString(5);

				CCDTyE e = new CCDTyE(CCDTyENombre, Ubicacion, FechaApertura, FechaCierre,Fnombre);
				centros.add(e);
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

		return centros;
	}

	public CCDTyE traersolouno(String nomidre) {
		CCDTyE centross = null;
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			//ResultSet rs = stmt.executeQuery("SELECT CCDTyENombre, Ubicacion,FechaApertura,FechaCierre FROM ccdtye");

			PreparedStatement pStmt = conn.prepareStatement(
					"SELECT ccdtye.CCDTyENombre,ccdtye.FechaApertura,ccdtye.FechaCierre,ccdtye.Ubicacion, operaba.FuerzaNombre  FROM ccdtye  INNER JOIN operaba ON operaba.CCDTyENombre = ccdtye.CCDTyENombre WHERE ccdtye.CCDTyENombre = ?;");
			pStmt.setString(1, nomidre);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String Centro_Nombre = rs.getString(1);
				int Ubicacion = rs.getInt(2);
				LocalDate FechaApertura = rs.getDate(3).toLocalDate();
				LocalDate FechaCierre = rs.getDate(4).toLocalDate();
				String Fnombre=rs.getString(5);
				centross = new CCDTyE(Centro_Nombre, Ubicacion, FechaApertura, FechaCierre,Fnombre);

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
		return centross;
	}

	
}
