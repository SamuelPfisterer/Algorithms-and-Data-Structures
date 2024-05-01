import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;


class Main {
  public static void main(String[] args) {
    // Uncomment this line if you want to read from a file
    //In.open("public/example.in");
    //Out.compareTo("public/example.out");

    int n = In.readInt(); // number of nodes
    int m = In.readInt(); // number of edges
    
    ArrayList<Integer>[] edges = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      edges[i] = new ArrayList<Integer>();
    }
    
    // edges
    for(int i = 0; i < m; i++) {
      int node1 = In.readInt();
      int node2 = In.readInt();
      edges[node1].add(node2);
      edges[node2].add(node1);
    }
    
    Out.println(getMinDistance(n, edges));
    
    // Uncomment this line if you want to read from a file
    //In.close();
  }
  
  public static int getMinDistance(int n, ArrayList<Integer>[] edges) {
    // n = number of nodes
    // for 0 <= i < n - 1, edges[i] is an array of the neighbors of node i
    
    //find all edges with odd degreee
    
    ArrayList<Integer> oddVertices = new ArrayList<Integer>();
    boolean[] oddIndex = new boolean[n];
    for(int i = 0; i < n; i++){
      if(edges[i].size() % 2 != 0){
        oddVertices.add(i);
        oddIndex[i] = true;
      }
    }
    
    //creating a super node odd vertice / the first odd node gets the super vertex
    int superNode = oddVertices.get(0);
    for(int i = 1; i < oddVertices.size(); i++){
      edges[superNode].addAll(edges[oddVertices.get(i)]);
    }
    /*
    for(int i = 0; i < oddVertices.size(); i++){
      //go through all the neighbors of this odd vertice
      int oddParent = oddVertices.get(i);
      for(int j = 0; j < edges[oddParent].size(); j++){
        int neighbor = edges[oddParent].get(j);
        if(oddIndex[neighbor] && neighbor != oddParent){//neighbor is an odd vertice
          //delete it from the oddVertice list and add all its elements to oddParent
          oddVertices.remove(new Integer(neighbor));
          //edges[oddParent].addAll(edges[neighbor]);
        }
      }
    }
    */
    
    int[] d = BFS(oddVertices.get(0), n, edges);
    /*
    for(int i = 1; i < oddVertices.size(); i++){
      int[] addArray = BFS(oddVertices.get(i), n, edges);
      for(int j = 0; j < n; j++){
        if(d[j] > addArray[j]){
          d[j] = addArray[j];
        }
        //d[j] = d[j] + addArray[j];
      }
    }
    */
    // this is the part where i calculate the min value for which min distance from the node to the others is maximized
    int output = Integer.MIN_VALUE;
    for(int i = 0; i < d.length; i++){
      if(d[i] > output){
        output = d[i];
      }
    }
    return output;
  }
  
  public static int[] BFS(int start, int n, ArrayList<Integer>[] edges){
    boolean[] visited = new boolean[n];
    
    LinkedList<Integer> que = new LinkedList<Integer>();
    visited[start] = true;
    que.add(start);
    int[] distance = new int[n];
    distance[start] = 0;
    
    while(que.size() != 0){
      int s = que.poll();
      for(int i = 0; i < edges[s].size(); i++){
        int neighbor = edges[s].get(i);
        if(!visited[neighbor]){
          visited[neighbor] = true;
          distance[neighbor] = distance[s] + 1;
          que.add(neighbor);
        }
      }
    }
    return distance;
  }
}