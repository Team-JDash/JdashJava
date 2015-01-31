package stringPluginsExemple;
 
import tutoPlugins.plugins.StringPlugins;
 
public class classTest implements StringPlugins {
 
	public String actionOnString(String arg0) {
 
		return " ** - " + arg0 + " - ** ";
	}
 
	public String getLibelle() {
 

		return "Ajouter de jolis caractères à la String, like a boss !!";

		
	}
 
	public int getCategorie() {
 
		return 0;
	}
 
}
