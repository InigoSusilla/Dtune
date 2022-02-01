import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;


public class VentanaIniciarSesion extends JFrame {
	
	private static final long serialVersionUID = -4771659016438612137L;

	private static Logger logger = Logger.getLogger( "Inicio de sesi�n" );

	private JPanel contentPane;
	private JTextField infoUsuario;
	private JPasswordField infoContrasena;


	/**
	 * Create the frame.
	 */
	public VentanaIniciarSesion() {
		setLocationRelativeTo(null);
		setTitle("DTune Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		try { //TODO Este try es la forma de poner un fondo a la ventana
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("back.jpg"));
            contentPane = new JPanel(new BorderLayout()) {
				private static final long serialVersionUID = -6618213232244732463L;
				@Override 
				public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, -150, 400, 300, null);
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
		
		JPanel panelIniciarSesion = new JPanel();
		panelIniciarSesion.setOpaque(false); //TODO Esto hace que los paneles que añades no tapen el fondo
		contentPane.add(panelIniciarSesion, BorderLayout.SOUTH);
		panelIniciarSesion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnInicioSesion = new JButton("Iniciar Sesi\u00F3n");
		panelIniciarSesion.add(btnInicioSesion);
		
		btnInicioSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String cont = String.valueOf(infoContrasena.getPassword());
				String usu = infoUsuario.getText();
				if(usu.strip().equals("") || cont.strip().equals("") ) {
					JOptionPane.showMessageDialog(null,"El nombre y/o la contrase�a esta vacio");
				}else {
					int resul = BaseDeDatos.comprobacionUsuario(usu, cont);
					if (resul == 1) {
						
						JOptionPane.showMessageDialog(null, "Usuario y contrase�a correctos");
						
						//Borrado de ventanas a mano
						Window [] ventanas = Window.getWindows();
						ventanas[ventanas.length-3].dispose();
						ventanas[ventanas.length-4].dispose();
						
						
						Usuario u = BaseDeDatos.esAdministrador2(usu);
						new VentanaPostLogging(u);
						if( ((Administrador)u).aniadirCancion())
							logger.log( Level.INFO, "El administrador: " + u.getNombre() + " ha iniciado sesion correctamente");
					}else if(resul == 2) {
						JOptionPane.showMessageDialog(null,"Contrase�a incorrecta");
					}else if(resul == 3) {
						JOptionPane.showMessageDialog(null,"Usuario no encontrado");
					}
				}	
		}
		});
		
		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		contentPane.add(panelBotones, BorderLayout.CENTER);
		panelBotones.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesUsuarios = new JPanel(new FlowLayout());
		panelBotonesUsuarios.setOpaque(false);
		panelBotones.add(panelBotonesUsuarios, BorderLayout.NORTH);
		
		JLabel lblUsuario = new JLabel("<html><font color=\"white\">Usuario</font></html>");
		panelBotonesUsuarios.add(lblUsuario);
		
		infoUsuario = new JTextField(12);
		panelBotonesUsuarios.add(infoUsuario);
		infoUsuario.setColumns(10);
		
		JPanel panelBotonesContrasena = new JPanel(new FlowLayout());
		panelBotonesContrasena.setOpaque(false);
		panelBotones.add(panelBotonesContrasena, BorderLayout.CENTER);
		
		JLabel lblContrasena = new JLabel("<html><font color=\"white\">Contrase\u00F1a</font></html>");
		panelBotonesContrasena.add(lblContrasena);
		
		infoContrasena = new JPasswordField(12);
		panelBotonesContrasena.add(infoContrasena);
			
		setSize(350,150);
		setVisible(true);
	} 	

}
