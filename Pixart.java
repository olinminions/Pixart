/**
 * Pixart class sets up pixart, which is the main class
 * it implements getters, applyVariation, getGraphicPixart, setColors, generateColorMat methods
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 11 Dec 2016
 */

import java.util.*;
public class Pixart{
  
  private int width;
  private int height;
  private int variation;
  private int complexity;
  private ColorMat colorMat;
  private int colorscheme;
  private int []initPos;
  private GraphPixart gp;

  public Pixart(int height, int width, int complexity, int variation, int colorscheme){
   this.height = height;
   this.width = width;
   this.complexity = complexity;
   this.variation = variation;
   this.colorscheme = colorscheme;
   this.colorMat = new ColorMat(height, width);
   this.initPos = new int[2];
   this.gp = new GraphPixart(complexity);
  }

  public int getWidth(){
   return this.width;
  }
  public int getHeight(){
   return this.height;
  }
  public ColorMat getColorMat(){
   return this.colorMat;
  }
  public GraphPixart getGraphPixart(){
   return this.gp;
  }
  public void applyVariation(){
   gp.removeRandomArcs(variation);
  }

  public void setColor(Pixel pixel){
    int id = pixel.getId();
    int r = 0; //red (0-255)
    int g = 0; //green (0-255)
    int b = 0; //blue (0-255)
    int cv; //color value (0-400)

    cv = (int) (gp.getVertexNoArcs(id%complexity)/(double)gp.getMaxArc() * 20);
    cv = cv*20;

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
          r = 255; g = 182; b = 193; //lightpink
        }
        else if (cv < 100){
          r = 171; g = 130; b = 255; //mediumpurple
        }
        else if (cv < 150){
          r = 176; g = 224; b = 230; //powderblue
        }
        else if (cv < 200){
          r = 102; g = 205; b = 170; //aquamarine
        }
        else if (cv < 250){
          r = 188; g = 238; b = 104; //darkolivegreen
        }
        else if (cv < 300){
          r = 255; g = 246; b = 143; //khaki
        }
        else if (cv < 350){
          r = 255; g = 211; b = 155; //burlywood
        }
        else{
          r = 152; g = 251; b = 152; //palegreen
        }
        break;
    }

    System.out.println(colorscheme);
    System.out.println("cv: " + cv);
    System.out.println("r: " + r + "g: " + g + "b: " + b);
    pixel.setColor(r, g, b);
  }


  public void generateColorMat(){
   Random rnd = new Random();
   int initX = rnd.nextInt(width);
   int initY = rnd.nextInt(height);
   this.initPos[0] = initX;
   this.initPos[1] = initY;
   System.out.println("Initial position: " + initX + " , " + initY);
   Pixel origin = new Pixel(0, initX, initY);
   setColor(origin);
   colorMat.setInitialPixel(origin);
   Pixel current = origin;
   //Decide on whether we want to:
   // 1. fill in only the same number of pixels as vertices and blend in the rest 
   //or
   // 2. loop through and fill in all pixels (might create a repeating pattern that maybe looks nice)
   for(int i = 0; i < width*height; i++){
    current = colorMat.setNextPixel(current);
    // if (current == null)
    //    break;
    setColor(current);
   }
   colorMat.fillColors();
  }
  public static void main(String[] args){
    Pixart art = new Pixart(10,10,100,250,0);
    art.applyVariation();
    art.generateColorMat();
  }
}


