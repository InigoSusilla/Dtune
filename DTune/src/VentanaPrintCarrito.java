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
	private JTextArea taResumen;
	private JScrollPane scrollAreaResumen;
	public String pagar;
	public String volver;
	public String cancelar;

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public VentanaPrintCarrito() {
		setLocationRelativeTo(null);
		taResumen = new JTextArea();
		scrollAreaResumen = new JScrollPane(taResumen);
		
		getContentPane().add(scrollAreaResumen, BorderLayout.CENTER);
		//Eventos
		cargarCarritoEnTextArea();
		
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
		taResumen.setText("");
		PanelPagar.add(btnPagar);
		generarFicheroFactura();
		
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
				System.out.println("aaa");
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
	private void cargarCarritoEnTextArea() {
		String texto = "";
		double total = 0;
		ArrayList<Cancion> listaCanciones = new ArrayList<>();
		for(Cancion c: listaCanciones) {
			texto = texto + c + "\n";
			total = total + c.getPrecio();
		}
		texto = texto + "TOTAL: "+total+" €";
		taResumen.setText(texto);
	}


	private void generarFicheroFactura() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new Date(System.currentTimeMillis());
		String nomfich = Usuario.getNombre()+" "+sdf.format(d) +".txt";
		PrintWriter pw = null;
	
		try {
			pw = new PrintWriter(nomfich);
			pw.println(taResumen.getText());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pw!=null) {
				pw.flush();
				pw.close();
		}
	}
}

}
