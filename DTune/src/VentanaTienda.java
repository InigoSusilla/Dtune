import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.formdev.flatlaf.FlatLightLaf;

 // Esta clase define las cosas comunes entre la tienda de canciones y entradas

public abstract class VentanaTienda extends JFrame{
	private static final long serialVersionUID = -8007325425284480782L;
	protected final Usuario user;
	protected final JScrollPane scrollCarrito;
	protected final JList<Comprable> listaCarrito;
	protected final DefaultListModel<Comprable> modeloCarrito;
	public Usuario getUsuario() {
		return user;
	}
	public VentanaTienda(Usuario u) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.user = u;
		FlatLightLaf.setup();
		setLocationRelativeTo(null);
		setVisible(true);
		this.setSize(1500, 550);
		listaCarrito = new JList<>();
		modeloCarrito = new DefaultListModel<>();
		scrollCarrito = new JScrollPane(listaCarrito);
		scrollCarrito.setMinimumSize(new Dimension(400,1200));
		listaCarrito.setModel(modeloCarrito);
	}
	public ArrayList<Comprable> obtenerCarrito() {
		ArrayList<Comprable> canciones = new ArrayList<>();
		if(modeloCarrito.getSize() > 0) {
		for (int i = 0; i < modeloCarrito.getSize(); i++) {
			canciones.add((Comprable)modeloCarrito.get(i));
		}
			System.out.println(canciones);
		}
		
		return canciones;
	}
	public void vaciarCarrito() {
		modeloCarrito.removeAllElements();
	}
}
