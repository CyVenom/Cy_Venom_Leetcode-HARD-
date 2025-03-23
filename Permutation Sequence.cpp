#The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
#By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
#"123"
#"132"
#"213"
#"231"
#"312"
#"321"
#Given n and k, return the kth permutation sequence.

class Solution {
public:
    string getPermutation(int n, int k) {
        vector<int> numbers; 
        vector<int> fact(n, 1); 
        string result = "";

        
        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        for (int i = 1; i <= n; i++) {
            numbers.push_back(i);
        }

        
        k--;

        
        for (int i = n - 1; i >= 0; i--) {
            int index = k / fact[i]; 
            result += to_string(numbers[index]);
            numbers.erase(numbers.begin() + index); 
            k %= fact[i]; 
        }

        return result;
    }
};
