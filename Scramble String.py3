#We can scramble a string s to get a string t using the following algorithm:

#If the length of the string is 1, stop.
#If the length of the string is > 1, do the following:
#Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
#Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
#Apply step 1 recursively on each of the two substrings x and y.
#Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.

class Solution:
    def isScramble(self, s1: str, s2: str) -> bool:
        if s1 == s2:
            return True
        if sorted(s1) != sorted(s2):  # Quick check to eliminate non-matching characters
            return False

        @lru_cache(None)  # Memoization to avoid redundant computations
        def dfs(s1: str, s2: str) -> bool:
            if s1 == s2:
                return True
            if sorted(s1) != sorted(s2):  # If the character counts don't match, it's impossible
                return False

            n = len(s1)
            for i in range(1, n):  # Try splitting at every possible index
                # Case 1: No swap - s1[:i] with s2[:i] and s1[i:] with s2[i:]
                if dfs(s1[:i], s2[:i]) and dfs(s1[i:], s2[i:]):
                    return True
                
                # Case 2: Swap - s1[:i] with s2[-i:] and s1[i:] with s2[:-i]
                if dfs(s1[:i], s2[-i:]) and dfs(s1[i:], s2[:-i]):
                    return True

            return False
        
        return dfs(s1, s2)
