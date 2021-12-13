import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class VentanaMain extends JFrame{
	private static JComboBox comboBoxGenero;
	private DefaultListModel modeloListaCanciones;
	private JList<Cancion> listCanciones;
	public static JButton btnAnadirCancion;
	
	public VentanaMain() {
		FlatLightLaf.setup();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
	
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
		
		comboBoxGenero = new JComboBox();
		panelMusica.add(comboBoxGenero, BorderLayout.NORTH);
		
		/*comboBoxGenero.addItem("Todos los gï¿½neros");   
		//Hay que aï¿½ador aqui todos los gï¿½neros de las canciones
		comboBoxGenero.addItem("Rock");
		comboBoxGenero.addItem("Pop");
		comboBoxGenero.addItem("Reguetton");
		comboBoxGenero.addItem("Techno");
		*/
		
		cargarGenerosDeLaBBDD();
		//Ordenar las canciones por el gï¿½nero seleccionado
		comboBoxGenero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String genero = (String) comboBoxGenero.getSelectedItem();
				System.out.println(genero);
				if(genero!=null) {
					ArrayList<Cancion> ac = BaseDeDatos.filtrarCancionPorGenero(genero);
					modeloListaCanciones.removeAllElements();
					for(Cancion c : ac) {
						modeloListaCanciones.addElement(c);
					}
				}
			}
		});
		
		listCanciones = new JList();
		modeloListaCanciones = new DefaultListModel();
		ArrayList<Cancion> ALCanciones = BaseDeDatos.obtenerCanciones();
		for(Cancion c: ALCanciones) {
			modeloListaCanciones.addElement(c);
		}
		listCanciones.setModel(modeloListaCanciones);
		JScrollPane scrollListaCanciones = new JScrollPane(listCanciones);
		scrollListaCanciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollListaCanciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelMusica.add(scrollListaCanciones, BorderLayout.CENTER);
		
		JPanel panelMusicaBotones = new JPanel();
		panelMusica.add(panelMusicaBotones, BorderLayout.SOUTH);
		
		
		JCheckBox cbVinilo = new JCheckBox("Vinilo");
		panelMusicaBotones.add(cbVinilo);
			
		btnAnadirCancion = new JButton("Cancion");
		panelMusicaBotones.add(btnAnadirCancion);
		btnAnadirCancion.setVisible(false);
		
		btnAnadirCancion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCancion();
				System.out.println("Ventana crear cancion");
				//cargarGenerosDeLaBBDD();
				
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
		
		JList<Cancion> listaCarrito = new JList();
		DefaultListModel modeloCarrito = new DefaultListModel();
		panelCarrito.add(listaCarrito);
		listaCarrito.setModel(modeloCarrito);
		
		
		
		
		setSize(1900, 800);
		
		
		btnAnadirCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cancion a = listCanciones.getSelectedValue();
				System.out.println(a);
				modeloCarrito.addElement(a);
				
			}
		});
		
	
	btnIniciarSesion.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new VentanaPreLogging();
			System.out.println("Ventana pre iniciar");
			
		}
	});
	
	btnQuitarCarrito.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Cancion a = listaCarrito.getSelectedValue();
			modeloCarrito.removeElement(a);
			
		}
	});
	
	btnPreviewCancion.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Cancion a = listCanciones.getSelectedValue();
			String ruta = a.getRuta();
			Reproductor.ReproducirCancion(ruta);
		}
	});
	
	cbVinilo.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			modeloListaCanciones.removeAllElements();
			ArrayList<Cancion> ac;
			if(cbVinilo.isSelected()) {
				ac = BaseDeDatos.filtrarCancionPorVinilo((String)comboBoxGenero.getSelectedItem());
			}else {
				ac = BaseDeDatos.filtrarCancionPorGenero((String)comboBoxGenero.getSelectedItem());
			}
			for(Cancion c: ac) {
				modeloListaCanciones.addElement(c);
			}
			
		}
		
	});
	/*
	try
	{
	   //Correcion hecha por Chuster Boy ;)
	   UIManager.setLookAndFeel(new FlatLightLaf());

	}
	catch (Exception e)
	{
	   e.printStackTrace();
	}
	
	*/
	
	
	
	}
	//Cargar de manera recursiva los generos en la combobox
	private static void cargarRec(ArrayList<String> ag, int i) {
		if(i<ag.size()) {
			comboBoxGenero.addItem(ag.get(i));
			cargarRec(ag, i+1);
		}
	}
	public static void cargarGenerosDeLaBBDD() {
		ArrayList<String> ageneros = BaseDeDatos.obtenerGeneros();
		comboBoxGenero.removeAllItems();
		comboBoxGenero.addItem("Todos los gï¿½neros");
		cargarRec(ageneros, 0);
	}
	
	public static void main(String[] args) throws SQLException {
		new VentanaMain();
		
	}
	
	

	
}
