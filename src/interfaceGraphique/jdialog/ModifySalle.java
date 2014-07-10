package interfaceGraphique.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import metier.SalleObject;
import metier.SalleObjectAdapter;
import bdd.DatabaseAccess;

public class ModifySalle extends JDialog{

	private JTable tableau;
	private JLabel NomLabel, NbPlaceLabel;
	private JTextField Nom, NbPlace;
	private Dimension d, halfD;
	private JPanel panNom, panNbPlace, panVideo, buttons, content;
	private JButton ok, cancel;
	private DatabaseAccess data;
	private JRadioButton oui, non;
	private SalleObject salle;
	private SalleObjectAdapter adapter;
	private boolean hasProj;
	private int selection;

	public ModifySalle(JFrame parent, boolean modal, JTable tableau) {
		super(parent, "Modifier une salle", modal);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		d = Toolkit.getDefaultToolkit().getScreenSize();
		halfD = new Dimension(d.width / 2, d.height / 2);
		setSize(halfD.width, 2*halfD.height/3);
		setResizable(false);
		setLocationRelativeTo(null);
		this.tableau = tableau;
		selection = tableau.getSelectedRow();
		adapter = new SalleObjectAdapter();
		salle = new SalleObject((String)adapter.getValueAt(selection, 0), (int)adapter.getValueAt(selection, 1), (boolean)adapter.getValueAt(selection, 2));
		initWindow();

		setVisible(true);
	}

	private void initWindow() {

		// Nom
		panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(halfD.width, halfD.height / 6));
		Nom = new JTextField();
		Nom.setPreferredSize(new Dimension(halfD.width / 2, halfD.height / 20));
		Nom.setText(salle.getNom());
		panNom.setBorder(BorderFactory.createTitledBorder("Nom"));
		NomLabel = new JLabel("Saisir le nom de la salle");
		panNom.add(NomLabel);
		panNom.add(Nom);

		// nbPlace
		panNbPlace = new JPanel();
		panNbPlace.setBackground(Color.white);
		panNbPlace
				.setPreferredSize(new Dimension(halfD.width, halfD.height / 6));
		NbPlace = new JTextField();
		NbPlace.setPreferredSize(new Dimension(halfD.width / 2,
				halfD.height / 20));
		NbPlace.setText(""+salle.getNbPlace());
		panNbPlace.setBorder(BorderFactory
				.createTitledBorder("Nombre de place"));
		NbPlaceLabel = new JLabel("Saisir le nombre de place dans la salle");
		panNbPlace.add(NbPlaceLabel);
		panNbPlace.add(NbPlace);

		// Avec ou sans vidéoprojecteur
		panVideo = new JPanel();
		panVideo.setBackground(Color.white);
		panVideo.setPreferredSize(new Dimension(halfD.width, halfD.height / 6));
		panVideo.setBorder(BorderFactory
				.createTitledBorder("A un vidéoprojecteur"));
		oui = new JRadioButton("Oui");
		non = new JRadioButton("non");
		
		if(salle.isProjecteur())
		{
			oui.setSelected(true);
		}
		else non.setSelected(true);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(oui);
		bg.add(non);
		panVideo.add(oui);
		panVideo.add(non);

		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panNbPlace);
		content.add(panVideo);

		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (getType().equals("Oui"))
					hasProj = true;
				else
					hasProj = false;
				int a = 0;

				try {
					a = Integer.parseInt(NbPlace.getText());
					salle = new SalleObject(Nom.getText(), a, hasProj);
					data = new DatabaseAccess();
					data.updateSalle(salle, adapter.getSalleId(selection));

					adapter = new SalleObjectAdapter();
					adapter.fireTableDataChanged();
					tableau.setModel(adapter);
					setVisible(false);
				} catch (NumberFormatException e1) {
					JOptionPane
							.showMessageDialog(null,
									"Veuillez entrez un nombre entier dans la case 'nombre de place'");
					
				}

			}
			public String getType() {
				return (oui.isSelected()) ? oui.getText() : (non
						.isSelected()) ? non.getText() : oui
						.getText();
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
