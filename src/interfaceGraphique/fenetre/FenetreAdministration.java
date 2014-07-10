package interfaceGraphique.fenetre;

import interfaceGraphique.jpanel.Edt;
import interfaceGraphique.jpanel.Event;
import interfaceGraphique.jpanel.Salle;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;


public class FenetreAdministration extends Fenetre {

	public FenetreAdministration() 
	{
		super();
		onglets = new JTabbedPane();
		onglets.setBackground(Color.white);
		
		Salle ressources = new Salle();
		onglets.add("Gestion des salles", ressources);
		ressources.setLayout(new BoxLayout(ressources, BoxLayout.Y_AXIS));
		
		
		Event event = new Event();
		onglets.add("Gestion des évènements", event);
		event.setLayout(new BoxLayout(event, BoxLayout.Y_AXIS));
		
		Edt edt = new Edt();
		onglets.add("Gestion des emplois du temps", edt);
		edt.setLayout(new BoxLayout(edt, BoxLayout.Y_AXIS));
		
			
		getContentPane().add(onglets, BorderLayout.CENTER);
		setVisible(true);
	}
}
