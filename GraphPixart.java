/**
 * GraphPixart class creates a Adjacent Matrix Graph created specifically or Pixart
 * GraphPixart class implements getNoVertices, getNoArcs, ind, getVertexesNoArc, 
 * getMaxArc, removeRandomArcs and toString methods
 * @author: HaoZheng Du, Jee Hyun Kim, XiaoZheng Xu
 * Last Modified Date: 4 Dec 2016
 */

import java.util.*;

public class GraphPixart{

 protected int n; //no. of vertices
 protected boolean[][] arcs; //adjacency matrix
 protected int[] vertices; //value of vertices

 //vertex
 private final int NOTFOUND = -1;

 private final int CAP = 100;

 //create empty graph
 public GraphPixart(){
  n = 0;
  arcs = new boolean[CAP][CAP];
  vertices = new int[CAP];
 }

 //create full graph with given number of vertices
 public GraphPixart(int v){
  this();

  if (v == 0)
   return;

  n = v;

  for (int i = 0; i<v; i++){
   vertices[i] = i;
   for (int j = 0; j<v; j++){
    arcs[i][j] = true;
    if (i == j)
     arcs[i][j] = false;
   }
  }
 }

 //get number of vertices
 public int getNoVertices(){
  return n;
 }

 //get number of arcs
 public int getNoArcs(){
  int m = 0;
  for (boolean[] a: arcs){
   for (boolean b: a){
    if (b)
     m++;
   }
  }

  return m;
 }

 //get the index of vertex. Returns -1(NOTFOUND) if not in the graph
 private int ind(int v){
  if (v<n)
   return v;
  return NOTFOUND;
 }

 //get number of arcs a vertex have
 //used for setting the color of the vertex
 public int getVertexNoArcs(int v){
  int count = 0;

  if (v < 0)
   return 0;
  for (int i = 0; i<CAP; i++){
   if (arcs[i][v])
    count++;
   if (arcs[v][i])
    count++;
  }
  return count;
 }

 //maximum number of Arc a vertex can have
 public int getMaxArc(){
  return n*2-2;
 }

 public void removeArcs(int var){
  int arcstoremove = var;
  for (int i = 0; i<n; i++){
   for (int j = i; j<n; j++){
    if (arcs[i][j]){
     arcs[i][j] = false;
     if (--arcstoremove <= 0)
      return;
    }
   }
  }
 }

 //removes var number of arcs at random
 //var input is from the variation input of the user
 public void removeRandomArcs(int var){

  //generate number of arcs to be removed
  Random r = new Random();
  int rmEdge = r.nextInt(var); //generate random number from 0(in) to var(exc) to remove
  if (rmEdge == 0)
   rmEdge = 1;

  //just in case Edges to remove exceeds Number of total Arcs
  if (rmEdge >= getNoArcs()){
   System.out.println("reduce variability input limit");
   return;
  }

  //create list of all arcs
  List<int[]> arclist = new ArrayList<int[]>();

  for (int i = 0; i<n; i++){
   for (int j = 0; j<n; j++){
    int[] e = {i,j};
    arclist.add(e);
   }
  }

  //shuffle the list of arcs
  Collections.shuffle(arclist);

  //remove arcs
  while (rmEdge > 0){ //arcs left to be removed
   int[] e = arclist.get(0); //first element of shuffled list of arc
   if (arcs[e[0]][e[1]] == true){
    arcs[e[0]][e[1]] = false; //remove arc
    rmEdge--; //took out one arc
   }
   arclist.remove(0); //remove the removed arc
  }
 }

 //overwriting toString method
 public String toString(){
     String result = "";
     
     for (int i = 0; i<n; i++){
         for (int j = 0; j<n; j++){
          if (arcs[i][j])
              result += (1 + "\t");
          else
           result += (0 + "\t");
         }
         result += "\n";
     }

     return result;
 }

 //testing
 public static void main(String[] args){
  GraphPixart g = new GraphPixart(5);
     
     System.out.println(g.toString());
     System.out.println("No of arcs for vertex 2:" + g.getVertexNoArcs(2));
     
     g.removeRandomArcs(15);
     System.out.println("No of arcs for vertex 2:" + g.getVertexNoArcs(2));
     System.out.println(g.toString());

     g = new GraphPixart(5);
     
     System.out.println(g.toString());
     System.out.println("No of arcs for vertex 2:" + g.getVertexNoArcs(2));
     
     g.removeArcs(5);
     System.out.println("No of arcs for vertex 2:" + g.getVertexNoArcs(2));
     System.out.println(g.toString());
 }
}