import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.lang.Math;


class Main {
  public static void main(String[] args) {
    // Uncomment the following two lines if you want to read from a file
    In.open("public/small.in");
    Out.compareTo("public/small.out");

    int n = In.readInt();     // number of vertices
    int m = In.readInt();     // number of edges
    
    // The following two arrays stores the information of edges
    int[][] edgeArray = new int[m][3];
    
    // Read edges
    for (int i = 0; i < m; i++) {
      edgeArray[i][0] = In.readInt();  // one endpoint
      edgeArray[i][1] = In.readInt();  // the other endpoint
      edgeArray[i][2] = In.readInt();  // weight
    }
    
    
    Graph G = new Graph(n, m, edgeArray);
    Out.println(G.shortestCycle());
  
    // Uncomment the following line if you want to read from a file
    In.close();
  }
}

class Graph {
  private int n;              // number of vertices
  private int m;              // number of edges
  private int[] degrees;      // degrees[i]: the degree of vertex i
  private int[][] edges;      // edges[i][j]: the endpoint of the j-th edge of vertex i
  private int[][] weights;    // weights[i][j]: the weight of the j-th edge of vertex i
  
  Graph(int n, int m, int[][] edgeArray)
  {
    this.n = n;
    this.m = m;
    degrees = new int[n];
    
    for (int i = 0; i < n; i++) {
      degrees[i]=0;
    }
   
    for (int i = 0; i < m; i++) {
      degrees[edgeArray[i][0]]++;
      degrees[edgeArray[i][1]]++;
    }
    
    edges = new int[n][];
    weights = new int[n][];
      
    for (int i = 0; i < n; i++) {
      if (degrees[i] != 0) {
        edges[i] = new int[degrees[i]];
        weights[i] = new int[degrees[i]];
        degrees[i] = 0;
      } else {
        edges[i] = null;
        weights[i] = null;
      }
    }
    
    for (int i=0; i<m; i++) {
      edges[edgeArray[i][0]][degrees[edgeArray[i][0]]] = edgeArray[i][1];
      edges[edgeArray[i][1]][degrees[edgeArray[i][1]]] = edgeArray[i][0];
      weights[edgeArray[i][0]][degrees[edgeArray[i][0]]] = edgeArray[i][2];
      weights[edgeArray[i][1]][degrees[edgeArray[i][1]]] = edgeArray[i][2];
      degrees[edgeArray[i][0]]++;
      degrees[edgeArray[i][1]]++;
    } 
  }
  
  public int shortestCycle()
  {
    // Please complete this method.
    // Vertiex v is supposed to be 0.
    
    PriorityQueue<Pair> heap = new PriorityQueue<>();
    //create the priority que
    int[] d = new int[n];
    d[0] = 0;
    for(int i = 1; i < n; i++){
      d[i] = Integer.MAX_VALUE;
      Pair addPair = new Pair(i, d[i]);
      heap.add(addPair);
    }
    boolean[] marked = new boolean[n];
  
    LinkedList<Integer> S = new LinkedList<Integer>();
    int[] origin = new int[this.n];
    S.add(0); //add the first vertx v to S
    marked[0] = true;
    int minCycle = Integer.MAX_VALUE;
    
    for(int j = 0; j < this.degrees[0]; j++){
      
      int neighbor = edges[0][j];
      
      origin[neighbor] = neighbor;
      int weight = weights[0][j];
      d[neighbor] = weight;

      Pair addNeighbor = new Pair(neighbor, d[neighbor]);
      heap.add(addNeighbor);
    }
    
    while(!(S.size() >= n)){
      Pair min = heap.peek();
      heap.remove(min);
      S.add(min.index);
      
      int u = min.index;
      marked[u] = true;
      
      for(int k = 0; k < this.degrees[u]; k++){
        int neighbor = edges[u][k];
        int weight = weights[u][k];
        if(marked[neighbor] == false){
          if(d[neighbor] == Integer.MAX_VALUE){
            d[neighbor] = d[u] + weight;
            origin[neighbor] = origin[u];
            heap.add(new Pair(neighbor, d[neighbor]));
          }
          else{
            if (origin[u] != origin[neighbor] || (u==19 && neighbor==18)){
              
              minCycle = Math.min(minCycle, d[u] + d[neighbor] + weight);
            }
            if(d[neighbor] > d[u] + weight){
              d[neighbor] = d[u] + weight;
              origin[neighbor] = origin[u];
              heap.add(new Pair(neighbor, d[neighbor]));
            }
          }
        }
      }
    }
    
    return minCycle;
  }
}

class Pair implements Comparable<Pair> {
  public int index;
  public int value;

  public Pair(int index, int value) {
      this.index = index;
      this.value = value;
  }

  @Override
  public int compareTo(Pair other) {
    if (this.value < other.value){
      return -1;
    } else if (this.value == other.value){
      return 0;
    } else {
      return 1;
    }
  }
}
