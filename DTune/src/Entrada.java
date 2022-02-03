import java.text.SimpleDateFormat;
import java.util.Date;

public class Entrada implements Comprable{
	public final String artista;
	public final String ubicacion;
	public final Date fecha;
	public final Long fechaMilis;
	public final Double precio;
	public Entrada(String artista, String ubicacion, Long fecha, Double precio) {
		this.artista = artista;
		this.ubicacion = ubicacion;
		this.fecha = new Date(fecha);
		this.fechaMilis = fecha;
		this.precio = precio;
		
	}
	@Override
	public String toString() {
		return artista+" en "+ubicacion+" el dia "+new SimpleDateFormat("dd/MM/yyyy hh:mm").format(fecha);
	}
	@Override
	public double getPrecio() {
		return precio;
	}
	@Override
	public boolean equals(Object e) {
		return (e instanceof Entrada) && ((Entrada)e).artista == artista && ((Entrada)e).fechaMilis == ((Entrada)e).fechaMilis;
	}
	public EntradaComprada comprar(String dueno) {
		return new EntradaComprada(dueno, artista, ubicacion, fechaMilis, precio);
	}
	public class EntradaComprada extends Entrada{
		public final String dueno;
		private EntradaComprada(String dueno ,String artista, String ubicacion, Long fecha, Double precio) {
			super(artista, ubicacion, fecha, precio);
			this.dueno = dueno;
		}
		@Override
		public String toString() {
			return super.toString()+". "+dueno;
		}
	}
}
