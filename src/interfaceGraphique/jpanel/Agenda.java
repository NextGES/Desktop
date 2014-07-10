package interfaceGraphique.jpanel;

import interfaceGraphique.fenetre.KeyDate;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import metier.ScheduleObjectAdapter;

public class Agenda extends JPanel {

	private JButton agenda;
	private JPanel panel;
	private JTable tableau;
	private int selection;
	private ScheduleObjectAdapter adapter;
	private String login;
	
	public Agenda(String login)
	{
		this.login = login;
		agenda = new  JButton("Ouvrir l'agenda");
		agenda.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selection = tableau.getSelectedRow();
				
				adapter = new ScheduleObjectAdapter(Agenda.this.login);
				try {
					KeyDate keydate = new KeyDate(adapter, selection, (int)adapter.getValueAt(selection, 7));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un cours");
					e.printStackTrace();
				}
				
			}
		});
		
		panel = new JPanel();
		panel.add(agenda);
		tableau = new JTable(new ScheduleObjectAdapter(this.login));
		tableau.setAutoCreateRowSorter(true);
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(new JScrollPane(tableau));
		this.add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}

}
