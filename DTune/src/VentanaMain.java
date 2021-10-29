import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaMain extends JFrame {
	public VentanaMain() {
		
		JPanel PanelIzquierda = new JPanel();
		getContentPane().add(PanelIzquierda, BorderLayout.WEST);
		
		JScrollPane ScrollCanciones = new JScrollPane();
		PanelIzquierda.add(ScrollCanciones);
		
		JRadioButton rdbtnVinilo = new JRadioButton("Vinilo");
		rdbtnVinilo.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnVinilo.setVerticalAlignment(SwingConstants.BOTTOM);
		PanelIzquierda.add(rdbtnVinilo);
		
		JRadioButton rdbtnCD = new JRadioButton("CD");
		rdbtnCD.setHorizontalAlignment(SwingConstants.TRAILING);
		rdbtnCD.setVerticalAlignment(SwingConstants.BOTTOM);
		PanelIzquierda.add(rdbtnCD);
		
		JPanel PanelCentral = new JPanel();
		getContentPane().add(PanelCentral, BorderLayout.CENTER);
		
		JButton btnAnadir = new JButton("Añadir");
		PanelCentral.add(btnAnadir);
		
		JButton btnRetirar = new JButton("Retirar");
		PanelCentral.add(btnRetirar);
		
		JButton btnFinalizar = new JButton("Finalizar");
		PanelCentral.add(btnFinalizar);
		
		JPanel PanelDerecha = new JPanel();
		getContentPane().add(PanelDerecha, BorderLayout.EAST);
		
		JScrollPane ScrollCarrito = new JScrollPane();
		PanelDerecha.add(ScrollCarrito);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		PanelDerecha.add(btnIniciarSesion);
		
		JRadioButton rdbtnPreviewCancion = new JRadioButton("Preview Cancion");
		PanelDerecha.add(rdbtnPreviewCancion);
	}

}
