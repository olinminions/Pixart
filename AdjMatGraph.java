/**
 * AdjMatGraph.java    
 * @author: 
 */

import java.util.*;
import java.io.*;

public class AdjMatGraph<T> implements Graph<T>{
  
  protected final int NOT_FOUND = -1; 
  protected final int CAP = 100; //max capacity
  
  protected int n; //no. of vertices
  protected boolean[][] arcs; //adjacency matrix
  protected T[] vertices; //value of vertices
  
  public AdjMatGraph(){
    //create empty graph
    n = 0;
    this.arcs = new boolean[CAP][CAP];
    this.vertices = (T[])(new Object[CAP]);
  }
  
  /** Returns true if this graph is empty, false otherwise. */
  public boolean isEmpty(){
    return n == 0;
  }
  
  /** Returns the number of vertices in this graph. */
  public int n(){
    return n;
  }
  
  /** Returns the number of arcs in this graph. */
  public int m(){
    int m = 0;
    for (boolean[] t: arcs){
      for (boolean b: t){
        if (b)
          m ++;
      }
    }
    return m;
    
  }
  
  private int ind(T v){
    for (int i = 0; i<n; i++){
      if (vertices[i] == v)
        return i;
    }
    return -1;
  }
  
  /** Returns true iff a directed edge exists b/w given vertices */
  public boolean isArc (T vertex1, T vertex2){
    int i = ind(vertex1);
    int j = ind(vertex2);
    return arcs[i][j];
  }
  
  /** Returns true iff an edge exists between two given vertices
    * which means that two corresponding arcs exist in the graph */
  public boolean isEdge (T vertex1, T vertex2){
    int i = ind(vertex1);
    int j = ind(vertex2);
    return arcs[i][j] && arcs[j][i];
  }
  
  /** Returns true IFF the graph is undirected, that is, for every
    * pair of nodes i,j for which there is an arc, the opposite arc
    * is also present in the graph.  */
  public boolean isUndirected(){
    for(int i = 0; i < n; i++) {
      for(int j = i+1; j < n; j++) {
        if (arcs[i][j] != arcs[i][j])
          return false;
      }
    }
    return true;
  }
  
  /** Adds a vertex to this graph, associating object with vertex.
    * If the vertex already exists, nothing is inserted. */
  public void addVertex (T vertex){
    if (ind(vertex) == -1){
      vertices[n] = vertex;
      n++;
    }
  }
  
  
  /** Removes a single vertex with the given value from this graph.
    * If the vertex does not exist, it does not change the graph. */
  public void removeVertex (T vertex){

    int i = ind(vertex);
    if (i != -1){
      for (int j = i; j<n-1; j++){
        vertices[j] = vertices[j+1];
        
        for(int k = 0; k<n; k++){
          arcs[j][k] = arcs[j+1][k];
        }
      }
        
        for (int k = i; k<n-1; k++){
          for(int j = 0; j<n-1; j++){
            arcs[j][k] = arcs[j][k+1];
          }
        }
        n--;
      }
    }
    
    /** Inserts an arc between two vertices of this graph,
      * if the vertices exist. Else it does not change the graph. */
    public void addArc (T vertex1, T vertex2){
      int i = ind(vertex1);
      int j = ind(vertex2);
      if (i!=-1 && j!=-1)
        arcs[i][j] = true;
    }
    
    /** Removes an arc between two vertices of this graph,
      * if the vertices exist. Else it does not change the graph. */
    public void removeArc (T vertex1, T vertex2){
      int i = ind(vertex1);
      int j = ind(vertex2);
      if (i!=-1 && j!=-1)
        arcs[i][j] = false;
    }
  
  /** Inserts an edge between two vertices of this graph,
    * if the vertices exist. Else does not change the graph. */
  public void addEdge (T vertex1, T vertex2){
    int i = ind(vertex1);
      int j = ind(vertex2);
    if (i!=-1 && j!=-1){
      arcs[i][j] = true;
      arcs[j][i] = true;
    }
  }
  
  /** Removes an edge between two vertices of this graph,
    if the vertices exist, else does not change the graph. */
  public void removeEdge (T vertex1, T vertex2){
    int i = ind(vertex1);
    int j = ind(vertex2);
    if (i!=-1 && j!=-1){
      arcs[i][j] = false;
      arcs[j][i] = false;
    }
  }
  
  /** Retrieve from a graph the vertices adjacent to vertex v.
    Assume that the vertex is in the graph */
  public LinkedList<T> getSuccessors(T vertex){
    LinkedList<T> l = new LinkedList<T>();
    int i = ind(vertex);
    for (int j = 0; j < n ; j++){
      if (arcs[i][j])
      l.add(vertices[j]);
    }
    return l;
  }
  
  /** Retrieve from a graph the vertices x preceding vertex v (x->v)
    and returns them onto a linked list */
  public LinkedList<T> getPredecessors(T vertex){
    LinkedList<T> l = new LinkedList<T>();
    int i = ind(vertex);
    for (int j = 0; j < n ; j++){
      if (arcs[j][i])
      l.add(vertices[j]);
    }
    return l;
  }
  
  /** Returns a string representation of the adjacency matrix. */
  public String toString(){
    String result = "";
    
    for (int i = 0; i < n; i++){
        result += ((i+1) + " " + this.vertices[i] + "\n");
      }
      result += ("#" + "\n");
      for (int i = 0; i<n; i++){
        for (int j = 0; j<n; j++){
          if (arcs[i][j])
            result += ((i+1) + " " + (j+1)  + "\n");
        }
      }
    return result;
  }
  
  /** Saves the current graph into a .tgf file.
    If it cannot save the file, a message is printed. */
  public void saveTGF(String tgf_file_name){
    try{
      PrintWriter writer = new PrintWriter(tgf_file_name + ".tgf"); 
      for (int i = 0; i < n; i++){
        writer.println((i+1) + " " + this.vertices[i]);
      }
      writer.println("#");
      for (int i = 0; i<n; i++){
        for (int j = 0; j<n; j++){
          if (arcs[i][j])
            writer.println((i+1) + " " + (j+1));
        }
      }
      writer.close();
    } catch(FileNotFoundException e){}
  }
  
  public static void main(String[] args){
    AdjMatGraph<String> myGraph = new AdjMatGraph<String>();
    
    myGraph.addVertex("A");
    myGraph.addVertex("B");
    myGraph.addVertex("C");
    myGraph.addVertex("D");
    myGraph.addVertex("C");
    myGraph.addVertex("E");
    myGraph.addVertex("F");
    myGraph.addArc("A", "B");
    myGraph.addArc("A", "B");
    myGraph.addArc("A", "C");
    myGraph.addArc("C", "E");
    myGraph.addEdge("F", "E");
    
    //myGraph.removeArc("A", "B");
    //myGraph.removeVertex("A");
    //myGraph.removeEdge("E", "F");
    System.out.println(myGraph.getPredecessors("E"));
    System.out.println(myGraph.getSuccessors("A"));

    System.out.print(myGraph.toString());
    
    myGraph.saveTGF("myGraph");
  }
}
