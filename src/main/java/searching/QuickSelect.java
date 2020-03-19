package searching;

import utils.ArrayUtils;

/**
 * The implementation of quick select algorithm
 * to find Kth smallest element
 */
public class QuickSelect {

    public int findKthSmallestElement(int[] array, int k) {
        if (array.length - 1 < k) {
            return -1;
        }
        return quickSelect(array, k, 0, array.length - 1);
    }

    private int quickSelect(int[] array, int k, int lo, int hi) {
        if (hi == lo) {
            return array[k];
        }

        int pivotIdx = partition(array, lo, hi);

        if (pivotIdx == k) {
            return array[pivotIdx];
        } else if (pivotIdx < k) {
            // go right
            return quickSelect(array, k, pivotIdx + 1, hi);
        } else {
            // go left
            return quickSelect(array, k, lo, pivotIdx - 1);
        }
    }

    /**
     * Using Lomuto partition scheme
     */
    private int partition(int[] array, int lo, int hi) {
        int pivot = hi;

        int left = lo;
        for (int right = lo; right <= hi; right++) {
            if (array[right] < array[pivot]) {
                ArrayUtils.swap(array, left, right);
                left++;
            }
        }

        ArrayUtils.swap(array, left, pivot);

        return left;
    }
}
