import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;




import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class VentanaCrearCuenta extends JFrame  {

	public String Usuario;
	public String Contrasena;

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField infoUsuario;
	private JTextField txtContrasena;
	private JTextField infoContrasena;
	
	JLabel lprogreso;
	JProgressBar progreso;

	/**
	 * Create the frame.
	 */
	public VentanaCrearCuenta() {
		setLocationRelativeTo(null);
		setTitle("DTune Crear cuenta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		PanelRegistrar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRegistrar = new JButton("Registrar");
		PanelRegistrar.add(btnRegistrar);
		lprogreso = new JLabel("Creando base de datos...");
		lprogreso.setVisible(false);
		progreso = new JProgressBar(0, 100);
		progreso.setVisible(false);
		
		btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					Thread hilo = new Thread(new Runnable() {
						public void run() {
							lprogreso.setVisible(true);
							progreso.setVisible(true);
							for (int i = 0; i <= 100; i++) {
								progreso.setValue(i);
								try {
									Thread.sleep(100);
								}
								catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							// TODO Auto-generated method stub
							String nom = infoUsuario.getText();
							String c = infoContrasena.getText();
							
							
							if(!nom.equals("") && !c.equals("") ) {//FUNCIONA
								if(BaseDeDatos.comprobarRepeticionUsuario(nom) == 1) {
									Usuario u ;
									if(c.charAt(0) == 'a')
										u = new Administrador(nom, c);
									else
										u = new Cliente(nom,c);
									//Usuario u = new UsuarioCliente(nom, c);
									BaseDeDatos.insertarUsuario(u);
									BaseDeDatos.mostrarUsuario(nom);
									JOptionPane.showMessageDialog(null, "Te has registrado correctamente");
									
								}else if(BaseDeDatos.comprobarRepeticionUsuario(nom) == 2){
									JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya existe");
								}
								//btnRegistrar.setEnabled(false);
								else{
								JOptionPane.showMessageDialog(null, "El nombre y/o la contrase�a esta vacio");
							infoUsuario.setText("");
							infoContrasena.setText("");
								}
							}else {
								JOptionPane.showMessageDialog(null,"El nombre y/o la contrase�a esta vacio");
							}
							lprogreso.setVisible(false);
							progreso.setVisible(false);
						}
						
					});
					hilo.start();
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