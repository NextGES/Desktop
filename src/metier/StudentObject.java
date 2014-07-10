package metier;

import java.util.Date;

public class StudentObject {
	
	private int idStudent, idCourseSpeciality;
	private String prenom, nom, email, phone;
	private boolean isDelegue;
	private Date birthday;
	
	public StudentObject(int idStudent, int idCourseSpeciality, String prenom,
			String nom, String email, String phone, boolean isDelegue,
			Date birthday) {
		super();
		this.idStudent = idStudent;
		this.idCourseSpeciality = idCourseSpeciality;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.phone = phone;
		this.isDelegue = isDelegue;
		this.birthday = birthday;
	}

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	public int getIdCourseSpeciality() {
		return idCourseSpeciality;
	}

	public void setIdCourseSpeciality(int idCourseSpeciality) {
		this.idCourseSpeciality = idCourseSpeciality;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isDelegue() {
		return isDelegue;
	}

	public void setDelegue(boolean isDelegue) {
		this.isDelegue = isDelegue;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	

}
