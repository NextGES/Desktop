package interfaceGraphique.fenetre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import metier.AppelObjectAdapter;
import metier.SalleObjectAdapter;
import metier.ScheduleObjectAdapter;
import bdd.DatabaseAccess;

public class EdtSalle extends JFrame{
	
	private SalleObjectAdapter adapter;
	private JButton valider;
	private JPanel panel;
	private JTable tableau;
	private DatabaseAccess data = new DatabaseAccess();
	private int selection, selectedSchedule, idSchedule;
	private ScheduleObjectAdapter adapter2;
	
	public EdtSalle(int idSalle)
	{
		
		setTitle("Emploi du temps de la salle");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width / 2, d.height / 2);
		setLocationRelativeTo(null);
		
		data = new DatabaseAccess();
		valider = new JButton("Ok");
		valider.addMouseListener(new MouseListener() {
			
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
				setVisible(false);
				
			}
		});
		panel = new JPanel();
		panel.add(valider);
		tableau = new JTable(new ScheduleObjectAdapter(idSalle));
		tableau.setAutoCreateRowSorter(true);
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(new JScrollPane(tableau));
		this.add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}
	

}
