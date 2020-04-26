package string;

/**
 * Calculates Z algorithm of a given string
 */
public class ZAlgorithm {

    public int[] calculate(String str) {
        int n = str.length();
        int[] table = new int[n];

        int len = 0;
        // [left, right] box represents the left and right borders of the longest prefix
        int left = 1;
        int right = 1;
        while (left < n) {

            // expand [left, right] box as much as possible
            while (right < n && str.charAt(len) == str.charAt(right)) {
                len++;
                right++;
            }

            table[left] = len;

            // fill in [left, right] box
            int k = 1;
            while (left + k + table[k] < right) {
                table[left + k] = table[k];
                k++;
            }

            left = left + k;
            right = Math.max(left, right); // the right can't be behind the left border
            len = right - left;
        }

        return table;
    }
}
