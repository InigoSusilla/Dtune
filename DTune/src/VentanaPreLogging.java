import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaPreLogging extends JFrame {

	private JPanel contentPane;
	int prueba;
	int prueba2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPreLogging frame = new VentanaPreLogging();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPreLogging() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		contentPane.add(lblNewLabel);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		contentPane.add(btnIniciarSesion, BorderLayout.NORTH);
		
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
		contentPane.add(btnCrearCuenta, BorderLayout.SOUTH);
	}

}
