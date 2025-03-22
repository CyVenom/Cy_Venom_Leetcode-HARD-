#The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
#Given an integer n, return the number of distinct solutions to the n-queens puzzle.

class Solution {
public:
    int count = 0;
    
    void backtrack(int row, int n, unordered_set<int>& cols, 
                   unordered_set<int>& diag1, unordered_set<int>& diag2) {
        if (row == n) {
            count++;
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (cols.count(col) || diag1.count(row - col) || diag2.count(row + col))
                continue;

            
            cols.insert(col);
            diag1.insert(row - col);
            diag2.insert(row + col);

            
            backtrack(row + 1, n, cols, diag1, diag2);

           
            cols.erase(col);
            diag1.erase(row - col);
            diag2.erase(row + col);
        }
    }
    
    int totalNQueens(int n) {
        unordered_set<int> cols, diag1, diag2;
        backtrack(0, n, cols, diag1, diag2);
        return count;
    }
};
