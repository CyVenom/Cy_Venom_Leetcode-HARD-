#The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
#Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
#Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.


class Solution {
public:
    vector<vector<string>> res;
    
    void backtrack(int row, int n, vector<string>& board, unordered_set<int>& cols, 
                   unordered_set<int>& diag1, unordered_set<int>& diag2) {
        if (row == n) {
            res.push_back(board);
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (cols.count(col) || diag1.count(row - col) || diag2.count(row + col))
                continue;

         
            board[row][col] = 'Q';
            cols.insert(col);
            diag1.insert(row - col);
            diag2.insert(row + col);

            backtrack(row + 1, n, board, cols, diag1, diag2);

            board[row][col] = '.';
            cols.erase(col);
            diag1.erase(row - col);
            diag2.erase(row + col);
        }
    }
    
    vector<vector<string>> solveNQueens(int n) {
        vector<string> board(n, string(n, '.')); 
        unordered_set<int> cols, diag1, diag2;
        
        backtrack(0, n, board, cols, diag1, diag2);
        return res;
    }
};
