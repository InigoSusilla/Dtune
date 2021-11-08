import java.io.Serializable;



public class Usuario {
	public static final long serialVersionUID = 1L;    //Para poder escribirlo o leerlo del fichero
	
	private String nombre;
	private String contrasenia;
	
	
	
	//constructor
	public Usuario(String nombre, String con ) {
		this.nombre = nombre;
		contrasenia = con;
	}
	//getters and setters
	public String getNombre() {
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
	
	//metodo toString
		public String toString() {
			return "Usuario [ nombre=" + nombre + ", contrasenia=" + contrasenia + "]";
		}
}
