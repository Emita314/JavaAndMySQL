package app;

import javax.swing.JFrame;

import vista.PantallaPrincipal;

public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame marco = new JFrame();
		marco.setVisible(true);
		marco.setBounds(50,50,500,500);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		marco.setContentPane(new PantallaPrincipal());
		marco.validate();
		
		
	}

}