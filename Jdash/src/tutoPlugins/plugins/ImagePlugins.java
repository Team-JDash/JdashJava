package tutoPlugins.plugins;

import java.awt.Component;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public interface ImagePlugins extends PluginsBase {

	/**
	 * Fonction de traitement principale du plugins de manipulation de String
	 * @param jif La chaine initiale
	 * @return La chaine traitée
	 */
	public Component actionOnPlugin(JInternalFrame jif,JDesktopPane desktop);
	
}