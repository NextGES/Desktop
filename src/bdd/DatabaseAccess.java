package bdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import metier.EventObject;
import metier.KeyDateObject;
import metier.SalleObject;
import metier.ScheduleObject;
import metier.ScheduleObjectAdapter;
import metier.UserObject;

public class DatabaseAccess {

	private Connection conn;
	private PreparedStatement statement;
	private ResultSet result;
	private ResultSetMetaData metadata;
	private String sql;
	private Object[][] userList, studentList, scheduleList, keydateList, salleList, eventList, courseSpecialityList, subjectList, presenceList;
	private Object[] teacher, subject, coursespeciality, scheduleList2,
			presence, tmp, salle;
	private boolean success;

	/*
	 * public Connection getConn() { return conn; }
	 * 
	 * public void setConn(Connection conn) { this.conn = conn; }
	 */

	public DatabaseAccess() {

	}

	public void Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/nextges";
			this.conn = DriverManager.getConnection(url, "root", "");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public String[] getUserMdp(String login) {
		Connection();
		String[] mdp = null;
		sql = "SELECT * FROM user WHERE login = ?";
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, login);
			result = statement.executeQuery();
			result.next();
			if (result != null) {
				mdp = new String[2];
				mdp[0] = result.getString(3);
				mdp[1] = "" + result.getBoolean(4);
			} else
				mdp = null;
			Deconnexion();
		} catch (Exception e) {
		} finally {
			return mdp;

		}

	}

	public Object[][] getAllUser() {
		Connection();
		sql = "SELECT * FROM user";
		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();
			metadata = result.getMetaData();

			result.last();
			userList = new Object[result.getRow()][metadata.getColumnCount() - 1];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 1; j < metadata.getColumnCount(); j++) {
					userList[i][j - 1] = result.getObject(j + 1);
				}
				i++;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun utilisateurs dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

		return userList;
	}

	public boolean addUser(UserObject user) {
		Connection();
		sql = "SELECT * FROM user WHERE login = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			result = statement.executeQuery();
			result.last();
			if (result.getRow() != 0) {
				JOptionPane.showMessageDialog(null, "Ce login existe déjà !");
				success = false;
			} else {
				sql = "INSERT INTO user (login, mdp, isProf) VALUES(?,?,?)";
				try {
					statement = conn.prepareStatement(sql);
					statement.setString(1, user.getLogin());
					statement.setString(2, user.getMdp());
					statement.setBoolean(3, user.isProf());
					statement.executeUpdate();
					success = true;
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		Deconnexion();
		return success;
	}

	public int removeUser(String login) {
		int a = 0;
		Connection();
		sql = "DELETE FROM user WHERE login = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, login);
			a = statement.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		Deconnexion();
		return a;
	}

	public int modifyUser(UserObject user, String login) {
		int a = 0;
		Connection();
		sql = "UPDATE user SET login=?, mdp=?, isProf=? WHERE login = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getMdp());
			statement.setBoolean(3, user.isProf());
			statement.setString(4, login);
			a = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
		return a;
	}

	public int getIdUser(String login) {
		int a = 0;
		Connection();
		try {
			sql = "SELECT idUser FROM user WHERE login =?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, login);
			result = statement.executeQuery();
			result.next();
			a = result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
		return a;
	}

	public Object[] getTeacher(int idUser) {
		teacher = new Object[7];
		Connection();
		sql = "SELECT * FROM teacher WHERE User_idUser =?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idUser);
			result = statement.executeQuery();
			result.next();

			for (int i = 0; i < 7; i++) {
				teacher[i] = result.getObject(i + 1);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun professeur dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return teacher;
	}
	
	public Object[] getTeacherBySubject(int idSubject)
	{
		
		Connection();
		sql = "SELECT * FROM teacher WHERE Subjects_idSubjects = ?";
		teacher = new Object[7];
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSubject);
			result = statement.executeQuery();
			result.next();

			for (int i = 0; i < 7; i++) {
				teacher[i] = result.getObject(i + 1);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun professeur dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return teacher;
		
	}

	public Object[] getSubject(int idSubjects) {
		subject = new Object[2];
		Connection();
		sql = "SELECT * FROM subjects WHERE idSubjects = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSubjects);
			result = statement.executeQuery();
			result.next();

			for (int i = 0; i < 2; i++) {
				subject[i] = result.getObject(i + 1);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucune matière dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return subject;
	}
	
	public Object[][] getAllSubject()
	{
		Connection();
		sql = "SELECT * FROM subjects ORDER BY subjectsLabel ASC";

		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();

			result.last();
			subjectList = new Object[result.getRow()][2];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 2; j++) {
					subjectList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun cours dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return subjectList;
	}

	public Object[][] getSchedule(int idSubjects) {
		Connection();
		sql = "SELECT * FROM schedule WHERE Subjects_idSubjects = ? ORDER BY date ASC, heureDebut ASC";

		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSubjects);
			result = statement.executeQuery();

			result.last();
			scheduleList = new Object[result.getRow()][7];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 7; j++) {
					scheduleList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun cours dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return scheduleList;
	}
	
	public Object[][] getAllSchedule()
	{
		Connection();
		sql = "SELECT * FROM schedule ORDER BY date ASC, heureDebut ASC";

		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();

			result.last();
			scheduleList = new Object[result.getRow()][7];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 7; j++) {
					scheduleList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun cours dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return scheduleList;
	}

	public Object[] getSchedule2(int idSchedule) {
		Connection();
		sql = "SELECT * FROM schedule WHERE idSchedule = ?";
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSchedule);
			result = statement.executeQuery();

			result.first();
			scheduleList2 = new Object[7];
			for (int i = 0; i < 7; i++) {
				scheduleList2[i] = result.getObject(i + 1);
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		Deconnexion();
		return scheduleList2;
	}
	
	public Object [][] getScheduleByIdSalle(int idSalle)
	{
		Connection();
		sql = "SELECT * FROM schedule WHERE salle_idressource = ? ORDER BY date ASC, heureDebut ASC";
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSalle);
			result = statement.executeQuery();

			result.last();
			scheduleList = new Object[result.getRow()][7];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 7; j++) {
					scheduleList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun cours dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return scheduleList;
	}
	
	public void addSchedule(Date date, Time debut, Time fin, int idCourseSpeciality, int idSubject, int idSalle)
	{
		Connection();
		sql = "INSERT INTO schedule (date, heureDebut, heureFin, CourseSpeciality_idCourseSpeciality, Subjects_idSubjects, salle_idressource) VALUES (?,?,?,?,?,?)";
		try {
			statement = conn.prepareStatement(sql);
			statement.setDate(1, date);
			statement.setTime(2, debut);
			statement.setTime(3, fin);
			statement.setInt(4, idCourseSpeciality);
			statement.setInt(5, idSubject);
			statement.setInt(6, idSalle);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Echec de l'ajout");
			e.printStackTrace();
		}
		Deconnexion();
	}
	
	public void updateSchedule(Date date, Time debut, Time fin, int idCourseSpeciality, int idSubject, int idSalle, int idSchedule)
	{
		Connection();
		
		sql = "UPDATE schedule SET date=?, heureDebut=?, heureFin=?,  CourseSpeciality_idCourseSpeciality=?, Subjects_idSubjects=?, salle_idressource=? WHERE idSchedule = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setDate(1, date);
			statement.setTime(2, debut);
			statement.setTime(3, fin);
			statement.setInt(4, idCourseSpeciality);
			statement.setInt(5, idSubject);
			statement.setInt(6, idSalle);
			statement.setInt(7, idSchedule);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Echec de l'ajout");
			e.printStackTrace();
		}
		Deconnexion();
	}
	
	public void deleteSchedule(int idSchedule)
	{
		Connection();
		sql = "DELETE FROM schedule WHERE idSchedule = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSchedule);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
	}

	public Object[] getCourseSpecility(int idCourseSpeciality) {
		Connection();
		sql = "SELECT * FROM coursespeciality WHERE idCourseSpeciality = ?";

		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idCourseSpeciality);
			result = statement.executeQuery();
			result.next();
			coursespeciality = new Object[4];
			for (int i = 0; i < 4; i++) {
				coursespeciality[i] = result.getObject(i + 1);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucune spécialisation dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

		Deconnexion();
		return coursespeciality;
	}
	
	public Object[][] getAllCourseSpeciality()
	{
		Connection();
		sql = "SELECT * FROM coursespeciality ORDER BY labelSpeciality ASC";

		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();
			
			result.last();
			courseSpecialityList = new Object[result.getRow()][4];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 4; j++) {
					courseSpecialityList[i][j] = result.getObject(j + 1);
				}
				i++;
			}}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucune spécialisation dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

		Deconnexion();
		return courseSpecialityList;
	}
	
	public Object[][] getAllStudent()
	{
		Connection();
		sql = "SELECT * FROM students  ORDER BY lastname ASC";

		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();
			
			result.last();
			studentList = new Object[result.getRow()][6];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 6; j++) {
					studentList[i][j] = result.getObject(j + 1);
				}
				i++;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun étudiant dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return studentList;
	}

	public Object[][] getStudent(int idCourseSpeciality) {
		Connection();
		sql = "SELECT * FROM students WHERE CourseSpeciality_idCourseSpeciality = ? ORDER BY lastname ASC";

		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idCourseSpeciality);
			result = statement.executeQuery();
			
			result.last();
			studentList = new Object[result.getRow()][6];
			result.beforeFirst();

			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 6; j++) {
					studentList[i][j] = result.getObject(j + 1);
				}
				i++;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun étudiant dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return studentList;
	}

	public Object[] checkPresence(int idSchedule, int idStudent) {
		Connection();
		sql = "SELECT * FROM presence WHERE Schedule_idSchedule = ? AND students_idStudents= ?";

		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSchedule);
			statement.setInt(2, idStudent);
			result = statement.executeQuery();

			if (result.next()) {
				presence = new Object[7];
				for (int j = 0; j < 7; j++) {
					presence[j] = result.getObject(j + 1);
				}

			} else
				presence = new Object[0][0];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Deconnexion();
		return presence;
	}
	
	public Object[][] getAllPresence()
	{
		Connection();
		sql = "SELECT * FROM presence ORDER BY Schedule_idSchedule ASC";
		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();
			
			result.last();
			presenceList = new Object[result.getRow()][7];
			result.beforeFirst();
			
			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 7; j++) {
					presenceList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
		return presenceList;
	}

	public void addPresence(boolean present, boolean late,
			ScheduleObjectAdapter adapter2, int selectedSchedule,
			int idSchedule, int idStudent) {
		tmp = checkPresence(idSchedule, idStudent);
		if (tmp.length == 0) {

			Connection();
			sql = "INSERT INTO presence (present, late, Schedule_idSchedule, Schedule_CourseSpeciality_idCourseSpeciality, Schedule_Subjects_idSubjects, Students_idStudents) VALUES(?,?,?,?,?,?)";
			try {
				statement = conn.prepareStatement(sql);
				statement.setBoolean(1, present);
				statement.setBoolean(2, late);
				statement.setInt(3, idSchedule);
				statement.setInt(4,
						(int) adapter2.getCourseSpecialityId(selectedSchedule));
				statement.setInt(5,
						(int) adapter2.getSubjectId(selectedSchedule));
				statement.setInt(6, idStudent);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Deconnexion();
		}

		else {
			Connection();
			sql = "UPDATE presence SET present=?, late=? WHERE Schedule_idSchedule = ? AND Students_idStudents = ?";
			try {
				statement = conn.prepareStatement(sql);
				statement.setBoolean(1, present);
				statement.setBoolean(2, late);
				statement.setInt(3, idSchedule);
				statement.setInt(4, idStudent);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Deconnexion();
		}

	}

	public Object[][] getKeyDate(int idSchedule) {
		Connection();
		sql = "SELECT * FROM keydate WHERE CourseSpeciality_idCourseSpeciality = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSchedule);
			result = statement.executeQuery();

			if (result.next()) {
				result.last();
				keydateList = new Object[result.getRow()][7];
				result.beforeFirst();
				int i = 0;
				while (result.next()) {
					for (int j = 0; j < 6; j++) {
						keydateList[i][j] = result.getObject(j + 1);
					}
					i++;
				}

			} else
				keydateList = new Object[0][0];

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Il n'y a aucun rappel dans la base de données !",
					"La connexion a échoué", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		Deconnexion();
		return keydateList;
	}

	public void addKeyDate(KeyDateObject keydate,
			ScheduleObjectAdapter adapter, int selectedSchedule, int idTeacher) {
		Connection();
		sql = "INSERT INTO keydate (subject, CourseSpeciality_idCourseSpeciality, teacher_idTeacher, teacher_Subjects_idSubjects, teacher_user_idUser) VALUES (?,?,?,?,?)";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, keydate.getEnonce());
			statement
					.setInt(2, adapter.getCourseSpecialityId(selectedSchedule));
			statement.setInt(3, adapter.getTeacherId(selectedSchedule));
			statement.setInt(4, adapter.getSubjectId(selectedSchedule));
			statement.setInt(5, idTeacher);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
	}

	public int updateKeyDate(KeyDateObject keydate, int idKeyDate) {
		int a = 0;
		Connection();
		sql = "UPDATE keydate SET subject=? WHERE idKeyDate = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, keydate.getEnonce());
			statement.setInt(2, idKeyDate);
			a = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
		return a;
	}

	public void deleteKeyDate(int idKeyDate) {
		int a;
		Connection();
		sql = "DELETE FROM keydate WHERE idKeyDate = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idKeyDate);
			a = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();

	}
	
	public Object [][] getAllSalle()
	{
		Connection();
		sql="SELECT * FROM salle ORDER BY nom ASC";
		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();
			
			result.last();
			salleList = new Object[result.getRow()][4];
			result.beforeFirst();
			
			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 4; j++) {
					salleList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
		return salleList;
	}
	
	public Object[] getSalleById(int idSalle)
	{
		Connection();
		sql="SELECT * FROM salle WHERE idressource = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSalle);
			result = statement.executeQuery();
			
			salle = new Object[4];
			result.first();
			for (int j = 0; j < 4; j++) {
					salle[j] = result.getObject(j + 1);
				}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
		return salle;
	}
	
	public Object[][] getSalleByCapacity(int nbplace)
	{
		Connection();
		sql="SELECT * FROM salle WHERE nbPlace >= ? ORDER BY nom ASC";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, nbplace);
			result = statement.executeQuery();
			
			result.last();
			salleList = new Object[result.getRow()][4];
			result.beforeFirst();
			
			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 4; j++) {
					salleList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Deconnexion();
		return salleList;
	}
	
	public void addSalle(SalleObject salle)
	{
		Connection();
		sql = "INSERT INTO salle (nom, nbPlace, videoProjecteur)VALUES (?,?,?)";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, salle.getNom());
			statement.setInt(2, salle.getNbPlace());
			statement.setBoolean(3, salle.isProjecteur());
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de la salle");
			e.printStackTrace();
		}
		Deconnexion();
	}
	
	public void updateSalle(SalleObject salle, int idSalle)
	{
		Connection();
		sql="UPDATE salle SET nom = ?, nbPlace = ?, videoProjecteur = ? WHERE idressource = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, salle.getNom());
			statement.setInt(2, salle.getNbPlace());
			statement.setBoolean(3, salle.isProjecteur());
			statement.setInt(4, idSalle);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Echec de la modification");
			e.printStackTrace();
		}
		Deconnexion();
	}
	
	public void deleteSalle(int idSalle)
	{
		Connection();
		sql = "DELETE FROM salle WHERE idressource = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idSalle);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer une salle qui a des cours de programmés. \nVeuillez d'abord changer la salle de ces cours");
			
		}
		Deconnexion();
	}

	public Object[][] getAllEvent()
	{
		Connection();
		sql = "SELECT * FROM event ORDER BY date ASC";
		try {
			statement = conn.prepareStatement(sql);
			result = statement.executeQuery();
			
			result.last();
			eventList = new Object[result.getRow()][5];
			result.beforeFirst();
			
			int i = 0;
			while (result.next()) {
				for (int j = 0; j < 5; j++) {
					eventList[i][j] = result.getObject(j + 1);
				}
				i++;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Aucun évènement dans la base");
			e.printStackTrace();
		}
		Deconnexion();
		return eventList;
	}
	
	public void addEvent(EventObject event)
	{
		Connection();
		sql = "INSERT INTO event (nom, description, lieu, date) Values(?,?,?,?)";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, event.getNom());
			statement.setString(2, event.getDescription());
			statement.setString(3, event.getLieu());
			statement.setDate(4, (java.sql.Date) event.getDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Echec de l'ajout");
			e.printStackTrace();
		}
		Deconnexion();
	}
	
	public void modifyEvent(EventObject event, int idEvent)
	{
		Connection();
		sql = "UPDATE event SET nom = ?, description = ?, lieu = ?, date = ? WHERE idEvent = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, event.getNom());
			statement.setString(2, event.getDescription());
			statement.setString(3, event.getLieu());
			statement.setDate(4, event.getDate());
			statement.setInt(5, idEvent);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Echec de l'update");
			e.printStackTrace();
		}
		Deconnexion();
	}
	
	public void deleteEvent(int idEvent)
	{
		
		Connection();
		sql = "DELETE FROM event WHERE idEvent = ?";
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, idEvent);
			statement.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Echec de la suppression");
			
		}
		Deconnexion();
	}
	
	public void Deconnexion() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
