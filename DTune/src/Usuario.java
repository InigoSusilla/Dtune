import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;




public class Usuario {
	public static final long serialVersionUID = 1L;    //Para poder escribirlo o leerlo del fichero
	
	private static String nombre;
	private String contrasenia;
	//private int num_u = 0;
	//private ArrayList<Integer> lNumU = new ArrayList<Integer>();
	
	//constructor
	public Usuario(String nombre, String con ) {
		this.nombre = nombre;
		contrasenia = con;
	}
		/**while (num_u != lNumU.get(num_u)) {
			num_u +=1;
		}
		
	}**/
	//getters and setters
	public static String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	/**public int getNum_u() {
		return num_u;
	}**/
		//metodo toString
		public String toString() {
			return "Usuario [ nombre=" + nombre + ", contrasenia=" + contrasenia + /** + ", Numero de Usuario=" + num_u + **/"]";
		}
}
