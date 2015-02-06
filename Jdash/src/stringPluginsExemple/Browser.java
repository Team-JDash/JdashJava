package stringPluginsExemple;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import tutoPlugins.plugins.ImagePlugins;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class Browser implements ImagePlugins{
	JFrame frame;
	public Browser() {
	    NativeInterface.open();
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            frame = new JFrame("YouTube Viewer");
	            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	            frame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
	            frame.setSize(800, 600);
	            frame.setLocationByPlatform(true);
	            frame.setVisible(true);
	        }
	    });
	    NativeInterface.runEventPump();
	    // don't forget to properly close native components
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        @Override
	        public void run() {
	            NativeInterface.close();
	        }
	    }));
	}

	public static JPanel getBrowserPanel() {
	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	    JWebBrowser webBrowser = new JWebBrowser();
	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	    webBrowser.setBarsVisible(true);
	    webBrowser.navigate("https://www.youtube.com//watch?v=0AgGrAyG5rk");
	    return webBrowserPanel;
	}

	@Override
	public String getLibelle() {
		
		return "Browser integré";
	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Component actionOnPlugin(JInternalFrame jif, JDesktopPane desktop) {
		Browser bro = new Browser();
		jif.add(frame);
		return null;
	}
}
