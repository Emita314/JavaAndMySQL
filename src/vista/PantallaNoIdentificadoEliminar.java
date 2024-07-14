package vista;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import dao.DetenidoNoIdeDAO;

public class PantallaNoIdentificadoEliminar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtapododata;

	/**
	 * Create the panel.
	 */
	public PantallaNoIdentificadoEliminar() {
setLayout(null);
		
		txtapododata = new JTextField();
		txtapododata.setBounds(169, 81, 86, 20);
		add(txtapododata);
		txtapododata.setColumns(10);
		
		JTextArea txtrApodo = new JTextArea();
		txtrApodo.setText("Apodo");
		txtrApodo.setBounds(83, 79, 76, 22);
		add(txtrApodo);
		
		JButton btnEliminar = new JButton("Confirmar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Apodo = txtapododata.getText();

				DetenidoNoIdeDAO detnoDao = new DetenidoNoIdeDAO();
				String mensaje = "";
				if(detnoDao.eliminarVC(Apodo)) {
					mensaje = "La baja fue exitosa.";
					if(detnoDao.eliminar(Apodo)) {
						mensaje = "La baja fue exitosa.";
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
		btnEliminar.setBounds(311, 208, 89, 23);
		add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaNoIdentificado());
				marco.validate();
			}
		});
		btnVolver.setBounds(0, 208, 89, 23);
		add(btnVolver);

	}

}
