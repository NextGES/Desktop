package interfaceGraphique.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import metier.ScheduleObjectAdapter;
import bdd.DatabaseAccess;

public class ModifySchedule extends JDialog {

	private ScheduleObjectAdapter adapter;
	private JTable tableau;
	private JLabel DateLabel, HeureLabel;
	private JTextField Annee, Mois, Jour, HeureDebut, MinuteDebut,
			SecondeDebut, HeureFin, MinuteFin, SecondeFin;
	private JComboBox courseSpecialityChoice, subjectChoice, salleChoice;
	private Dimension d, halfD;
	private JPanel panDate, panHeureDebut, panHeureFin, panCourse, buttons,
			content;
	private JButton ok, cancel;
	private DatabaseAccess data;
	private int chosenCours, chosenSubject, chosenSalle;
	private String[] courseSpecialityStringList, subjectStringList,
			salleStringList;
	private Object[][] courseSpecialityList, subjectList, salleList;
	private Date date;
	private Time debut, fin;

	public ModifySchedule(JFrame parent, boolean modal, JTable tableau) {
		super(parent, "Modifier un cours", modal);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		d = Toolkit.getDefaultToolkit().getScreenSize();

		halfD = new Dimension(d.width / 2, d.height / 2);
		setSize(halfD.width, 2 * halfD.height / 3);
		setResizable(false);
		setLocationRelativeTo(null);
		this.tableau = tableau;
		data = new DatabaseAccess();
		adapter = new ScheduleObjectAdapter();
		courseSpecialityList = data.getAllCourseSpeciality();

		subjectList = data.getAllSubject();
		salleList = data.getAllSalle();

		courseSpecialityStringList = new String[courseSpecialityList.length + 1];
		subjectStringList = new String[subjectList.length + 1];
		salleStringList = new String[salleList.length + 1];

		courseSpecialityStringList[0] = "Classe...";
		subjectStringList[0] = "Matière...";
		salleStringList[0] = "Salle...";

		date = (Date) adapter.getValueAt(tableau.getSelectedRow(), 0);
		date.setYear(date.getYear() + 1900);
		date.setMonth(date.getMonth() + 1);
		debut = (Time) adapter.getValueAt(tableau.getSelectedRow(), 1);
		fin = (Time) adapter.getValueAt(tableau.getSelectedRow(), 2);

		for (int i = 0; i < courseSpecialityList.length; i++) {
			courseSpecialityStringList[i + 1] = (String) courseSpecialityList[i][2];
		}

		for (int i = 0; i < subjectList.length; i++) {
			subjectStringList[i + 1] = (String) subjectList[i][1];
		}

		for (int i = 0; i < salleList.length; i++) {
			salleStringList[i + 1] = (String) salleList[i][1];
		}

		initWindow();
		setVisible(true);
	}

	private void initWindow() {

		// Date
		panDate = new JPanel();
		panDate.setBackground(Color.white);
		panDate.setPreferredSize(new Dimension(halfD.width, halfD.height / 9));
		Annee = new JTextField();
		Annee.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));
		Annee.setText("" + date.getYear());

		Mois = new JTextField();
		Mois.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));
		Mois.setText("" + date.getMonth());

		Jour = new JTextField();
		Jour.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));
		Jour.setText("" + date.getDate());

		panDate.setBorder(BorderFactory.createTitledBorder("Date"));
		DateLabel = new JLabel("Saisir la date au format AAAA-MM-JJ");
		panDate.add(DateLabel);
		panDate.add(Annee);
		panDate.add(Mois);
		panDate.add(Jour);

		// HeureDebut
		panHeureDebut = new JPanel();
		panHeureDebut.setBackground(Color.white);
		panHeureDebut.setPreferredSize(new Dimension(halfD.width,
				halfD.height / 9));
		HeureDebut = new JTextField();
		HeureDebut.setPreferredSize(new Dimension(halfD.width / 4,
				halfD.height / 20));
		HeureDebut.setText("" + debut.getHours());

		MinuteDebut = new JTextField();
		MinuteDebut.setPreferredSize(new Dimension(halfD.width / 4,
				halfD.height / 20));
		MinuteDebut.setText("" + debut.getMinutes());

		SecondeDebut = new JTextField();
		SecondeDebut.setPreferredSize(new Dimension(halfD.width / 4,
				halfD.height / 20));
		SecondeDebut.setText("" + debut.getSeconds());

		panHeureDebut.setBorder(BorderFactory
				.createTitledBorder("Heure de début du cours"));
		HeureLabel = new JLabel("Saisir l'heure au format HH-MM-SS");
		panHeureDebut.add(HeureLabel);
		panHeureDebut.add(HeureDebut);
		panHeureDebut.add(MinuteDebut);
		panHeureDebut.add(SecondeDebut);

		// HeureFin
		panHeureFin = new JPanel();
		panHeureFin.setBackground(Color.white);
		panHeureFin.setPreferredSize(new Dimension(halfD.width,
				halfD.height / 9));
		HeureFin = new JTextField();
		HeureFin.setPreferredSize(new Dimension(halfD.width / 4,
				halfD.height / 20));
		HeureFin.setText("" + fin.getHours());

		MinuteFin = new JTextField();
		MinuteFin.setPreferredSize(new Dimension(halfD.width / 4,
				halfD.height / 20));
		MinuteFin.setText("" + fin.getMinutes());

		SecondeFin = new JTextField();
		SecondeFin.setPreferredSize(new Dimension(halfD.width / 4,
				halfD.height / 20));
		SecondeFin.setText("" + fin.getSeconds());

		panHeureFin.setBorder(BorderFactory
				.createTitledBorder("Heure de fin du cours"));
		HeureLabel = new JLabel("Saisir l'heure au format HH-MM-SS");
		panHeureFin.add(HeureLabel);
		panHeureFin.add(HeureFin);
		panHeureFin.add(MinuteFin);
		panHeureFin.add(SecondeFin);

		// CourseSpeciality
		panCourse = new JPanel();
		panCourse.setBackground(Color.white);
		panCourse
				.setPreferredSize(new Dimension(halfD.width, halfD.height / 9));
		courseSpecialityChoice = new JComboBox(courseSpecialityStringList);
		courseSpecialityChoice.setSelectedItem(adapter.getValueAt(
				tableau.getSelectedRow(), 3));
		chosenCours = courseSpecialityChoice.getSelectedIndex();
		courseSpecialityChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chosenCours = (int) courseSpecialityChoice.getSelectedIndex();

			}
		});
		panCourse.setBorder(BorderFactory
				.createTitledBorder("Promotion, Classe & Matière"));
		panCourse.add(courseSpecialityChoice);

		// Subject
		subjectChoice = new JComboBox(subjectStringList);
		subjectChoice.setSelectedItem(adapter.getValueAt(
				tableau.getSelectedRow(), 4));
		chosenSubject = subjectChoice.getSelectedIndex();
		subjectChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chosenSubject = (int) subjectChoice.getSelectedIndex();

			}
		});
		panCourse.add(subjectChoice);

		// Salle
		salleChoice = new JComboBox(salleStringList);
		salleChoice.setSelectedItem(adapter.getValueAt(
				tableau.getSelectedRow(), 5));
		chosenSalle = salleChoice.getSelectedIndex();
		salleChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chosenSalle = (int) salleChoice.getSelectedIndex();

			}
		});
		panCourse.add(salleChoice);

		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panDate);
		content.add(panHeureDebut);
		content.add(panHeureFin);
		content.add(panCourse);

		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					date = new Date(Integer.parseInt(Annee.getText()) - 1900,
							Integer.parseInt(Mois.getText()) - 1, Integer
									.parseInt(Jour.getText()));
					debut = new Time(Integer.parseInt(HeureDebut.getText()),
							Integer.parseInt(MinuteDebut.getText()), Integer
									.parseInt(SecondeDebut.getText()));
					fin = new Time(Integer.parseInt(HeureFin.getText()), Integer
							.parseInt(MinuteFin.getText()), Integer
							.parseInt(SecondeFin.getText()));
					data = new DatabaseAccess();
					data.updateSchedule(date, debut, fin,
							(int) courseSpecialityList[chosenCours - 1][0],
							(int) subjectList[chosenSubject - 1][0],
							(int) salleList[chosenSalle - 1][0],
							(int) adapter.getValueAt(tableau.getSelectedRow(), 7));

					adapter = new ScheduleObjectAdapter();
					adapter.fireTableDataChanged();
					tableau.setModel(adapter);
					setVisible(false);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Veuillez entrez une date et une heure correcte");
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
