package vista;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.DetenidoDAO;
import modelo.Identificado;

public class BuscarDetenidoIDe extends JPanel {
	private JTable tabla;
	private static final long serialVersionUID = 1L;
	private JTextField texCampoBuscar;

	/**
	 * Create the panel.
	 */
	public BuscarDetenidoIDe() {
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 181, 435, 152);
		add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);

		DefaultTableModel mostrar = new DefaultTableModel();
		mostrar.addColumn("Nombre");
		mostrar.addColumn("Apellido");
		mostrar.addColumn("DNIdetenido");
		mostrar.addColumn("LugarSecuestro");
		mostrar.addColumn("UltFechaVisto");
		mostrar.addColumn("Biografia");
		mostrar.addColumn("Audiovisual");
		mostrar.addColumn("DNI Testigo");
		
		tabla.setModel(mostrar);
		
		texCampoBuscar = new JTextField();
		texCampoBuscar.setToolTipText("");
		texCampoBuscar.setFont(new Font("Rockwell", Font.ITALIC, 11));
		texCampoBuscar.setBounds(21, 109, 299, 20);
		add(texCampoBuscar);
		texCampoBuscar.setColumns(10);
		
		JTextPane txttitulo = new JTextPane();
		txttitulo.setFont(new Font("SimSun", Font.BOLD, 18));
		txttitulo.setText("Buscar");
		txttitulo.setBounds(189, 11, 104, 34);
		add(txttitulo);
		
		JTextPane txtsubtitulo = new JTextPane();
		txtsubtitulo.setText("Ingrese el nombre del CCDTyE:");
		txtsubtitulo.setBounds(21, 81, 165, 20);
		add(txtsubtitulo);
		
		JButton btnvolver = new JButton("Volver");
		btnvolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaIdentificado());
				marco.validate();
			}});
		btnvolver.setBounds(10, 350, 89, 23);
		add(btnvolver);
		
		JButton btnbuscar = new JButton("Buscar");
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int DNI = Integer.valueOf(texCampoBuscar.getText());
				
				
				DetenidoDAO tdao = new DetenidoDAO();
				Identificado centro = tdao.traersolouno(DNI);
			
					mostrar.addRow(new Object[] {centro.getDet_Nombre(),centro.getDet_Apellido(),centro.getDNIdetenido(),centro.getLugarSecuestro(),centro.getUltFechaVisto(),centro.getBiografia(),centro.getMaterialAudivisual(),centro.getDniTes()});
			}
		});
		btnbuscar.setBounds(349, 108, 89, 23);
		add(btnbuscar);

	}

}
