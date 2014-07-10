package metier;

import java.sql.Date;

import javax.swing.table.AbstractTableModel;

import bdd.DatabaseAccess;

public class KeyDateObjectAdapter extends AbstractTableModel {

	private DatabaseAccess data = new DatabaseAccess();
	private final KeyDateObject[] keydate;
	private Object[][] object, keydateList;
	private Object[] schedule;
	private int[] idList;
	private final String[] entetes = { "Sujet", "Classe", "Matière" };
	
	public KeyDateObjectAdapter(int idSchedule, ScheduleObjectAdapter schedule2, int selectedSchedule)
	{
		schedule = data.getSchedule2(idSchedule);
		object = data.getKeyDate((int) schedule[4]);
		
		keydate = new KeyDateObject[object.length];
		
		for(int i=0; i<object.length; i++)
		{
			keydate[i]= new KeyDateObject( (String)object[i][1], (String)schedule2.getValueAt(selectedSchedule, 3), (String)schedule2.getValueAt(selectedSchedule, 4));
		}
	}
	
	public int getKeyDateId(int row)
	{
		return (int)object[row][0];
	}

	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return keydate.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return entetes[column];
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
        case 0:
            return keydate[rowIndex].getEnonce();
        case 1:
            return keydate[rowIndex].getPromo();
        case 2:
        	return keydate[rowIndex].getMatiere();
       default:
            return null;
		
	}
	}
	
	
}
