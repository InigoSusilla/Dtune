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

	@Override
	public String toString() {
		return "VentanaPrintCarrito [pagar=" + pagar + ", volver=" + volver + ", cancelar=" + cancelar + "]";
	}

	public String getPagar() {
		return pagar;
	}

	public void setPagar(String pagar) {
		this.pagar = pagar;
	}

	public String getVolver() {
		return volver;
	}

	public void setVolver(String volver) {
		this.volver = volver;
	}

	public String getCancelar() {
		return cancelar;
	}

	public void setCancelar(String cancelar) {
		this.cancelar = cancelar;
	}

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public VentanaPrintCarrito() {
		
		taResumen = new JTextArea();
		scrollAreaResumen = new JScrollPane(taResumen);
		
		getContentPane().add(scrollAreaResumen, BorderLayout.CENTER);
		//Eventos
		cargarCarritoEnTextArea();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel PanelPagar = new JPanel();
		contentPane.add(PanelPagar, BorderLayout.WEST);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setForeground(Color.GREEN);
		btnPagar.setBackground(Color.RED);
		taResumen.setText("");
		PanelPagar.add(btnPagar);
		generarFicheroFactura();
		
		JPanel PanelVolver = new JPanel();
		contentPane.add(PanelVolver, BorderLayout.EAST);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(Color.GREEN);
		PanelVolver.add(btnVolver);
		
		JPanel PanelCancelar = new JPanel();
		contentPane.add(PanelCancelar, BorderLayout.CENTER);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.GREEN);
		PanelCancelar.add(btnCancelar);
		
		btnPagar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaImprimir();
				System.out.println("aaa");
				
			}
			
		});
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaMain();
				System.out.println("aaa");
				
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
