#Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
#You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

class Solution {
public:
    int firstMissingPositive(vector<int>& nums) {
        int n = nums.size();


        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums[i], nums[nums[i] - 1]);
            }
        }

        
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1; 
            }
        }

       
        return n + 1;
    }
};
