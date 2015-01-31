package tutoPlugins;
import java.util.Calendar;
import java.awt.event.*;

import javax.swing.*;
import java.awt.*;
 
public class MyHorloge extends JFrame 
{
 
  public MyHorloge( ) {
    super("Exemple d'une Horloge");
    setSize(300, 100);
    setUndecorated(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
 
    HorlogeLabel clock = new HorlogeLabel( );
    getContentPane( ).add(clock, BorderLayout.CENTER);
  }
 
  public static void main(String args[]) {
    MyHorloge ct = new MyHorloge( );
    ct.setVisible(true);
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

