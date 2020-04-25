package string;

/**
 * The implementation of Knuth-Morris-Pratt algorithm for
 * finding the first occurrence of a string P in a string T
 */
public class KMP {

    // if pattern is empty the returns 0
    public int indexOf(String text, String pattern) {
        int[] lps = generateLPS(pattern);

        return match(text, pattern, lps);
    }

    /**
     * Generates LPS (a prefix which is also a suffix) table based on the pattern string
     */
    private int[] generateLPS(String pattern) {
        int[] lps = new int[pattern.length()];

        for (int i = 0, j = 1; j < pattern.length(); ) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[j] = i + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    j++;
                } else {
                    i = lps[i - 1];
                }
            }
        }

        return lps;
    }

    /**
     * Finds the first occurrence of the pattern in the text using LPS table
     */
    private int match(String text, String pattern, int[] lps) {
        int i = 0;
        int j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }

        return j == pattern.length() ? i - j : -1;
    }
}
