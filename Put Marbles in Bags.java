class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (k == 1) return 0; 

        int[] pairSums = new int[n - 1];
        
        
        for (int i = 0; i < n - 1; i++) {
            pairSums[i] = weights[i] + weights[i + 1];
        }
        
        Arrays.sort(pairSums); 

        long minSum = 0, maxSum = 0;
        
        
        for (int i = 0; i < k - 1; i++) {
            minSum += pairSums[i];              
            maxSum += pairSums[n - 2 - i];       
        }
        
        return maxSum - minSum;
    }
}
