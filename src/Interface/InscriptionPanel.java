package Interface;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;

import classeMetier.Joueur;

import javax.swing.JPanel;
import java.awt.GridLayout;

public class InscriptionPanel extends JPanel {

	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_3;
	private JPanel panel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_6;
	private JLabel label_4;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_4;
	private Socket soc;
	private Frame fr;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;

	public String getTextPseudo() {
		return textField.getText();
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public String getTextPassword() {
		String b = new String(passwordField.getPassword());
		return b;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public void setBtnNewButton_1(JButton btnNewButton_1) {
		this.btnNewButton_1 = btnNewButton_1;
	}

	

	public InscriptionPanel(final Socket soc, final Frame fr) {

		this.fr = fr;
		this.soc = soc;

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		// panel.setBounds(0,20,100,100);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		label = new JLabel("");
		panel.add(label);

		lblNewLabel_2 = new JLabel("Inscription sur Eat me if you can", JLabel.CENTER);
		lblNewLabel_2.setFont(new Font(panel.getName(), Font.PLAIN, 20));
		panel.add(lblNewLabel_2);

		label_1 = new JLabel("");
		panel.add(label_1);

		label_2 = new JLabel("");
		panel.add(label_2);

		lblNewLabel = new JLabel("Le pseudo et le mot de passe ne doivent contenir");
		panel.add(lblNewLabel);

		lblNewLabel_8 = new JLabel("que des lettres et/ou des chiffres.");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_8);

		lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);

		lblNewLabel_7 = new JLabel("Pseudo");
		panel.add(lblNewLabel_7);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		lblNewLabel_1 = new JLabel("Mot de passe");
		panel.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		panel.add(passwordField);

		label_4 = new JLabel("");
		panel.add(label_4);

		btnNewButton = new JButton("Valider");
		panel.add(btnNewButton);

		lblNewLabel_6 = new JLabel("");
		panel.add(lblNewLabel_6);

		btnNewButton_1 = new JButton("Page de connexion");
		panel.add(btnNewButton_1);

		lblNewLabel_4 = new JLabel("");
		panel.add(lblNewLabel_4);

		lblNewLabel_3 = new JLabel("Erreur", JLabel.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
		panel.add(lblNewLabel_3);

		this.add(panel);

		label_3 = new JLabel("");
		panel.add(label_3);

		label_6 = new JLabel("");
		panel.add(label_6);

	}


}
