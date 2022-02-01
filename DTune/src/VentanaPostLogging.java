import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPostLogging extends JFrame{
	private static final long serialVersionUID = -2948809651881055074L;
	private JPanel contentPane;
	public VentanaPostLogging(Usuario u) {
		setLocationRelativeTo(null);
		setTitle("DTune Crear cuenta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 100);
		try { //TODO Este try es la forma de poner un fondo a la ventana
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("back.jpg"));
            contentPane = new JPanel(new BorderLayout()) {
				private static final long serialVersionUID = -6618213232244732463L;
				@Override 
				public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, -139, 400, 300, null);
                }
            };
            setVisible(true);
        } catch (IOException e) {
        	contentPane = new JPanel();
            e.printStackTrace();
        }
		setContentPane(contentPane);
		JPanel panelBotones = new JPanel(new FlowLayout());
		JButton botonCanciones = new JButton("Canciones");
		JButton botonEntradas = new JButton("Entradas");
		panelBotones.add(botonEntradas);
		panelBotones.add(botonCanciones);
		panelBotones.setOpaque(false);
		
		botonCanciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaMain(u);
				if(u instanceof Administrador) {
					if( ((Administrador)u).aniadirCancion()) {
						VentanaMain.btnAnadirCancion.setVisible(true);
						VentanaMain.btnEstadisticas.setVisible(true);
					}
				}
				dispose();
				
			}
			
		});
		botonEntradas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaEntradas(u);
				dispose();
			}
			
		});
		JPanel panelCabecera = new JPanel(new FlowLayout());
		JLabel labelCabecera = new JLabel("<html><font color=\"white\">Seleccione la tienda a la que entrar</font></html>");
		panelCabecera.add(labelCabecera);
		panelCabecera.setOpaque(false);
		contentPane.add(panelCabecera, BorderLayout.NORTH);
		contentPane.add(panelBotones, BorderLayout.CENTER);
	}
}
