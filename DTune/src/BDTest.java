
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class BDTest {

	@Test
	void testInsertarUsuario() {
		Usuario u1 = new Cliente("u","y");
		BaseDeDatos.insertarUsuario(u1);
		Usuario u2 = BaseDeDatos.mostrarUsuario("u");
		assertNotNull(u2);
		assertEquals("y", u2.getContrasenia());
	}
	
	@Test
	void testInsertarCancion() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		Date fecha = sdf.parse("12/02/2001");
		Cancion c1 = new Cancion("n","a",1,true, fecha, "Pop","ss",1.3);
		BaseDeDatos.insertarCancion(c1);
		ArrayList<Cancion> ar = BaseDeDatos.obtenerCanciones();
		assertNotNull(ar);
		assertEquals(c1, ar.get(0));
	}
	
}
