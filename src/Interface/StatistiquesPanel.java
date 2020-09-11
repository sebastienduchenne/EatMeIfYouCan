package Interface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import classeMetier.Joueur;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

	
public class StatistiquesPanel extends JPanel{
	
	private JLabel titreStatLbl;
	private JLabel stat1Lbl;
	private JLabel stat2Lbl;
	private JLabel stat3Lbl;
	private JLabel stat4Lbl;
	private JLabel stat5Lbl;
	private JButton menuPrincipalBt;
	
	
	public StatistiquesPanel(Joueur joueur) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		
		titreStatLbl = new JLabel("Statistiques de jeu");
		titreStatLbl.setBounds(300, 30, 200, 30);
		titreStatLbl.setFont(new Font(getName(), Font.PLAIN, 20));
		add(titreStatLbl);
		titreStatLbl.setBackground(Color.WHITE);
		titreStatLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		stat1Lbl = new JLabel("Nombre de parties jouées : ");
		stat1Lbl.setHorizontalAlignment(SwingConstants.LEFT);
		stat1Lbl.setBounds(150, 120, 450, 30);
		add(stat1Lbl);
		
		stat2Lbl = new JLabel("Nombre de parties gagnées");
		stat2Lbl.setBounds(150, 160, 450, 30);
		add(stat2Lbl);
		
		stat3Lbl = new JLabel("Inscrit depuis le : ");
		stat3Lbl.setBounds(150, 200, 450, 30);
		add(stat3Lbl);
		
		menuPrincipalBt = new JButton("Menu principal");
		menuPrincipalBt.setBounds(325, 450, 150, 30);
		add(menuPrincipalBt);
	}
	
	public void setTitreStat(String text){
		titreStatLbl.setText(text);
	}
	
	public void setStat1(String text){
		stat1Lbl.setText(text);
	}
	
	public void setStat2(String text){
		stat2Lbl.setText(text);
	}
	
	public void setStat3(String text){
		stat3Lbl.setText(text);
	}
	
	public void setStat4(String text){
		stat4Lbl.setText(text);
	}
	
	public void setStat5(String text){
		stat5Lbl.setText(text);
	}
	
	public JButton getMenuPrincipalBt(){
		return menuPrincipalBt;
	}
}
