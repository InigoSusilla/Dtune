import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;

public class VentanaMain extends VentanaTienda{
	private static final long serialVersionUID = -8713729984758159890L;
	private static JComboBox<String> comboBoxGenero;
	private DefaultListModel<Cancion> modeloListaCanciones;
	private JList<Cancion> listCanciones;
	public static JButton btnAnadirCancion;
	public static JButton btnEstadisticas;
	//private Thread hiloReproductor = null;
	public VentanaMain(Usuario u) {
		super(u);
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
		
		comboBoxGenero = new JComboBox<String>();
		panelMusica.add(comboBoxGenero, BorderLayout.NORTH);
		
		
		cargarGenerosDeLaBBDD();
		//Ordenar las canciones por el genero seleccionado
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
		
		listCanciones = new JList<>();
		modeloListaCanciones = new DefaultListModel<>();
		ArrayList<Cancion> ALCanciones = BaseDeDatos.obtenerCanciones();
		for(Cancion c: ALCanciones) {
			modeloListaCanciones.addElement(c);
		}
		listCanciones.setModel(modeloListaCanciones);
		listCanciones.addMouseListener(new MouseAdapter() { //TODO esto hace que se pueda meter canciones al carrito con doble click
			Cancion selected = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(selected);
				if(listCanciones.getSelectedIndex() != -1)
				if(selected == null || !selected.equals(listCanciones.getSelectedValue()))selected = listCanciones.getSelectedValue();
				else {
					modeloCarrito.addElement(selected);
					selected = null;
				}
			}
		});
		
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
		
		
		
		JPanel panelRetirar = new JPanel();
		panelBotones.add(panelRetirar);
		
		JButton btnAnadirCarrito = new JButton("Añadir Carrito");
		panelRetirar.add(btnAnadirCarrito);
		
		JButton btnQuitarCarrito = new JButton("Retirar del carrito");
		panelRetirar.add(btnQuitarCarrito);
		
		btnQuitarCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cancion a = (Cancion)listaCarrito.getSelectedValue();
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
		VentanaMain t = this;
		btnFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(user == null) {
				JOptionPane.showMessageDialog(null, "Tienes que iniciar sesion para poder comprar");
				}else {
					new VentanaPrintCarrito(t);
				}

				
			}
		});
		
		JButton btnAtras = new JButton("Volver");
		panelFinalizar.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaPostLogging(user);
				dispose();
			}
			
		});
		
		panelCentro.add(scrollCarrito);
		
		JLabel lblCarrito = new JLabel("Carrito");
		lblCarrito.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCarrito.setFont(new Font("Perpetua Titling MT", Font.PLAIN, 18));
		scrollCarrito.setColumnHeaderView(lblCarrito);
		
		JButton btnPreviewCancion = new JButton("Reproducir Cancion");
		panelRetirar.add(btnPreviewCancion);
		
	
		Thread hiloReproductor = new Thread( new Runnable() {
			@Override
			public void run() {
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
		comboBoxGenero.addItem("Todos los géneros");
		//comboBoxGenero.addItem("Rap");
		//comboBoxGenero.addItem("Reggaeton");
		cargarRec(ageneros, 0);
	}
	
	public static void main(String[] args) throws SQLException {
		new VentanaPreLogging();
		
	}
	
	

	
}
