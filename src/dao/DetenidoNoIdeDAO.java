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
import modelo.NoIdentificado;

public class DetenidoNoIdeDAO {
	public boolean guardar(NoIdentificado noidentificado) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO `detenido_noidentificado` (`Apodo`, `Descripcion`,`DNItestigo` ) VALUES (?,?,?)");
			pStmt.setString(1, noidentificado.getApodo());
			pStmt.setString(2, noidentificado.getDescripcion());
			pStmt.setInt(3, noidentificado.getDniTes());
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
	public boolean guardarVC(NoIdentificado noidentificado) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement(
					"INSERT INTO `lugardetencion` (`CCDTyENombre`, `Apodo_NOide` ) VALUES (?,?)");
			
			pStmt.setString(1, noidentificado.getNombreCentro());
			pStmt.setString(2, noidentificado.getApodo());

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


	public boolean actualizar(NoIdentificado noidentificado, String apodo ) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn
		.prepareStatement("UPDATE detenido_noidentificado SET `Descripcion` = ?,`DNItestigo` = ? WHERE `Apodo` = ?;");
	
			pStmt.setString(1, noidentificado.getDescripcion());
			pStmt.setInt(2, noidentificado.getDniTes());
			pStmt.setString(3, apodo);
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
	
	public boolean actualizarVC(NoIdentificado noidentificado, String apodo ) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn
		.prepareStatement("UPDATE lugardetencion SET `CCDTyENombre` = ? WHERE `Apodo_NOide` = ?;");

			pStmt.setString(1, noidentificado.getNombreCentro());
		
			pStmt.setString(2, apodo);
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
	
	public boolean eliminar(String apodo) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM `detenido_noidentificado` WHERE Apodo = ?;");
			pStmt.setString(1, apodo);

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
	public boolean eliminarVC(String apodo) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		int filasAfectas = 0;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM `lugardetencion` WHERE Apodo_NOide = ?;");
			pStmt.setString(1, apodo);

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

	
	public ArrayList<NoIdentificado> traerTodos() {
		ArrayList<NoIdentificado> detenidos = new ArrayList<NoIdentificado>();
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(
			"SELECT detenido_noidentificado.Apodo,detenido_noidentificado.Descripcion,detenido_noidentificado.DNItestigo, lugardetencion.CCDTyENombre FROM detenido_noidentificado INNER JOIN lugardetencion ON lugardetencion.Apodo_NOide = detenido_noidentificado.Apodo;");


			while (rs.next()) {
				String Apodo = rs.getString(1);
				String Descripcion = rs.getString(2);
				int DNIt=rs.getInt(3);
				String Centro=rs.getString(4);
				NoIdentificado e = new NoIdentificado(Apodo, Descripcion,DNIt,Centro);
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
	
	public NoIdentificado traersolouno(String apodo) {
		String url = "jdbc:mysql://localhost:3306/olimpo3";
		String usuario = "root";
		String contrasenia = "marifabi26";
		Connection conn = null;
		NoIdentificado NOide = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement pStmt = conn.prepareStatement(
					"SELECT detenido_noidentificado.Apodo,detenido_noidentificado.Descripcion,detenido_noidentificado.DNItestigo, lugardetencion.CCDTyENombre FROM detenido_noidentificado INNER JOIN lugardetencion ON lugardetencion.Apodo_NOide = detenido_noidentificado.Apodo WHERE detenido_noidentificado.Apodo = ? ;");
			pStmt.setString(1, apodo);

			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				String Apo= rs.getString(1);
				String descrip= rs.getString(2);
				int DNIt = rs.getInt(3);
				String centro=rs.getString(4);

				NOide = new NoIdentificado(Apo,descrip,DNIt,centro);

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
		return NOide;
		
	}

}
