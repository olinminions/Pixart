/** 
* FILE NAME: PixartGUI.java
* WHO: Xiaozheng Xu, Haozheng Du, Jee Hyun Kim
* WHAT: Sets up the GUI for pixart.
*/
import javax.swing.JFrame;

public class PixartGUI {

  public static void main (String[] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Pixart");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //create an instance of the TicTacToe game
    Pixart art = new Pixart();
    
    //create a panel, and add it to the frame
    PixartPanel panel = new PixartPanel(art);
    frame.getContentPane().add(panel);
    
    frame.pack();
    frame.setVisible(true);
  }
}
