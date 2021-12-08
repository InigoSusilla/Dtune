import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class VentanaMain extends JFrame {
	public VentanaMain() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel PanelPreview = new JPanel();
		getContentPane().add(PanelPreview, BorderLayout.SOUTH);
		
		JButton btnPreviewCancion = new JButton("Reproducir Cancion");
		PanelPreview.add(btnPreviewCancion);
		
		btnPreviewCancion.addActionListener(new ActionListener() {
			Thread hilo = new Thread( new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Reproductor.ReproducirCancion("demosCanciones/DemoThunder.mp3");
				}
			});
			@Override
			public void actionPerformed(ActionEvent e) {
				hilo.start();
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
		
		comboBoxGenero.addItem("Todos los g�neros");   
		//Hay que a�ador aqui todos los g�neros de las canciones
		comboBoxGenero.addItem("Rock");
		comboBoxGenero.addItem("Pop");
		comboBoxGenero.addItem("Reguetton");
		comboBoxGenero.addItem("Techno");
		
		
		
		//Ordenar las canciones por el g�nero seleccionado
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
		
		ButtonGroup bg = new ButtonGroup(); //QUE NO SE SELECCIONEN LOS DOS BOTNES AL MISMO TIMEPO
		bg.add(rdbtnVinilo);
		bg.add(rdbtnCD);
		
		JButton btnAnadirCancion = new JButton("Cancion");
		panelMusicaBotones.add(btnAnadirCancion);
		
		btnAnadirCancion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCancion();
				System.out.println("Ventana crear cancion");
				
			}
		});
		
		JPanel panelBotones = new JPanel();
		panelCentro.add(panelBotones);
		panelBotones.setLayout(new GridLayout(3, 1, 3, 1));
		
		JPanel panelAnadirCarrito = new JPanel();
		panelBotones.add(panelAnadirCarrito);
		
		JButton btnAnadirCarrito = new JButton("Añadir Carrito");
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
	
	//Cargamos las canciones
	ArrayList<Cancion> listaCanciones = BaseDeDatos.obtenerCanciones();
	for(Cancion c: listaCanciones) {
		//scrollCanciones.add(c);
	}
	
	
	
	}
	
	public static void main(String[] args) throws SQLException {
		new VentanaMain();
		
	}
	
	

	
}
