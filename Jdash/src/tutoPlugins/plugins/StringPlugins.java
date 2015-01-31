package tutoPlugins.plugins;

/**
 * Interface d�finissant les m�thodes de manipulation de String ajout�s par le plugins qui l'impl�mente.
 * @author Lain� Vincent (dev01, http://vincentlaine.developpez.com/ )
 *
 * Cette interface est <u>strictement</u> destin�e � l'utilisation par des plugins et en aucun cas par des classes internes � notre application
 *
 */
public interface StringPlugins extends PluginsBase {

	/**
	 * Fonction de traitement principale du plugins de manipulation de String
	 * @param ini La chaine initiale
	 * @return La chaine trait�e
	 */
	public String actionOnString(String ini);
	
}
