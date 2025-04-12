class Solution {
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private LinkedList<String> result = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Build the graph
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        dfs("JFK");
        return result;
    }

    private void dfs(String airport) {
        PriorityQueue<String> destinations = graph.get(airport);

        while (destinations != null && !destinations.isEmpty()) {
            String next = destinations.poll();
            dfs(next);
        }

        result.addFirst(airport); // build path in reverse
    }
}
