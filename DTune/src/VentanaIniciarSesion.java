import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class VentanaIniciarSesion extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField infoUsuario;
	private JTextField txtContrasea;
	private JTextField infoContrasena;


	/**
	 * Create the frame.
	 */
	public VentanaIniciarSesion() {
		setTitle("DTune Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelIniciarSesion = new JPanel();
		contentPane.add(panelIniciarSesion, BorderLayout.SOUTH);
		panelIniciarSesion.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Iniciar Sesi\u00F3n");
		panelIniciarSesion.add(btnNewButton, BorderLayout.NORTH);
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.CENTER);
		panelBotones.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesUsuarios = new JPanel();
		panelBotones.add(panelBotonesUsuarios, BorderLayout.NORTH);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setText("Usuario");
		panelBotonesUsuarios.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		infoUsuario = new JTextField();
		panelBotonesUsuarios.add(infoUsuario);
		infoUsuario.setColumns(10);
		
		JPanel panelBotonesContrasena = new JPanel();
		panelBotones.add(panelBotonesContrasena, BorderLayout.CENTER);
		
		txtContrasea = new JTextField();
		txtContrasea.setEditable(false);
		txtContrasea.setText("Contrase\u00F1a");
		panelBotonesContrasena.add(txtContrasea);
		txtContrasea.setColumns(10);
		
		infoContrasena = new JTextField();
		panelBotonesContrasena.add(infoContrasena);
		infoContrasena.setColumns(10);
			
		setSize(250,150);
		setVisible(true);
	}
	

}
