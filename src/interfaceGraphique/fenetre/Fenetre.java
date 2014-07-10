package interfaceGraphique.fenetre;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import securité.Authentification;

import bdd.DatabaseAccess;

public class Fenetre extends JFrame {

	protected JTabbedPane onglets;
	protected DatabaseAccess data = new DatabaseAccess();
	protected JTable tableau;
	protected JPanel panel;
	protected JButton add, remove, modify;
	protected JMenuBar menuBar;
	protected JMenu fichier;
	protected JMenuItem deco, close;
	private SplashWindow window;
	private ImageIcon img = new ImageIcon("logo_footer_ges.png");
	private Color color = new Color(0, 0, 0, 0);
	protected int selection;

	public Fenetre() {
		
		menuBar = new JMenuBar();
		fichier = new JMenu("Fichier");
		deco = new JMenuItem("Déconnexion");
		close = new JMenuItem("Fermer");
		

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				setTitle("NextGes");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
				setSize(d.width/2, d.height/2);
				setLocationRelativeTo(null);
				setIconImage(Toolkit.getDefaultToolkit().getImage(
						"logo_footer_ges.png"));
				

			}
		});
		
		fichier.add(deco);
		fichier.addSeparator();
		fichier.add(close);
		
		deco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Authentification auth = new Authentification(null, true);
				
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		menuBar.add(fichier);
		
		setJMenuBar(menuBar);
		setVisible(true);
	}

}
