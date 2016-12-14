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
  private JLabel status, h,w,c,v, colorl, b; //status is the logo
  private JPanel[][] pixelsBoxes;
  private JButton quitButton, submit;
  private JRadioButton red, blue, green, grey, purple, yellow, cyan, pastel; 
  private JRadioButton blur0, blur1, blur2;
  private JTextField heightField, widthField,complexityField, variationField;
  private int colorScheme;
  private int blur;

  //private ImageIcon xImg, oImg, tieImg; //these images will be used in a couple
  // of diff methods,so make them instance vars, and create them only once.
  int height, width, complexity, variation;

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
    heightField.setMaximumSize(new Dimension(400,20));
    //width input
    widthField = new JTextField(5);
    widthField.addActionListener(new TextListener());
    widthField.setMaximumSize(new Dimension(400,20));
    //complexity input
    complexityField = new JTextField(5);
    complexityField.addActionListener(new TextListener());
    complexityField.setMaximumSize(new Dimension(400,20));
    //variation input
    variationField = new JTextField(5);
    variationField.addActionListener(new TextListener());
    variationField.setMaximumSize(new Dimension(400,20));
    //color schme input
    createRadioButtons();
    //set labels
    h = new JLabel("Height(10-1000):");
    w = new JLabel("Width(10-1000):");
    c = new JLabel("Complexity(0-100):");
    v = new JLabel("Variation():");
    colorl = new JLabel("Choose a color schme:");
    b = new JLabel("Blur Option:");
    //generate button graph
    submit = new JButton ("Generate Graph!");
    submit.addActionListener(new ButtonListener());
    
    settings.add(h);
    settings.add(heightField);
    settings.add(w);
    settings.add(widthField);
    settings.add(c);
    settings.add(complexityField);
    settings.add(v);
    settings.add(variationField);
    settings.add(colorl);
    settings.add(red);settings.add(blue);settings.add(green);settings.add(grey);
    settings.add(purple);settings.add(yellow);settings.add(cyan);settings.add(pastel);
    settings.add(b);
    settings.add(blur0); settings.add(blur1); settings.add(blur2);
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
  
  private void createRadioButtons(){
    red = new JRadioButton("Red");
    blue = new JRadioButton("Blue");
    green = new JRadioButton("Green");
    grey = new JRadioButton("Grey");
    cyan = new JRadioButton("Cyan");
    purple = new JRadioButton("Purple");
    yellow = new JRadioButton("Yellow");
    pastel = new JRadioButton("Pastel");
    ButtonGroup group = new ButtonGroup();
    group.add(red);group.add(blue);group.add(green);group.add(grey);
    group.add(cyan);group.add(purple);group.add(yellow);group.add(pastel);
    RadioButtonListener listener = new RadioButtonListener();
    red.addActionListener(listener);
    blue.addActionListener(listener);
    green.addActionListener(listener);
    grey.addActionListener(listener);
    cyan.addActionListener(listener);
    purple.addActionListener(listener);
    yellow.addActionListener(listener);
    pastel.addActionListener(listener);

    blur0 = new JRadioButton("No blur");
    blur1 = new JRadioButton("Light Blur");
    blur2 = new JRadioButton("Heavy Blur");
    ButtonGroup groupBlur = new ButtonGroup();
    groupBlur.add(blur0);
    groupBlur.add(blur1);
    groupBlur.add(blur2);
    RadioButtonListener listener = new RadioButtonListener();
    blur0.addActionListener(listener);
    blur1.addActionListener(listener);
    blur2.addActionListener(listener);
  }


  
   private class RadioButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      Object source = event.getSource();
      if (source == red)
        colorScheme = 1;
      else if (source == green)
        colorScheme = 2;
      else if (source == purple)
        colorScheme = 4;
      else if (source == blue)
        colorScheme = 3;
      else if (source == yellow)
        colorScheme = 6;
      else if (source == grey)
        colorScheme = 0;
      else if (source == cyan)
        colorScheme = 5;
      else if (source == pastel)
        colorScheme = 7;
      else if (source == blur0)
        blur = 0;
      else if (source == blur1)
        blur = 1;
      else if (source == blur2)
        blur = 2;

    }
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
          art = new Pixart(height,width,complexity,variation, colorScheme, blur);
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
          doLayout();
          revalidate();
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
          v.setText("Variation(0 - " + (complexity-1)*complexity/2 +"):");
          settings.repaint();
          System.out.println(complexity);
        }
    }
  }

  
} //end PixartPanel public class