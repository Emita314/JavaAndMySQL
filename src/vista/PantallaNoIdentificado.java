package vista;

import java.awt.Component;
import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import dao.CCDTyEDAO;
import dao.DetenidoNoIdeDAO;
import dao.TestigoDAO;
import modelo.CCDTyE;
import modelo.NoIdentificado;
import modelo.Testigo;

public class PantallaNoIdentificado extends JPanel {
	private JTable tabla;
	private JTable listado;
	private JTable tabla1;
	private JTextField txtApodo;
	private JTextField txtDescripcion;
	private JTextField txtDNItes;
	private JTextField textNombreCentro;

	/**
	 * Create the panel.
	 */
	public PantallaNoIdentificado() {
		setLayout(null);
		
		txtApodo = new JTextField();
		txtApodo.setBounds(174, 63, 104, 20);
		add(txtApodo);
		txtApodo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(174, 94, 104, 20);
		add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtDNItes = new JTextField();
		txtDNItes.setBounds(174, 141, 104, 20);
		add(txtDNItes);
		txtDNItes.setColumns(10);
		JTextArea txtrCentroAlQue = new JTextArea();
		txtrCentroAlQue.setText("CentroVinculado");
		txtrCentroAlQue.setBounds(20, 191, 125, 22);
		add(txtrCentroAlQue);
		
		textNombreCentro = new JTextField();
		textNombreCentro.setBounds(174, 193, 104, 20);
		add(textNombreCentro);
		textNombreCentro.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Apodo = txtApodo.getText();
				String Descripcion = txtDescripcion.getText();		
				int DNIt = Integer.valueOf(txtDNItes.getText().toString());
				String Centro=textNombreCentro.getText();
				
				NoIdentificado noide = new NoIdentificado(Apodo, Descripcion,DNIt);
				NoIdentificado DeNO = new NoIdentificado(Apodo,Centro);
				
				DetenidoNoIdeDAO detnoDao = new DetenidoNoIdeDAO();
				String mensaje = "";
				if(detnoDao.guardar(noide)) {
					mensaje = "La alta fue exitosa.";
					if(detnoDao.guardarVC(DeNO)) {
						mensaje = "La alta fue exitosa.";
					} else {
						mensaje = "Algo fall�. Intenta nuevamente";
					}
				} else {
					mensaje = "Algo fall�. Intenta nuevamente";
				}
				
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				JOptionPane.showMessageDialog(marco, mensaje);
			}
		});
		btnNewButton_3.setBounds(336, 62, 89, 23);
		add(btnNewButton_3);
		
		JTextArea Nombre = new JTextArea();
		Nombre.setText("Apodo");
		Nombre.setBounds(23, 61, 65, 22);
		add(Nombre);
		
		JTextArea Ubicacion = new JTextArea();
		Ubicacion.setText("Descripcion");
		Ubicacion.setBounds(20, 94, 98, 22);
		add(Ubicacion);
		JTextArea DNItesti = new JTextArea();
		DNItesti.setText("DNI testigo");
		DNItesti.setBounds(20, 139, 98, 22);
		add(DNItesti);
		
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaPrincipal());
				
				marco.validate();
			}
		});
		btnNewButton.setBounds(10, 538, 89, 23);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 390, 294, 137);
		add(scrollPane);
		
		tabla1 = new JTable();
		scrollPane.setViewportView(tabla1);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Apodo");
		modelo.addColumn("Descripcion");
		modelo.addColumn("DNI Testigo");
		modelo.addColumn("Nombre Centro");
		tabla1.setModel(modelo);
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<NoIdentificado> noide = new ArrayList<NoIdentificado>();
				DetenidoNoIdeDAO detnoDao = new DetenidoNoIdeDAO();
				noide = detnoDao.traerTodos();
				for(NoIdentificado nide : noide) {
					modelo.addRow(new Object[] {nide.getApodo(),nide.getDescripcion(),nide.getDniTes(),nide.getNombreCentro()});
				}
			}
		});
		btnMostrar.setBounds(336, 404, 89, 23);
		add(btnMostrar);
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame modelo = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				modelo.setContentPane(new PantallaNoIdentificadoEliminar());
				modelo.validate();
			}
		});
		btnEliminar.setBounds(336, 86, 89, 23);
		add(btnEliminar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				int fila= tabla1.getSelectedRow();
	
				String mensaje = "";
				if(e.getSource() == btnEditar) {
					if(fila==-1) {
					  mensaje = "Selecione una fila";
					  } else{
							String Apodo=(String)modelo.getValueAt(fila , 0);
							String Descripcion=(String)modelo.getValueAt(fila , 1);
							int Dni=Integer.parseInt((String)modelo.getValueAt(fila,2).toString());
							String Centro=(String)modelo.getValueAt(fila,3);
							
							txtApodo.setText(Apodo);
							txtDescripcion.setText(Descripcion);
							txtDNItes.setText(""+Dni);
							textNombreCentro.setText(Centro);

							}
					
							};
			}
		});
		
		
		btnEditar.setBounds(336, 472, 89, 23);
		add(btnEditar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Apodo = txtApodo.getText();
				String Descripcion = txtDescripcion.getText();		
				int DNIt = Integer.valueOf(txtDNItes.getText().toString());
				String Centro=textNombreCentro.getText();
				
				NoIdentificado noide = new NoIdentificado(Apodo,Descripcion,DNIt);
				NoIdentificado noide2 = new NoIdentificado(Apodo,Centro);
				DetenidoNoIdeDAO detnoDao = new DetenidoNoIdeDAO();
				String mensaje = "";
				
				 
				if(detnoDao.actualizar(noide,Apodo)) {
					mensaje = "La modificacion fue exitosa.";
					}else {
						mensaje = "Algo fall�. Intenta nuevamente";
					
					}
				 if(detnoDao.actualizarVC(noide2,Apodo)) 
				  {
					  mensaje = "La modificacion fue exitosa."; 
					  }else {
				mensaje = "Algo fall�. Intenta nuevamente";
				  
				  }
				 
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					JOptionPane.showMessageDialog(marco, mensaje);
				
			}
		});
		btnActualizar.setBounds(336, 438, 89, 23);
		add(btnActualizar);
		
		
		JTextArea txtrDetenidoNoIdentificado = new JTextArea();
		txtrDetenidoNoIdentificado.setBackground(UIManager.getColor("Button.background"));
		txtrDetenidoNoIdentificado.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtrDetenidoNoIdentificado.setText("Detenido no identificado");
		txtrDetenidoNoIdentificado.setBounds(92, 11, 222, 22);
		add(txtrDetenidoNoIdentificado);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame modelo = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				modelo.setContentPane(new BuscarNoIde());
				modelo.validate();
			}
		});
		btnBuscar.setBounds(336, 140, 89, 23);
		add(btnBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 245, 275, 100);
		add(scrollPane_1);
		tabla = new JTable();
		scrollPane_1.setViewportView(tabla);
		
		DefaultTableModel tab = new DefaultTableModel();
		tab.addColumn("DNI");
		tab.addColumn("Nombre");
		tab.addColumn("Apellido");
		
		tabla.setModel(tab);
		ArrayList<Testigo> testi = new ArrayList<Testigo>();
		TestigoDAO detnoDao = new TestigoDAO();
		testi = detnoDao.traerTodos();
		for(Testigo nide : testi) {
			tab.addRow(new Object[] {nide.getDNItestigo(),nide.getTes_Nombre(),nide.getTestimonio()});
		}
		JTextArea txtrTablaDeTestigos = new JTextArea();
		txtrTablaDeTestigos.setText("Tabla de Testigos disponibles");
		txtrTablaDeTestigos.setBounds(20, 224, 237, 22);
		add(txtrTablaDeTestigos);
		
	
		
		JTextArea txtrListadoDeDetenido = new JTextArea();
		txtrListadoDeDetenido.setText("Listado de Detenido No IDe");
		txtrListadoDeDetenido.setBounds(23, 356, 213, 22);
		add(txtrListadoDeDetenido);
		
		JScrollPane scrollPane_2= new JScrollPane();
		scrollPane_2.setBounds(336, 245, 357, 100);
		add(scrollPane_2);
		listado = new JTable();
		scrollPane_2.setViewportView(listado);
		
		DefaultTableModel tab1 = new DefaultTableModel();
		tab1.addColumn("Nombre");
		tab1.addColumn("Ubicacion");
		tab1.addColumn("FechaApertura");
		tab1.addColumn("FechaCierre");
		tab1.addColumn("FuerzaV");
		listado.setModel(tab1);
		ArrayList<CCDTyE> ccdtye = new ArrayList<CCDTyE>();
		CCDTyEDAO cdao = new CCDTyEDAO();
		ccdtye = cdao.traerTodos();
		for(CCDTyE cen : ccdtye) {
			tab1.addRow(new Object[] {cen.getCCDTyENombre(),cen.getUbicacion(),cen.getFechaApertura(),cen.getFechaCierre(),cen.getFuerzaN()});
		}
		
	}	
}
