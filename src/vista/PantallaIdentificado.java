package vista;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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

import dao.DetenidoDAO;
import dao.TestigoDAO;
import modelo.Identificado;
import modelo.Testigo;

public class PantallaIdentificado extends JPanel {

	/**
	 * Create the panel.
	 */
	private JTable tabla;
	private JTable tabla1;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtLugarSecuestro;
	private JTextField txtFechaUltVisto;
	private JTextField txtBiografia;
	private JTextField txtAudiovisual;
	private JTextField txtTestigo;
	private JButton btnActualizar;
	private JTextField textDniTestigo;
	
	/**
	 * Create the panel.
	 */
	public PantallaIdentificado() {
		setLayout(null);
		
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(186, 74, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(186, 43, 86, 20);
		add(txtDNI);
		txtDNI.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(186, 105, 86, 20);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		txtLugarSecuestro = new JTextField();
		txtLugarSecuestro.setBounds(186, 137, 86, 20);
		add(txtLugarSecuestro);
		txtLugarSecuestro.setColumns(10);
		
		txtFechaUltVisto = new JTextField();
		txtFechaUltVisto.setBounds(186, 170, 86, 20);
		add(txtFechaUltVisto);
		txtFechaUltVisto.setColumns(10);

		txtBiografia = new JTextField();
		txtBiografia.setBounds(186, 202, 86, 20);
		add(txtBiografia);
		txtBiografia.setColumns(10);
		
		txtAudiovisual = new JTextField();
		txtAudiovisual.setBounds(186, 236, 86, 30);
		add(txtAudiovisual);
		txtAudiovisual.setColumns(10);
		
		textDniTestigo = new JTextField();
		textDniTestigo.setBounds(186, 317, 86, 20);
		add(textDniTestigo);
		textDniTestigo.setColumns(10);
		
		JTextArea txtrDniTestigo = new JTextArea();
		txtrDniTestigo.setText("Dni Testigo");
		txtrDniTestigo.setBounds(20, 315, 109, 22);
		add(txtrDniTestigo);
		
		JTextArea txtrFechaultvisto = new JTextArea();
		txtrFechaultvisto.setText("FechaUltVisto");
		txtrFechaultvisto.setBounds(20, 168, 109, 22);
		add(txtrFechaultvisto);
		
		JTextArea txtrBiografia = new JTextArea();
		txtrBiografia.setText("Biografia");
		txtrBiografia.setBounds(20, 201, 77, 22);
		add(txtrBiografia);
		
		JTextArea txtrMaterialAudiovisual = new JTextArea();
		txtrMaterialAudiovisual.setText("Material \r\nAudiovisual");
		txtrMaterialAudiovisual.setBounds(20, 234, 109, 46);
		add(txtrMaterialAudiovisual);
		
		JTextArea Nombre = new JTextArea();
		Nombre.setText("DNI");
		Nombre.setBounds(20, 42, 65, 22);
		add(Nombre);
		
		JTextArea Ubicacion = new JTextArea();
		Ubicacion.setText("Nombre\r\n");
		Ubicacion.setBounds(20, 73, 77, 22);
		add(Ubicacion);
		
		JTextArea FechaApertura = new JTextArea();
		FechaApertura.setText("Apellido\r\n");
		FechaApertura.setBounds(20, 106, 109, 22);
		add(FechaApertura);
		
		JTextArea FechaCierre = new JTextArea();
		FechaCierre.setText("LugarSecuestro");
		FechaCierre.setBounds(20, 135, 117, 22);
		add(FechaCierre);
		
		
		
		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Nombre = txtNombre.getText();
				String Apellido = txtApellido.getText();
				int DNI = Integer.valueOf(txtDNI.getText());
				int LugarSecuestro = Integer.valueOf(txtLugarSecuestro.getText());
				String UltfechaVisto = txtFechaUltVisto.getText();
				
				DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
				
				LocalDate ultfechavisto = LocalDate.parse(UltfechaVisto, format);
				String Biografia = txtBiografia.getText();
				String Audiovisual = txtAudiovisual.getText();
				int DNIt = Integer.valueOf(textDniTestigo.getText());
				
				Identificado ide = new Identificado(Nombre, Apellido, DNI, LugarSecuestro, ultfechavisto, Biografia, Audiovisual,DNIt);

				DetenidoDAO dDao = new DetenidoDAO();
				String mensaje = "";
				if(dDao.guardar(ide)) {
					mensaje = "La alta fue exitosa.";
				} else {
					mensaje = "Algo fall�. Intenta nuevamente";
				}
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				JOptionPane.showMessageDialog(marco, mensaje);
			}
		});
		btnNewButton_3.setBounds(336, 43, 89, 23);
		add(btnNewButton_3);
		
		
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaPrincipal());
				
				marco.validate();
			}
		});
		btnNewButton.setBounds(10, 550, 89, 23);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 371, 525, 165);
		add(scrollPane);
		
		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("DNIdetenido");
		modelo.addColumn("LugarSecuestro");
		modelo.addColumn("UltFechaVisto");
		modelo.addColumn("Biografia");
		modelo.addColumn("Audiovisual");
		modelo.addColumn("DNI Testigo");
		
		tabla.setModel(modelo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(330, 195, 291, 165);
		add(scrollPane_1);
		
		tabla1 = new JTable();
		scrollPane_1.setViewportView(tabla1);
		DefaultTableModel mostrar = new DefaultTableModel();
		mostrar.addColumn("DNI");
		mostrar.addColumn("Nombre");
		mostrar.addColumn("Apellido");
		mostrar.addColumn("Testimonio");
		tabla1.setModel(mostrar);
		ArrayList<Testigo> testigos = new ArrayList<Testigo>();
		TestigoDAO tdao = new TestigoDAO();
		testigos = tdao.traerTodos();
		for(Testigo cen : testigos) {
			mostrar.addRow(new Object[] {cen.getDNItestigo(),cen.getTes_Nombre(),cen.getTes_Apellido(),cen.getTestimonio()});
		}
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaIdentificadoEliminar());
				
				marco.validate();
			}
		});
		btnEliminar.setBounds(336, 73, 89, 23);
		add(btnEliminar);
		
		
		
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int fila= tabla.getSelectedRow();
	
				String mensaje = "";
				if(e.getSource() == btnEditar) {
					if(fila==-1) {
					  mensaje = "Selecione una fila";
					  } else{
							String nombre=(String)modelo.getValueAt(fila , 0);
							String Apellido=(String)modelo.getValueAt(fila , 1);
							int Dni=Integer.parseInt((String)modelo.getValueAt(fila,2).toString());
							int LugarSecuestro=Integer.parseInt((String)modelo.getValueAt(fila,3).toString());
							LocalDate FechaUltVisto = (LocalDate)modelo.getValueAt(fila ,4);
							String biografia=(String)modelo.getValueAt(fila , 5);
							String material=(String)modelo.getValueAt(fila , 6);
							int Dnit=Integer.parseInt((String)modelo.getValueAt(fila,7).toString());
							txtNombre.setText(nombre);
							txtApellido.setText(Apellido);
							txtDNI.setText(""+Dni);
							txtLugarSecuestro.setText(""+LugarSecuestro);
							txtFechaUltVisto.setText(""+FechaUltVisto);
							txtBiografia.setText(biografia);
							txtAudiovisual.setText(material);
							textDniTestigo.setText(""+Dnit);
							}
					
							};
			}
		});
		
		
		btnEditar.setBounds(545, 402, 89, 23);
		add(btnEditar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Dni=Integer.parseInt(txtDNI.getText());
				String Nombre=txtNombre.getText();
				String Apellido=txtApellido.getText();
				int LugarSecuestro = Integer.valueOf(txtLugarSecuestro.getText());
				String UltfechaVisto = txtFechaUltVisto.getText();
				
				DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
				
				LocalDate ultfechavisto = LocalDate.parse(UltfechaVisto, format);
				String Biografia=txtBiografia.getText();
				String Audiovisual=txtAudiovisual.getText();
				int DNIt = Integer.valueOf(textDniTestigo.getText());
				Testigo testigo = null;
				Identificado ide = new Identificado(Nombre, Apellido, Dni, LugarSecuestro, ultfechavisto, Biografia, Audiovisual,DNIt);
				DetenidoDAO detdao = new DetenidoDAO();
				String mensaje = "";
				if(detdao.actualizar(ide,Dni)) {
					mensaje = "La alta fue exitosa.";
					}else {
						mensaje = "Algo fall�. Intenta nuevamente";
					
					}
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					JOptionPane.showMessageDialog(marco, mensaje);
				
			}
		});
		btnActualizar.setBounds(545, 436, 89, 23);
		add(btnActualizar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new BuscarDetenidoIDe());
				
				marco.validate();
			}
		});
		btnBuscar.setBounds(336, 107, 89, 23);
		add(btnBuscar);
		
		JTextArea txtrTablaDeTestigos = new JTextArea();
		txtrTablaDeTestigos.setText("Tabla de Testigos disponibles");
		txtrTablaDeTestigos.setBounds(336, 168, 285, 22);
		add(txtrTablaDeTestigos);
		
		JButton btnMostrartodo = new JButton("Mostrar");
		btnMostrartodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Identificado> detenidoide = new ArrayList<Identificado>();
				DetenidoDAO ddao = new DetenidoDAO();
				detenidoide = ddao.traerTodos();
				for(Identificado ide : detenidoide) {
					modelo.addRow(new Object[] {ide.getDet_Nombre(),ide.getDet_Apellido(),ide.getDNIdetenido(),ide.getLugarSecuestro(),ide.getUltFechaVisto(),ide.getBiografia(),ide.getMaterialAudivisual(),ide.getDniTes()});
				}
			}
		});
		
		btnMostrartodo.setBounds(336, 136, 89, 23);
		add(btnMostrartodo);
		
	
		
		
		
		
	}
}