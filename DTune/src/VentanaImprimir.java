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
	
	
	@Override
	public String toString() {
		return "VentanaImprimir [imprimir=" + imprimir + "]";
	}


	public String getImprimir() {
		return imprimir;
	}


	public void setImprimir(String imprimir) {
		this.imprimir = imprimir;
	}


	public VentanaImprimir() {
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setHorizontalAlignment(SwingConstants.LEFT);
		setSize(100,100);
		getContentPane().add(btnImprimir, BorderLayout.SOUTH);
		setVisible(true);
	}
	
}
