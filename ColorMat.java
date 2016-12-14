/**
 * ColorMat class sets up the color matrix of pixel
 * it implements getMat, getPixelById, setInitialPixel, setNextPixel and getColors method
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 11 Dec 2016
 */

import java.util.*;

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
 
 //get color matrix
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
 
 //set the initial pixel position
 public void setInitialPixel(Pixel p){
   mat[p.getY()][p.getX()] = p;
 }


 public Pixel setNextPixel(Pixel current){
  int currentX = current.getX();
  int currentY = current.getY();
  int id = current.getId();

  Random r = new Random();
  int randomneighbor = r.nextInt(4);

  for (int i = randomneighbor; i<randomneighbor+4; i++){
    
    switch(i%4){
      case 0:
        if(currentX>0 && mat[currentY][currentX-1] == null){
         Pixel nextPixel = new Pixel(id+1, currentX-1, currentY);
         mat[currentY][currentX-1] = nextPixel;
         count ++;
         return nextPixel; 
        }
        break;
      
      case 1:
        if(currentX<width-1 && mat[currentY][currentX+1] == null){
         Pixel nextPixel = new Pixel(id+1, currentX+1, currentY);
         mat[currentY][currentX+1] = nextPixel;
         count ++;
         return nextPixel; 
        }
        break;
      
      case 2:
        if(currentY<height-1 && mat[currentY+1][currentX] == null){
        Pixel nextPixel = new Pixel(id+1, currentX, currentY+1);
        mat[currentY+1][currentX] = nextPixel;
        count ++; 
        return nextPixel;
      }
        break;
      
      case 3:
        if(currentY>0 && mat[currentY-1][currentX] == null){
           Pixel nextPixel = new Pixel(id+1, currentX, currentY-1);
           mat[currentY-1][currentX] = nextPixel;
           count ++;
           return nextPixel; 
        }
        break;
      }
  }

  // if(currentX>0 && mat[currentY][currentX-1] == null){
  //  Pixel nextPixel = new Pixel(id+1, currentX-1, currentY);
  //  mat[currentY][currentX-1] = nextPixel;
  //  count ++;
  //  return nextPixel; 
  // }

  // else if(currentX<width-1 && mat[currentY][currentX+1] == null){
  //  Pixel nextPixel = new Pixel(id+1, currentX+1, currentY);
  //  mat[currentY][currentX+1] = nextPixel;
  //  count ++;
  //  return nextPixel; 
  // }

  // else if(currentY<height-1 && mat[currentY+1][currentX] == null){
  //  Pixel nextPixel = new Pixel(id+1, currentX, currentY+1);
  //  mat[currentY+1][currentX] = nextPixel;
  //  count ++; 
  //  return nextPixel; 

  // }
  // else if(currentY>0 && mat[currentY-1][currentX] == null){
  //  Pixel nextPixel = new Pixel(id+1, currentX, currentY-1);
  //  mat[currentY-1][currentX] = nextPixel;
  //  count ++;
  //  return nextPixel; 
  // }

  // What is this below doing: (if can't find a non-empty pixel next to the current one, try to find one next to the last one? but doesn't that mess up with id)?
  //This else if statement below gives the StackOverflow Error 
  //This should be if the neiboring 4 pixels are all occupied, try the neiboring pixels 2 units away, and so on... 
  if(id>0 && count<width*height){
    current.check();
    return setNextPixel(getPixelById(id-1));
  }
  else{
    System.out.println("returning null");
    return null;
  }
 }
 
 public void fillColors(){
   for(int i = 0; i < height; i++){
     for(int j = 0; j < width; j++){
       if(mat[i][j] == null){
         Pixel p = new Pixel();
         mat[i][j] = p;
       }
     }
   }
   blur();
 }
 
 public void blur(){
   for(int i = 1; i < height-1; i++){
     for(int j = 1; j < width-1; j++){
//       if(mat[i][j] == null){
         float r = mat[i][j-1].getColor()[0];float g = mat[i][j-1].getColor()[1]; float b = mat[i][j-1].getColor()[2];
         for (int k = -1; k<2; k++){
           r+=mat[i-1][j+k].getColor()[0]; g+=mat[i-1][j+k].getColor()[1]; b+=mat[i-1][j+k].getColor()[2]; 
           r+=mat[i+1][j+k].getColor()[0]; g+=mat[i+1][j+k].getColor()[1]; b+=mat[i+1][j+k].getColor()[2]; 
         }
         r+=mat[i][j+1].getColor()[0]; g+=mat[i][j+1].getColor()[1]; b+=mat[i][j+1].getColor()[2];
         r/=8; g/=8; b/=8;
         Pixel x = new Pixel(j, i, (int)r, (int) g, (int) b);
         mat[i][j] = x;
//       }
     }
   }
 }
}