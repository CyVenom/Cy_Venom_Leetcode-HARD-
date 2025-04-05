class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        int[] lps = computeLPS(combined);
        int len = lps[lps.length - 1];
        return rev.substring(0, s.length() - len) + s;
    }

    private int[] computeLPS(String str) {
        int[] lps = new int[str.length()];
        int length = 0;
        for (int i = 1; i < str.length(); i++) {
            while (length > 0 && str.charAt(i) != str.charAt(length)) {
                length = lps[length - 1];
            }
            if (str.charAt(i) == str.charAt(length)) {
                length++;
                lps[i] = length;
            }
        }
        return lps;
    }
}
