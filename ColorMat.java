
/** 
 * CS230 Final Project: Pixart
 * Jee Hyun Kim, Haozheng Du, Xiaozheng Xu
 * Color Matrix class 
 */

public class ColorMat{
 private int height;
 private int width;
 private int count;
 private Pixel[][] mat;

 public ColorMat(int height, int width){
  this.height = height;
  this.width = width;
  this.mat = new Pixel[height][width];
  
  this.count = 0;
 }
 
 public Pixel[][] getMat(){
   return mat;
 }
 
 // Change this method below to be more efficient!:
 public Pixel getPixelById(int id){
//   System.out.println(id);
   for(int i = 0; i < height; i++){
     for(int j = 0; j < width; j++){
       if(mat[i][j]!= null){
         if(mat[i][j].getId() == id)
           return mat[i][j];
       }
     }
   }
   Pixel dummy = new Pixel();
   return dummy;
 }
 
 public void setInitialPixel(Pixel p){
   mat[p.getY()][p.getX()] = p;
 }
 //We need to change the following else if cases so that it pickes a random neiboring pixel rather than always going to the right if the right one is empty 
 public Pixel setNextPixel(Pixel current){
  int currentX = current.getX();
  int currentY = current.getY();
  int id = current.getId();
  if(currentX>0 && mat[currentY][currentX-1] == null){
   Pixel nextPixel = new Pixel(id+1, currentX-1, currentY);
   mat[currentY][currentX-1] = nextPixel;
   count ++;
   return nextPixel; 
  }
  else if(currentX<width-1 && mat[currentY][currentX+1] == null){
   Pixel nextPixel = new Pixel(id+1, currentX+1, currentY);
   mat[currentY][currentX+1] = nextPixel;
   count ++;
   return nextPixel; 
  }
  else if(currentY<height-1 && mat[currentY+1][currentX] == null){
   Pixel nextPixel = new Pixel(id+1, currentX, currentY+1);
   mat[currentY+1][currentX] = nextPixel;
   count ++; 
   return nextPixel; 

  }
  else if(currentY>0 && mat[currentY-1][currentX] == null){
   Pixel nextPixel = new Pixel(id+1, currentX, currentY-1);
   mat[currentY-1][currentX] = nextPixel;
   count ++;
   return nextPixel; 
  }
  // What is this below doing: (if can't find a non-empty pixel next to the current one, try to find one next to the last one? but doesn't that mess up with id)?
  //This else if statement below gives the StackOverflow Error 
  //This should be if the neiboring 4 pixels are all occupied, try the neiboring pixels 2 units away, and so on... 
  else if(id>0 && count<width*height){
    current.check();
    return setNextPixel(getPixelById(id-1));
  }
  else{
    System.out.println("returning null");
    return null;
  }
 }
 
 public void fillColors(){
   Pixel p = new Pixel();
   for(int i = 0; i < height; i++)
     for(int j = 0; j < width; j++)
       if(mat[i][j]== null)
         mat[i][j] = p;
 }
}