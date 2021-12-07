import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class VentanaCrearCancion extends JFrame{
	private JTextField textFieldNombre;
	private JTextField textFieldAutor;
	private JTextField textFieldPrecio;
	private JTextField textFieldFecha;
	private JTextField textFieldGenero;
	private JTextField textFieldRuta;
	private JTextField textFieldDuracion;
	public VentanaCrearCancion() {
		
		JPanel panelNorth = new JPanel();
		getContentPane().add(panelNorth, BorderLayout.CENTER);
		
		textFieldNombre = new JTextField();
		panelNorth.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldAutor = new JTextField();
		panelNorth.add(textFieldAutor);
		textFieldAutor.setColumns(10);
		
		textFieldPrecio = new JTextField();
		panelNorth.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JRadioButton rdbtnVinilo = new JRadioButton("Vinilo");
		panelNorth.add(rdbtnVinilo);
		
		textFieldFecha = new JTextField();
		panelNorth.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		textFieldGenero = new JTextField();
		panelNorth.add(textFieldGenero);
		textFieldGenero.setColumns(10);
		
		textFieldRuta = new JTextField();
		panelNorth.add(textFieldRuta);
		textFieldRuta.setColumns(10);
		
		textFieldDuracion = new JTextField();
		panelNorth.add(textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnCrear = new JButton("Crear");
		panelSouth.add(btnCrear);
	}
	
}
