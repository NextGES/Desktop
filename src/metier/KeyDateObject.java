package metier;

import java.sql.Date;

public class KeyDateObject {

	
	private String enonce, promo, matiere;
	
	
	public KeyDateObject( String enonce, String promo, String matiere) {
		super();
		
		this.enonce = enonce;
		this.promo = promo;
		this.matiere = matiere;
	}
	
	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	
	
}
