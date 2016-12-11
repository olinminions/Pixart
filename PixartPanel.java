/**
 * PixartPanel class creates the main JPanel for the GUI 
 * ButtonListener and TextListener class serves as the linkage between the GUI and the User
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 11 Dec 2016
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PixartPanel extends JPanel {

  //instance vars
  private JPanel bottom,top,settings,pixels;
  private JLabel status;
  private JPanel[][] pixelsBoxes;
  private JButton quitButton, submit;
  private JTextField heightField, widthField,complexityField, variationField, randomnessField;

  //private ImageIcon xImg, oImg, tieImg; //these images will be used in a couple
  // of diff methods,so make them instance vars, and create them only once.
  int height, width, complexity, variation, randomness;

  private Pixart art; 
 
  // PixartPanel constructor
  public PixartPanel() {
    setLayout(new BorderLayout());
    
    //Bottom panel with quit button
    bottom = new JPanel();
    bottom.setLayout(new BoxLayout(bottom,BoxLayout.X_AXIS));
    quitButton = new JButton("Quit");
    quitButton.addActionListener(new ButtonListener());
    bottom.add(Box.createHorizontalGlue());
    bottom.add(quitButton);
    add(bottom, BorderLayout.SOUTH);
    


    //Top panel with pisart logo
    ImageIcon logo = new ImageIcon("pixart.png", "logo");
    status = new JLabel(logo);
    top = new JPanel();
    top.add(status);
    add(top, BorderLayout.NORTH);
    


    //Panel for all the user inputs
    settings = new JPanel();
    settings.setLayout(new BoxLayout(settings,BoxLayout.Y_AXIS));
    //height input
    heightField = new JTextField(5);
    heightField.addActionListener(new TextListener());
    heightField.setMaximumSize(new Dimension(200,20));
    //width input
    widthField = new JTextField(5);
    widthField.addActionListener(new TextListener());
    widthField.setMaximumSize(new Dimension(200,20));
    //complexity input
    complexityField = new JTextField(5);
    complexityField.addActionListener(new TextListener());
    complexityField.setMaximumSize(new Dimension(200,20));
    //randomness input
    randomnessField = new JTextField(5);
    randomnessField.addActionListener(new TextListener());
    randomnessField.setMaximumSize(new Dimension(200,20));
    //variation input
    variationField = new JTextField(5);
    variationField.addActionListener(new TextListener());
    variationField.setMaximumSize(new Dimension(200,20));
    //set labels
    JLabel h = new JLabel("height:");
    JLabel w = new JLabel("width:");
    JLabel c = new JLabel("complexity ():");
    JLabel r = new JLabel("randomness ():");
    JLabel v = new JLabel("variation ():");
    //generate button graph
    submit = new JButton ("Generate Graph!");
    submit.addActionListener(new ButtonListener());
    
    settings.add(h);
    settings.add(heightField);
    settings.add(w);
    settings.add(widthField);
    settings.add(c);
    settings.add(complexityField);
    settings.add(r);
    settings.add(randomnessField);
    settings.add(v);
    settings.add(variationField);
    settings.add(Box.createVerticalGlue());
    settings.add(submit);
    add(settings, BorderLayout.WEST);
    


    //panel which displays the art
    pixels = new JPanel();
    height = 30; // default height
    width = 30; //default width

    pixels.setLayout(new GridLayout(height,width));
    pixelsBoxes = new JPanel[height][width];

    //adding pixel boxes to the each grid of the JPanel
    for (int i = 0; i<height;i++){
       for(int j = 0; j<width;j++){
          pixelsBoxes[i][j] = new JPanel();
          if ((j+i)%2 == 0)
            pixelsBoxes[i][j].setBackground(Color.white);
          else
            pixelsBoxes[i][j].setBackground(Color.blue);
          pixels.add(pixelsBoxes[i][j]);
       }
    }
    add(pixels, BorderLayout.CENTER);

    //set background color
    top.setBackground(Color.white);
    bottom.setBackground(Color.white);
    settings.setBackground(Color.white);
    pixels.setBackground(Color.white);
  }
  


  private class ButtonListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      //quit if quit button
        if (event.getSource() == quitButton){
          System.exit(0);
        }
        //for other buttons
        else if (event.getSource() == submit){
          //get new pixart
          art = new Pixart(height,width,complexity,variation,randomness);
          art.applyVariation();
          art.generateColorMat();
          Pixel[][] cM = art.getColorMat().getMat();
          //remove old pixel
          remove(pixels);
          //create new image
          pixels = new JPanel();
          pixels.setBackground(Color.white);
          pixels.setLayout(new GridLayout(height,width));
          pixelsBoxes = new JPanel[height][width];
          for (int i = 0; i<height;i++){
            for(int j = 0; j<width;j++){
              pixelsBoxes[i][j] = new JPanel();
              int[] color = cM[i][j].getColor();
              pixelsBoxes[i][j].setBackground(new Color(color[0],color[1],color[2]));
              pixels.add(pixelsBoxes[i][j]);
            }
          }
          add(pixels, BorderLayout.CENTER);
        }
    }
  }
  


  private class TextListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      //update values for each inputs
        if (event.getSource() == heightField){
          String text = heightField.getText();
          height = Integer.parseInt(text);
          System.out.println(height);

        }if (event.getSource() == widthField){
          String text = widthField.getText();
          width = Integer.parseInt(text);
          System.out.println(width);

        }if (event.getSource() == variationField){
          String text = variationField.getText();
          variation = Integer.parseInt(text);
          System.out.println(variation);

        }if (event.getSource() == complexityField){
          String text = complexityField.getText();
          complexity = Integer.parseInt(text);
          System.out.println(complexity);

        }if (event.getSource() == randomnessField){
          String text = randomnessField.getText();
          randomness = Integer.parseInt(text);
          System.out.println(randomness);
        }
    }
  }

  
} //end PixartPanel public class