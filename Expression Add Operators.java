class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        backtrack(num, target, 0, 0, 0, "", result);
        return result;
    }

    private void backtrack(String num, int target, int index, long currentValue, long lastOperand, String expression, List<String> result) {
        if (index == num.length()) {
            if (currentValue == target) {
                result.add(expression);
            }
            return;
        }
        
        for (int i = index + 1; i <= num.length(); i++) {
            String currentSubstring = num.substring(index, i);
            if (currentSubstring.length() > 1 && currentSubstring.charAt(0) == '0') {
                continue;
            }
            
            long currentNum = Long.parseLong(currentSubstring);
            
            if (index == 0) {
                backtrack(num, target, i, currentNum, currentNum, currentSubstring, result);
            } else {
                backtrack(num, target, i, currentValue + currentNum, currentNum, expression + "+" + currentSubstring, result);
                backtrack(num, target, i, currentValue - currentNum, -currentNum, expression + "-" + currentSubstring, result);
                backtrack(num, target, i, currentValue - lastOperand + lastOperand * currentNum, lastOperand * currentNum, expression + "*" + currentSubstring, result);
            }
        }
    }
}
