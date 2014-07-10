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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import metier.EventObject;
import metier.EventObjectAdapter;
import metier.KeyDateObject;
import metier.KeyDateObjectAdapter;
import metier.ScheduleObjectAdapter;
import bdd.DatabaseAccess;

public class AddEvent extends JDialog {

	
	private EventObjectAdapter adapter;
	private JTable tableau;
	private JLabel NomLabel, DescriptionLabel, LieuLabel, DateLabel;
	private JTextField Nom, Description, Lieu, Annee, Mois, Jour;
	private Dimension d, halfD;
	private JPanel panNom, panDescription, panLieu, panDate, buttons, content;
	private JButton ok, cancel;
	private DatabaseAccess data;
	private String matiere, classe;
	private ScheduleObjectAdapter schedule;
	private Date date;

	public AddEvent(JFrame parent, boolean modal, JTable tableau) {

		super(parent, "Ajouter un évènement", modal);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		d = Toolkit.getDefaultToolkit().getScreenSize();

		halfD = new Dimension(d.width / 2, d.height / 2);
		setSize(halfD.width, 2*halfD.height/3);
		setResizable(false);
		setLocationRelativeTo(null);
		this.tableau = tableau;

		initWindow();

		setVisible(true);
	}

	private void initWindow() {

		// Nom
		panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(halfD.width, halfD.height / 8));
		Nom = new JTextField();
		Nom.setPreferredSize(new Dimension(halfD.width / 2, halfD.height / 20));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		NomLabel = new JLabel("Saisir le nom de l'évènement");
		panNom.add(NomLabel);
		panNom.add(Nom);

		// Description
		panDescription = new JPanel();
		panDescription.setBackground(Color.white);
		panDescription.setPreferredSize(new Dimension(halfD.width,
				halfD.height / 8));
		Description = new JTextField();
		Description.setPreferredSize(new Dimension(halfD.width / 2,
				halfD.height / 20));
		panDescription.setBorder(BorderFactory
				.createTitledBorder("Description"));
		DescriptionLabel = new JLabel("Saisir la description de l'évènement");
		panDescription.add(DescriptionLabel);
		panDescription.add(Description);

		// Lieu
		panLieu = new JPanel();
		panLieu.setBackground(Color.white);
		panLieu.setPreferredSize(new Dimension(halfD.width, halfD.height / 8));
		Lieu = new JTextField();
		Lieu.setPreferredSize(new Dimension(halfD.width / 2, halfD.height / 20));
		panLieu.setBorder(BorderFactory.createTitledBorder("Lieu"));
		LieuLabel = new JLabel("Saisir le lieu de l'évènement");
		panLieu.add(LieuLabel);
		panLieu.add(Lieu);

		// Date
		panDate = new JPanel();
		panDate.setBackground(Color.white);
		panDate.setPreferredSize(new Dimension(halfD.width, halfD.height / 8));
		Annee = new JTextField();
		Annee.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));
		Mois = new JTextField();
		Mois.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));
		Jour = new JTextField();
		Jour.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));
		panDate.setBorder(BorderFactory.createTitledBorder("Date"));
		DateLabel = new JLabel("Saisir la date au format AAAA-MM-JJ");
		panDate.add(DateLabel);
		panDate.add(Annee);
		panDate.add(Mois);
		panDate.add(Jour);

		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panDescription);
		content.add(panLieu);
		content.add(panDate);
		

		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					date = new Date(Integer.parseInt(Annee.getText())-1900, Integer.parseInt(Mois.getText())-1, Integer.parseInt(Jour.getText()));
					EventObject event = new EventObject(Nom.getText(), Description.getText(), Lieu.getText(), date);
					data = new DatabaseAccess();
					data.addEvent(event);

					adapter = new EventObjectAdapter();
					adapter.fireTableDataChanged();
					tableau.setModel(adapter);
					setVisible(false);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Veuillez entrer une date correcte");
					e1.printStackTrace();
				}

				

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
