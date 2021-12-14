

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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
						   " contrasenia string," +
						   "esAdministrador boolean) "
						   );
			statement.executeUpdate("create table Canciones"+
					   "(nombre string, "+
					   "autor string, "+
					   "precio double, "+
					   "esVinillo boolean, "+
					   "fechaLanzamiento long," +
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
				String c = rs.getString("contrasenia");
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
		long milis = c.getFechaLanzamiento().getTime();
		String sentSQL = "INSERT INTO Canciones VALUES('"+c.getNombre()+"','"+c.getAutor()+"',"+c.getPrecio()+","+c.getEsVinillo()+","+milis+",'"+c.getGenero()+"','"+c.getRuta()+"',"+c.getDuracion()+")";
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
	
	public static ArrayList<Cancion> obtenerCanciones(){
		
		Connection con = BaseDeDatos.initBD();
		Statement stt = null;
		String sentSQL = "select * from canciones";
		ArrayList<Cancion> listaCanciones = new ArrayList<>();
		try {
			stt = con.createStatement();
			ResultSet rs = stt.executeQuery(sentSQL);
			while(rs.next()){
				String nomb = rs.getString("nombre"); 
				String aut = rs.getString("autor"); 
				Double prec = rs.getDouble("precio");
				boolean flan = rs.getBoolean("esVinillo");
				long milisLanz = rs.getLong("fechaLanzamiento");
				String gen = rs.getString("genero");
				String rut = rs.getString("ruta");
				Double dura = rs.getDouble("duracion");
				
				SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
				Date fechaLanz = new Date(milisLanz);
				
				Cancion pa = new Cancion(nomb, aut, prec , flan, fechaLanz, gen, rut, dura);
				listaCanciones.add(pa);
				
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaCanciones;
		}
		
		
		
	
	
	public static boolean esAdministrador(String usuario){
		Connection con = BaseDeDatos.initBD();
		Statement stt = null;
		String sentSQL = "select esAdministrador from Usuarios where nombre = '"+ usuario+"'";
		boolean esAdmin = false;
		try {
			stt = con.createStatement();
			ResultSet rs = stt.executeQuery(sentSQL);
			if(rs.next()){
				esAdmin = rs.getBoolean("esAdministrador");
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return esAdmin;
		
	}
	
	public static ArrayList<Cancion> filtrarCancionPorGenero(String genero) {
		Connection con = BaseDeDatos.initBD();
		
		Statement stt = null;
		if(genero.equals("Todos los g�neros")) {
			return obtenerCanciones();
		}else {
			ArrayList<Cancion> listaCanciones= new ArrayList<>();
			String sentSQL = "select * from canciones where genero='" + genero+"'";
		
		
		try {
			stt = con.createStatement();
			stt.executeUpdate(sentSQL);
			ResultSet rs = stt.executeQuery(sentSQL);
			while(rs.next()){
				String nomb = rs.getString("nombre"); 
				String aut = rs.getString("autor"); 
				Double prec = rs.getDouble("precio");
				boolean flan = rs.getBoolean("esVinillo");
				long milisLanz = rs.getLong("fechaLanzamiento");
				String gen = rs.getString("genero");
				String rut = rs.getString("ruta");
				Double dura = rs.getDouble("duracion");
				
				SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
				Date fechaLanz = new Date(milisLanz);
				
				Cancion pa = new Cancion(nomb, aut, prec , flan, fechaLanz, gen, rut, dura);
				
				listaCanciones.add(pa);
					
				
				
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				BaseDeDatos.closeBD(con, stt);
			}
		return listaCanciones; 	
		
	}
		}
	
	public static ArrayList<String> obtenerGeneros(){
		ArrayList<String> aGeneros = new ArrayList<>();
		String sent = "select distinct(genero) from canciones";
		Connection con = BaseDeDatos.initBD();
		try {
			Statement stt = con.createStatement();
			ResultSet rs = stt.executeQuery(sent);
			while(rs.next()) {
				String gen = rs.getString("genero");
				aGeneros.add(gen);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aGeneros;
		
	}

	public static ArrayList<Cancion> filtrarCancionPorVinilo(String genero) {
		Connection con = BaseDeDatos.initBD();
		String sentSQL;
		Statement stt = null;
		ArrayList<Cancion> listaCanciones= new ArrayList<>();
		
		if(genero.equals("Todos los g�neros")) {
			sentSQL = "select * from canciones where esVinillo = 1";
		}else {
			sentSQL = "select * from canciones where genero='" + genero+"' and esVinillo=1";
		}
		
		try {
			stt = con.createStatement();
			stt.executeUpdate(sentSQL);
			ResultSet rs = stt.executeQuery(sentSQL);
			while(rs.next()){
				String nomb = rs.getString("nombre"); 
				String aut = rs.getString("autor"); 
				Double prec = rs.getDouble("precio");
				boolean flan = rs.getBoolean("esVinillo");
				long milisLanz = rs.getLong("fechaLanzamiento");
				String gen = rs.getString("genero");
				String rut = rs.getString("ruta");
				Double dura = rs.getDouble("duracion");
				
				SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
				Date fechaLanz = new Date(milisLanz);
				
				Cancion pa = new Cancion(nomb, aut, prec , flan, fechaLanz, gen, rut, dura);
				
				listaCanciones.add(pa);
					
				
				
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				BaseDeDatos.closeBD(con, stt);
			}
		return listaCanciones; 	
		
	
		}
	
}
