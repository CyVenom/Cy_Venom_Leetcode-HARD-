#Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

#You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

#Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

#For the last line of text, it should be left-justified, and no extra space is inserted between words.

#Note:

#A word is defined as a character sequence consisting of non-space characters only.
#Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
#The input array words contains at least one word.


class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int n = digits.size();
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits.insert(digits.begin(), 1);
        return digits;
    }
    
    string addBinary(string a, string b) {
        string result = "";
        int i = a.size() - 1, j = b.size() - 1, carry = 0;
        
        while (i >= 0 || j >= 0 || carry) {
            int sum = carry;
            if (i >= 0) sum += a[i--] - '0';
            if (j >= 0) sum += b[j--] - '0';
            result = char(sum % 2 + '0') + result;
            carry = sum / 2;
        }
        
        return result;
    }
    
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> result;
        int i = 0, n = words.size();
        
        while (i < n) {
            int j = i, lineLength = 0;
            while (j < n && lineLength + words[j].size() + (j - i) <= maxWidth) {
                lineLength += words[j].size();
                j++;
            }
            
            int spaceSlots = j - i - 1;
            string line = words[i];
            
            if (j == n || spaceSlots == 0) { // Last line or single word
                for (int k = i + 1; k < j; k++) {
                    line += " " + words[k];
                }
                line += string(maxWidth - line.size(), ' ');
            } else { // Normal justification
                int spaces = (maxWidth - lineLength) / spaceSlots;
                int extraSpaces = (maxWidth - lineLength) % spaceSlots;
                
                for (int k = i + 1; k < j; k++) {
                    line += string(spaces + (k - i <= extraSpaces ? 1 : 0), ' ') + words[k];
                }
            }
            
            result.push_back(line);
            i = j;
        }
        return result;
    }
};
