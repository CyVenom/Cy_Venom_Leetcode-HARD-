#You are given a string s and an array of strings words. All the strings of words are of the same length.
#A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
#For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
#Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.


class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        vector<int> result;
        if (words.empty() || s.empty()) return result;

        int wordLength = words[0].length();
        int totalWords = words.size();
        int totalLength = wordLength * totalWords;
        int sLength = s.length();

        
        unordered_map<string, int> wordCount;
        for (const string& word : words) {
            wordCount[word]++;
        }

        
        for (int i = 0; i < wordLength; i++) {
            int left = i, right = i, count = 0;
            unordered_map<string, int> windowCount;

            while (right + wordLength <= sLength) {
                string word = s.substr(right, wordLength);
                right += wordLength;

                if (wordCount.find(word) != wordCount.end()) {
                    windowCount[word]++;
                    count++;

                    while (windowCount[word] > wordCount[word]) {
                        string leftWord = s.substr(left, wordLength);
                        windowCount[leftWord]--;
                        count--;
                        left += wordLength;
                    }

                    if (count == totalWords) {
                        result.push_back(left);
                    }
                } else {
                    
                    windowCount.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
};

