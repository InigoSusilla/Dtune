import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class BaseDeDatosTest {

	@Test
	public void testInsertarUsuario() {
		Usuario u1 = new Administrador("u","y");
		BaseDeDatos.insertarUsuario(u1);
		Usuario u2 = BaseDeDatos.mostrarUsuario("u");
		assertTrue(u2 != null);
		
	}
	
	@Test
	public void testBorrarCancion() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		Date fecha = sdf.parse("12/02/2001");
		Cancion c1 = new Cancion("Prueba","a",1,true, fecha, "Pop","ss",1.3);
		BaseDeDatos.insertarCancion(c1);
		ArrayList<Cancion> canciones = BaseDeDatos.obtenerCanciones();
		BaseDeDatos.eliminarCancion("Prueba");
		ArrayList<Cancion> canciones2 = BaseDeDatos.obtenerCanciones();
		assertTrue(canciones.size() < canciones2.size()-1);
		
	}
}
