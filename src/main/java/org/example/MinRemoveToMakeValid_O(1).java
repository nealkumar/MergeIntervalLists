public class Solution {
    public void minRemoveToMakeValid(char[] s) {
        int extraOpens = 0;
        int totalOpens = 0;
        int j = 0;
        
        // First pass: Remove unmatched closing parentheses.
        // We write valid characters into the beginning of the array.
        for (int i = 0; i < s.length; i++) {
            char c = s[i];
            if (c == ')') {
                if (extraOpens == 0) {
                    // Skip this unmatched closing parenthesis.
                    continue;
                }
                extraOpens--;
                s[j++] = c;
            } else if (c == '(') {
                totalOpens++;
                extraOpens++;
                s[j++] = c;
            } else {
                s[j++] = c;
            }
        }
        
        // After first pass, j is the length of the partially processed array.
        int lengthAfterFirstPass = j;
        j = 0;
        // Number of '(' that should remain.
        int keep = totalOpens - extraOpens;
        
        // Second pass: Remove extra '(' by only keeping the first 'keep' occurrences.
        for (int i = 0; i < lengthAfterFirstPass; i++) {
            char c = s[i];
            if (c == '(') {
                if (keep > 0) {
                    s[j++] = c;
                    keep--;
                }
                // Otherwise, skip the extra '('.
            } else {
                s[j++] = c;
            }
        }
        
        // Optional: Clear the remainder of the array.
        for (int i = j; i < s.length; i++) {
            s[i] = '\0';
        }
    }
    
    // Example usage:
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test case 1
        char[] test1 = "))(ab()c)(".toCharArray();
        sol.minRemoveToMakeValid(test1);
        System.out.println(new String(test1).trim());  // Expected valid result: "((ab)c)"
        
        // Test case 2
        char[] test2 = "((ab((()))c)(".toCharArray();
        sol.minRemoveToMakeValid(test2);
        System.out.println(new String(test2).trim());  // Expected valid result: "((ab(()))c)"
    }
}
