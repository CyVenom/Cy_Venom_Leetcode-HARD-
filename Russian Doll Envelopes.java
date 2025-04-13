class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        
        Arrays.sort(envelopes, (a, b) -> 
            a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]
        );

        
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++)
            heights[i] = envelopes[i][1];

     
        return lengthOfLIS(heights);
    }

    private int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            int idx = Collections.binarySearch(lis, num);
            if (idx < 0) idx = -(idx + 1);
            if (idx == lis.size()) lis.add(num);
            else lis.set(idx, num);
        }
        return lis.size();
    }
}
