package classeMetier;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.plaf.TabbedPaneUI;

import database.DataBase;

public class Joueur implements Serializable{

	private String pseudo;
	private int nbPoint;
	private int nbPartieGagnees;
	private CartePersonnage personnage;
	private CartePiege cartePiege;
	private CarteBonneNuit carteBonneNuit;
	private int taillePartie;
	private String password;
	private boolean inGame;
	private boolean connect;
	private Socket soc;

	public Joueur(String pseudo, int taillePartie) {
		this.pseudo = pseudo;
		this.setTaillePartie(taillePartie);
		this.setInGame(false);
		connect = false;
		setSoc(new Socket());
	}

	public Joueur(String pseudo, String mdp) {
		this.pseudo = pseudo;
		this.password = mdp;
	}

	public void distribuerCarte(ArrayList<Joueur> joueurs, ArrayList<CartePersonnage> gentils, CartePersonnage loup) {

	}

	public void choisirMaison() {
		// appel à dormir() de carteGentil ou pas
	}

	public Joueur(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setNbPartieGagnees(int nbPartieGagnees) {
		this.nbPartieGagnees = nbPartieGagnees;
	}

	public boolean Authentification() {
		DataBase.connecterJoueur(this);
		return true;
	}

	public boolean Inscription() {
		return DataBase.inscrireJoueur(this);
	}
	
	public void deconnexion(){
		DataBase.syncroJoueur(this);
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public void setNbPoint(int nbPoint) {
		this.nbPoint = nbPoint;
	}

	public CartePersonnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(CartePersonnage personnage) {
		this.personnage = personnage;
	}

	public CartePiege getCartePiege() {
		return cartePiege;
	}

	public void setCartePiege(CartePiege cartePiege) {
		this.cartePiege = cartePiege;
	}

	public CarteBonneNuit getCarteBonneNuit() {
		return carteBonneNuit;
	}

	public void setCarteBonneNuit(CarteBonneNuit carteBonneNuit) {
		this.carteBonneNuit = carteBonneNuit;
	}

	public int getTaillePartie() {
		return taillePartie;
	}

	public void setTaillePartie(int taillePartie) {
		this.taillePartie = taillePartie;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNbPartieGagnees() {
		return nbPartieGagnees;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public boolean getConnect() {		
		return this.connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}

	public Socket getSoc() {
		return soc;
	}

	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	
}