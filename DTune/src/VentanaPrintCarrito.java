import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;

public class VentanaPrintCarrito extends JFrame {


	public String pagar;
	public String volver;
	public String cancelar;

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrintCarrito() {
		setLocationRelativeTo(null);
		

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel PanelPagar = new JPanel();
		contentPane.add(PanelPagar, BorderLayout.WEST);
		
		JButton btnPagar = new JButton("Pagar");
		PanelPagar.add(btnPagar);
	
		
		JPanel PanelVolver = new JPanel();
		contentPane.add(PanelVolver, BorderLayout.EAST);
		
		JButton btnVolver = new JButton("Volver");
		PanelVolver.add(btnVolver);
		
		JPanel PanelCancelar = new JPanel();
		contentPane.add(PanelCancelar, BorderLayout.CENTER);
		
		JButton btnCancelar = new JButton("Cancelar");
		PanelCancelar.add(btnCancelar);
		
		btnPagar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaImprimir();
				dispose();
				
			}
			
		});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMain.vaciarCarrito();
				dispose();
				}
		});
		
		setSize(300, 100);
		
	}


}
