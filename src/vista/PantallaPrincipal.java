package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;


public class PantallaPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PantallaPrincipal() {
		JButton btnNewButton = new JButton("CCDTyE");
		btnNewButton.setBounds(183, 11, 88, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaCCDTyE());
				
				marco.validate();
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Identificado");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaIdentificado());
				marco.validate();
			}
		});
		btnNewButton_1.setBounds(121, 121, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNoIde = new JButton("No Identificado");
		btnNoIde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaNoIdentificado());
				marco.validate();
			}
		});
		btnNoIde.setBounds(237, 121, 89, 23);
		add(btnNoIde);
		
		JButton btnNewButton_2 = new JButton("Testigo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PantallaTestigo());
				marco.validate();
			}
		});
		btnNewButton_2.setBounds(183, 57, 89, 23);
		add(btnNewButton_2);


	}

}
