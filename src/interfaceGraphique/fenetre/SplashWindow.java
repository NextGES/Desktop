package interfaceGraphique.fenetre;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JWindow;

public class SplashWindow extends JWindow {

	private ImageIcon image;
	
	public SplashWindow(ImageIcon icon)
	{
		image = icon;
	}
	
	public void update(Graphics g)
	{
		image.paintIcon(this, g, 0, 0);
	}
	
	public void paint(Graphics g)
	{
		image.paintIcon(this, g, 0, 0);
	}
}
