package classeMetier;

import java.io.Serializable;

public class CarteGentil extends CartePersonnage implements Serializable{
	private int valeur;
	private boolean dort;

	public CarteGentil(int i, String nom) {
		this.valeur = i;
		this.setNom(nom);
	}

	public CarteGentil() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gagnerTour(){
		super.gagnerTour();
		this.getJoueur().setNbPoint(this.getJoueur().getNbPoint() + this.valeur);
	}
	
	@Override
	public void perdreTour(){
		super.perdreTour();
		if(this.getJoueur().getNbPoint() < this.valeur){
			this.getJoueur().setNbPoint(this.getJoueur().getNbPoint() - this.valeur);
		}
	}
	
	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public void getValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public boolean isDort() {
		return dort;
	}

	public void setDort(boolean dort) {
		this.dort = dort;
	}
}
