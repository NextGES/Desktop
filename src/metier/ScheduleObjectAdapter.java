package metier;

import java.sql.Time;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import bdd.DatabaseAccess;

public class ScheduleObjectAdapter extends AbstractTableModel {
	
	private DatabaseAccess data = new DatabaseAccess();
	private final ScheduleObject[] schedule;
	private Object[] teacher, coursespeciality, subject, salle ;
	private Object[][] object;
	private final String[] entetes = {"Date", "Heure de début", "Heure de fin", "Classe", "Matière", "Salle", "Professeur"};
	private Date date;
	private Time debut, fin;
	private String login, nom;
	
	public ScheduleObjectAdapter(String login)
	{
		super();
		teacher = data.getTeacher(data.getIdUser(login));
		this.login = login;
		object = data.getSchedule((int)teacher[5]);
		subject = data.getSubject((int)teacher[5]);
		
		
		schedule = new ScheduleObject[object.length]; 
		for(int i=0; i<object.length;i++)
		{
			subject = data.getSubject((int)object[i][5]);
			teacher = data.getTeacherBySubject((int)subject[0]);
			salle = data.getSalleById((int)object[i][6]);
			coursespeciality = data.getCourseSpecility((int)object[i][4]);
			date = (Date)object[i][1];
			debut = (Time)object[i][2];
			fin = (Time)object[i][3];
			nom = ""+teacher[1]+" "+teacher[2];
			schedule[i] = new ScheduleObject(date, debut, fin, (String)coursespeciality[2], (String)subject[1], (int)object[i][0], (String)salle[1], nom);
			
		}
			
	}
	
	public ScheduleObjectAdapter(int idSalle)
	{
		super();
		
		object = data.getScheduleByIdSalle(idSalle);
		schedule = new ScheduleObject[object.length];
		
		for(int i=0; i<object.length;i++)
		{
			subject = data.getSubject((int)object[i][5]);
			teacher = data.getTeacherBySubject((int)subject[0]);
			salle = data.getSalleById((int)object[i][6]);
			coursespeciality = data.getCourseSpecility((int)object[i][4]);
			date = (Date)object[i][1];
			debut = (Time)object[i][2];
			fin = (Time)object[i][3];
			nom = ""+teacher[1]+" "+teacher[2];
			schedule[i] = new ScheduleObject(date, debut, fin, (String)coursespeciality[2], (String)subject[1], (int)object[i][0], (String)salle[1], nom);
			
		}
	}
	
	public ScheduleObjectAdapter()
	{
		super();
		object = data.getAllSchedule();
		schedule = new ScheduleObject[object.length];
		
		for(int i=0; i<object.length;i++)
		{
			subject = data.getSubject((int)object[i][5]);
			teacher = data.getTeacherBySubject((int)subject[0]);
			salle = data.getSalleById((int)object[i][6]);
			coursespeciality = data.getCourseSpecility((int)object[i][4]);
			date = (Date)object[i][1];
			debut = (Time)object[i][2];
			fin = (Time)object[i][3];
			nom = ""+teacher[1]+" "+teacher[2];		
			schedule[i] = new ScheduleObject(date, debut, fin, (String)coursespeciality[2], (String)subject[1], (int)object[i][0], (String)salle[1], nom);
			
		}
	}
	
	
	public String getSubject(int selectedScedule)
	{
		return (String)subject[1];
	}
	
	public String getIdUser()
	{
		return this.login;
	}

	public int getTeacherId(int selectedSchedule)
	{
		return (int)teacher[0];
	}
	
	public int getCourseSpecialityId(int selectedSchedule)
	{
		return (int)object[selectedSchedule][4];
	}
	
	public int getSubjectId(int selectedSchedule)
	{
		return (int)object[selectedSchedule][5];
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
		 
	}
	
	@Override
	public String getColumnName(int column) {
		return entetes[column];
	}

	@Override
	public int getRowCount() {
		return schedule.length;
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
        case 0:
            return schedule[rowIndex].getDate();
        case 1:
            return schedule[rowIndex].getDebut();
        case 2:
            return schedule[rowIndex].getFin();
        case 3:
        	return schedule[rowIndex].getClasse();
        case 4:
        	return schedule[rowIndex].getMatiere();
        case 5:
        	return schedule[rowIndex].getSalle();
        case 6:
        	return schedule[rowIndex].getProfesseur();
        case 7:
        	return schedule[rowIndex].getIdSchedule();
        default:
            return null;
		
	}
		
		
}

}
