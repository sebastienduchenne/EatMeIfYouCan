package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classeMetier.Joueur;

import java.awt.GridLayout;
import java.awt.Insets;

public class MenuPrincipalPanel extends JPanel {
	private JButton rejoindrePartieBt;
	private JButton statBt;
	private JButton changerPasswordBt;
	private JButton supprimerCompteBt;
	private Socket soc;
	private Frame fr;
	private Joueur joueur;
	private JPanel panel;
	private JLabel lblVide1;
	private JButton deconnexionBt;
	private JLabel lblVide2;
	private JLabel titreMenuLbl;

	public MenuPrincipalPanel(final Joueur joueur, final Socket soc, final Frame fr) {
		this.setJoueur(joueur);
		this.setSoc(soc);
		this.fr = fr;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//setBackground(Color.WHITE);

		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(8, 1, 0, 20));
		this.add(panel);

		lblVide1 = new JLabel("");
		panel.add(lblVide1);

		titreMenuLbl = new JLabel("Menu principal");
		titreMenuLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titreMenuLbl.setFont(new Font(panel.getName(), Font.PLAIN, 20));
		panel.add(titreMenuLbl);

		lblVide2 = new JLabel("");
		panel.add(lblVide2);

		rejoindrePartieBt = new JButton("Rejoindre une partie");
		panel.add(rejoindrePartieBt);

		statBt = new JButton("Voir les statistiques");
		panel.add(statBt);

		changerPasswordBt = new JButton("Changer le mot de passe(bientôt)");
		panel.add(changerPasswordBt);

		supprimerCompteBt = new JButton("Supprimer le compte(bientôt)");
		panel.add(supprimerCompteBt);

		deconnexionBt = new JButton("Se déconnecter");
		panel.add(deconnexionBt);		
		
		

	}

	public JButton getRejoindrePartieBt() {
		return rejoindrePartieBt;
	}

	public JButton getStatBt() {
		return statBt;
	}

	public JButton getChangerPasswordBt() {
		return changerPasswordBt;
	}

	public JButton getSupprimerCompteBt() {
		return supprimerCompteBt;
	}
	
	public JButton deconnexionBt(){
		return deconnexionBt;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Socket getSoc() {
		return soc;
	}

	public void setSoc(Socket soc) {
		this.soc = soc;
	}

	/*
	 * pour la sélection d'une image
	 * jBtn.setBorder(BorderFactory.createCompoundBorder(
	 * BorderFactory.createLineBorder(Color.CYAN, 5),
	 * BorderFactory.createLineBorder(Color.BLACK, 20)));
	 */

}
