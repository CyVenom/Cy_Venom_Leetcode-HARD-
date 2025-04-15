class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }

        int[] mapped = new int[n];
        for (int i = 0; i < n; i++) {
            mapped[i] = pos[nums1[i]];
        }

        BIT bit1 = new BIT(n);
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = bit1.query(mapped[i]);
            bit1.update(mapped[i], 1);
        }

        BIT bit2 = new BIT(n);
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            right[i] = bit2.query(n - 1) - bit2.query(mapped[i]);
            bit2.update(mapped[i], 1);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            res += (long) left[i] * right[i];
        }
        return res;
    }

    class BIT {
        int[] tree;
        int n;

        public BIT(int size) {
            n = size + 2;
            tree = new int[n];
        }

        public void update(int i, int delta) {
            i++;
            while (i < n) {
                tree[i] += delta;
                i += (i & -i);
            }
        }

        public int query(int i) {
            i++;
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= (i & -i);
            }
            return sum;
        }
    }
}
