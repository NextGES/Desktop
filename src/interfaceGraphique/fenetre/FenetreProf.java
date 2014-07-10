package interfaceGraphique.fenetre;

import interfaceGraphique.jpanel.Agenda;
import interfaceGraphique.jpanel.Emargement;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;


public class FenetreProf extends Fenetre {
	
	
	
	public FenetreProf(String login) 
	{
		super();
		onglets = new JTabbedPane();
		onglets.setBackground(Color.white);
		
		Emargement emarg = new Emargement(login);
		onglets.add("Emargement", emarg);
		emarg.setLayout(new BoxLayout(emarg, BoxLayout.Y_AXIS));
		
		
		Agenda agenda = new Agenda(login);
		onglets.add("Date importantes", agenda);
		agenda.setLayout(new BoxLayout(agenda, BoxLayout.Y_AXIS));
		
		
		getContentPane().add(onglets, BorderLayout.CENTER);
		setVisible(true);
	}

}
