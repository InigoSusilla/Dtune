import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VentanaImprimir extends JFrame{
	
	public String imprimir;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private JTextArea taResumen;

	public VentanaImprimir() {
		setLocationRelativeTo(null);
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		setSize(150,150);
		getContentPane().add(btnImprimir, BorderLayout.SOUTH);
		setVisible(true);
		
		btnImprimir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cargarCarritoEnTextArea();
				generarFicheroFactura();
				
			}
			
			
		});
	}
	private void cargarCarritoEnTextArea() {

		Date d = new Date(System.currentTimeMillis());
		String texto = ""
				+ "Factura de la compra del dia: " + sdf.format(d) + "\n";
		double total = 0;
		ArrayList<Cancion> listaCanciones = VentanaMain.obtenerCarrito();
		for(Cancion c: listaCanciones) {
			texto = texto + "	" + c + "\n";
			total = total + c.getPrecio();
		}
		texto = texto + "TOTAL: "+total+" €";
		taResumen.setText(texto);
	}


	private void generarFicheroFactura() {
		Date d = new Date(System.currentTimeMillis());
		String nomfich = VentanaIniciarSesion.getUsuario().getNombre() +" "+sdf.format(d) +".txt";
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
