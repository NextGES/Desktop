package interfaceGraphique.jpanel;

import interfaceGraphique.fenetre.EdtSalle;
import interfaceGraphique.jdialog.AddSalle;
import interfaceGraphique.jdialog.ModifySalle;

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

public class Salle extends JPanel{

	private JButton edt, modifier, ajouter, supprimer;
	private JPanel panel;
	private JTable tableau;
	private int selection;
	private SalleObjectAdapter adapter;
	DatabaseAccess data;
	ScheduleObjectAdapter adapter2;
	
	
	public Salle()
	{
		data = new DatabaseAccess();
		ajouter = new  JButton("Ajouter une salle");
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
				AddSalle add = new AddSalle(null, true, tableau);
				
			}
		});
		
		modifier = new JButton("Modifier une salle");
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
				ModifySalle modify = new ModifySalle(null, true, tableau);
				
			}
		});
		
		supprimer = new JButton("Supprimer une salle");
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
				
				adapter = new SalleObjectAdapter();
				data.deleteSalle(adapter.getSalleId(tableau.getSelectedRow()));
				adapter = new SalleObjectAdapter();
				adapter.fireTableDataChanged();
				tableau.setModel(adapter);
				
			}
		});
		
		edt = new JButton("Afficher l'emploi du temps de la salle");
		edt.addMouseListener(new MouseListener() {
			
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
				selection = tableau.getSelectedRow();
				adapter = new SalleObjectAdapter();
				EdtSalle edt = new EdtSalle(adapter.getSalleId(selection));
				 
			}
		});
				
		
		panel = new JPanel();
		panel.add(ajouter);
		panel.add(modifier);
		panel.add(supprimer);
		panel.add(edt);
		tableau = new JTable(new SalleObjectAdapter());
		tableau.setAutoCreateRowSorter(true);
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(new JScrollPane(tableau));
		this.add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}
}
