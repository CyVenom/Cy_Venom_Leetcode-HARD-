#Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
#The testcases will be generated such that the answer is unique.

class Solution {
public:
    string minWindow(string s, string t) {
        if (s.empty() || t.empty()) return "";

        unordered_map<char, int> freqT, windowFreq;
        int required = 0;  

        for (char c : t) {
            if (freqT[c]++ == 0) required++;
        }

        int left = 0, right = 0, formed = 0;
        int minLen = INT_MAX, startIdx = 0;

        while (right < s.size()) {
            char c = s[right];
            windowFreq[c]++;

            if (freqT.count(c) && windowFreq[c] == freqT[c]) {
                formed++;
            }

          
            while (left <= right && formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIdx = left;
                }

                char leftChar = s[left];
                windowFreq[leftChar]--;

                if (freqT.count(leftChar) && windowFreq[leftChar] < freqT[leftChar]) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return (minLen == INT_MAX) ? "" : s.substr(startIdx, minLen);
    }
};
