package metier;

import javax.swing.table.AbstractTableModel;

import bdd.DatabaseAccess;

public class SalleObjectAdapter extends AbstractTableModel {

	private DatabaseAccess data = new DatabaseAccess();
	private final SalleObject[] salle;
	private Object[][] object = data.getAllSalle();
	private final String[] entetes = { "Nom", "Nombre de place",
			"Vidéoprojecteur" };

	public SalleObjectAdapter()
	{
		super();
		salle = new SalleObject[object.length];
		for(int i=0; i<object.length;i++)
		{
			salle[i]=new SalleObject((String)object[i][1], (int)object[i][2], (boolean)object[i][3]);
		}
	}
	
	public int getSalleId(int selectedRow)
	{
		return (int)object[selectedRow][0];
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
		return salle.length;
	}
	
	@Override
	public Class<?> getColumnClass(int column) {

		return this.getValueAt(0, column).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
        case 0:
            return salle[rowIndex].getNom();
        case 1:
            return salle[rowIndex].getNbPlace();
        case 2:
            return salle[rowIndex].isProjecteur();
        default:
            return null;
		
	}

}
}
