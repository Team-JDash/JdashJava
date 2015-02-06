package stringPluginsExemple;

import java.awt.Component;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import tutoPlugins.plugins.ImagePlugins;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;

public class VideoPlayer extends MediaListenerAdapter implements ImagePlugins  {

	public  VideoPlayer (String Filename){
		    
		    // create a new mr. decode an play audio and video
		    IMediaReader reader = ToolFactory.makeReader(Filename);
		    reader.addListener(ToolFactory.makeViewer());
		    while(reader.readPacket() == null)
		      do {} while(false);
	}
	
	public static void main(String[] args) {
		VideoPlayer vid = new VideoPlayer("C:\\AirStrike.mp4");
	}
	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Component actionOnPlugin(JInternalFrame jif, JDesktopPane desktop) {
		// TODO Auto-generated method stub
		return null;
	}

	      	
}
