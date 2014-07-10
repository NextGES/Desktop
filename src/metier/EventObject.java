package metier;

import java.sql.Date;

public class EventObject {
	
	private String nom, description, lieu;
	private Date date;
	public EventObject(String nom, String description, String lieu, Date date) {
		super();
		this.nom = nom;
		this.description = description;
		this.lieu = lieu;
		this.date = date;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
