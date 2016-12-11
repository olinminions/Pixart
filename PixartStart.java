/**
 * PixartStart.java is a starting window for pixart application
 * It was modified from FadeOurImage.java from http://www.java2s.com/Code/Java/2D-Graphics-GUI/Fadeoutanimageimagegraduallygetmoretransparentuntilitiscompletelyinvisible.htm
 * PixartStart implements running, paint and actionPerformed methods
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 4 Dec 2016
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PixartStart extends JPanel implements ActionListener {
  
  private Image im = new ImageIcon("pixart.png").getImage(); //image
  public Timer timer = new Timer(20, this); //time
  private float alpha = 1f; //fade

  //constructor starts timer
  public PixartStart() {
    timer.start();
  }

  //returns if the timer is still running
  public boolean running(){
    return timer.isRunning();
  }

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;

    //set transparency level
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    //draw image
    g2d.drawImage(im, 10, 10, null);
  }

  public void actionPerformed(ActionEvent e) {
    //update transparency
    alpha += -0.005f;
    //stop once it has faded
    if (alpha <= 0) {
      alpha = 0;
      timer.stop();
    }
    //update image
    repaint();
  }
  
  //check how it works
  public static void main(String[] args) {
    JFrame frame = new JFrame("Fade out");
    frame.add(new PixartStart());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 250);
    frame.setVisible(true);
  }

}