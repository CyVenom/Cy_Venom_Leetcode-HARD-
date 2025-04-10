class Solution {
    class Pair {
        int val, index;
        Pair(int v, int i) {
            val = v;
            index = i;
        }
    }

    private void mergeSort(Pair[] pairs, int left, int right, int[] counts, Pair[] temp) {
        if (right - left <= 1) return;

        int mid = (left + right) / 2;
        mergeSort(pairs, left, mid, counts, temp);
        mergeSort(pairs, mid, right, counts, temp);

        int i = left, j = mid, k = left, rightCount = 0;

        while (i < mid && j < right) {
            if (pairs[i].val <= pairs[j].val) {
                counts[pairs[i].index] += rightCount;
                temp[k++] = pairs[i++];
            } else {
                rightCount++;
                temp[k++] = pairs[j++];
            }
        }

        while (i < mid) {
            counts[pairs[i].index] += rightCount;
            temp[k++] = pairs[i++];
        }

        while (j < right) {
            temp[k++] = pairs[j++];
        }

        for (i = left; i < right; i++) {
            pairs[i] = temp[i];
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] pairs = new Pair[n];
        Pair[] temp = new Pair[n];
        int[] counts = new int[n];

        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }

        mergeSort(pairs, 0, n, counts, temp);

        List<Integer> result = new ArrayList<>();
        for (int c : counts) {
            result.add(c);
        }
        return result;
    }
}
