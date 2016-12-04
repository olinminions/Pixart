/** 
* FILE NAME: PixartGUI.java
* WHO: Xiaozheng Xu, Haozheng Du, Jee Hyun Kim
* WHAT: Sets up the GUI for pixart.
*/

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.Timer;

public class PixartGUI {


  public static void main (String[] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Pixart");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //create an instance of the TicTacToe game
    Pixart art = new Pixart();
    

    //start frame
    PixartStart start = new PixartStart();
    frame.add(start);
    frame.getContentPane().setBackground(Color.white);
    frame.setSize(250, 100);
    frame.setVisible(true);

    while (start.running()){} //wait for starting window to end

    frame.remove(start);


    //create a panel, and add it to the frame
    PixartPanel panel = new PixartPanel(art);
    frame.getContentPane().add(panel);

    frame.pack();

  }
}
