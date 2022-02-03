import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaEntradas extends VentanaTienda{
	private static final long serialVersionUID = -7365630505600894911L;
	private static ArrayList<Entrada> listaEntradas = BaseDeDatos.obtenerEntradas();
	public VentanaEntradas(Usuario u) {
		super(u);
		setLocationRelativeTo(null);
		ModelEntradas model = new ModelEntradas();
		JTable tablaEntradas = new JTable(model) {
			private static final long serialVersionUID = -417297738736674095L;

			public boolean isCellEditable(int row, int column) {                
                return false;               
			}
		};
		for(Entrada e : listaEntradas)
			model.addValue(e);
		tablaEntradas.setShowGrid(true);
		JScrollPane scroll = new JScrollPane(tablaEntradas);
		getContentPane().add(scroll, BorderLayout.CENTER);
		JPanel panelIteraction = new JPanel(new GridLayout(3,1));
		JButton addCarrito = new JButton("Añadir al carrito");
		JButton delete = new JButton("Eliminar del carrito");
		JButton end = new JButton("Terminar compra");
		JButton back = new JButton("Atras");
		JTextField filtro = new JTextField("Artista", 32);
		JButton filtrar = new JButton("Filtrar");
		JScrollPane comprasPane = new JScrollPane(listaCarrito);
		comprasPane.setBorder(BorderFactory.createTitledBorder("Carrito"));
		addCarrito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modeloCarrito.addElement(model.getValueAt(tablaEntradas.getSelectedRow()).comprar(JOptionPane.showInputDialog("Introduzca el nombre del comprador")));
			}
			
		});
		tablaEntradas.addMouseListener(new MouseAdapter() {
			Entrada selected = null;
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tablaEntradas.getSelectedRow() != -1)
				if(selected == null || !selected.equals(model.getValueAt(tablaEntradas.getSelectedRow())))selected = model.getValueAt(tablaEntradas.getSelectedRow());
				else {
					modeloCarrito.addElement(selected.comprar(JOptionPane.showInputDialog("Introduzca el nombre del comprador")));
					selected = null;
				}
			}
		});
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listaCarrito.getSelectedIndex() != -1)
					modeloCarrito.removeElement(listaCarrito.getSelectedValue());	
			}
			
		});
		VentanaEntradas t = this;
		end.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaPrintCarrito(t);
			}
			
		});
		filtrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.clear();
				for(Entrada en : listaEntradas) {
					if(en.artista.contains(filtro.getText()))
						model.addValue(en);
				}
			}
			
		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaPostLogging(user);
				dispose();
			}
			
		});
		JPanel panelFiltro = new JPanel(new FlowLayout());
		panelFiltro.add(filtro);
		panelFiltro.add(filtrar);
		
		JPanel panelBotones = new JPanel(new GridLayout(2,1));
		JPanel panelBotonesSup = new JPanel(new FlowLayout());
		JPanel panelBotonesInf = new JPanel(new FlowLayout());
		panelBotonesSup.add(addCarrito);
		panelBotonesSup.add(delete);
		panelBotonesInf.add(end);
		panelBotonesInf.add(back);
		
		panelBotones.add(panelBotonesSup);
		panelBotones.add(panelBotonesInf);
		
		panelIteraction.add(panelFiltro);
		panelIteraction.add(panelBotones);
		panelIteraction.add(comprasPane);
		getContentPane().add(panelIteraction, BorderLayout.EAST);
		setVisible(true);
	}

}
class ModelEntradas extends DefaultTableModel{
	private static final long serialVersionUID = -2575496514011702954L;
	public ModelEntradas() {
		super(null, new String[] {"Artista", "Ubicacion", "Fecha", "Precio"});
	}
	public void addValue(Entrada e) {
		addRow(new Object[] {e.artista, e.ubicacion, e.fecha, e.precio});
	}
	public Entrada getValueAt(int row) {
		return new Entrada((String)getValueAt(row, 0),(String)getValueAt(row, 1),((Date)getValueAt(row, 2)).getTime(),(double)getValueAt(row, 3));
	}
	public void clear() {
		while(getRowCount()>0)removeRow(0);
	}
}