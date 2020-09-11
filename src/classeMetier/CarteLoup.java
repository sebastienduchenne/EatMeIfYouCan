package classeMetier;

import java.io.Serializable;

public class CarteLoup extends CartePersonnage implements Serializable{
	private Joueur choix;
	
	public CarteLoup(){
		this.setNom("Loup");
	}
	
	
//	public void gagnerTour(){
//		super.gagnerTour();
//		this.getJoueur().setNbPoint(this.choix.getJoueur().getNbPoint() + this.choix.getValeur());
//	}
//	
//	@Override
//	public void perdreTour(){
//		super.perdreTour();
//		if(this.getJoueur().getNbPoint() > 0){
//			this.getJoueur().setNbPoint(this.choix.getJoueur().getNbPoint() - this.choix.getValeur());
//		}
//	}

	public Joueur getChoix() {
		return choix;
	}

	public void setChoix(Joueur choix) {
		this.choix = choix;
	}
}
