import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VentanaPrintCarrito extends JFrame {

	private static final long serialVersionUID = -8705514754898251314L;
	public String pagar;
	public String volver;
	public String cancelar;
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy kk.mm.ss");
	private VentanaTienda parent;
	private JTextArea taResumen;
	private JScrollPane scrollAreaResumen;
	
	private JPanel contentPane;
	
	 //Create the frame.
	
	public VentanaPrintCarrito(VentanaTienda parent) {
		setLocationRelativeTo(null);
		

		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 200);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		taResumen = new JTextArea();
		taResumen.setEditable(false);
		scrollAreaResumen = new JScrollPane(taResumen);
		getContentPane().add(scrollAreaResumen, BorderLayout.CENTER);
		taResumen.setText("");
		cargarCarritoEnTextArea();
		
		//setContentPane(contentPane);
		
		JPanel PanelPagar = new JPanel(new GridLayout(3,1));
		
		JButton btnPagar = new JButton("Pagar");
		PanelPagar.add(btnPagar);
		
		JButton btnVolver = new JButton("Volver");
		PanelPagar.add(btnVolver);
		
		JButton btnCancelar = new JButton("Cancelar");
		PanelPagar.add(btnCancelar);
		
		
		btnPagar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaImprimir(parent);
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
				parent.vaciarCarrito();
				dispose();
				}
		});
		
		getContentPane().add(PanelPagar, BorderLayout.WEST);
		setVisible(true);
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


}
