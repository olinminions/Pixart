import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * PixartStart.java is a starting window for pixart application
 * It was modified from FadeOurImage.java from http://www.java2s.com/Code/Java/2D-Graphics-GUI/Fadeoutanimageimagegraduallygetmoretransparentuntilitiscompletelyinvisible.htm
 */

public class PixartStart extends JPanel implements ActionListener {
  
  private Image im = new ImageIcon("pixart.png").getImage();
  public Timer timer = new Timer(20, this);
  private float alpha = 1f;

  public PixartStart() {
    timer.start();
  }

  public boolean running(){
    return timer.isRunning();
  }

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    g2d.drawImage(im, 10, 10, null);
  }

  public void actionPerformed(ActionEvent e) {
    alpha += -0.01f;
    if (alpha <= 0) {
      alpha = 0;
      timer.stop();
    }
    repaint();
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Fade out");
    frame.add(new FadeOutImage());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 250);
    frame.setVisible(true);
  }

}