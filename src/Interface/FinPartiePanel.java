package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import classeMetier.Joueur;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FinPartiePanel extends JPanel{
	
	private JLabel titreStatLbl;
	private JLabel gagnantLbl;
	private JButton menuPrincipalBt;
	private JLabel nbTourLbl;
	private ArrayList<JLabel> autresJoueurs;
	
	
	public FinPartiePanel(ArrayList<Joueur> joueurs) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		titreStatLbl = new JLabel("Fin de la Partie");
		titreStatLbl.setBounds(300, 30, 200, 30);
		titreStatLbl.setFont(new Font(getName(), Font.PLAIN, 20));
		add(titreStatLbl);
		titreStatLbl.setBackground(Color.WHITE);
		titreStatLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		gagnantLbl = new JLabel("Gagnant : xxxxxxx, 12 points");
		gagnantLbl.setBounds(300, 80, 200, 50);
		add(gagnantLbl);
		gagnantLbl.setBackground(Color.WHITE);
		gagnantLbl.setHorizontalAlignment(SwingConstants.LEFT);
		
		nbTourLbl = new JLabel("Nombre de tour : 15");
		nbTourLbl.setHorizontalAlignment(SwingConstants.LEFT);
		nbTourLbl.setBounds(300, 130, 200, 50);
		//add(nbTourLbl);
		
		
		
		int plusHaut = 0;
		Joueur gagnant = null;
		for (Joueur joueur : joueurs) {
			if(joueur.getNbPoint() > plusHaut){
				plusHaut = joueur.getNbPoint();
				gagnant = joueur;
			}
		}
		
		gagnantLbl.setText(gagnant.getPseudo());
		joueurs.remove(gagnant);
		
		int i = 200;
		for (Joueur joueur : joueurs) {
			JLabel lab = new JLabel(joueur.getPseudo() + ", " + joueur.getNbPoint() + " points"); 
			lab.setBounds(300, 200, 150, 30);
			i += 30;
			add(lab);
		}
		

		menuPrincipalBt = new JButton("Menu principal");
		menuPrincipalBt.setBounds(325, 450, 150, 30);
		add(menuPrincipalBt);
		
		
//		getMenuPrincipalBt().addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				setMenuPanel(menuPanel);
//				getContentPane().add(getMenuPanel());
//				getMenuPanel().setLayout(new GridLayout(0, 1, 0, 0));
//				setContentPane(getMenuPanel());
//				editerFenetre();
//				revalidate();
//			}
//		});
		
	}
	
	public JButton getMenuPrincipalBt() {
		return menuPrincipalBt;
	}

	public void setTitreStat(String text){
		titreStatLbl.setText(text);
	}
	
	public void setGagnantLbl(String text){
		gagnantLbl.setText(text);
	}
	
	public void setNbTourLbl(String text){
		nbTourLbl.setText(text);
	}
	
	

}
