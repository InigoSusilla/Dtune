import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JLabel lblNewLabel;
	public VentanaCrearCancion() {
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(700,300);
		
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
		
		lblNewLabel = new JLabel("Nombre, Autor, Precio(X.X), vinillo, Fecha(dd/mm/yyyy),genero,ruta,duracion(X.X)");
		panelNorth.add(lblNewLabel);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnCrear = new JButton("Crear");
		panelSouth.add(btnCrear);
		
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		
		btnCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			String nombre = textFieldNombre.getText();
			String autor = textFieldAutor.getText();
			Double precio = Double.parseDouble(textFieldPrecio.getText());
			String fechaRAW = textFieldFecha.getText();
			String genero = textFieldGenero.getText();
			String rutaRAW = textFieldRuta.getText();
			Double duracion = Double.parseDouble(textFieldDuracion.getText());
			boolean esVinillo = rdbtnVinilo.isSelected();
			
			try {
				Date fecha = sdf.parse(fechaRAW);
				String ruta = "demosCanciones/Demo"+ rutaRAW + ".mp3";
				Cancion c = new Cancion(nombre, autor, precio, esVinillo, fecha, genero, ruta, duracion);
				BaseDeDatos.insertarCancion(c);
				
				JOptionPane.showMessageDialog(null, "Creada con exito");
				VentanaMain.cargarGenerosDeLaBBDD();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
		
			
			
		}
		});
		
	}
	
}
