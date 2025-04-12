class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();

            for (int j = 0; j <= len; j++) {
                String prefix = word.substring(0, j);
                String suffix = word.substring(j);

                if (isPalindrome(prefix)) {
                    String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                    if (map.containsKey(reversedSuffix) && map.get(reversedSuffix) != i) {
                        result.add(List.of(map.get(reversedSuffix), i));
                    }
                }

                if (j != len && isPalindrome(suffix)) {
                    String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                    if (map.containsKey(reversedPrefix) && map.get(reversedPrefix) != i) {
                        result.add(List.of(i, map.get(reversedPrefix)));
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
