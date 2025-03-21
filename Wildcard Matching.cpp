#Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

#'?' Matches any single character.
#'*' Matches any sequence of characters (including the empty sequence).
#The matching should cover the entire input string (not partial).

#Optimized Space Complexity (1D DP)
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        vector<bool> prev(n + 1, false), curr(n + 1, false);

       
        prev[0] = true;

       
        for (int j = 1; j <= n; j++) {
            if (p[j - 1] == '*') prev[j] = prev[j - 1];
        }


        for (int i = 1; i <= m; i++) {
            curr[0] = false;
            for (int j = 1; j <= n; j++) {
                if (p[j - 1] == s[i - 1] || p[j - 1] == '?') {
                    curr[j] = prev[j - 1];
                } else if (p[j - 1] == '*') {
                    curr[j] = prev[j] || curr[j - 1];
                } else {
                    curr[j] = false;
                }
            }
            prev = curr; 
        }

        return prev[n];
    }
};


#Optimized C++ Solution (DP)
class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1, false));

        // Base case: Empty string matches empty pattern
        dp[0][0] = true;

        // '*' at the beginning can match empty sequence
        for (int j = 1; j <= n; j++) {
            if (p[j - 1] == '*') dp[0][j] = dp[0][j - 1];
        }

        // DP computation
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p[j - 1] == s[i - 1] || p[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1]; // Match exact character or '?'
                } else if (p[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // '*' as empty or any char
                }
            }
        }

        return dp[m][n];
    }
};














