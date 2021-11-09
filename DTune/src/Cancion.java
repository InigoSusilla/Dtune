import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;


public class Cancion {
	
	
	public String nombre;
	public String autor;
	public double precio;
	public boolean esVinillo;
	public Date fechaLanzamiento;
	public String genero;
	public String ruta;
	public double duracion;
	
	
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
	public double getPrecio() {
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
	public double getDuracion() {
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
	public Cancion(String nombre, String autor, double precio, boolean esVinillo, Date fechaLanzamiento, String genero,
			String ruta, double duracion) {
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
		this.esVinillo = esVinillo;
		this.fechaLanzamiento = fechaLanzamiento;
		this.genero = genero;
		this.ruta = ruta;
		this.duracion = duracion;
	}
	



	public static void main(String[] args) {
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Cancion thunder = new Cancion("Thunderstruck","AC/DC",(float)5.5,true, sdf.parse("1090-09-12") ,"Rock","demosCanciones/Thunder.mp3",(float)5.3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fallo al crear la cancion");
			
		}

		

	}
}
