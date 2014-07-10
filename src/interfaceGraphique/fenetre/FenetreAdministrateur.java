package interfaceGraphique.fenetre;

import interfaceGraphique.jdialog.AddUser;
import interfaceGraphique.jdialog.ModifyUser;
import interfaceGraphique.renderer.UserTypeRenderer;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import metier.UserObjectAdapter;




public class FenetreAdministrateur extends Fenetre {

	private UserObjectAdapter tmp;
	public FenetreAdministrateur() {
		
		super();
		JOptionPane.showMessageDialog(null, "Bienvenue dans l'interface de gestion des utilisateurs",
				"Bienvenue", JOptionPane.INFORMATION_MESSAGE);
		
		add = new JButton("Ajouter un utilisateur");
		add.addMouseListener(new MouseListener() {
			
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
				AddUser add = new AddUser(null, true, tableau);
				
				
			}
		});
		
		remove = new JButton("Supprimer un utilisateur");
		remove.addMouseListener(new MouseListener() {
			
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
				try {
					tmp = new UserObjectAdapter();
					selection = tableau.getSelectedRow();
					data.removeUser((String)tmp.getValueAt(selection, 0));
					tmp = new UserObjectAdapter();
					tmp.fireTableDataChanged();
					tableau.setModel(tmp);
					tableau.getColumnModel().getColumn(2).setCellRenderer(new UserTypeRenderer());
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(getParent(), "Veuillez sélectionner une utilisateur");
					
				}catch (Exception e2){
					e2.printStackTrace();
				}
				
			}
		});
		
		modify = new JButton("Modifier un utilisateur");
		modify.addMouseListener(new MouseListener() {
			
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
				try {
					ModifyUser modify = new ModifyUser(null, true, tableau);
				}catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(getParent(), "Veuillez sélectionner une utilisateur");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		panel = new JPanel();
		panel.add(add);
		panel.add(remove);
		panel.add(modify);
		tableau = new JTable(new UserObjectAdapter());
		tableau.getColumnModel().getColumn(2).setCellRenderer(new UserTypeRenderer());
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add(new JScrollPane(tableau));
		getContentPane().add(panel, BorderLayout.SOUTH);
		setVisible(true);
}
}
