package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.CCDTyEDAO;
import dao.FuerzaDAO;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PantallaCCDTyEeliminar extends JPanel {
	private JTextField txtNombre;

	/**
	 * Create the panel.
	 */
	public PantallaCCDTyEeliminar() {
		setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(169, 81, 86, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JTextArea txtrNombre = new JTextArea();
		txtrNombre.setText("Nombre");
		txtrNombre.setBounds(83, 79, 76, 22);
		add(txtrNombre);
		
		JButton btnEliminar = new JButton("Confirmar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Nombre = txtNombre.getText();

				CCDTyEDAO cDao = new CCDTyEDAO();
				FuerzaDAO fDAO= new FuerzaDAO();
				String mensaje = "";
				if(fDAO.eliminarFV(Nombre)) {
					if(cDao.eliminar(Nombre)) {
						mensaje = "La baja fue exitosa.";
					} else {
						mensaje = "Algo fall�. Intenta nuevamente";
					}
					mensaje = "La baja fue exitosa.";
				} else {
					mensaje = "Algo fall�. Intenta nuevamente";
				}
			
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				JOptionPane.showMessageDialog(marco, mensaje);
			}
		});
		btnEliminar.setBounds(311, 208, 89, 23);
		add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaCCDTyE());
				marco.validate();
			}
		});
		btnVolver.setBounds(0, 208, 89, 23);
		add(btnVolver);

	}
}
