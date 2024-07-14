package vista;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import dao.CCDTyEDAO;
import modelo.CCDTyE;
import dao.FuerzaDAO;
import modelo.Fuerza;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class PantallaCCDTyE extends JPanel {
	
	private JTable tabla1;

	private JTable tabla2;
	private JTextField txtNombre;
	private JTextField txtUbicacion;
	private JTextField txtFechaApertura;
	private JTextField txtFechaCierre;
	private JTextField textNombreFuerza;
	
	/**
	 * Create the panel.
	 */
	public PantallaCCDTyE() {
		setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(145, 44, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtUbicacion = new JTextField();
		txtUbicacion.setBounds(145, 75, 86, 20);
		add(txtUbicacion);
		txtUbicacion.setColumns(10);
		
		txtFechaApertura = new JTextField();
		txtFechaApertura.setBounds(145, 106, 86, 20);
		add(txtFechaApertura);
		txtFechaApertura.setColumns(10);
		
		txtFechaCierre = new JTextField();
		txtFechaCierre.setBounds(145, 137, 86, 20);
		add(txtFechaCierre);
		txtFechaCierre.setColumns(10);
		
		JTextArea txtrFuerzaVinculada = new JTextArea();
		txtrFuerzaVinculada.setText("Fuerza Vinculada");
		txtrFuerzaVinculada.setBounds(20, 168, 133, 22);
		add(txtrFuerzaVinculada);
		
		textNombreFuerza = new JTextField();
		textNombreFuerza.setBounds(163, 170, 86, 20);
		add(textNombreFuerza);
		textNombreFuerza.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Nombre = txtNombre.getText();
				int Ubicacion = Integer.valueOf(txtUbicacion.getText());			
				String FechaApertur = txtFechaApertura.getText();
				String FechaCierr = txtFechaCierre.getText();
				String FuerzaV=textNombreFuerza.getText();
				
				DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
				
				LocalDate FechaApertura = LocalDate.parse(FechaApertur, format);
				LocalDate FechaCierre = LocalDate.parse(FechaCierr, format);
				
				CCDTyE CCDTyE = new CCDTyE(Nombre, Ubicacion, FechaApertura, FechaCierre);
				Fuerza fuezaV=new Fuerza(Nombre,FuerzaV);
				FuerzaDAO Fdao= new FuerzaDAO();

				CCDTyEDAO cDao = new CCDTyEDAO();
				String mensaje = "";
				if(cDao.guardar(CCDTyE)) {
					mensaje = "La alta fue exitosa.";
				} else {
					mensaje = "Algo fall�. Intenta nuevamente";
				}
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				JOptionPane.showMessageDialog(marco, mensaje);
				
				if(Fdao.guardar(fuezaV)) {
					mensaje = "La alta fue exitosa.F";
				} else {
					mensaje = "Algo fall�. Intenta nuevamente";
				}
				JFrame marco1 = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				JOptionPane.showMessageDialog(marco1, mensaje);
			}
		});
		btnNewButton_3.setBounds(336, 43, 89, 23);
		add(btnNewButton_3);
		
		JTextArea Nombre = new JTextArea();
		Nombre.setText("Nombre\r\n");
		Nombre.setBounds(20, 42, 65, 22);
		add(Nombre);
		
		JTextArea Ubicacion = new JTextArea();
		Ubicacion.setText("Ubicacion");
		Ubicacion.setBounds(20, 73, 77, 22);
		add(Ubicacion);
		
		JTextArea FechaApertura = new JTextArea();
		FechaApertura.setText("FechaApertura");
		FechaApertura.setBounds(20, 106, 109, 22);
		add(FechaApertura);
		
		JTextArea FechaCierre = new JTextArea();
		FechaCierre.setText("FechaCierre\r\n");
		FechaCierre.setBounds(20, 139, 109, 22);
		add(FechaCierre);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaPrincipal());
				
				marco.validate();
			}
		});
		btnNewButton.setBounds(10, 520, 89, 23);
		add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 372, 294, 137);
		add(scrollPane);
		
		tabla1 = new JTable();
		scrollPane.setViewportView(tabla1);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Nombre");
		modelo.addColumn("Ubicacion");
		modelo.addColumn("FechaApertura");
		modelo.addColumn("FechaCierre");
		modelo.addColumn("FuerzaV");
		tabla1.setModel(modelo);
		
		ArrayList<CCDTyE> ccdtye = new ArrayList<CCDTyE>();
		CCDTyEDAO cdao = new CCDTyEDAO();
		ccdtye = cdao.traerTodos();
		for(CCDTyE cen : ccdtye) {
			modelo.addRow(new Object[] {cen.getCCDTyENombre(),cen.getUbicacion(),cen.getFechaApertura(),cen.getFechaCierre(),cen.getFuerzaN()});
		}
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaCCDTyEeliminar());
				marco.validate();
			}
		});
		btnEliminar.setBounds(336, 74, 89, 23);
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
							String Nombre=(String)modelo.getValueAt(fila , 0);
							int Ubicacion=Integer.parseInt((String)modelo.getValueAt(fila,1).toString());
							LocalDate FechaApertura=(LocalDate)modelo.getValueAt(fila , 2);
							LocalDate FechaCierre=(LocalDate)modelo.getValueAt(fila ,3);
							String Fvincu=(String)modelo.getValueAt(fila, 4);
							txtNombre.setText(Nombre);
							txtUbicacion.setText(""+Ubicacion);
							txtFechaApertura.setText(""+FechaApertura);
							txtFechaCierre.setText(""+FechaCierre);
							textNombreFuerza.setText(Fvincu);
							}
					
							};
			}
		});
		
		
		btnEditar.setBounds(336, 200, 89, 23);
		add(btnEditar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Nombre = txtNombre.getText();
				int Ubicacion = Integer.valueOf(txtUbicacion.getText());			
				String FechaApertur = txtFechaApertura.getText();
				String FechaCierr = txtFechaCierre.getText();
				String FuerzaV=textNombreFuerza.getText();
				DateTimeFormatter format = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toFormatter();
				
				LocalDate FechaApertura = LocalDate.parse(FechaApertur, format);
				LocalDate FechaCierre = LocalDate.parse(FechaCierr, format);
				
				CCDTyE ccdtye = new CCDTyE(Nombre,Ubicacion,FechaApertura,FechaCierre);
				
				Fuerza fuer= new Fuerza(Nombre,FuerzaV);
				FuerzaDAO Fdao=new FuerzaDAO();
				
				CCDTyEDAO tesdao = new CCDTyEDAO();
				String mensaje = "";
				
				if(tesdao.actualizar(ccdtye,Nombre)) {
					mensaje = "La modificacion fue exitosa.";
					}else {
						mensaje = "Algo fall�. Intenta nuevamente";
					}
				if(Fdao.actualizar(fuer,Nombre)) {
					mensaje = "La modificacion fue exitosa.fff";
					}else {
						mensaje = "Algo fall�. Intenta nuevamente";
					}
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				JOptionPane.showMessageDialog(marco, mensaje);
				
			}
		});
		btnActualizar.setBounds(336, 105, 89, 23);
		add(btnActualizar);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<CCDTyE> ccdtye = new ArrayList<CCDTyE>();
				CCDTyEDAO cdao = new CCDTyEDAO();
				ccdtye = cdao.traerTodos();
				for(CCDTyE cen : ccdtye) {
					modelo.addRow(new Object[] {cen.getCCDTyENombre(),cen.getUbicacion(),cen.getFechaApertura(),cen.getFechaCierre(),cen.getFuerzaN()});
				}
			}
		});
		btnMostrar.setBounds(336, 169, 89, 23);
		add(btnMostrar);
		
		JButton btnbuscar = new JButton("Buscar");
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new BusacrCCDTyE());
				marco.validate();
			}
		});
		btnbuscar.setBounds(336, 136, 89, 23);
		add(btnbuscar);
		

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 243, 126, 99);
		add(scrollPane1);
		
		tabla2 = new JTable();
		scrollPane1.setViewportView(tabla2);
		
		DefaultTableModel listado = new DefaultTableModel();
		listado.addColumn("Nombre");		
		tabla2.setModel(listado);
		
		ArrayList<Fuerza> Fuerzas = new ArrayList<Fuerza>();
		FuerzaDAO fdao = new FuerzaDAO();
		Fuerzas = fdao.traerTodos();
		for(Fuerza cen : Fuerzas) {
			listado.addRow(new Object[] {cen.getFuerza_Npmbre()});
		}
		
	}
}