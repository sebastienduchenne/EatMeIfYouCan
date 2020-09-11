package classeMetier;

import java.io.Serializable;

public  class CartePersonnage extends Carte implements Serializable{
	private Tour tour;
	private String nom;
	private Joueur joueur;
	private int position;
	
	public void gagnerTour(){
		this.tour.getGagnantsTour().add(this.joueur);
	}
	
	public void perdreTour(){
		this.tour.setPerdant(this.joueur);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	
}