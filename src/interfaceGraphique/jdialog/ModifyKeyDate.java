package interfaceGraphique.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import metier.KeyDateObject;
import metier.KeyDateObjectAdapter;
import metier.ScheduleObjectAdapter;
import bdd.DatabaseAccess;

public class ModifyKeyDate extends JDialog{
	
	private KeyDateObject KeyDate;
	private KeyDateObjectAdapter adapter; 
	private JTable tableau;
	private JLabel  DetailsLabel;
	private JTextField  Details;
	private Dimension d, halfD;
	private JPanel  panDetails, buttons, content;
	private JButton ok, cancel;
	private DatabaseAccess data;
	private ScheduleObjectAdapter schedule;
	private int selectedSchedule, idSchedule, selection;
		
	public ModifyKeyDate(JFrame parent, boolean modal, JTable tableau, ScheduleObjectAdapter schedule, KeyDateObjectAdapter adapter, int idSchedule) {
		super(parent, "Modifier une date clé", modal);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		d = Toolkit.getDefaultToolkit().getScreenSize();
		//System.out.println(""+d);
		halfD = new Dimension(d.width/2, d.height/2);
		setSize(halfD.width, halfD.height/3);
		setResizable(false);
		setLocationRelativeTo(null);
		this.tableau = tableau;
		this.schedule = schedule;
		selection = tableau.getSelectedRow();
		
		this.adapter = adapter;
		this.idSchedule = idSchedule;
		KeyDate = new KeyDateObject((String)adapter.getValueAt(selection, 0), (String)adapter.getValueAt(selection, 1), (String)adapter.getValueAt(selection, 2));
		this.schedule = schedule;
		
		initWindow();
		
		setVisible(true);
	}
	
	private void initWindow() {

		
		// Details
		panDetails = new JPanel();
		panDetails.setBackground(Color.white);
		panDetails.setPreferredSize(new Dimension(halfD.width, halfD.height / 6));
		Details = new JTextField();
		Details.setPreferredSize(new Dimension(halfD.width / 2, halfD.height / 20));
		//Details.setText(KeyDate.getEnonce());
		panDetails.setBorder(BorderFactory
				.createTitledBorder("Détails"));
		DetailsLabel = new JLabel("Saisir le détails");
		panDetails.add(DetailsLabel);
		panDetails.add(Details);

		
		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panDetails);
		
		
		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			

			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				KeyDate = new KeyDateObject(Details.getText(),KeyDate.getPromo(), KeyDate.getMatiere());
				data = new DatabaseAccess();
				data.updateKeyDate(KeyDate, adapter.getKeyDateId(selection));
				
					adapter = new KeyDateObjectAdapter(ModifyKeyDate.this.idSchedule, ModifyKeyDate.this.schedule, ModifyKeyDate.this.selectedSchedule);
					adapter.fireTableDataChanged();
					tableau.setModel(adapter);
					setVisible(false);
				
				

			}

		});
		
		cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				
			}
		});
		
		buttons = new JPanel();
		buttons.add(ok);
		buttons.add(cancel);
		
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);

	}

}
