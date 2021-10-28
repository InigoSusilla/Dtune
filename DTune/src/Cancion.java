import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Cancion {
	
	
	public String nombre;
	public String autor;
	public float precio;
	public boolean esVinillo;
	public Date fechaLanzamiento;
	public String genero;
	public String ruta;
	public float duracion;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public boolean isEsVinillo() {
		return esVinillo;
	}
	public void setEsVinillo(boolean esVinillo) {
		this.esVinillo = esVinillo;
	}
	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public float getDuracion() {
		return duracion;
	}
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}
	@Override
	public String toString() {
		return "Cancion [nombre=" + nombre + ", autor=" + autor + ", precio=" + precio + ", esVinillo=" + esVinillo
				+ ", fechaLanzamiento=" + fechaLanzamiento + ", genero=" + genero + ", ruta=" + ruta + ", duracion="
				+ duracion + "]";
	}
	public Cancion(String nombre, String autor, float precio, boolean esVinillo, Date fechaLanzamiento, String genero,
			String ruta, float duracion) {
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
		this.esVinillo = esVinillo;
		this.fechaLanzamiento = fechaLanzamiento;
		this.genero = genero;
		this.ruta = ruta;
		this.duracion = duracion;
	}

	
	//Cancion thunder = new Cancion("Thunderstruck","AC/DC",5.5,true, a ,"Rock","a",5.3);
	
	public void Sonar(Cancion a) {
		
	

}
	public static void main(String[] args) {
		
	}
}
