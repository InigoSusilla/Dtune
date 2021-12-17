import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VentanaImprimir extends JFrame{
	
	public String imprimir;


	public VentanaImprimir() {
		setLocationRelativeTo(null);
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		setSize(150,150);
		getContentPane().add(btnImprimir, BorderLayout.SOUTH);
		setVisible(true);
	}
	
}
