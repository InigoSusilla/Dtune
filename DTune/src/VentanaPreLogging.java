import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaPreLogging extends JFrame {
	
	private static final long serialVersionUID = 2901945423384895L;
	public String iniciarSesion;
	public String crearCuenta;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public VentanaPreLogging() {
		setTitle("DTune Logging");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
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
		
		JLabel lblNewLabel = new JLabel();
		contentPane.add(lblNewLabel);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		contentPane.add(btnIniciarSesion, BorderLayout.NORTH);
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaIniciarSesion();
				
			}
		});
		
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
		contentPane.add(btnCrearCuenta, BorderLayout.SOUTH);
		setSize(400,120);
		
		btnCrearCuenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCuenta();
				
			}
		});

	}
	


}
