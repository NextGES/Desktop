package interfaceGraphique.jpanel;

import interfaceGraphique.fenetre.Students;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import metier.ScheduleObjectAdapter;

public class Emargement extends JPanel {
	
	private JButton appel;
	private JPanel panel;
	private JTable tableau;
	private int selection;
	private ScheduleObjectAdapter adapter;
	private String login;
	
	public Emargement(String login)
	{
		this.login = login;
		appel = new  JButton("Faire l'appel");
		appel.addMouseListener(new MouseListener() {
			
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
				
				adapter = new ScheduleObjectAdapter(Emargement.this.login);
				Students students = new Students(adapter, selection, (int)adapter.getValueAt(selection, 7));
				
			}
		});
		
		panel = new JPanel();
		panel.add(appel);
		tableau = new JTable(new ScheduleObjectAdapter(this.login));
		tableau.setAutoCreateRowSorter(true);
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(new JScrollPane(tableau));
		this.add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}

}
