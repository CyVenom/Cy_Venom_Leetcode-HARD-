#We can scramble a string s to get a string t using the following algorithm:

#If the length of the string is 1, stop.
#If the length of the string is > 1, do the following:
#Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
#Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
#Apply step 1 recursively on each of the two substrings x and y.
#Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.


class Solution {
public:
    unordered_map<string, bool> dp;

    bool isScramble(string s1, string s2) {
        if (s1 == s2) return true;
        if (s1.length() != s2.length()) return false;

        string key = s1 + "_" + s2;
        if (dp.find(key) != dp.end()) return dp[key];

        int n = s1.length();
        vector<int> count(26, 0);

       
        for (int i = 0; i < n; i++) {
            count[s1[i] - 'a']++;
            count[s2[i] - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return dp[key] = false;
        }

        for (int i = 1; i < n; i++) {
            
            if (isScramble(s1.substr(0, i), s2.substr(0, i)) &&
                isScramble(s1.substr(i), s2.substr(i))) {
                return dp[key] = true;
            }

            
            if (isScramble(s1.substr(0, i), s2.substr(n - i)) &&
                isScramble(s1.substr(i), s2.substr(0, n - i))) {
                return dp[key] = true;
            }
        }

        return dp[key] = false;
    }
};

