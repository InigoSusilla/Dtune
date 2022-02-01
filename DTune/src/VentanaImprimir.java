import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

public class VentanaImprimir extends JFrame{
	private static final long serialVersionUID = 4661571094229970726L;

	private JScrollPane scrollAreaResumen;
	
	public String imprimir;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy kk.mm.ss");
	private JTextArea taResumen;
	private VentanaTienda parent; //TODO esta es la clase de la que viene esta ventana

	public VentanaImprimir(VentanaTienda parent) {
		setLocationRelativeTo(null);
		
		
		this.parent = parent;
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		setSize(500,300);
		getContentPane().add(btnImprimir, BorderLayout.SOUTH);
		taResumen = new JTextArea();
		taResumen.setEditable(false);
		scrollAreaResumen = new JScrollPane(taResumen);
		getContentPane().add(scrollAreaResumen, BorderLayout.CENTER);
		taResumen.setText("");
		cargarCarritoEnTextArea();
		
		setVisible(true);
		
		btnImprimir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generarFicheroFactura();
			}
		});
	}
	private void cargarCarritoEnTextArea() {

		Date d = new Date(System.currentTimeMillis());
		String texto = ""
				+ "Factura de la compra del dia: " + sdf.format(d) + "\n";
		double total = 0;
		ArrayList<Comprable> listaCanciones = parent.obtenerCarrito();
		for(Comprable c: listaCanciones) {
			texto = texto + "	" + c.toString().replace("\n", "") + "\n";
			total = total + c.getPrecio();
		}
		texto = texto + "TOTAL: "+total+" $";
		taResumen.setText(texto);
	}


	private void generarFicheroFactura() {
		Date d = new Date(System.currentTimeMillis());
		String nomfich = parent.getUsuario().getNombre() +" "+sdf.format(d) +".txt";
		PrintWriter pw = null;
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Guardar factura en");
		jfc.setCurrentDirectory(new File("."));
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setAcceptAllFileFilterUsed(false);
		String dir;
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
		      dir = jfc.getSelectedFile().toString();
		}else {
			dir = "facturas";
		}
	
		try {
			System.out.println(dir + "/" + nomfich);
			pw = new PrintWriter(dir + "/" + nomfich);
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
