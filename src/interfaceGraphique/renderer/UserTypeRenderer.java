package interfaceGraphique.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

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
