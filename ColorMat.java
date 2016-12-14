/**
 * ColorMat class sets up the color matrix of pixel
 * it implements getMat, getPixelById, setInitialPixel, setNextPixel ,fillColors, blur and getColors method
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
 
 // can make this method more efficient: since this is called in the recursion and we know the position of the next pixel,
 // we can give it the posiitons instead of the id.
 public Pixel getPixelById(int id){
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

  //It there's no more empty pixel around the current pixel, try to find an empty space around a previous pixel. 
  if(id>0 && count<width*height){ //If we got back to the first pixel, return null
    current.check();
    return setNextPixel(getPixelById(id-1)); //RECURSION
  }
  // Almost all the pixels are filled up
  else{
//    System.out.println("returning null");
    return null;
  }
 }
 
  /*
  * This method fills all spaces in the matrix with a dummy pixel 
  * with original color set to white. This prevent null pointer exception from happening in other code 
  * and provide a white color for bluring adjacent colors in the blur() method
  * @params int blur: how many times to call the blur function (0,1,2)
  */ 
 public void fillColors(int blur){
   for(int i = 0; i < height; i++){
     for(int j = 0; j < width; j++){
       if(mat[i][j] == null){
         Pixel p = new Pixel();
         mat[i][j] = p;
       }
     }
   }
   for (int b =  0; b<blur; b++){
     blur();
   }
 }
 
 
 /*
  * This method blurs the image by setting each pixel's color values to be the average of the 8 pixel color values around it 
  * It sets all pixels in place instead of creating a new matrix so that pixels further down are affected by previous ones
  */ 
 public void blur(){
   for(int i = 0; i < height; i++){
     for(int j = 0; j < width; j++){
       float r = 0, g = 0, b = 0;
       int count = 0;
       for (int x = Math.max(i-1,0); x<=Math.min(i+1,height-1); x++){ // The Math.max and min takes care of the boundary values 
         for (int y = Math.max(j-1,0); y<=Math.min(j+1,width-1); y++){
           r+=mat[x][y].getColor()[0]; g+=mat[x][y].getColor()[1]; b+=mat[x][y].getColor()[2];
           count++;
         }
       }
       r-=mat[i][j].getColor()[0];g-=mat[i][j].getColor()[1];b-=mat[i][j].getColor()[2]; // subtract the color values of the pixel in the middle
       r/= (count-1); g/= (count-1); b/=(count-1); 
       Pixel x = new Pixel(j, i, (int)r, (int) g, (int) b);
       mat[i][j] = x;
     }
   }
 }
}