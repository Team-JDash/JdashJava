package stringPluginsExemple;
 
import tutoPlugins.plugins.StringPlugins;
 
public class classTest implements StringPlugins {
 
	public String actionOnString(String arg0) {
 
		//return " ** - " + arg0 + " - ** ";
		return "First own plugin ! ";
	}
 
	public String getLibelle() {
 

		return "Ajouter de jolis caract�res � la String, like a boss !!";

		
	}
 
	public int getCategorie() {
 
		return 0;
	}
 
}
