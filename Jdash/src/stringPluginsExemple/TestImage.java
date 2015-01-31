package stringPluginsExemple;

import tutoPlugins.plugins.ImagePlugins;
import tutoPlugins.plugins.StringPlugins;

public class TestImage implements ImagePlugins {
	 

	public String actionOnPlugin(String arg0) {
		
		//return " ** - " + arg0 + " - ** ";
				return "First own iamge plugin mother fucker ! ";
	}
	
	
	public String getLibelle() {
 

		return "Ajoute une image dans une frame";

		
	}
 
	public int getCategorie() {
 
		return 0;
	}


 
}