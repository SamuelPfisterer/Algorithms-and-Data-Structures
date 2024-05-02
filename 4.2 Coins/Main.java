import java.util.Arrays;
import java.util.ArrayList;


class Main {
  private static int n;
  private static int[] E1;
  private static int[] E2;

  public static void main(String[] args) {
    // Uncomment this line if you want to read from a file
    //In.open("public/custom.in");
    //Out.compareTo("public/custom.out");

    n = In.readInt(); // number of nodes
    E1 = new int[n - 1];
    E2 = new int[n - 1];
    
    // edges
    for(int i = 0; i < n - 1; i++) {
      // edge between E1[i] and E2[i], such that E1[i] is the parent of E2[i]
      E1[i] = In.readInt();
      E2[i] = In.readInt();
    }
    
    Out.println(getMinCoins());
    
    // Uncomment this line if you want to read from a file
    //In.close();
  }
  
  public static int getMinCoins() {
    // n = number of nodes
    // for 0 <= i < n - 1, there is an edge between E1[i] and E2[i], such that E1[i] is the parent of E2[i]
    //create adjacency list
    ArrayList<Integer>[] adL = new ArrayList[n]; 
    for(int i = 0; i < n; i++){
      adL[i] = new ArrayList<Integer>();
    }
    
    //fill the adjacency list with the successors
    for(int i = 0; i < n-1; i++){
      adL[E1[i]].add(E2[i]);
    }
    
    
    
    
    
    return DFS(adL, 0);
  }
  public static int DFS(ArrayList<Integer>[] adjL, int node){
    if(adjL[node].size() == 0){
      return 1;
    }
    if(adjL[node].size() == 1){
      return DFS(adjL, adjL[node].get(0));
    }
    else{//(adjL[node].size()>1)
      //calculate everything for the first two nodes
      
      //we create a new array with all the needed k values for every child node of node
      int[] arr = new int[adjL[node].size()];
      for(int i = 0; i < arr.length; i++){
        arr[i] = DFS(adjL, adjL[node].get(i));
      }
      //sort array
      Arrays.sort(arr);
      int length = arr.length;
      int k;
      
      if(arr[length - 1] > arr[length - 2] + 1){
        k = arr[length -1];
      }
      else{
        k = arr[length - 2] + 1;
      }
      
      
    
    //check if the rest is enough for all the additional nodes, if not adjust k
    
    int rest = k -2;
    
    for(int i = length-3; i >= 0; i--){
      //check if rest is enough
      if(rest < arr[i]){//rest isn't enough => we have to add coins and change rest accordingly
        int addCoins = arr[i] - rest;
        k = k + addCoins;
        rest = rest + addCoins;
      }
      rest = rest -1;
    }
    
    return k;
    }
  }
}
