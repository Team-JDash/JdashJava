package tutoPlugins.plugins;

/**
 * Interface définissant les méthodes de manipulation de String ajoutés par le plugins qui l'implémente.
 * @author Lainé Vincent (dev01, http://vincentlaine.developpez.com/ )
 *
 * Cette interface est <u>strictement</u> destinée à l'utilisation par des plugins et en aucun cas par des classes internes à notre application
 *
 */
public interface StringPlugins extends PluginsBase {

	/**
	 * Fonction de traitement principale du plugins de manipulation de String
	 * @param ini La chaine initiale
	 * @return La chaine traitée
	 */
	public String actionOnString(String ini);
	
}
