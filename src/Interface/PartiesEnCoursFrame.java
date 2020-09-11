package Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class PartiesEnCoursFrame extends JFrame{
	public PartiesEnCoursFrame() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel titreLbl = new JLabel("Parties en cours");
		titreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titreLbl.setBackground(Color.WHITE);
		getContentPane().add(titreLbl);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);
	}

}
