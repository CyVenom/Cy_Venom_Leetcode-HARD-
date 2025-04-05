class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> window = new TreeSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            long num = (long) nums[i];
            
            Long floor = window.floor(num + valueDiff);
            Long ceiling = window.ceiling(num - valueDiff);
            
            if ((floor != null && floor >= num) || (ceiling != null && ceiling <= num))
                return true;

            window.add(num);
            if (i >= indexDiff) {
                window.remove((long) nums[i - indexDiff]);
            }
        }
        
        return false;
    }
}
