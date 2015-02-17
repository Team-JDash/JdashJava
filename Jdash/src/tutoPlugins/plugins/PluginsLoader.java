package tutoPlugins.plugins;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;

public class PluginsLoader {

	private String[] files;
	
	private ArrayList classStringPlugins;
	private ArrayList classIntPlugins;
	private ArrayList classImagePlugins;
	
	/**
	 * Constructeur par d�faut
	 *
	 */
	public PluginsLoader(){
		this.classIntPlugins = new ArrayList();
		this.classStringPlugins = new ArrayList();
		this.classImagePlugins = new ArrayList();
	}
	
	/**
	 * Constucteur initialisant le tableau de fichier � charger.
	 */
	public PluginsLoader(String[] files){
		this();
		this.files = files;
	}
	
	/**
	 * D�fini l'ensemble des fichiers � charger
	 */
	public void setFiles(String[] files ){
		this.files = files;
	}
	
	/**
	 * Fonction de chargement de tout les plugins de type StringPlugins
	
	 */

	public ImagePlugins[] loadAllImagePlugins() throws Exception {
		
		this.initializeLoader();
		
		ImagePlugins[] tmpPlugins = new ImagePlugins[this.classImagePlugins.size()];
		
		for(int index = 0 ; index < tmpPlugins.length; index ++ ){
			
			//On cr�er une nouvelle instance de l'objet contenu dans la liste gr�ce � newInstance() 
			//et on le cast en StringPlugins. Vu que la classe impl�mente StringPlugins, le cast est toujours correct
			tmpPlugins[index] = (ImagePlugins)((Class)this.classImagePlugins.get(index)).newInstance() ;
			
		}
		
		return tmpPlugins;
	}
	

	
	private void initializeLoader() throws Exception{
		//On v�rifie que la liste des plugins � charger � �t� initialis�
		if(this.files == null || this.files.length == 0 ){
			throw new Exception("Pas de fichier sp�cifi�");
		}
		//Pour eviter le double chargement des plugins
		/*if(this.classImagePlugins.size() != 0){
			System.out.println("Double chargement");
			return ;
		}*/
		
		File[] f = new File[this.files.length];
//		Pour charger le .jar en memoire
		URLClassLoader loader;
		//Pour la comparaison de chaines
		String tmp = "";
		//Pour le contenu de l'archive jar
		Enumeration enumeration;
		//Pour d�termin� quels sont les interfaces impl�ment�es
		Class tmpClass = null;
		//��������������������������������������������������������������������������������������� midif f.length -> this.files.length
		for(int index = 0 ; index < this.files.length ; index ++ ){
			/*for(int check=0;check<this.files.length;check++){
				if(f[check].toString()==(this.files[check]))
					System.out.println(f[check]+" = "+ this.files[check]);
			}*/
			f[index] = new File(this.files[index]);
			System.out.println("Nom files Initializer: "+ f[index]);
			if( !f[index].exists() ) {
				break;
			}
			
			URL u = f[index].toURL();
			//On cr�er un nouveau URLClassLoader pour charger le jar qui se trouve ne dehors du CLASSPATH
			loader = new URLClassLoader(new URL[] {u}); 
			
			//On charge le jar en m�moire
			JarFile jar = new JarFile(f[index].getAbsolutePath());
			
			//On r�cup�re le contenu du jar
			enumeration = jar.entries();
			
			while(enumeration.hasMoreElements()){
				
				tmp = enumeration.nextElement().toString();

				//On v�rifie que le fichier courant est un .class (et pas un fichier d'informations du jar )
				if(tmp.length() > 6 && tmp.substring(tmp.length()-6).compareTo(".class") == 0) {
					
					tmp = tmp.substring(0,tmp.length()-6);
					tmp = tmp.replaceAll("/",".");
					
					tmpClass = Class.forName(tmp ,true,loader);
					
					for(int i = 0 ; i < tmpClass.getInterfaces().length; i ++ ){
						
						//Une classe ne doit pas appartenir � deux cat�gories de plugins diff�rents. 
						//Si tel est le cas on ne la place que dans la cat�gorie de la premi�re interface correct
						// trouv�e
						if(tmpClass.getInterfaces()[i].getName().toString().equals("tutoPlugins.plugins.ImagePlugins")){
							this.classImagePlugins.add(tmpClass);
						}

					}
					
				}
			}
			
		
		}
		
	}
	
	
}
