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
import java.awt.Color;
public class VentanaCrearCuenta extends JFrame  {

	public String Usuario;
	public String Contrasena;

	private JPanel contentPane;
	private JTextField infoUsuario;
	
	JLabel lprogreso;
	JProgressBar progreso;
	private JPanel panelContra;
	private JPanel panelProgress;
	private JLabel lblContra;
	private JTextField infoContrasena;
	private JLabel lblUsuario;

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
		
		lblUsuario = new JLabel("Usuario");
		PanelUsuario.add(lblUsuario);
		
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
				lprogreso.setVisible(true);
				progreso.setVisible(true);
				
					Thread hilo = new Thread(new Runnable() {
						public void run() {
							for (int i = 0; i <= 50; i++) {
								progreso.setValue(i);
								try {
									Thread.sleep(100);
								}
								catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							lprogreso.setForeground(Color.GREEN);
							lprogreso.setText("Registrando usuario...");
							
							// TODO Auto-generated method stub
							String nom = infoUsuario.getText();
							String c = infoContrasena.getText();
							for (int i = 51; i <= 100; i++) {
								progreso.setValue(i);
								try {
									Thread.sleep(100);
								}
								catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
							
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
		PanelContrasena.setLayout(new BorderLayout(0, 0));
		
		panelContra = new JPanel();
		PanelContrasena.add(panelContra, BorderLayout.NORTH);
		
		lblContra = new JLabel("Contrasena");
		panelContra.add(lblContra);
		
		infoContrasena = new JTextField();
		panelContra.add(infoContrasena);
		infoContrasena.setColumns(10);
		
		panelProgress = new JPanel();
		PanelContrasena.add(panelProgress, BorderLayout.SOUTH);
		lprogreso = new JLabel("Validando usuario...");
		panelProgress.add(lprogreso);
		lprogreso.setForeground(Color.RED);
		lprogreso.setVisible(false);
		progreso = new JProgressBar(0, 100);
		panelProgress.add(progreso);
		progreso.setVisible(false);
		setSize(350,200);
		setVisible(true);
	}

}