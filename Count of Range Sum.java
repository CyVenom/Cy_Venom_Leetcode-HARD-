class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefixSums = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        return mergeSort(prefixSums, 0, prefixSums.length - 1, lower, upper);
    }

    private int mergeSort(long[] prefixSums, int left, int right, int lower, int upper) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int count = mergeSort(prefixSums, left, mid, lower, upper) 
                    + mergeSort(prefixSums, mid + 1, right, lower, upper);

        // Count the valid range sums
        int j = mid + 1, k = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && prefixSums[j] - prefixSums[i] < lower) {
                j++;
            }
            while (k <= right && prefixSums[k] - prefixSums[i] <= upper) {
                k++;
            }
            count += k - j;
        }

        // Merge the two halves
        long[] temp = new long[right - left + 1];
        int i = left, l = 0;
        int r = mid + 1;
        while (i <= mid && r <= right) {
            if (prefixSums[i] <= prefixSums[r]) {
                temp[l++] = prefixSums[i++];
            } else {
                temp[l++] = prefixSums[r++];
            }
        }
        while (i <= mid) {
            temp[l++] = prefixSums[i++];
        }
        while (r <= right) {
            temp[l++] = prefixSums[r++];
        }

        System.arraycopy(temp, 0, prefixSums, left, temp.length);
        return count;
    }
}
