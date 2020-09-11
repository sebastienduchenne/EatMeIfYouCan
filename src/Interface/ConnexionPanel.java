package Interface;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
import java.awt.FlowLayout;
import java.awt.Font;

public class ConnexionPanel extends JPanel {

	private JTextField textField;
	private JButton btSeConnecter;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	public Socket getSoc() {
		return soc;
	}

	public void setSoc(Socket soc) {
		this.soc = soc;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

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
	private JButton btSInscrire;
	private JLabel label_5;
	Socket soc;
	Frame fr;
	Joueur joueur;

	public ConnexionPanel(final Socket soc, final Frame fr, Joueur joueur) {
		this.soc = soc;
		this.fr = fr;
		this.joueur = joueur;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		label = new JLabel("");
		panel.add(label);

		JLabel lblNewLabel_2 = new JLabel("Bienvenue sur Eat me if you can", JLabel.CENTER);
		lblNewLabel_2.setFont(new Font(panel.getName(), Font.PLAIN, 20));
		panel.add(lblNewLabel_2);

		label_1 = new JLabel("");
		panel.add(label_1);

		label_2 = new JLabel("");
		panel.add(label_2);

		lblNewLabel_8 = new JLabel("");
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

		btSeConnecter = new JButton("Valider");
		panel.add(btSeConnecter);

		lblNewLabel_6 = new JLabel("");
		panel.add(lblNewLabel_6);

		btSInscrire = new JButton("S'inscrire");
		panel.add(btSInscrire);

		label_5 = new JLabel("");
		panel.add(label_5);

		lblNewLabel_3 = new JLabel("Erreur", JLabel.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
		panel.add(lblNewLabel_3);

		this.add(panel);

		label_3 = new JLabel("");
		panel.add(label_3);

		label_6 = new JLabel("");
		panel.add(label_6);
		
	}

	public JButton getBtSeConnecter() {
		return btSeConnecter;
	}

	public JButton getBtSInscrire() {
		return btSInscrire;
	}

	public String getTextPseudo() {
		return textField.getText();
	}

	public String getTextPassword() {
		String b = new String(passwordField.getPassword());
		return b;
	}

}