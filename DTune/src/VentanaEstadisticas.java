import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VentanaEstadisticas extends JFrame {

	private static final long serialVersionUID = 2134933947729434274L;
	private JPanel contentPane;
	private TreeMap<String, ArrayList<Cancion>> tmGeneros;
	private JTextArea area;
	private int pos;
	private boolean parar;


	/**
	 * Create the frame.
	 */
	public VentanaEstadisticas() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		cargarMapa();
		area = new JTextArea(convertirMapaATexto());
		contentPane.add(new JScrollPane(area), BorderLayout.CENTER);
		
		JPanel panelBoton = new JPanel();
		contentPane.add(panelBoton, BorderLayout.SOUTH);
		
		JButton btnEstadis = new JButton("Comprobar duracion media");
		panelBoton.add(btnEstadis);
		
		btnEstadis.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String respuesta = JOptionPane.showInputDialog("Este metodo recursivo multiple no lo he podido ralizar ya que genera un bucle infinito, �Quieres continuar?"
						+ "(y/n)");
				if(respuesta.equals("y")) {
					metodo();
				}
			}	
		});
	}
	
	private void cargarMapa() {
		tmGeneros = new TreeMap<>();
		ArrayList<Cancion> aCanciones = BaseDeDatos.obtenerCanciones();
		for(Cancion c: aCanciones) {
			if(!tmGeneros.containsKey(c.getGenero()))
				tmGeneros.put(c.getGenero(), new ArrayList<>());
			tmGeneros.get(c.getGenero()).add(c);
		}
	}
	
	private String convertirMapaATexto() {
		String texto = "";
		for(String genero: tmGeneros.keySet()) {
			texto = texto + genero + ": " + tmGeneros.get(genero).size() + " canciones\n";
		}
		return texto;
	}

	private void recorrer(ArrayList<Cancion> aCanciones, int i, double media) {
		if(i>=0 && i<aCanciones.size()) {
			System.out.println("Posicion "+i+" duracion "+aCanciones.get(i).getDuracion());
			if(Math.abs(aCanciones.get(i).getDuracion()-media)<0.5) {
				pos = i;
			}else {
				recorrer(aCanciones, i+1, media);
				recorrer(aCanciones, i-1, media);
			}
		}else {
			pos = -1;
		}
	}

	private void metodo() {
		ArrayList<Cancion> aCanciones = BaseDeDatos.obtenerCanciones();
		Collections.sort(aCanciones);
		double media = 0;
		for(Cancion c : aCanciones)
			media = media + c.getDuracion();
		if(aCanciones.size()>0)
			media =  media / aCanciones.size();
		System.out.println("Media = "+media);
		recorrer(aCanciones,aCanciones.size()/2,media);
		if(pos==-1)
			System.out.println("No hay ninguna canci�n con una duraci�n de menos de un segundo de diferencia respecto a la media");
		else
			System.out.println("La canci�n de la posici�n "+pos+"tiene una diferencia de menos de 1 segundo respecto a la media");
	}
}
