package tutoPlugins;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import tutoPlugins.plugins.ImagePlugins;
import tutoPlugins.plugins.IntPlugins;
import tutoPlugins.plugins.PluginsLoader;
import tutoPlugins.plugins.StringPlugins;

/**
 * Frame principale du programme de test des plugins
 * @author Lainé Vincent (dev01, http://vincentlaine.developpez.com/ )
 *
 */
public class MainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4932662545205980307L;
	
	private JDesktopPane MainPane;
	private JTextArea testtext;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu stringPluginsMenu;
	private JMenu intPluginsMenu;
	private JMenu ImagePlugingsMenu;
	
	private JMenuItem exitMenuItem;
	private JMenuItem loadMenuItem;
	private JMenuItem runPluginsMenuItem;
	private JTextArea stringTextArea;
	private JTextArea intTextArea;
	private MyHorloge clock;
	
	private PluginsLoader pluginsLoader;
	private ArrayList files;
	private ArrayList stringPlugins;
	private ArrayList intPlugins;
	private ArrayList ImagePlugins;
	
	public MainFrame(){
		this.pluginsLoader = new PluginsLoader();
		this.files = new ArrayList();
		this.stringPlugins = new ArrayList();
		this.intPlugins = new ArrayList();
		this.ImagePlugins = new ArrayList();
		
		this.initialize();
	}
	
	private void initialize(){
		this.menuBar = new JMenuBar();
		this.fileMenu = new JMenu();
		this.stringPluginsMenu = new JMenu();
		this.intPluginsMenu = new JMenu();
		this.ImagePlugingsMenu = new JMenu();
		this.exitMenuItem = new JMenuItem();
		this.loadMenuItem = new JMenuItem();
		this.runPluginsMenuItem = new JMenuItem();
		this.stringTextArea = new JTextArea();
		this.intTextArea = new JTextArea();
		this.testtext = new JTextArea();
		
		//menuBar
		this.menuBar.add(this.fileMenu);
		this.menuBar.add(this.stringPluginsMenu);
		this.menuBar.add(this.intPluginsMenu);
		this.menuBar.add(this.ImagePlugingsMenu);
		
		//fileMenu
		this.fileMenu.setText("Fichier");
		this.fileMenu.add(this.loadMenuItem);
		this.fileMenu.add(this.runPluginsMenuItem);
		this.fileMenu.addSeparator();
		this.fileMenu.add(this.exitMenuItem);
		
		//stringPluginsMenu
		this.stringPluginsMenu.setText("Manipulation de String");
		
		//intPluginsMenu
		this.intPluginsMenu.setText("Manipulation de int");
		
		//imagepluginsmenu
		this.ImagePlugingsMenu.setText("Manip' image");
		
		//exitMenuItem
		this.exitMenuItem.setText("Fermer");
		this.exitMenuItem.addActionListener(this);
		
		//loadMenuItem
		this.loadMenuItem.setText("Charger un plugins");
		this.loadMenuItem.addActionListener(this);
		
		//runPluginsMenuItem
		this.runPluginsMenuItem.setText("Lancer les plugins charger");
		this.runPluginsMenuItem.addActionListener(this);
		
		//stringTextArea
		this.stringTextArea.setBorder(new LineBorder(Color.black));
		this.stringTextArea.setText("Zone pour les plugins sur les String");
		
		//intTextArea
		this.intTextArea.setBorder(new LineBorder(Color.black));
		this.intTextArea.setText("Zone pour les plugins sur les int");
		
		//Zone de text pour l'internalFrame
		this.testtext.setBorder(new LineBorder(Color.black));
		this.testtext.setText("Zone de test text ! ");
		
		
		this.setSize(800,600);
		this.setJMenuBar(this.menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3,1));
		this.getContentPane().add(this.stringTextArea);
		this.getContentPane().add(this.intTextArea);
		
		//---------------------INTERNAL FRAME---------31/01/2015---PAUL---
		//On crée un desktopPane qui va recupérer toutes nos internal frame
		JDesktopPane desktop = new JDesktopPane();
		//On rajoute le desktop dans le dernier emplacement libre avec le GridLayout
		this.add(desktop);
		/* Création de la fenêtre interne */
		JInternalFrame jif = new JInternalFrame("Fenetre",true,true,true,true);		
		jif.setSize(200, 150);
		jif.setBorder(null);

		/* Ajout au desktop */
		desktop.add(jif);
		try {//On remplie tout 'espace disponible puis on enlève toutes les barres pour avoir une fenetre "transparente"
			jif.setMaximum(true);
			((javax.swing.plaf.basic.BasicInternalFrameUI) jif.getUI()).setNorthPane(null);	
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//On rajoute la zone de texte puis on rend le tout visible
		jif.add(this.testtext);
		jif.setVisible(true);
		//-------------------------------------------------
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == this.exitMenuItem ){
			this.setVisible(false);
		}
		else {
			if( arg0.getSource() == this.loadMenuItem ){
				JFileChooser f = new JFileChooser();
				
				if(f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
					this.files.add(f.getSelectedFile().getAbsolutePath());
				}
				
			}
			else {
				if( this.runPluginsMenuItem == arg0.getSource() ){
					this.pluginsLoader.setFiles(this.convertArrayListToArrayString(this.files));
					
					try {
						this.fillStringPlugins(this.pluginsLoader.loadAllStringPlugins());
						this.fillImagePlugins(this.pluginsLoader.loadAllImagePlugins());
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
				else {
					this.ActionFromPlugins(arg0);
				}
			}
		}
		
	}
	
	private String[] convertArrayListToArrayString(ArrayList list){
		String[] tmp = new String[list.size()];
		
		for(int index = 0 ; index < tmp.length ; index ++ ){
			tmp[index] = (String)list.get(index);
		}
		
		return tmp;
	}

	private void fillStringPlugins(StringPlugins[] plugins){
		
		JMenuItem menuItem ;
	
		for(int index = 0 ; index < plugins.length; index ++ ){
			this.stringPlugins.add(plugins[index]);
			
			menuItem = new JMenuItem();
			menuItem.setText(plugins[index].getLibelle() );
			menuItem.addActionListener(this);
			//Ajout dans la collection de JMenuItem pour détection du click
			//this.stringPluginsMenuItem.add(menuItem);
			//Ajout dans le menu
			this.stringPluginsMenu.add(menuItem);
		}
		
		
	}
	
	private void fillImagePlugins(ImagePlugins[] imagePlugins2){
		
		JMenuItem menuItem ;
	
		for(int index = 0 ; index < imagePlugins2.length; index ++ ){
			this.ImagePlugins.add(imagePlugins2[index]);
			
			menuItem = new JMenuItem();
			menuItem.setText(imagePlugins2[index].getLibelle() );
			menuItem.addActionListener(this);
			//Ajout dans la collection de JMenuItem pour détection du click
			//this.stringPluginsMenuItem.add(menuItem);
			//Ajout dans le menu
			this.ImagePlugingsMenu.add(menuItem);
		}
		
		
	}
	
	
	private void ActionFromPlugins(ActionEvent e){
		
		for(int index = 0 ; index < this.stringPlugins.size(); index ++ )
		{
			if(e.getActionCommand().equals( ((StringPlugins)this.stringPlugins.get(index)).getLibelle() )){
				this.stringTextArea.setText(((StringPlugins)this.stringPlugins.get(index)).actionOnString(this.stringTextArea.getText()));
				
				return ;
			}
		}
		
		for(int index = 0 ; index < this.intPlugins.size(); index ++ ){
			if(e.getActionCommand().equals( ((IntPlugins)this.intPlugins.get(index)).getLibelle() )){
				
				int res = ((IntPlugins)this.intPlugins.get(index)).actionOnInt( Integer.parseInt(this.stringTextArea.getText()) );
				
				this.stringTextArea.setText( new Integer(res).toString() );
				
				return ;
			}
		}
		
	}
	
}

