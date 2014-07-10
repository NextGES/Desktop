package metier;

public class SalleObject {

	private String nom;
	private int nbPlace;
	private boolean projecteur;
	
	public SalleObject(String nom, int nbPlace, boolean projecteur) {
		super();
		this.nom = nom;
		this.nbPlace = nbPlace;
		this.projecteur = projecteur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNbPlace() {
		return nbPlace;
	}
	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	public boolean isProjecteur() {
		return projecteur;
	}
	public void setProjecteur(boolean projecteur) {
		this.projecteur = projecteur;
	}
	
	
}
