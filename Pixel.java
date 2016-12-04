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

 public Pixel(){
   color = new int[3];
   color[0] = color[1]= color[2] = 255;
 }
 public void check(){
   this.checked = true;
 }
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