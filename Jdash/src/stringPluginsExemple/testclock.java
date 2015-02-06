package stringPluginsExemple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import tutoPlugins.plugins.ImagePlugins;

public class testclock implements ImagePlugins


{
	
	public testclock()
	{
		fenetre=new JInternalFrame("JClock");
		/*fenetre.addInternalFrameListener( new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});*/
		
		calend=new GregorianCalendar();
		h=calend.get(GregorianCalendar.HOUR_OF_DAY);
		min=calend.get(GregorianCalendar.MINUTE);
		sec=calend.get(GregorianCalendar.SECOND);
		
		clockPanel=new ClockPanel();
		fenetre.getContentPane().add(clockPanel);
		fenetre.setSize(400,400);
		fenetre.show();
		clockThread=new ClockThread(this);
		clockThread.start();
		
	}/* Fin constructeur*/
	
	public testclock(JInternalFrame jif)
	{
		fenetre=new JInternalFrame("JClock");
		/*fenetre.addInternalFrameListener( new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});*/
		
		calend=new GregorianCalendar();
		h=calend.get(GregorianCalendar.HOUR_OF_DAY);
		min=calend.get(GregorianCalendar.MINUTE);
		sec=calend.get(GregorianCalendar.SECOND);
		
		clockPanel=new ClockPanel();
		jif.getContentPane().add(clockPanel);
		jif.setSize(400,400);
		jif.show();
		clockThread=new ClockThread(this);
		clockThread.start();
		
	}/* Fin constructeur*/
	
	class ClockPanel extends JPanel
	{
		public void paintComponent (Graphics g)
		{
			super.paintComponent(g);
			
			 xc=getWidth()/2;
			 yc=getHeight()/2;
		     int rayon=Math.min(xc,yc)*80/100;
		     
		     font= new Font("Times New Roman",0,15);
		     g.setFont(font);
			
			for(int i=1;i<=12;i++)
			{
			     double angle=i*Math.PI/6.0-Math.PI/2.0;
			     double x=xc+rayon*Math.cos(angle);
			     double y=yc+rayon*Math.sin(angle);
			      g.drawString(" "+i,(int)x,(int)y);
			}
			
			/* gestion et affichage de aiguilles*/
			
		 double anglesec=(sec*((Math.PI)/30.0)-(Math.PI/2.0));
		int xsf=xc+(int)(0.7*rayon*Math.cos(anglesec));
		int ysf=yc+(int)(0.7*rayon*Math.sin(anglesec));
		g.setColor(Color.red);
		g.drawLine(xc,yc,xsf,ysf);
			
	double anglemin=(min*((Math.PI)/30.0)-(Math.PI/2.0));
	int xmf=xc+(int)(0.6*rayon*Math.cos(anglemin));
	int ymf=yc+(int)(0.6*rayon*Math.sin(anglemin));
	g.setColor(Color.yellow);
	g.drawLine(xc,yc,xmf,ymf);
	double angleheure=(h*((2*Math.PI)/12.0)-(Math.PI/2.0));
	int xhf=xc+(int)(0.4*rayon*Math.cos(angleheure));
	int yhf=yc+(int)(0.4*rayon*Math.sin(angleheure));
	g.setColor(Color.green);
	g.drawLine(xc,yc,xhf,yhf);
			
	
		}/* fin de paintComponent */ 
	
	}/* fin de classe ClockPanel */
	
	public void increment()
	{
		sec=sec+1;
		if(sec>60)
		{
			min=min+1;
			sec=1;
			if(min>60)
			{
				min=1;
				h=h+1;
				if(h>12)
				{
					h=1;
				}
			}
		}
		clockPanel.repaint();
	}
	
	/*public static void main (String args[])
	{
		new testclock();
	}*/
	
	private JInternalFrame fenetre;
	private GregorianCalendar calend;
	private int h,min,sec,xc,yc;
	private ClockPanel clockPanel;
	private ClockThread clockThread;
	private Font font;
	@Override
	public String getLibelle() {
	
		return "Horloge graphique";
	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Component actionOnPlugin(JInternalFrame jif, JDesktopPane desktop) {
		new testclock(jif);
		return jif;
	}
	
}/* fin de classe ClockApp*/

class ClockThread extends Thread
{
	ClockThread(testclock horloge)
	{
		this.horloge=horloge;
	}
	public void run()
	{
		while(true)
		{
			horloge.increment();
			try
			{
				sleep(1000);
			}
			catch(InterruptedException e)
			{
			}
				
		}
	}
	private testclock horloge;
}