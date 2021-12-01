import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;




import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class VentanaCrearCuenta extends JFrame  {

	public String Usuario;
	public String Contrasena;
	

	@Override
	public String toString() {
		return "VentanaCrearCuenta [Usuario=" + Usuario + ", Contrasena=" + Contrasena + "]";
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		this.Usuario = usuario;
	}

	public String getContrasena() {
		return Contrasena;
	}

	public void setContrasena(String contrasena) {
		this.Contrasena = contrasena;
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
		
		//Dtune.cargarUsuariosDeFichero();
		
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
		PanelRegistrar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRegistrar = new JButton("Registrar");
		PanelRegistrar.add(btnRegistrar);
				btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String Usuario = txtUsuario.getText();
				String Contrasena = String.valueOf(txtContrasena.getText());
				
				
				if( !Usuario.equals("") && !Contrasena.equals("")){
					Usuario u = new Usuario(Usuario, Contrasena);
					Dtune.addUsuario(u);
					JOptionPane.showMessageDialog(null, "Te has registrado correctamente");
					
					txtUsuario.setText("");
					txtContrasena.setText("");
					
				}
			}
		});
				
				
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
