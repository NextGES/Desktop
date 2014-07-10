package metier;

import java.sql.Date;

import javax.swing.table.AbstractTableModel;

import bdd.DatabaseAccess;

public class EventObjectAdapter extends AbstractTableModel{

	private final String[] entetes = {"Nom", "Description", "Lieu", "Date"};
	private DatabaseAccess data = new DatabaseAccess();
	private EventObject[] event;
	private Object[][] object;
	
	
	public EventObjectAdapter() {
		
		object = data.getAllEvent();
		event = new EventObject[object.length]; 
		for(int i=0; i<object.length;i++)
		{					
			event[i] = new EventObject((String)object[i][1], (String)object[i][2], (String)object[i][3], (Date)object[i][4]);
			
		}
	}
	
	public int getEventId(int selectedRow)
	{
		return (int)object[selectedRow][0];
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}

	@Override
	public int getRowCount() {
		return event.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return entetes[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
        case 0:
            return event[rowIndex].getNom();
        case 1:
            return event[rowIndex].getDescription();
        case 2:
            return event[rowIndex].getLieu();
        case 3:
        	return event[rowIndex].getDate();
       default:
            return null;
		
	}

}
}
