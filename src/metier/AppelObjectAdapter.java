package metier;

import javax.swing.table.AbstractTableModel;

import bdd.DatabaseAccess;

public class AppelObjectAdapter extends AbstractTableModel {

	private DatabaseAccess data = new DatabaseAccess();
	private final AppelObject[] presence;
	private Object[][] object;
	private Object[] schedule, presenceList;
	private int[] idList;
	private final String[] entetes = { "Prenom", "Nom", "Présent", "Retard" };

	public AppelObjectAdapter(int idSchedule) {
		super();
		schedule = data.getSchedule2(idSchedule);
		
		object = data.getStudent((int) schedule[4]);
		
		presence = new AppelObject[object.length];
				
		idList = new int[object.length];

		
		for (int i = 0; i < object.length; i++) {
			idList[i] = (int)object[i][0];
			presenceList = data.checkPresence(idSchedule, (int)object[i][0]);
			if (presenceList.length==0) {
				presence[i] = new AppelObject((String) object[i][2],
						(String) object[i][1], false, false);
				
				
			} else {
				
				presence[i] = new AppelObject((String) object[i][2],
						(String) object[i][1], (boolean) presenceList[1],
						(boolean) presenceList[2]);
			}

		}
	}

	public int getIdStudent(int selection)
	{
		return idList[selection];
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
	public Class<?> getColumnClass(int column) {

		return this.getValueAt(0, column).getClass();
	}

	@Override
	public int getRowCount() {
		return presence.length;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 2 || columnIndex == 3)

			return true;
		else
			return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return presence[rowIndex].getPrenom();
		case 1:
			return presence[rowIndex].getNom();
		case 2:
			return presence[rowIndex].isPresent();
		case 3:
			return presence[rowIndex].isLate();
		default:
			return null;
		}
	}

	public void setValueAt(Object object, int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 2:
			presence[rowIndex].setPresent((boolean) object);
			break;
		case 3:
			presence[rowIndex].setLate((boolean) object);
			break;

		default:
			break;

		}

	}
}
