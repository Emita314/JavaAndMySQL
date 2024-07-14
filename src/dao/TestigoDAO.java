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
import modelo.Testigo;

public class TestigoDAO {
	public boolean guardar(Testigo testigo) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO `testigo` (`DNItestigo`, `Tes_Nombre`,`Tes_Apellido`, `Testimonio`) VALUES (?,?,?,?)");
			pStmt.setInt(1, testigo.getDNItestigo());
			pStmt.setString(2, testigo.getTes_Nombre());
			pStmt.setString(3, testigo.getTes_Apellido());
			pStmt.setString(4, testigo.getTestimonio());

			
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
	
	public boolean actualizar(Testigo testigo, int DNItestigo) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn
		.prepareStatement("UPDATE testigo SET `Tes_Nombre` = ?,`Tes_Apellido` = ?,`Testimonio` = ? WHERE `DNItestigo` = ?;");
			
			pStmt.setString(1, testigo.getTes_Nombre());
			pStmt.setString(2, testigo.getTes_Apellido());
			pStmt.setString(3, testigo.getTestimonio());
			pStmt.setInt(4, DNItestigo);

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
	
	public boolean eliminar(int Dni) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM `testigo` WHERE DNItestigo = ?;");
			pStmt.setInt(1, Dni);

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

public ArrayList<Testigo> traerTodos() {
	ArrayList<Testigo> testigos = new ArrayList<Testigo>();
	String url = "jdbc:mysql://localhost:3306/olimpo3";
	String usuario = "root";
	String contrasenia = "marifabi26";
	Connection conn = null;
	try {
		conn = DriverManager.getConnection(url, usuario, contrasenia);
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT DNItestigo, Tes_Nombre,Tes_Apellido, Testimonio FROM testigo");

		while (rs.next()) {
			int DNItestigo = rs.getInt(1);
			String Tes_Nombre = rs.getString(2);
			String Tes_Apellido = rs.getString(3);
			String Testimonio = rs.getString(4);
			
			
			Testigo tes = new Testigo(DNItestigo, Tes_Nombre,Tes_Apellido,Testimonio);
			testigos.add(tes);
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

	return testigos;
}

public Testigo traersolouno(Testigo test,int tesDNI) {
	Testigo testigo = new Testigo();
	String url = "jdbc:mysql://localhost:3306/olimpo3";
	String usuario = "root";
	String contrasenia = "marifabi26";
	Connection conn = null;
	try {
		conn = DriverManager.getConnection(url, usuario, contrasenia);
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT DNItestigo, Tes_Nombre,Tes_Apellido, Testimonio FROM testigo");
	
		
		PreparedStatement pStmt = conn
				.prepareStatement("SELECT DNItestigo, Tes_Nombre,Tes_Apellido, Testimonio FROM testigo WHERE DNItestigo LIKE ?; ");
		pStmt.setInt(1, tesDNI);
		
		
		while (rs.next()) {
			
			int DNItestigo = rs.getInt(1);
			String Tes_Nombre = rs.getString(2);
			String Tes_Apellido = rs.getString(3);
			String Testimonio = rs.getString(4);
			
			
			Testigo tes = new Testigo(DNItestigo, Tes_Nombre,Tes_Apellido,Testimonio);
			if(tesDNI==DNItestigo) {
				testigo=tes;
			}
			}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}}
	return testigo;
}
}
