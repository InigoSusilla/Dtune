import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;

public class VentanaMain extends JFrame{
	private static JComboBox comboBoxGenero;
	private DefaultListModel modeloListaCanciones;
	private JList<Cancion> listCanciones;
	private JList<Cancion> listaCarrito;
	private static DefaultListModel modeloCarrito;
	public static JButton btnAnadirCancion;
	public static JButton btnEstadisticas;
	//private Thread hiloReproductor = null;
	public VentanaMain() {
		FlatLightLaf.setup();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
	
		JPanel PanelPreview = new JPanel();
		getContentPane().add(PanelPreview, BorderLayout.SOUTH);
		
		
		
		JPanel panelIniciarSesion = new JPanel();
		getContentPane().add(panelIniciarSesion, BorderLayout.NORTH);
		
		JPanel panelCentro = new JPanel();
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(1, 3, 1, 3));
		
		JPanel panelMusica = new JPanel();
		panelCentro.add(panelMusica);
		panelMusica.setLayout(new BorderLayout(0, 0));
		
		comboBoxGenero = new JComboBox();
		panelMusica.add(comboBoxGenero, BorderLayout.NORTH);
		
		
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
		
		btnEstadisticas = new JButton("Estadisticas");
		panelMusicaBotones.add(btnEstadisticas);
		btnAnadirCancion.setVisible(false);
		btnEstadisticas.setVisible(false);
		
		
		btnAnadirCancion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCancion();
				//cargarGenerosDeLaBBDD();
				
			}
		});
		
		btnEstadisticas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaEstadisticas();
				
			}
		});
		
		JPanel panelBotones = new JPanel();
		panelCentro.add(panelBotones);
		panelBotones.setLayout(new GridLayout(3, 1, 3, 1));
		
		JPanel panelAnadirCarrito = new JPanel();
		panelBotones.add(panelAnadirCarrito);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		panelAnadirCarrito.add(btnIniciarSesion);
		
	
	btnIniciarSesion.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(VentanaIniciarSesion.getUsuario() == null) {
				new VentanaPreLogging();
			}else {
				JOptionPane.showMessageDialog(null, "Ya has iniciado sesion");
			}

			
		}
	});
		
		
		
		JPanel panelRetirar = new JPanel();
		panelBotones.add(panelRetirar);
		
		JButton btnAnadirCarrito = new JButton("Añadir Carrito");
		panelRetirar.add(btnAnadirCarrito);
		
		JButton btnQuitarCarrito = new JButton("Retirar del carrito");
		panelRetirar.add(btnQuitarCarrito);
		
		btnQuitarCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cancion a = listaCarrito.getSelectedValue();
				modeloCarrito.removeElement(a);
				
			}
		});
		
		
		btnAnadirCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cancion a = listCanciones.getSelectedValue();
				System.out.println(a);
				modeloCarrito.addElement(a);
				
			}
		});
		
		JPanel panelFinalizar = new JPanel();
		panelBotones.add(panelFinalizar);
		
		JButton btnFinalizar = new JButton("Finalizar Compra");
		panelFinalizar.add(btnFinalizar);
		
		btnFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(VentanaIniciarSesion.getUsuario() == null) {
				JOptionPane.showMessageDialog(null, "Tienes que iniciar sesion para poder comprar");
				}else {
					new VentanaPrintCarrito();
				}

				
			}
		});
		
		listaCarrito = new JList();
		modeloCarrito = new DefaultListModel();
		JScrollPane scrollCarrito = new JScrollPane(listaCarrito);
		scrollCarrito.setMinimumSize(new Dimension(400,1200));
		panelCentro.add(scrollCarrito);
		listaCarrito.setModel(modeloCarrito);
		
		JLabel lblCarrito = new JLabel("Carrito");
		lblCarrito.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCarrito.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 18));
		scrollCarrito.setColumnHeaderView(lblCarrito);
		
		JButton btnPreviewCancion = new JButton("Reproducir Cancion");
		panelRetirar.add(btnPreviewCancion);
		
	
		Thread hiloReproductor = new Thread( new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Cancion a = listCanciones.getSelectedValue();
				String ruta = a.getRuta();		
				Reproductor.ReproducirCancion(ruta);
				
			}
		});
		
		btnPreviewCancion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hiloReproductor.isAlive()) {
					Reproductor.PararCancion();
					Cancion a = listCanciones.getSelectedValue();
					String ruta = a.getRuta();		
					Reproductor.ReproducirCancion(ruta);
				}else {
					hiloReproductor.start();
				}
				
			}
		});
		
		
		
		
		setSize(1900, 800);
	
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
		comboBoxGenero.addItem("Rap");
		comboBoxGenero.addItem("Reggaeton");
		cargarRec(ageneros, 0);
	}
	
	public static void vaciarCarrito() {
		modeloCarrito.removeAllElements();
	}
	
	public static ArrayList<Cancion> obtenerCarrito() {
		ArrayList<Cancion> canciones = new ArrayList<>();
		if(modeloCarrito.getSize() > 0) {
		for (int i = 0; i < modeloCarrito.getSize(); i++) {
			canciones.add((Cancion)modeloCarrito.get(i));
		}
			System.out.println(canciones);
		}
		
		return canciones;
		
	}
	
	public static void main(String[] args) throws SQLException {
		new VentanaMain();
		
	}
	
	

	
}
