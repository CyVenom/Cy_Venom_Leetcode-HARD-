class SummaryRanges {

    private TreeSet<Integer> set;

    public SummaryRanges() {
        set = new TreeSet<>();
    }

    public void addNum(int value) {
        set.add(value);
    }

    public int[][] getIntervals() {
        List<int[]> result = new ArrayList<>();
        if (set.isEmpty()) return new int[0][0];

        Iterator<Integer> it = set.iterator();
        int start = it.next();
        int end = start;

        while (it.hasNext()) {
            int num = it.next();
            if (num == end + 1) {
                end = num;
            } else {
                result.add(new int[]{start, end});
                start = end = num;
            }
        }

        result.add(new int[]{start, end});
        return result.toArray(new int[result.size()][]);
    }
}
