import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
public class VentanaCrearCuenta extends JFrame  {

	private static final long serialVersionUID = -1027647170303777734L;
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
		try { //TODO Este try es la forma de poner un fondo a la ventana
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("back.jpg"));
            contentPane = new JPanel(new BorderLayout()) {
				private static final long serialVersionUID = -6618213232244732463L;
				@Override 
				public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, -139, 400, 300, null);
                }
            };
            setVisible(true);
        } catch (IOException e) {
        	contentPane = new JPanel();
            e.printStackTrace();
        }
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panelUsuario = new JPanel();
		contentPane.add(panelUsuario, BorderLayout.NORTH);
		panelUsuario.setOpaque(false);
		
		lblUsuario = new JLabel("<html><font color=\"white\">Usuario</font></html>");
		panelUsuario.add(lblUsuario);
		
		infoUsuario = new JTextField();
		panelUsuario.add(infoUsuario);
		infoUsuario.setColumns(10);
		
		JPanel panelRegistrar = new JPanel();
		contentPane.add(panelRegistrar, BorderLayout.SOUTH);
		panelRegistrar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelRegistrar.setOpaque(false);
		
		JButton btnRegistrar = new JButton("Registrar");
		panelRegistrar.add(btnRegistrar);
		
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
						
						if(!nom.strip().equals("") && !c.strip().equals("") ) {//FUNCIONA
							if(BaseDeDatos.comprobarRepeticionUsuario(nom) == 1) {
								Usuario u ;
								if(c.charAt(0) == 'a')
									u = new Administrador(nom, c);
								else
									u = new Cliente(nom,c);
								//Usuario u = new UsuarioCliente(nom, c);
								BaseDeDatos.insertarUsuario(u);
								//BaseDeDatos.mostrarUsuario(nom);
								JOptionPane.showMessageDialog(null, "Te has registrado correctamente");
								new VentanaPostLogging(u);
								//Borrado de ventanas a mano
								Window [] ventanas = Window.getWindows();
								ventanas[ventanas.length-3].dispose();
								ventanas[ventanas.length-4].dispose();

								
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

				
		JPanel panelContrasena = new JPanel();
		contentPane.add(panelContrasena, BorderLayout.CENTER);
		panelContrasena.setLayout(new BorderLayout(0, 0));
		panelContrasena.setOpaque(false);
		
		panelContra = new JPanel();
		panelContra.setOpaque(false);
		panelContrasena.add(panelContra, BorderLayout.NORTH);
		
		lblContra = new JLabel("<html><font color=\"white\">Contrasena</font></html>");
		panelContra.add(lblContra);
		
		infoContrasena = new JTextField();
		panelContra.add(infoContrasena);
		infoContrasena.setColumns(10);
		
		panelProgress = new JPanel();
		panelProgress.setOpaque(false);
		panelContrasena.add(panelProgress, BorderLayout.SOUTH);
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