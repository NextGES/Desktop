package interfaceGraphique.jpanel;

import interfaceGraphique.fenetre.EdtSalle;
import interfaceGraphique.jdialog.AddSchedule;
import interfaceGraphique.jdialog.ModifySchedule;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import metier.SalleObjectAdapter;
import metier.ScheduleObjectAdapter;
import bdd.DatabaseAccess;

public class Edt extends JPanel{

	private JButton modifier, ajouter, supprimer;
	private JPanel panel;
	private JTable tableau;
	private int selection;
	private SalleObjectAdapter adapter;
	DatabaseAccess data;
	ScheduleObjectAdapter adapter2;
	
	
	public Edt()
	{
		data = new DatabaseAccess();
		ajouter = new  JButton("Ajouter un cours");
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
								
				//adapter = new SalleObjectAdapter();
				AddSchedule schedule = new AddSchedule(null, true, tableau);
				
			}
		});
		
		modifier = new JButton("Modifier un cours");
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
				ModifySchedule schedule = new ModifySchedule(null, true, tableau);
				
			}
		});
		
		supprimer = new JButton("Supprimer un cours");
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
				
				adapter2 = new ScheduleObjectAdapter();
				data.deleteSchedule((int)adapter2.getValueAt(tableau.getSelectedRow(), 7));
				adapter2 = new ScheduleObjectAdapter();
				adapter2.fireTableDataChanged();
				tableau.setModel(adapter2);
				
			}
		});
		
				
		panel = new JPanel();
		panel.add(ajouter);
		panel.add(modifier);
		panel.add(supprimer);
		tableau = new JTable(new ScheduleObjectAdapter());
		tableau.setAutoCreateRowSorter(true);
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(new JScrollPane(tableau));
		this.add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}

}
