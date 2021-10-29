import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class VentanaMain extends JFrame {
	public VentanaMain() {
		
		JPanel PanelPreview = new JPanel();
		getContentPane().add(PanelPreview, BorderLayout.SOUTH);
		
		JButton btnPreviewCancion = new JButton("Reproducir Cancion");
		PanelPreview.add(btnPreviewCancion);
		
		JPanel panelIniciarSesion = new JPanel();
		getContentPane().add(panelIniciarSesion, BorderLayout.NORTH);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		panelIniciarSesion.add(btnIniciarSesion);
		
		JPanel panelCentro = new JPanel();
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(1, 3, 1, 3));
		
		JPanel panelMusica = new JPanel();
		panelCentro.add(panelMusica);
		panelMusica.setLayout(new BorderLayout(0, 0));
		
		JComboBox comboBoxGenero = new JComboBox();
		panelMusica.add(comboBoxGenero, BorderLayout.NORTH);
		
		JScrollBar scrollCanciones = new JScrollBar();
		panelMusica.add(scrollCanciones, BorderLayout.CENTER);
		
		JPanel panelMusicaBotones = new JPanel();
		panelMusica.add(panelMusicaBotones, BorderLayout.SOUTH);
		
		JRadioButton rdbtnCD = new JRadioButton("CD");
		panelMusicaBotones.add(rdbtnCD);
		rdbtnCD.setSelected(true);
		
		JRadioButton rdbtnVinilo = new JRadioButton("Vinilo");
		panelMusicaBotones.add(rdbtnVinilo);
		
		JPanel panelBotones = new JPanel();
		panelCentro.add(panelBotones);
		panelBotones.setLayout(new GridLayout(3, 0, 0, 0));
		
		JButton btnAnadirCarrito = new JButton("Añadir Carrito");
		panelBotones.add(btnAnadirCarrito);
		btnAnadirCarrito.setSize(20, 20);
		
		JButton btnRetirarCarrito = new JButton("Retirar Carrito");
		panelBotones.add(btnRetirarCarrito);
		btnRetirarCarrito.setSize(20,20);
		
		JButton btnFinalizar = new JButton("Finalizar Compra");
		panelBotones.add(btnFinalizar);
		btnFinalizar.setSize(20,20);
		
		JPanel panelCarrito = new JPanel();
		panelCentro.add(panelCarrito);
		
		JScrollBar scrollCarrito = new JScrollBar();
		panelCarrito.add(scrollCarrito);
		
		setSize(1900, 1050);
	
	
	btnIniciarSesion.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new VentanaPreLogging();
			System.out.println("aaa");
			
		}
	});
	
	btnFinalizar.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new VentanaPrintCarrito();
			System.out.println("aaa");
			
		}
	});
	
	
	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMain frame = new VentanaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

}
}
