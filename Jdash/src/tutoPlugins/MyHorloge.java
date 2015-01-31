package tutoPlugins;
import java.util.Calendar;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
 
public class MyHorloge extends JInternalFrame 
{
 
  public MyHorloge( ) {
    super("Exemple d'une Horloge");
    setSize(300, 100);
 
    HorlogeLabel clock = new HorlogeLabel( );
    this.add(clock, BorderLayout.CENTER);
  }
 
  public static void main(String args[]) {
    MyHorloge ct = new MyHorloge( );

  }
 
  class HorlogeLabel extends JLabel implements ActionListener 
  	{
 
  public HorlogeLabel( ) {
    super(""+ Calendar.getInstance());
    Timer t = new Timer(0, this);
    t.start( );
  }
 
  public void actionPerformed(ActionEvent ae) {
    setText(String.format("%tT", Calendar.getInstance()));
  }
   }
 
}

