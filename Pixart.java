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
  private int randomness;
  private int variation;
  private int complexity;
  private ColorMat colorMat;
  private int []initPos;
  private GraphPixart gp;

  public Pixart(int height, int width, int complexity, int variation, int randomness){
   this.height = height;
   this.width = width;
   this.complexity = complexity;
   this.variation = variation;
   this.randomness = randomness;
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
   int colorValue = (int) (gp.getVertexNoArcs(id%complexity)/(double)gp.getMaxArc() * 255);
//   System.out.println(colorValue);
   pixel.setColor(colorValue,colorValue,colorValue);
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
    if (current == null)
       break;
    setColor(current);
   }
   colorMat.fillColors();
  }
  public static void main(String[] args){
    Pixart art = new Pixart(10,10,100,250,1);
    art.applyVariation();
    art.generateColorMat();
  }
}


