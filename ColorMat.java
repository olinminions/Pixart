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

 public Pixel getPixelById(int id){
   System.out.println(id);
   for(int i = 0; i < height; i++){
     for(int j = 0; j < width; j++){
       if(mat[i][j]!= null)
       if(mat[i][j].getId() == id)
         return mat[i][j];
     }
   }
   Pixel dummy = new Pixel();
   return dummy;
 }
 public Pixel setNextPixel(int id){
  Pixel current = getPixelById(id);
  int currentX = current.getX();
  int currentY = current.getY();
  if(currentX>0 && mat[currentX - 1][currentY] == null){
   Pixel nextPixel = new Pixel(id+1, currentX-1, currentY);
   mat[currentX - 1][currentY] = nextPixel;
   count ++;
   return nextPixel; 
  }
  else if(currentX<width-1 && mat[currentX + 1][currentY] == null){
   Pixel nextPixel = new Pixel(id+1, currentX+1, currentY);
   mat[currentX+1][currentY] = nextPixel;
   count ++;
   return nextPixel; 
  }
  else if(currentY<height-1 && mat[currentX][currentY + 1] == null){
   Pixel nextPixel = new Pixel(id+1, currentX, currentY+1);
   mat[currentX][currentY+1] = nextPixel;
   count ++; 
   return nextPixel; 

  }
  else if(currentY>0 && mat[currentX][currentY - 1] == null){
   Pixel nextPixel = new Pixel(id+1, currentX, currentY-1);
   mat[currentX][currentY-1] = nextPixel;
   count ++;
   return nextPixel; 
  }
  else if(id>0){
   return setNextPixel(id-1);
  }
  else
    return null;
 }
}