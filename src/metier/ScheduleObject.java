package metier;

import java.sql.Time;
import java.util.Date;


public class ScheduleObject {
	
	private Date date;
	private Time debut, fin;
	private String classe, matiere, salle, professeur;
	private int idSchedule;
	
	public ScheduleObject(){}
	
		
	public ScheduleObject(Date date, Time debut, Time fin, String classe,
			String matiere, int idSchedule, String salle, String professeur) {
		super();
		this.date = date;
		this.debut = debut;
		this.fin = fin;
		this.classe = classe;
		this.matiere = matiere;
		this.salle = salle;
		this.professeur = professeur;
		this.idSchedule = idSchedule;
	}
	

	public String getProfesseur() {
		return professeur;
	}

	public void setProfesseur(String professeur) {
		this.professeur = professeur;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public int getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(int idSchedule) {
		this.idSchedule = idSchedule;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDebut(Time debut) {
		this.debut = debut;
	}

	public void setFin(Time fin) {
		this.fin = fin;
	}

	public Date getDebut() {
		return debut;
	}	

	public Time getFin() {
		return fin;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}



}
