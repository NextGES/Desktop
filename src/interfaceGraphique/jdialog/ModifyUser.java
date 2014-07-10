package interfaceGraphique.jdialog;

import interfaceGraphique.renderer.UserTypeRenderer;

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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import metier.UserObject;
import metier.UserObjectAdapter;
import bdd.DatabaseAccess;

public class ModifyUser extends JDialog {
	
	private UserObject user;
	private UserObjectAdapter adapter; 
	private JTable tableau;
	private JLabel loginLabel, mdpLabel;
	private JRadioButton prof, administration;
	private JTextField login, mdp;
	private Dimension d, halfD;
	private JPanel panLogin, panMdp, panType, buttons, content;
	private JButton ok, cancel;
	private boolean isProf;
	private DatabaseAccess data;
	private int selection;
	private String loginSave;
	
	public ModifyUser(JFrame parent, boolean modal, JTable tableau) {
		super(parent, "Modifier un utilisateur", modal);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		d = Toolkit.getDefaultToolkit().getScreenSize();
		halfD = new Dimension(d.width/2, d.height/2);
		setSize(halfD.width, 2*halfD.height/3);
		setResizable(false);
		setLocationRelativeTo(null);
		
		this.tableau = tableau;
		selection = tableau.getSelectedRow();
		adapter = new UserObjectAdapter();
		user = new UserObject(adapter.getValueAt(selection, 0), adapter.getValueAt(selection, 1), adapter.getValueAt(selection, 2));
		loginSave = user.getLogin();
		
		initWindow();
		setVisible(true);
	}
	
	private void initWindow() {
		// login
		
		panLogin = new JPanel();
		//panLogin.setLayout(new BorderLayout());
		panLogin.setBackground(Color.white);
		panLogin.setPreferredSize(new Dimension(halfD.width, halfD.height / 6));
		login = new JTextField();
		login.setText(user.getLogin());
		login.setPreferredSize(new Dimension(halfD.width/2, halfD.height/20));
		panLogin.setBorder(BorderFactory
				.createTitledBorder("Login de l'utilisateur"));
		loginLabel = new JLabel("Saisir un login");
		panLogin.add(loginLabel);
		panLogin.add(login);

		// mdp
		panMdp = new JPanel();
		panMdp.setBackground(Color.white);
		panMdp.setPreferredSize(new Dimension(halfD.width, halfD.height / 6));
		mdp = new JTextField();
		mdp.setText(user.getMdp());
		mdp.setPreferredSize(new Dimension(halfD.width / 2, halfD.height / 20));
		panMdp.setBorder(BorderFactory
				.createTitledBorder("Mot de passe de l'utilisateur"));
		mdpLabel = new JLabel("Saisir un mot de passe");
		panMdp.add(mdpLabel);
		panMdp.add(mdp);

		// prof ou administration
		panType = new JPanel();
		panType.setBackground(Color.white);
		panType.setPreferredSize(new Dimension(halfD.width, halfD.height / 6));
		panType.setBorder(BorderFactory
				.createTitledBorder("Type d'utilisateur"));
		prof = new JRadioButton("Professeur");
		administration = new JRadioButton("Administration");
		if(user.isProf())
		{
			prof.setSelected(true);
		}
		else administration.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(prof);
		bg.add(administration);
		panType.add(prof);
		panType.add(administration);

		content = new JPanel();
		content.setBackground(Color.white);
		content.add(panLogin);
		content.add(panMdp);
		content.add(panType);
	

		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getType().equals("Professeur"))
					isProf = true;
				else
					isProf = false;
				user = new UserObject(login.getText(), mdp.getText(), isProf);
				data = new DatabaseAccess();
				if (data.modifyUser(user, loginSave) > 0)
				{
					adapter = new UserObjectAdapter();
					adapter.fireTableDataChanged();
					tableau.setModel(adapter);
					tableau.getColumnModel().getColumn(2).setCellRenderer(new UserTypeRenderer());
					setVisible(false);
				}
				

			}

			public String getType() {
				return (prof.isSelected()) ? prof.getText() : (administration
						.isSelected()) ? administration.getText() : prof
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
