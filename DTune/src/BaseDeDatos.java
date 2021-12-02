
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class BaseDeDatos {
	
	public static Connection initBD() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + "DTuneBD.db");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	/**
	 public static Connection initBD() {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + "DTuneBD.db");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	 **/
	
	public static void closeBD(Connection con, Statement stt) {
			try {
				if (stt!=null) stt.close();
				if (con!=null) con.close();
			} catch (SQLException e) {
			}
	}
	
	public static Statement CrearTablasBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate("create table Usuarios"+
						   "(nombre string, "+
						   " contrasenia string)");
			statement.executeUpdate("create table Canciones"+
					   "(nombre string, "+
					   "autor string, "+
					   "precio double, "+
					   "esVinillo boolean, "+
					   "genero String, "+
					   "ruta String,"+
					   "duracion double)");
			return statement;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static Statement restablecerBD(Connection con) {
		try {
			Statement stt = con.createStatement();
			stt.executeUpdate("drop table Usuarios");
			stt.executeUpdate("drop table Canciones");
			return BaseDeDatos.CrearTablasBD(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void insertarUsuario(Usuario u) {
		Connection con = BaseDeDatos.initBD();
		Statement stt = null;
		String sentSQL = "INSERT INTO Usuarios VALUES('"+u.getNombre()+"','"+u.getContrasenia()+"')";
		try {
			stt = con.createStatement();
			stt.executeUpdate(sentSQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				BaseDeDatos.closeBD(con, stt);
			}
	}
	
	public static void eliminarUsuario(String nombre) {
		Connection con = BaseDeDatos.initBD();
		Statement stt = null;
		String sentSQL = "DELETE FROM Usuarios WHERE nombre = '"+nombre+"'";
		try {
			stt = con.createStatement();
			stt.executeUpdate(sentSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			BaseDeDatos.closeBD(con, stt);
		}
	}
	
	public static Usuario mostrarUsuario(String nombre) {
		Connection con = BaseDeDatos.initBD();
		String query = "SELECT * FROM Usuarios WHERE nombre = '"+nombre+"'";
		Statement st = null;
		Usuario u = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				String c = rs.getString("contrasenya");
				u = new Usuario(nombre, c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeBD(con, st);
		}
		return u;
	}
	
	public static void insertarCancion(Cancion c) {
		Connection con = BaseDeDatos.initBD();
		Statement stt = null;
		String sentSQL = "INSERT INTO Canciones VALUES('"+c.getNombre()+"','"+c.getAutor()+"',"+c.getPrecio()+","+c.getEsVinillo()+","+c.getFechaLanzamiento()+",'"+c.getGenero()+"','"+c.getRuta()+"',"+c.getDuracion()+")";
		try {
			stt = con.createStatement();
			stt.executeUpdate(sentSQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				BaseDeDatos.closeBD(con, stt);
			}
	}
	
	
	public static void eliminarCancion(String nombre) {
		Connection con = BaseDeDatos.initBD();
		Statement stt = null;
		String sentSQL = "DELETE FROM Canciones WHERE nombre = '"+nombre+"'";
		try {
			stt = con.createStatement();
			stt.executeUpdate(sentSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			BaseDeDatos.closeBD(con, stt);
		}
	}
	
	public static int comprobacionUsuario(String nombre, String contrasenya) {
		Connection con = BaseDeDatos.initBD();
		int resultado = 0;
		String sentSQL = "select * from Usuarios where nombre = '"+nombre+"'";
		Statement stt = null;
		try {
			stt = con.createStatement();
			ResultSet rs = stt.executeQuery(sentSQL);
			if(rs.next()) {
				String c = rs.getString("contrasenia");
				if(contrasenya.equals(c)) {
					resultado = 1;
				}else if(!contrasenya.equals(c)) {
					resultado = 2;
				}
			}else if(rs.next() == false) {
				resultado = 3;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeBD(con, stt);
		}
		return resultado;
	}
	
	public static int comprobarRepeticionUsuario (String nombre) {
		int resultado = 0;
		Connection con = BaseDeDatos.initBD();
		Statement stt = null;
		String sentSQL = "select nombre from Usuarios where nombre = '"+nombre+"'";
		try {
			stt = con.createStatement();
			ResultSet rs = stt.executeQuery(sentSQL);
			if(rs.next()){
				String nom = rs.getString("nombre");
				if(nom.equals(nombre)) {
					resultado = 2;//YA EXISTE
				}
			}else if(rs.next() == false) {
				resultado = 1;//NO EXISTE
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			BaseDeDatos.closeBD(con, stt);
		}
		return resultado;
	}
	
	
}
