package metier;

public class AppelObject {

	private boolean present, late;
	private String prenom, nom;
	
	public AppelObject (String prenom, String nom, boolean present, boolean late)
	{
		this.prenom = prenom;
		this.nom = nom;
		this.present = present;
		this.late = late;
	}
	
	

	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}
	
	
}
