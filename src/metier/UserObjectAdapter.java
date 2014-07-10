package metier;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import bdd.DatabaseAccess;

public class UserObjectAdapter extends AbstractTableModel{

	private DatabaseAccess data = new DatabaseAccess();
	private final UserObject[] user;
	private Object[][] object = data.getAllUser();
	private final String[] entetes = {"Login", "Mot de passe", "Type d'utilisateur"};
		
	public UserObjectAdapter(){
		super();
		user = new UserObject[object.length]; 
		for(int i=0; i<object.length;i++)
		{
			
			user[i]=new UserObject(object[i][0], object[i][1], object[i][2]);
			
		}
				
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
		return user.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
        case 0:
            return user[rowIndex].getLogin();
        case 1:
            return user[rowIndex].getMdp();
        case 2:
            return user[rowIndex].isProf();
        default:
            return null;
		
	}
		
		
}
	
	public class UserTypeRenderer extends DefaultTableCellRenderer
	{
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			
			Boolean isProf = (Boolean)value;
			if(isProf)
				setText("Professeur");
			else setText("Administration");
			return this;
		}
	}
}
