#Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        if (n == 0) return 0; 
        
        int left = 0, right = n - 1;  
        int leftMax = 0, rightMax = 0; 
        int trappedWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];  
                } else {
                    trappedWater += leftMax - height[left]; 
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right]; 
                } else {
                    trappedWater += rightMax - height[right]; 
                }
                right--;
            }
        }

        return trappedWater;
    }
};
