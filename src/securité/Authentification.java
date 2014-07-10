package securité;

import interfaceGraphique.fenetre.FenetreAdministrateur;
import interfaceGraphique.fenetre.FenetreAdministration;
import interfaceGraphique.fenetre.FenetreProf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bdd.DatabaseAccess;

public class Authentification extends JDialog {

	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension halfD = new Dimension(d.width / 2, d.height / 2);
	private DatabaseAccess data = new DatabaseAccess();
	private JPanel panLogin, panMdp, content, buttons;
	private JTextField login;
	private JPasswordField mdp;
	private JLabel loginLabel, mdpLabel;
	private JButton ok, cancel;
	private boolean admin, goodAuth;

	public Authentification(JFrame parent, boolean modal) {
		super(parent, "Authentification", modal);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(halfD.width / 2, halfD.height / 2);
		setResizable(false);
		setLocationRelativeTo(null);
		initWindow();
		setVisible(true);
	}

	private void initWindow() {
		// login
		panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setPreferredSize(new Dimension(halfD.width / 2,
				halfD.height / 6));
		login = new JTextField();
		login.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));

		panLogin.setBorder(BorderFactory.createTitledBorder("Login"));
		loginLabel = new JLabel("Saisissez votre login");
		panLogin.add(loginLabel);
		panLogin.add(login);

		// mdp
		panMdp = new JPanel();
		panMdp.setBackground(Color.white);
		panMdp.setPreferredSize(new Dimension(halfD.width / 2, halfD.height / 6));
		mdp = new JPasswordField();
		mdp.setPreferredSize(new Dimension(halfD.width / 4, halfD.height / 20));
		panMdp.setBorder(BorderFactory
				.createTitledBorder("Mot de passe de l'utilisateur"));
		mdpLabel = new JLabel("Saisir un mot de passe");
		panMdp.add(mdpLabel);
		panMdp.add(mdp);

		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panLogin);
		content.add(panMdp);

		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						String[] tmp = data.getUserMdp(login.getText());
						String tempMdp = new String(mdp.getPassword());

						if (login.getText().isEmpty()
								|| mdp.getPassword().toString().isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"Veuillez remplir tous les champs",
									"Erreur", JOptionPane.INFORMATION_MESSAGE);

						}

						else {
							if (!tempMdp.equals(tmp[0])) {
								JOptionPane.showMessageDialog(null,
										"Mot de passe incorrect !",
										"La connexion a échoué",
										JOptionPane.INFORMATION_MESSAGE);

							} else {
								if (login.getText().equals("admin")) {
									admin = true;
								}
								setVisible(false);
								launchApp(tmp[1], login.getText());
							}

						}

					}

				}).start();

			}
		});

		cancel = new JButton("Annuler");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});

		buttons = new JPanel();
		buttons.add(ok);
		buttons.add(cancel);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(buttons, BorderLayout.SOUTH);
	}

	public void launchApp(String type, String login) {

		if (admin) {
			new FenetreAdministrateur();

		}
		if (type.equals("true") && !admin) {
			new FenetreProf(login);
		}
		if (type.equals("false") && !admin) {
			new FenetreAdministration();

		}
	}

}
