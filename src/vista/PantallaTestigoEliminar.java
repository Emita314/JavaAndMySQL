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
import dao.TestigoDAO;
import javax.swing.UIManager;


public class PantallaTestigoEliminar extends JPanel {
	private JTextField txtDni;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PantallaTestigoEliminar() {


setLayout(null);
		
		txtDni = new JTextField();
		txtDni.setBounds(10, 112, 192, 20);
		add(txtDni);
		txtDni.setColumns(10);
		
		JTextArea txtDNI = new JTextArea();
		txtDNI.setBackground(UIManager.getColor("Button.background"));
		txtDNI.setText("Ingrese el dni");
		txtDNI.setBounds(10, 81, 149, 22);
		add(txtDNI);
		
		JButton btnEliminar = new JButton("Confirmar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int DNI = Integer.valueOf(txtDni.getText().toString());
				TestigoDAO tDao = new TestigoDAO();
				String mensaje = "";
				if(tDao.eliminar(DNI)) {
					mensaje = "La alta fue exitosa.";
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
				marco.setContentPane(new PantallaTestigo());
				
				marco.validate();
			}
		});
		btnVolver.setBounds(25, 208, 89, 23);
		add(btnVolver);


	}
}
