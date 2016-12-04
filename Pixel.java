  public class Pixel{
 private int posX;
 private int posY;
 private int[] color;
 private int id;
 
 public Pixel(int id, int posX, int posY){
  this.id = id;
  this.posX = posX;
  this.posY = posY;
  color = new int[3];
 }

 public Pixel(){
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
 public void setColor(int r, int g, int b){
  this.color[0] = r;
  this.color[1] = g;
  this.color[2] = b;
 }
}
