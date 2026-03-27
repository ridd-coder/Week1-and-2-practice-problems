import java.util.*;

public class RiskThresholdLookup {

    // -------- Linear Search (unsorted) --------
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear -> Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear -> Comparisons: " + comparisons);
        return -1;
    }

    // -------- Binary Search: Insertion Point (lower_bound) --------
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("LowerBound -> Comparisons: " + comparisons);
        return low; // insertion index
    }

    // -------- Binary Search: Upper Bound --------
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        int comparisons = 0;

        while (low < high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println("UpperBound -> Comparisons: " + comparisons);
        return low;
    }

    // -------- Floor (largest ≤ target) --------
    public static Integer floor(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx == arr.length || arr[idx] > target) {
            idx--;
        }

        return (idx >= 0) ? arr[idx] : null;
    }

    // -------- Ceiling (smallest ≥ target) --------
    public static Integer ceiling(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx == arr.length) return null;

        return arr[idx];
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        // Linear Search (unsorted)
        int linearResult = linearSearch(unsorted, target);
        System.out.println("Linear Search result: " + linearResult);

        // Sort for Binary Search
        int[] sorted = {10, 25, 50, 100};
        System.out.println("Sorted risks: " + Arrays.toString(sorted));

        // Binary operations
        int lb = lowerBound(sorted, target);
        int ub = upperBound(sorted, target);

        Integer floor = floor(sorted, target);
        Integer ceiling = ceiling(sorted, target);

        System.out.println("Insertion index: " + lb);
        System.out.println("Floor(" + target + "): " + floor);
        System.out.println("Ceiling(" + target + "): " + ceiling);
    }
}