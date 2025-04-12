class Solution {
    public long countGoodIntegers(int n, int k) {
        Set<String> seenDigitFreqs = new HashSet<>();
        List<String> palindromes = generatePalindromes(n);

        for (String p : palindromes) {
            if (p.charAt(0) != '0' && Long.parseLong(p) % k == 0) {
                int[] freq = new int[10];
                for (char c : p.toCharArray()) freq[c - '0']++;
                seenDigitFreqs.add(freqToString(freq));
            }
        }

        long count = 0;
        for (String freqStr : seenDigitFreqs) {
            int[] freq = parseFreq(freqStr);
            count += countPermutations(freq, n);
        }

        return count;
    }

    private List<String> generatePalindromes(int n) {
        List<String> res = new ArrayList<>();
        int half = (n + 1) / 2;
        int start = (int) Math.pow(10, half - 1);
        int end = (int) Math.pow(10, half);

        for (int i = start; i < end; i++) {
            String left = Integer.toString(i);
            String right = new StringBuilder(left.substring(0, n / 2)).reverse().toString();
            res.add(left + right);
        }

        return res;
    }

    private String freqToString(int[] freq) {
        StringBuilder sb = new StringBuilder();
        for (int f : freq) sb.append(f).append(',');
        return sb.toString();
    }

    private int[] parseFreq(String freqStr) {
        String[] parts = freqStr.split(",");
        int[] freq = new int[10];
        for (int i = 0; i < 10; i++) freq[i] = Integer.parseInt(parts[i]);
        return freq;
    }

    private long countPermutations(int[] freq, int totalDigits) {
        long res = factorial(totalDigits);
        for (int f : freq) res /= factorial(f);

        long leadingZeroCases = 0;
        if (freq[0] > 0) {
            freq[0]--;
            long zeroStart = factorial(totalDigits - 1);
            for (int f : freq) zeroStart /= factorial(f);
            leadingZeroCases = zeroStart;
            freq[0]++;
        }

        return res - leadingZeroCases;
    }

    private long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }
}
