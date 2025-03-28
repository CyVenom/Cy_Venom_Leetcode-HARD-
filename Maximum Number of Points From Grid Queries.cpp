class Solution {
public:
    vector<int> maxPoints(vector<vector<int>>& grid, vector<int>& queries) {
        int m = grid.size(), n = grid[0].size();
        int k = queries.size();
        vector<int> answer(k, 0);

        
        vector<pair<int, int>> sortedQueries;
        for (int i = 0; i < k; ++i) {
            sortedQueries.emplace_back(queries[i], i);
        }
        sort(sortedQueries.begin(), sortedQueries.end());

        
        priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> minHeap;
        minHeap.push({grid[0][0], {0, 0}});

        
        vector<vector<bool>> visited(m, vector<bool>(n, false));
        visited[0][0] = true;

        int points = 0;  
        int index = 0;  
        vector<int> directions = {-1, 0, 1, 0, -1};  

        for (auto [q, i] : sortedQueries) {
            
            while (!minHeap.empty() && minHeap.top().first < q) {
                auto [value, pos] = minHeap.top();
                minHeap.pop();
                int r = pos.first, c = pos.second;

                points++;  

               
                for (int d = 0; d < 4; ++d) {
                    int nr = r + directions[d], nc = c + directions[d + 1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        minHeap.push({grid[nr][nc], {nr, nc}});
                    }
                }
            }
            answer[i] = points;  
        }

        return answer;
    }
};
