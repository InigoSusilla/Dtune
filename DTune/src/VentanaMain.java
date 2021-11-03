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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel PanelPreview = new JPanel();
		getContentPane().add(PanelPreview, BorderLayout.SOUTH);
		
		JButton btnPreviewCancion = new JButton("Reproducir Cancion");
		PanelPreview.add(btnPreviewCancion);
		
		btnPreviewCancion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reproductor.ReproducirCancion("demosCanciones/demoThunder.mp3");
				
		
			}
		});
		
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
		
		comboBoxGenero.addItem("Todos los géneros");   
		//Hay que añador aqui todos los géneros de las canciones
		comboBoxGenero.addItem("Rock");
		comboBoxGenero.addItem("Pop");
		
		
		//Ordenar las canciones por el género seleccionado
		comboBoxGenero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String genero = (String) comboBoxGenero.getSelectedItem();
				System.out.println(genero);
				
				}
			});
			
		
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
		panelBotones.setLayout(new GridLayout(3, 1, 3, 1));
		
		JPanel panelAnadirCarrito = new JPanel();
		panelBotones.add(panelAnadirCarrito);
		
		JButton btnAnadirCarrito = new JButton("AÃ±adir Carrito");
		panelAnadirCarrito.add(btnAnadirCarrito);
		
		JPanel panelRetirar = new JPanel();
		panelBotones.add(panelRetirar);
		
		JButton btnQuitarCarrito = new JButton("Retirar del carrito");
		panelRetirar.add(btnQuitarCarrito);
		
		JPanel panelFinalizar = new JPanel();
		panelBotones.add(panelFinalizar);
		
		JButton btnFinalizar = new JButton("Finalizar Compra");
		panelFinalizar.add(btnFinalizar);
		
		btnFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaPrintCarrito();
				System.out.println("Ventana print carrito");
				
			}
		});
		
		JPanel panelCarrito = new JPanel();
		panelCentro.add(panelCarrito);
		
		JScrollBar scrollCarrito = new JScrollBar();
		panelCarrito.add(scrollCarrito);
		
		setSize(1900, 800);
	
	
	
	
	btnIniciarSesion.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new VentanaPreLogging();
			System.out.println("Ventana pre iniciar");
			
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
