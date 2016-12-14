/**
 * PixartGUI class sets up GUI for pixart
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 11 Dec 2016
 */

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.Timer;

public class PixartGUI {


  public static void main (String[] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Pixart");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //starting frame
    PixartStart start = new PixartStart();
    frame.add(start);
    frame.getContentPane().setBackground(Color.white);
    frame.setSize(250, 100);
    frame.setVisible(true);
    while (start.running()){} //wait for starting window to end
    frame.remove(start); //remove starting frame once it ended

    //create a panel, and add it to the frame
    PixartPanel panel = new PixartPanel();
    frame.getContentPane().add(panel);
    frame.pack();

  }
}
