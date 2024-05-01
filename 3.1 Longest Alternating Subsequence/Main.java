class Main {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        //In.open("public/test2.in");
        //Out.compareTo("public/test2.out");

        int n = In.readInt(); //Read the size of Array;
        int []A = new int[n];
        
        // Read A
        for(int i = 0; i < n; i++) {
          A[i] = In.readInt(); 
        }
        
        Out.println(longest_alternating_subsequence(n, A));
        
        // Uncomment this line if you want to read from a file
        //In.close();
    }
    
    public static int longest_alternating_subsequence(int n, int []A)
    {
      // n: length of A
      // A: an array of distinct integers
      
      // setting everything up e.g. the base cases
      int [][] DP = new int[2][A.length];
      DP[0][0] = 1;
      DP[1][0] = 0;
      /*
      for(int i = 1; i<DP.length; i++){
        DP[0][i] = 0;
      }
      */
      
      // fillig the DP Table
      
      for(int a = 0; a < A.length; a++){
        for(int b = 0; b < A.length; b++){
          if (A[a] < A[b] && DP[1][b] >= 0 && (DP[0][b]+1) > DP[0][a]){
            DP[0][a] = DP[0][b]+1;
            DP[1][a] = -1;
          }
          if (A[a] > A[b] && DP[1][b] <= 0 && (DP[0][b]+1) > DP[0][a]){
            DP[0][a] = DP[0][b]+1;
            DP[1][a] = 1;
          }
        }
      }
      int max = 0;
      for (int q = 0; q<A.length; q++){
        if (DP[0][q] > max){
          max = DP[0][q];
        }
      }
      
      
      
      return max;
    }
  
}