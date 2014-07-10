package interfaceGraphique.fenetre;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import metier.AppelObjectAdapter;
import metier.ScheduleObjectAdapter;
import bdd.DatabaseAccess;

public class Students extends JFrame {

	private AppelObjectAdapter adapter;
	private JButton valider, annuler;
	private JPanel panel;
	private JTable tableau;
	private DatabaseAccess data = new DatabaseAccess();
	private int selection, selectedSchedule, idSchedule;
	private ScheduleObjectAdapter adapter2;

	public Students(ScheduleObjectAdapter adapter2, int selectedSchedule, int idSchedule) {
		this.adapter2 = adapter2;
		this.selectedSchedule = selectedSchedule;
		this.idSchedule = idSchedule;
		adapter = new AppelObjectAdapter(
				Students.this.idSchedule);
		setTitle("Liste d'émargement");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width / 2, d.height / 2);
		setLocationRelativeTo(null);

		valider = new JButton("Valider");
		valider.addMouseListener(new MouseListener() {

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
				
				
				
				for (int i = 0; i < adapter.getRowCount(); i++) {
					
					data.addPresence(
							(boolean) adapter.getValueAt(i, 2),
							(boolean) adapter.getValueAt(i, 3),
							Students.this.adapter2,
							Students.this.selectedSchedule,
							Students.this.idSchedule,
							
							adapter.getIdStudent(i));
				}
				setVisible(false);

			}
		});

		annuler = new JButton("Annuler");
		annuler.addMouseListener(new MouseListener() {

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
		panel.add(annuler);

		tableau = new JTable(adapter);
		tableau.setAutoCreateRowSorter(true);
		
		tableau.getColumnModel().getColumn(3)
				.setCellRenderer(new TableCellRenderer() {

					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						boolean marked1 = (Boolean) value;
						JCheckBox rendererComponent1 = new JCheckBox();
						if (marked1) {
							{
								rendererComponent1.setSelected(true);
								tableau.setValueAt(true, row, column);
							}

						} else {
							rendererComponent1.setSelected(false);
							tableau.setValueAt(false, row, column);
						}
						return rendererComponent1;
					}
				});

		tableau.getColumnModel().getColumn(2)
				.setCellRenderer(new TableCellRenderer() {

					public Component getTableCellRendererComponent(
							JTable table, Object value1, boolean isSelected,
							boolean hasFocus, int row, int column) {
						boolean marked = (Boolean) value1;
						JCheckBox rendererComponent = new JCheckBox();
						if (marked) {
							{

								rendererComponent.setSelected(true);
								tableau.setValueAt(true, row, column);
							}

						} else {
							rendererComponent.setSelected(false);
							tableau.setValueAt(false, row, column);
						}
						return rendererComponent;
					}
				});

		getContentPane().add(new JScrollPane(tableau));
		getContentPane().add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}

}
