import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaCrearCuenta extends JFrame {

	public String usuario;
	public String contrasena;
	

	@Override
	public String toString() {
		return "VentanaCrearCuenta [usuario=" + usuario + ", contrasena=" + contrasena + "]";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contrasena;
	}

	public void setContraseña(String contraseña) {
		this.contrasena = contraseña;
	}

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField infoUsuario;
	private JTextField txtContrasena;
	private JTextField infoContrasena;

	/**
	 * Create the frame.
	 */
	public VentanaCrearCuenta() {
		setTitle("DTune Crear cuenta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel PanelUsuario = new JPanel();
		contentPane.add(PanelUsuario, BorderLayout.NORTH);
		
		txtUsuario = new JTextField();
		txtUsuario.setText("Usuario");
		txtUsuario.setEditable(false);
		PanelUsuario.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		infoUsuario = new JTextField();
		PanelUsuario.add(infoUsuario);
		infoUsuario.setColumns(10);
		
		JPanel PanelRegistrar = new JPanel();
		contentPane.add(PanelRegistrar, BorderLayout.SOUTH);
		
		JButton btnRegistrar = new JButton("Registrar");
		PanelRegistrar.add(btnRegistrar);
		
		JPanel PanelContrasena = new JPanel();
		contentPane.add(PanelContrasena, BorderLayout.CENTER);
		
		txtContrasena = new JTextField();
		txtContrasena.setText("Contrase\u00F1a");
		txtContrasena.setEditable(false);
		PanelContrasena.add(txtContrasena);
		txtContrasena.setColumns(10);
		
		infoContrasena = new JTextField();
		PanelContrasena.add(infoContrasena);
		infoContrasena.setColumns(10);
		setSize(350,150);
		setVisible(true);
	}

}
