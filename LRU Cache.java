class LRUCache {
    private final int capacity;
    private final Map<Integer, Integer> cache;
    private final LinkedHashMap<Integer, Integer> order;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.order = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        int value = cache.get(key);
        order.remove(key);
        order.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            order.remove(key);
        } else if (cache.size() >= capacity) {
            int lruKey = order.entrySet().iterator().next().getKey();
            cache.remove(lruKey);
            order.remove(lruKey);
        }
        cache.put(key, value);
        order.put(key, value);
    }
}
