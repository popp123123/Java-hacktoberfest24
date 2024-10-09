import java.util.*;

public class StringMatcher {
    public static boolean matchStrings(String str1, String str2) {
        // Exact matching
        if (str1.equals(str2)) {
            return true;
        }

        // Wildcard matching using dynamic programming
        int m = str1.length();
        int n = str2.length();
        boolean[][] lookup = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    lookup[i][j] = j > 0 && str2.charAt(j - 1) == '*';
                } else if (j == 0) {
                    lookup[i][j] = i > 0 && str1.charAt(i - 1) == '*';
                } else {
                    char c1 = str1.charAt(i - 1);
                    char c2 = str2.charAt(j - 1);

                    if (c1 == c2 || c2 == '?') {
                        lookup[i][j] = lookup[i - 1][j - 1];
                    } else if (c2 == '*') {
                        lookup[i][j] = lookup[i][j - 1] || lookup[i - 1][j];
                    } else {
                        lookup[i][j] = false;
                    }
                }
            }
        }

        return lookup[m][n];
    }

    public static void main(String[] args) {
        String str1 = "abcdhgh";
        String str2 = "abc*d?*";

        System.out.println("Matching result: " + matchStrings(str1, str2));
    }
}
