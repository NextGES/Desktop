package interfaceGraphique.jpanel;

import interfaceGraphique.jdialog.AddEvent;
import interfaceGraphique.jdialog.ModifyEvent;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import bdd.DatabaseAccess;

import metier.EventObjectAdapter;

public class Event extends JPanel{

	private JButton ajouter, modifier, supprimer;
	private JPanel panel;
	private JTable tableau;
	private int selection;
	private EventObjectAdapter adapter;
	
	
	public Event()
	{
		
		ajouter = new  JButton("Ajouter un évènement");
		ajouter.addMouseListener(new MouseListener() {
			
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
				AddEvent event = new AddEvent(null, true, tableau);
				
			}
		});
		
		modifier = new JButton("Modifier un évènement");
		modifier.addMouseListener(new MouseListener() {
			
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
				ModifyEvent event = new ModifyEvent(null, true, tableau);
				
			}
		});
		
		supprimer = new JButton("Supprimer un évènement");
		supprimer.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				DatabaseAccess data = new DatabaseAccess();
				adapter = new EventObjectAdapter();
				data.deleteEvent(adapter.getEventId(tableau.getSelectedRow()));
				adapter = new EventObjectAdapter();
				adapter.fireTableDataChanged();
				tableau.setModel(adapter);
				
				
			}
		});
		
		panel = new JPanel();
		panel.add(ajouter);
		panel.add(modifier);
		panel.add(supprimer);
		tableau = new JTable(new EventObjectAdapter());
		tableau.setAutoCreateRowSorter(true);
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(new JScrollPane(tableau));
		this.add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}

}
