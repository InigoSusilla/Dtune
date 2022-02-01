import java.text.SimpleDateFormat;
import java.util.Date;

public class Cancion implements Comparable<Cancion>, Comprable{
	
	public String nombre;
	public String autor;
	public double precio;
	public boolean esVinillo;
	public Date fechaLanzamiento;
	public String genero;
	public String ruta;
	public double duracion;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
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
	public boolean getEsVinillo() {
		return esVinillo;
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
		return nombre +" "+ autor + " "+ sdf.format(getFechaLanzamiento()) + " "+ (int)duracion + ":"+(int)(duracion*100%100)+" "+ precio+"€"; 
		/*return "Cancion [nombre=" + nombre + ", autor=" + autor + ", precio=" + precio + ", esVinillo=" + esVinillo
				+ ", fechaLanzamiento=" + fechaLanzamiento + ", genero=" + genero + ", ruta=" + ruta + ", duracion="
				+ duracion + "]";*/
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
	@Override
	public int compareTo(Cancion o) {
		// TODO Auto-generated method stub
		return (int) (this.duracion - o.duracion);
	}
}
