class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new int[]{b[0], -b[2]});
            points.add(new int[]{b[1], b[2]});  
        }

        points.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1]; 
        });

        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(0);
        int prevMax = 0;

        for (int[] p : points) {
            int x = p[0], h = p[1];
            if (h < 0) {
                maxHeap.add(-h); 
            } else {
                maxHeap.remove(h); 
            }

            int currMax = maxHeap.peek();
            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}
