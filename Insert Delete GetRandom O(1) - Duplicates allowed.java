class RandomizedCollection {
    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;
    private Random rand;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        boolean notPresent = !map.containsKey(val);
        map.putIfAbsent(val, new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);
        return notPresent;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) return false;

        // Get any index of the value
        int indexToRemove = map.get(val).iterator().next();
        map.get(val).remove(indexToRemove);

        int lastVal = list.get(list.size() - 1);
        list.set(indexToRemove, lastVal);

        map.get(lastVal).add(indexToRemove);
        map.get(lastVal).remove(list.size() - 1);

        list.remove(list.size() - 1);

        if (map.get(val).isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
