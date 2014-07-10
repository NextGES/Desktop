package interfaceGraphique.fenetre;

import interfaceGraphique.jdialog.AddKeyDate;
import interfaceGraphique.jdialog.ModifyKeyDate;
import interfaceGraphique.renderer.UserTypeRenderer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import metier.KeyDateObjectAdapter;
import metier.AppelObjectAdapter;
import metier.ScheduleObjectAdapter;
import metier.UserObjectAdapter;
import bdd.DatabaseAccess;

public class KeyDate extends JFrame {

	private KeyDateObjectAdapter adapter;
	private JButton ajouter, modifier, supprimer;
	private JPanel panel;
	private JTable tableau;
	private DatabaseAccess data = new DatabaseAccess();
	private int selection, selectedSchedule, idSchedule, idUser;
	private ScheduleObjectAdapter adapter2;
	private String matiere, classe;

	public KeyDate(ScheduleObjectAdapter adapter2, int selectedSchedule, int idSchedule) {
		
		this.adapter2 = adapter2;
		this.selectedSchedule = selectedSchedule;
		this.idSchedule = idSchedule;
		this.matiere = (String)adapter2.getValueAt(selectedSchedule, 4);
		this.classe = (String)adapter2.getValueAt(selectedSchedule, 3);
		adapter = new KeyDateObjectAdapter(
		KeyDate.this.idSchedule,KeyDate.this.adapter2,KeyDate.this.selectedSchedule);
		idUser = data.getIdUser(adapter2.getIdUser());
		
		setTitle("Liste des rappels importants pour ce cours");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width / 2, d.height / 2);
		setLocationRelativeTo(null);

		ajouter = new JButton("Ajouter un rappel");
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
				AddKeyDate date = new AddKeyDate(null, true, tableau, matiere, classe, KeyDate.this.adapter2, KeyDate.this.selectedSchedule, KeyDate.this.idUser);
			}});

		modifier = new JButton("Modifier un rappel");
		modifier.addMouseListener(new MouseListener() {

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
					adapter = new KeyDateObjectAdapter(
							KeyDate.this.idSchedule,KeyDate.this.adapter2,KeyDate.this.selectedSchedule); 
					ModifyKeyDate modify = new ModifyKeyDate(null, true, tableau, KeyDate.this.adapter2, KeyDate.this.adapter, KeyDate.this.idSchedule);
				
			}
		});
		
		supprimer = new JButton("Supprimer un rappel");
		supprimer.addMouseListener(new MouseListener() {
			
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
				try {
					
					selection = tableau.getSelectedRow();
					data.deleteKeyDate(adapter.getKeyDateId(selection));
					adapter = new KeyDateObjectAdapter(KeyDate.this.idSchedule,KeyDate.this.adapter2,KeyDate.this.selectedSchedule);
					adapter.fireTableDataChanged();
					tableau.setModel(adapter);
					
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(getParent(), "Veuillez sélectionner un rappel");
					
				}catch (Exception e2){
					e2.printStackTrace();
				}
				
			}
				
			
		});

		panel = new JPanel();
		panel.add(ajouter);
		panel.add(modifier);
		panel.add(supprimer);

		tableau = new JTable(new KeyDateObjectAdapter(KeyDate.this.idSchedule,KeyDate.this.adapter2,KeyDate.this.selectedSchedule));
		tableau.setAutoCreateRowSorter(true);
		
		getContentPane().add(new JScrollPane(tableau));
		getContentPane().add(panel, BorderLayout.SOUTH);
		setVisible(true);
}}
