
public class Administrador extends Usuario implements ITienePermiso{

	public Administrador(String nombre, String con) {
		super(nombre, con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean aniadirCancion() {
		// TODO Auto-generated method stub
		return true;
	}



}
