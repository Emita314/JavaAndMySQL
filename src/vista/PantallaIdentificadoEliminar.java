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

import dao.DetenidoDAO;
import java.awt.Font;

public class PantallaIdentificadoEliminar extends JPanel {
	private JTextField txtDni;

	/**
	 * Create the panel.
	 */
	public PantallaIdentificadoEliminar() {
setLayout(null);
		
		txtDni = new JTextField();
		txtDni.setBounds(22, 123, 147, 20);
		add(txtDni);
		txtDni.setColumns(10);
		
		JTextArea txtrNombre = new JTextArea();
		txtrNombre.setText("Ingrese el DNI del Detenido");
		txtrNombre.setBounds(22, 86, 246, 22);
		add(txtrNombre);
		
		JButton btnEliminar = new JButton("Confirmar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int Dni = Integer.valueOf(txtDni.getText());

				DetenidoDAO dDAO = new DetenidoDAO();
				String mensaje = "";
				if(dDAO.eliminar(Dni)) {
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
				marco.setContentPane(new PantallaIdentificado());
				marco.validate();
			}
		});
		btnVolver.setBounds(22, 208, 89, 23);
		add(btnVolver);
		
		JTextArea txtrEliminarDetenido = new JTextArea();
		txtrEliminarDetenido.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtrEliminarDetenido.setText("Eliminar Detenido");
		txtrEliminarDetenido.setBounds(120, 28, 223, 34);
		add(txtrEliminarDetenido);
	}

}
