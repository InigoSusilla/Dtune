import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaPreLogging extends JFrame {
	
	public String iniciarSesion;
	public String crearCuenta;

	@Override
	public String toString() {
		return "VentanaPreLogging [iniciarSesion=" + iniciarSesion + ", crearCuenta=" + crearCuenta + "]";
	}

	public String getIniciarSesion() {
		return iniciarSesion;
	}

	public void setIniciarSesion(String iniciarSesion) {
		this.iniciarSesion = iniciarSesion;
	}

	public String getCrearCuenta() {
		return crearCuenta;
	}

	public void setCrearCuenta(String crearCuenta) {
		this.crearCuenta = crearCuenta;
	}

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public VentanaPreLogging() {
		setTitle("DTune Logging");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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
				System.out.println("bbb");
				
			}
		});
		
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
		contentPane.add(btnCrearCuenta, BorderLayout.SOUTH);
		setSize(400,120);
		
		btnCrearCuenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCuenta();
				System.out.println("aaa");
				
			}
		});

	}

}
