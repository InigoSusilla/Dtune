import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class VentanaEstadisticas extends JFrame {

	private JPanel contentPane;
	private TreeMap<String, ArrayList<Cancion>> tmGeneros;
	private JTextArea area;
	private int pos;
	private boolean parar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEstadisticas frame = new VentanaEstadisticas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaEstadisticas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		cargarMapa();
		area = new JTextArea(convertirMapaATexto());
		contentPane.add(new JScrollPane(area), BorderLayout.CENTER);
		//metodo();
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
			System.out.println("No hay ninguna canción con una duración de menos de un segundo de diferencia respecto a la media");
		else
			System.out.println("La canción de la posición "+pos+"tiene una diferencia de menos de 1 segundo respecto a la media");
	}
}
