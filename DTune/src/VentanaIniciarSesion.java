import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class VentanaIniciarSesion extends JFrame {

	public String usuario;
	public String contrasena;
	
	@Override
	public String toString() {
		return "VentanaIniciarSesion [usuario=" + usuario + ", contrasena=" + contrasena + "]";
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelIniciarSesion = new JPanel();
		contentPane.add(panelIniciarSesion, BorderLayout.SOUTH);
		panelIniciarSesion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnInicioSesion = new JButton("Iniciar Sesi\u00F3n");
		panelIniciarSesion.add(btnInicioSesion);
		
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String cont = infoContrasena.getText();
				String usu = infoUsuario.getText();
			if(usu.equals("") || cont.equals("") ) {
				if (BaseDeDatos.comprobacionUsuario(usu, cont) == 1) {
					JOptionPane.showMessageDialog(null, "Usuario y contraseña correctos");
					new VentanaMain();
				}else if(BaseDeDatos.comprobacionUsuario(usu, cont ) == 2) {
					JOptionPane.showMessageDialog(null,"Contraseña incorrecta");
				}else if(BaseDeDatos.comprobacionUsuario(usu, cont ) == 3) {
					JOptionPane.showMessageDialog(null,"Usuario no encontrado");
				}
			}else {
				JOptionPane.showMessageDialog(null,"El nombre y/o la contraseña esta vacio");
			}
			
			
			
			
		}
		});
		
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
			
		setSize(350,150);
		setVisible(true);
	}
	

}
