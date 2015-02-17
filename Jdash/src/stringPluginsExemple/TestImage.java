package stringPluginsExemple;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import tutoPlugins.plugins.ImagePlugins;
import tutoPlugins.plugins.StringPlugins;

public class TestImage implements ImagePlugins {
	 
	public String getLibelle() {
 

		return "Ajoute une image dans une frame";

		
	}
 
	public int getCategorie() {
 
		return 0;
	}


	@Override
	public Component actionOnPlugin(JInternalFrame jif, JDesktopPane desktop) {
		//jif.setSize(200, 150);
		//jif.setBorder(null);

		/* Ajout au desktop */
	
		/*try {//On remplie tout 'espace disponible puis on enlève toutes les barres pour avoir une fenetre "transparente"
			jif.setMaximum(true);
			((javax.swing.plaf.basic.BasicInternalFrameUI) jif.getUI()).setNorthPane(null);	
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("C:\\Users\\MSIsilafamille!\\Documents\\APluginTest\\picture.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		jif.add(picLabel);
		//jif.setSize(400,400);
		jif.setVisible(true);
		//On rajoute la zone de texte puis on rend le tout visible
		//jif.add(this.testtext);
		//jif.setVisible(true);
		return jif;
	}


 
}