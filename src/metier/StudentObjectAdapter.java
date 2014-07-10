package metier;

import javax.swing.table.AbstractTableModel;

import bdd.DatabaseAccess;

public class StudentObjectAdapter extends AbstractTableModel {
	
	private DatabaseAccess data = new DatabaseAccess();
	private StudentObject[] student;
	private Object[][] object;
	private final String[] entetes = {"Prenom", "Nom", "Présent", "Retard"};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
