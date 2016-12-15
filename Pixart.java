/**
 * Pixart class sets up pixart, which is the main class
 * it implements getters, applyVariation, getGraphicPixart, setColors, generateColorMat methods
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 11 Dec 2016
 */

import java.util.*;
public class Pixart{
  
  private int width; // width of the art generated (user input)
  private int height; // height of the art generated (user input)
  private int variation; // user input variation
  private int complexity; // user input complexity
  private ColorMat colorMat;
  private int colorscheme; // different color schemes (user input)
  private int blur; // the number of times we blur the image (0,1,2) (user input)
  private int []initPos; // position of the starting point
  private GraphPixart gp;

  // construct a Pixart given all user inputs.
  public Pixart(int height, int width, int complexity, int variation, int colorscheme, int blur){
   this.height = height;
   this.width = width;
   this.complexity = complexity;
   this.variation = variation;
   this.colorscheme = colorscheme;
   this.blur = blur;
   this.colorMat = new ColorMat(height, width);
   this.initPos = new int[2];
   this.gp = new GraphPixart(complexity);
  }

  // get width of the matrix
  public int getWidth(){
   return this.width;
  }
  // get height of the matrix
  public int getHeight(){
   return this.height;
  }
  // get the matrix
  public ColorMat getColorMat(){
   return this.colorMat;
  }
  // get the graph
  public GraphPixart getGraphPixart(){
   return this.gp;
  }
  // apply user input "variantion" to the graph
  public void applyVariation(){
   gp.removeRandomArcs(variation);
  }

  // set the color of a pixel
  public void setColor(Pixel pixel){
    int id = pixel.getId();
    int r = 0; //red (0-255)
    int g = 0; //green (0-255)
    int b = 0; //blue (0-255)
    int cv; //color value (0-400)

    // generate color value based on the graph
    cv = (int) (gp.getVertexNoArcs(id%complexity)/(double)gp.getMaxArc() * 20);
    cv = cv*20;
    // enable different color schemes
    switch(colorscheme){
      case 1: //Red
        if (cv > 255){
          r = 255;
          g = cv-255;
          b = cv-255;
        }
        else{
          r = cv;
        }
        break;

      case 2: //Green
        if (cv > 255){
          g = 255;
          r = cv-255;
          b = cv-255;
        }
        else{
          g = cv;
        }
        break;


      case 3: //Blue
        if (cv > 255){
          b = 255;
          r = cv-255;
          g = cv-255;
        }
        else{
          b = cv;
        }
        break;

      case 0: //Grey
        r = cv*255/400;
        g = cv*255/400;
        b = cv*255/400;
        break;

      case 4: //Purple
        if (cv > 255){
          r = 255;
          b = 255;
          g = cv-255;
        }
        else{
          r = cv;
          b = cv;
        }
        break;

      case 5: //Cyan
        if (cv > 255){
          g = 255;
          b = 255;
          r = cv-255;
        }
        else{
          g = cv;
          b = cv;
        }
        break;

      case 6: //Yellow
        if (cv > 255){
          r = 255;
          g = 255;
          b = cv-255;
        }
        else{
          r = cv;
          g = cv;
        }
        break;

      case 7: //Pastel
        if (cv<50){
        r = 255; g = 246; b = 143; //khaki
        }
        else if (cv < 100){
          r = 255; g = 211; b = 155; //burlywood
        }
        else if (cv < 150){
          r = 188; g = 238; b = 104; //darkolivegreen
        }
        else if (cv < 200){
          r = 102; g = 205; b = 170; //aquamarine
        }
        else if (cv < 250){
          r = 176; g = 224; b = 230; //powderblue
        }
        else if (cv < 300){
          r = 255; g = 182; b = 193; //lightpink
        }
        else if (cv < 350){
          r = 171; g = 130; b = 255; //mediumpurple
        }
        else{
          r = 152; g = 251; b = 152; //palegreen
        }
        break;
    }

//   System.out.println(colorscheme);
//   System.out.println("cv: " + cv);
//   System.out.println("r: " + r + "g: " + g + "b: " + b);
    pixel.setColor(r, g, b); // set the color of the pixel
  }

  // generate a ColorMat.
  public void generateColorMat(){
   // generate a pair of random number and set it as the starting point.
   Random rnd = new Random();
   int initX = rnd.nextInt(width);
   int initY = rnd.nextInt(height);
   this.initPos[0] = initX;
   this.initPos[1] = initY;
//   System.out.println("Initial position: " + initX + " , " + initY);
   
   // initialize the starting point and fill in the color. 
   Pixel origin = new Pixel(0, initX, initY);
   setColor(origin);
   colorMat.setInitialPixel(origin);
   Pixel current = origin;

   // loop through the colorMat and set colors for each pixel.
   for(int i = 0; i < width*height; i++){
    current = colorMat.setNextPixel(current);
    if (current == null)
      break;
    setColor(current);
   }
   colorMat.fillColors(blur); // blur the image
  }

  // testing Pixart
  public static void main(String[] args){
    Pixart art = new Pixart(10,10,100,250,0,0);
    art.applyVariation();
    art.generateColorMat();
  }
}