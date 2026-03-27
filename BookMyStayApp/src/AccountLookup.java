import java.util.*;

public class AccountLookup {

    // -------- Linear Search: First Occurrence --------
    public static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First -> Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First -> Comparisons: " + comparisons);
        return -1;
    }

    // -------- Linear Search: Last Occurrence --------
    public static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                index = i;
            }
        }

        System.out.println("Linear Last -> Comparisons: " + comparisons);
        return index;
    }

    // -------- Binary Search (Find any one occurrence) --------
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Search -> Comparisons: " + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search -> Comparisons: " + comparisons);
        return -1;
    }

    // -------- Count Occurrences (Binary Search) --------
    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    private static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) == 0) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) == 0) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // Linear Search (unsorted)
        int first = linearFirst(logs, "accB");
        int last = linearLast(logs, "accB");

        System.out.println("Linear First accB: index " + first);
        System.out.println("Linear Last accB: index " + last);

        // Sort before Binary Search
        Arrays.sort(logs);
        System.out.println("Sorted logs: " + Arrays.toString(logs));

        // Binary Search
        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Binary accB: index " + index + ", count=" + count);
    }
}