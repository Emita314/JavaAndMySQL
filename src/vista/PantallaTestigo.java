package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.CCDTyEDAO;
import dao.TestigoDAO;
import modelo.CCDTyE;
import modelo.Testigo;
public class PantallaTestigo extends JPanel {
	private JTable tabla;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDescripcion;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PantallaTestigo() {
setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 181, 325, 152);
		add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("DNI");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Testimonio");
		
		tabla.setModel(modelo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaTestigoEliminar());
				
				marco.validate();
			}
		});
		btnEliminar.setBounds(372, 85, 89, 23);
		add(btnEliminar);
		
		
		txtDNI = new JTextField();
		txtDNI.setBounds(115, 51, 86, 20);
		add(txtDNI);
		txtDNI.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(115, 86, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(115, 117, 86, 20);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(120, 150, 86, 20);
		add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		DefaultTableModel mostrar = new DefaultTableModel();
		mostrar.addColumn("DNI");
		mostrar.addColumn("Nombre");
		mostrar.addColumn("Apellido");
		mostrar.addColumn("Testimonio");
		tabla.setModel(mostrar);

		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int DNI = Integer.valueOf(txtDNI.getText());
				String Nombre = txtNombre.getText();
				String Apellido = txtApellido.getText();
				String Testimonio = txtDescripcion.getText();
 
				Testigo testigo = new Testigo( DNI, Nombre, Apellido, Testimonio);
				TestigoDAO tesdao = new TestigoDAO();
				
				String mensaje = "";
				if(tesdao.guardar(testigo)) {
					mensaje = "La alta fue exitosa.";
				} else {
					mensaje = "Algo fallo. Intenta nuevamente";
				}
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				JOptionPane.showMessageDialog(marco, mensaje);
				
			}
		});
		btnNewButton_3.setBounds(372, 50, 89, 23);
		add(btnNewButton_3);

		
		JTextArea dNI = new JTextArea();
		dNI.setText("DNI\r\n");
		dNI.setBounds(21, 49, 65, 22);
		add(dNI);
		
		JTextArea Nombre = new JTextArea();
		Nombre.setText("Nombre");
		Nombre.setBounds(21, 82, 77, 22);
		add(Nombre);
		
		JTextArea Apellido = new JTextArea();
		Apellido.setText("Apellido");
		Apellido.setBounds(21, 115, 77, 22);
		add(Apellido);
		
		JTextArea Testimonio = new JTextArea();
		Testimonio.setText("Testimonio\r\n");
		Testimonio.setBounds(21, 148, 89, 22);
		add(Testimonio);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaPrincipal());
				
				marco.validate();
			}
		});
		btnNewButton.setBounds(10, 344, 89, 23);
		add(btnNewButton);
		
		JButton mostrarDatos = new JButton("Mostrar");
		mostrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Testigo> testigos = new ArrayList<Testigo>();
				TestigoDAO tdao = new TestigoDAO();
				testigos = tdao.traerTodos();
				for(Testigo cen : testigos) {
					mostrar.addRow(new Object[] {cen.getDNItestigo(),cen.getTes_Nombre(),cen.getTes_Apellido(),cen.getTestimonio()});
				}
			}
		});
		ArrayList<Testigo> testigos = new ArrayList<Testigo>();
		TestigoDAO tdao = new TestigoDAO();
		testigos = tdao.traerTodos();
		for(Testigo cen : testigos) {
			mostrar.addRow(new Object[] {cen.getDNItestigo(),cen.getTes_Nombre(),cen.getTes_Apellido(),cen.getTestimonio()});
		}
		
		mostrarDatos.setBounds(372, 178, 89, 23);
		add(mostrarDatos);
		
		

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int fila= tabla.getSelectedRow();
	
				String mensaje = "";
				if(e.getSource() == btnEditar) {
					if(fila==-1) {
					  mensaje = "Selecione una fila";
					  } else{
							int Dni=Integer.parseInt((String)mostrar.getValueAt(fila,0).toString());
							String nombre=(String)mostrar.getValueAt(fila , 1);
							String Apellido=(String)mostrar.getValueAt(fila , 2);
							String Testimonio=(String)mostrar.getValueAt(fila ,3);
							
							txtNombre.setText(nombre);
							txtDNI.setText(""+Dni);
							txtApellido.setText(Apellido);
							txtDescripcion.setText(Testimonio);
							}
					
							};
			}
		});
		
		
		btnEditar.setBounds(372, 212, 89, 23);
		add(btnEditar);
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Dni=Integer.parseInt(txtDNI.getText());
				String Nombre=txtNombre.getText();
				String Apellido=txtApellido.getText();
				String Testimonio=txtDescripcion.getText();
				
				Testigo testi = new Testigo(Dni,Nombre,Apellido,Testimonio);
				TestigoDAO tesdao = new TestigoDAO();
				String mensaje = "";
				if(tesdao.actualizar(testi,Dni)) {
					mensaje = "La alta fue exitosa.";
					}else {
						mensaje = "Algo fall�. Intenta nuevamente";
					
					}
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					JOptionPane.showMessageDialog(marco, mensaje);
				
			}
		});
		btnActualizar.setBounds(372, 149, 89, 23);
		add(btnActualizar);
		JButton btnbuscar = new JButton("Buscar");
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new BuscarTestigo());
				
				marco.validate();
			}
		});
	btnbuscar.setBounds(372, 119, 89, 23);
	add(btnbuscar);
	
		JTextArea txtrTestigo = new JTextArea();
		txtrTestigo.setText("TESTIGO");
		txtrTestigo.setBounds(241, 11, 65, 22);
		add(txtrTestigo);

	}

}

