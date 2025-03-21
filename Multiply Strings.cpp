#Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
#Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

class Solution {
public:
    string multiply(string num1, string num2) {
        if (num1 == "0" || num2 == "0") return "0";

        int n = num1.size(), m = num2.size();
        vector<int> result(n + m, 0); 

       
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int mul = (num1[i] - '0') * (num2[j] - '0'); 
                int sum = mul + result[i + j + 1];

                result[i + j + 1] = sum % 10; 
                result[i + j] += sum / 10; 
            }
        }

        
        string product = "";
        for (int num : result) {
            if (!(product.empty() && num == 0)) { 
                product += to_string(num);
            }
        }

        return product.empty() ? "0" : product;
    }
};
