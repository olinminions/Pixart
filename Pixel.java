/**
 * Pixel class creates main object of Pixart
 * It has check, getters, setters method
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 11 Dec 2016
 */

public class Pixel{

 private int posX;
 private int posY;
 private int[] color;
 private int id;
 private boolean checked;

 public Pixel(int id, int posX, int posY){
  this.checked = false;
  this.id = id;
  this.posX = posX;
  this.posY = posY;
  color = new int[3];
  color[0] = color[1]= color[2] = 255;
 }

 public Pixel(int posX, int posY, int r, int g, int b){
   this.posX = posX;
   this.posY = posY;
   color = new int[3];
   color[0] = r;
   color[1]= g;
   color[2] = b;
 }
 
 public Pixel(){
   color = new int[3];
   color[0] = color[1]= color[2] = 255;
 }

 //check if the pixel was checked
 public void check(){
   this.checked = true;
 }

 //getters
 public int getId(){
  return id;
 }
 public int getX(){
  return posX;
 }
 public int getY(){
  return posY;
 }
 public int[] getColor(){
   return color;
 }

 //set colors
 public void setColor(int r, int g, int b){
  this.color[0] = r;
  this.color[1] = g;
  this.color[2] = b;
 }
 
 public static void main(String[] args){
   Pixel p = new Pixel();
   int[] colors = p.getColor();
   System.out.println(colors[0]);
   System.out.println(colors[1]);
   System.out.println(colors[2]);
 }
}