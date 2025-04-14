class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        
      
        for (int top = 0; top < m; top++) {
            int[] sums = new int[n];  
            for (int bottom = top; bottom < m; bottom++) {
                
              
                for (int i = 0; i < n; i++) {
                    sums[i] += matrix[bottom][i];
                }
                
            
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int currSum = 0;
                
                for (int sum : sums) {
                    currSum += sum;
                    
                  
                    Integer target = set.ceiling(currSum - k);
                    if (target != null) {
                        maxSum = Math.max(maxSum, currSum - target);
                    }
                    
                   
                    set.add(currSum);
                }
            }
        }
        
        return maxSum;
    }
}
