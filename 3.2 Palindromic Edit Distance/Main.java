class Main {
    public static void main(String[] args) {
      
        // Uncomment this line if you want to read from a file
        In.open("public/example.in");
        Out.compareTo("public/example.out");

        int m = In.readInt(); //Read the number of sequences
        
        
        for(int i=0;i<m;i++){
          
          int n = In.readInt(); // Read the number of charachters of the i-th sequence 
          String SA=In.readString();        // Read A as a string
          
          char[] A=new char[n];
          for(int j=0;j<n;j++){
            A[j]=SA.charAt(j);
          }

          Out.println(Palindromic_Edit_Distance(A,n));
        }
        
       
                
        // Uncomment this line if you want to read from a file
        In.close();
   
    }
    
    public static int Palindromic_Edit_Distance(char []A, int n)
    {
      
        //  A:      The input sequence
        //  n:      The length of A;
        
       int[][] DP = new int[n][n];
       
       for(int i = n-1; i >= 0; i--){
         for(int j = i +1; j < n; j++){
           int sigma = 1;
           if(A[i] == A[j]){
             sigma = 0;
           }
           
           int a = DP[i+1][j-1] + sigma;
           int b = DP[i+1][j] +1;
           int c = DP[i][j-1] +1;
           
           if(b < a){
            a= b;
           }
           if(c < a){
             a = c;
           }
           DP[i][j] = a;
         }
       }
       return DP[0][n-1];
       
    
    }

    
}
