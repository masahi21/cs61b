import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Note that every sorting algorithm takes in an argument k. The sorting 
 * algorithm should sort the array from index 0 to k. This argument could
 * be useful for some of your sorts.
 *
 * Class containing all the sorting algorithms from 61B to date.
 *
 * You may add any number instance variables and instance methods
 * to your Sorting Algorithm classes.
 *
 * You may also override the empty no-argument constructor, but please
 * only use the no-argument constructor for each of the Sorting
 * Algorithms, as that is what will be used for testing.
 *
 * Feel free to use any resources out there to write each sort,
 * including existing implementations on the web or from DSIJ.
 *
 * All implementations except Counting Sort adopted from Algorithms,
 * a textbook by Kevin Wayne and Bob Sedgewick. Their code does not
 * obey our style conventions.
 */
public class MySortingAlgorithms {

    /**
     * Java's Sorting Algorithm. Java uses Quicksort for ints.
     */
    public static class JavaSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            Arrays.sort(array, 0, k);
        }

        @Override
        public String toString() {
            return "Built-In Sort (uses quicksort for ints)";
        }
    }

    /** Insertion sorts the provided data. */
    public static class InsertionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            this.sort(array, 0, k);
        }

        public void sort(int[] array, int start, int end) {
            end = Math.min(end, array.length);
            start = Math.max(start, 0);
            for (int i = start; i < end; i++) {
                for (int j = i; j > start; j--) {
                    if (array[j] < array[j - 1]) {
                        swap(array, j - 1, j);
                    } else {
                        break;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "Insertion Sort";
        }
    }

    /**
     * Selection Sort for small K should be more efficient
     * than for larger K. You do not need to use a heap,
     * though if you want an extra challenge, feel free to
     * implement a heap based selection sort (i.e. heapsort).
     */
    public static class SelectionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            int N = Math.min(k, array.length);
            for (int i = 0; i < N; i++) {
                int min = i;
                for (int j = i + 1; j < N; j++) {
                    if (array[j] < array[min]) {
                        min = j;
                    }
                }
                swap(array, i, min);
            }
        }

        @Override
        public String toString() {
            return "Selection Sort";
        }
    }

    /** Your mergesort implementation. An iterative merge
      * method is easier to write than a recursive merge method.
      * Note: I'm only talking about the merge operation here,
      * not the entire algorithm, which is easier to do recursively.
      */
    public static class MergeSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            int[] arr = new int[array.length];
            int top = Math.min(k - 1, array.length - 1);
            sort(array, arr, 0, top);
        }

        private static void merge(int[] a, int[] arr,
                                  int bot, int mid, int top) {
            for (int k = bot; k <= top; k++) {
                arr[k] = a[k];
            }
            int i = bot, j = mid + 1;
            for (int k = bot; k <= top; k++) {
                if (i > mid) {
                    a[k] = arr[j++];
                } else if (j > top) {
                    a[k] = arr[i++];
                } else if (arr[j] < arr[i]) {
                    a[k] = arr[j++];
                } else {
                    a[k] = arr[i++];
                }
            }
        }
        private static void sort(int[] a, int[] arr, int bot, int top) {
            if (top <= bot) {
                return;
            }
            int mid = bot + (top - bot) / 2;
            sort(a, arr, bot, mid);
            sort(a, arr, mid + 1, top);
            merge(a, arr, bot, mid, top);
        }

        @Override
        public String toString() {
            return "Merge Sort";
        }
    }

    /**
     * Your Counting Sort implementation.
     * You should create a count array that is the
     * same size as the value of the max digit in the array.
     */
    public static class CountingSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            int maxVal = Integer.MIN_VALUE;
            int N = Math.min(k, array.length);

            for (int i = 0; i < N; i++) {
                if (array[i] > maxVal) {
                    maxVal = array[i];
                }
            }
            int[] counts = new int[maxVal++];

            for (int i = 0; i < N; i++) {
                counts[array[i]]++;
            }
            int[] starts = new int[maxVal++];
            for (int i = 1; i <= maxVal; i++) {
                starts[i] = starts[i - 1] + counts[i - 1];
            }
            int[] sorted = new int[N];
            for (int i = 0; i < N; i++) {
                sorted[starts[array[i]]] = array[i];
                starts[array[i]]++;
            }
            for (int i = 0; i < N; i++) {
                array[i] = sorted[i];
            }
        }

        @Override
        public String toString() {
            return "Counting Sort";
        }
    }

    /** Your Heapsort implementation.
     */
    public static class HeapSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            int N = Math.min(k, array.length);
            for (int m = N / 2; m >= 1; m--) {
                sink(array, m, N);
            }
            while (N > 1) {
                exchange(array, 1, N);
                N--;
                sink(array, 1, N);
            }
        }
        private static void sink(int[] arr, int m, int N) {
            while (2 * m <= N) {
                int i = 2 * m;
                if (i < N && small(arr, i, i++)) {
                    i++;
                }
                if (!small(arr, m, i)) {
                    break;
                }
                exchange(arr, m, i);
                m = i;

            }
        }
        private static boolean small(int[] arr, int i, int j) {
            return arr[i - 1] < arr[j - 1];
        }
        private static void exchange(int[] arr, int i, int j) {
            int temp = arr[i - 1];
            arr[i - 1] = arr[j - 1];
            arr[j - 1] = temp;
        }

        @Override
        public String toString() {
            return "Heap Sort";
        }
    }

    /** Your Quicksort implementation.
     */
    public static class QuickSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            int N = Math.min(k - 1, array.length - 1);
            quicksort(array, 0, N);
        }
        private static void quicksort(int[] a, int bot, int top) {
            if (top <= bot) {
                return;
            }
            int j = partition(a, bot, top);
            quicksort(a, bot, j - 1);
            quicksort(a, j + 1, top);
        }

        /** Returns array copy of AL. */
        private static int[] convertListToArray(List<Integer> al) {
            int[] returnArray = new int[al.size()];
            for (int i = 0; i < al.size(); i += 1) {
                returnArray[i] = al.get(i);
            }
            return returnArray;
        }
        private static int partition(int[] a, int bot, int top) {
            /** This method partitions by:
             * 1. Creating three ArrayList of the smaller, equal
             * and larger items, respectively.
             * 2. Concatenting these ArrayLists into a single list.
             * 3. Converting this ArrayList into an array.
             * 4. Copying the elements back into a.
             */

            List<Integer> smaller = new ArrayList<Integer>();
            List<Integer> equal = new ArrayList<Integer>();
            List<Integer> larger = new ArrayList<Integer>();

            int pivot = a[bot];
            for (int i = bot; i <= top; i += 1) {
                if (a[i] < pivot) {
                    smaller.add(a[i]);
                } else if (a[i] > pivot) {
                    larger.add(a[i]);
                } else {
                    equal.add(a[i]);
                }
            }

            List<Integer> partitioned = new ArrayList<Integer>();

            partitioned.addAll(smaller);
            partitioned.addAll(equal);
            partitioned.addAll(larger);

            int[] partitionedArray = convertListToArray(partitioned);
            System.arraycopy(partitionedArray, 0, a, bot,
                    partitionedArray.length);
            return smaller.size() + bot;
        }

        @Override
        public String toString() {
            return "Quicksort";
        }
    }

    /* For radix sorts, treat the integers as strings of x-bit numbers.  For
     * example, if you take x to be 2, then the least significant digit of
     * 25 (= 11001 in binary) would be 1 (01), the next least would be 2 (10)
     * and the third least would be 1.  The rest would be 0.  You can even take
     * x to be 1 and sort one bit at a time.  It might be interesting to see
     * how the times compare for various values of x. */

    /**
     * LSD Sort implementation.
     */
    public static class LSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {
            int bits = 32;
            int bitsPerByte = 8;
            int W = bits / bitsPerByte;
            int R = 1 << bitsPerByte;
            int mask = R - 1;

            int N = Math.min(a.length, k);
            int[] arr = new int[N];

            for (int d = 0; d < W; d++) {
                /** compute frequency counts. */
                int[] count = new int[R + 1];
                for (int i = 0; i < N; i++) {
                    int c = (a[i] >> bitsPerByte * d) & mask;
                    count[c + 1]++;
                }
                /** compute cumulates. */
                for (int r = 0; r < R; r++) {
                    count[r + 1] += count[r];
                }
                if (d == W - 1) {
                    int shift1 = count[R] - count[R / 2];
                    int shift2 = count[R / 2];
                    for (int r = 0; r < R / 2; r++) {
                        count[r] += shift1;
                    }
                    for (int r = R / 2; r < R; r++) {
                        count[r] -= shift2;
                    }
                }
                /** move data. */
                for (int i = 0; i < N; i++) {
                    int c = (a[i] >> bitsPerByte * d) & mask;
                    arr[count[c]++] = a[i];
                }
                /** copy back.*/
                for (int i = 0; i < N; i++) {
                    a[i] = arr[i];
                }
            }
        }


        @Override
        public String toString() {
            return "LSD Sort";
        }
    }

    /**
     * MSD Sort implementation.
     */
    public static class MSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {
            int N = Math.min(a.length, k);
            int[] aux = new int[N];
            sort(a, 0, N - 1, 0, aux);
        }

        public void sort(int[] a, int bot, int top, int d, int[] arr) {
            int bitsPerInt = 32;
            int bitsPerByte = 8;
            int R = 1 << bitsPerByte;
            int mask2 = R - 1;

            int[] count = new int[R + 1];
            int mask = R - 1;
            int shift = bitsPerInt - bitsPerByte * d - bitsPerByte;
            for (int i = bot; i <= top; i++) {
                int c = (a[i] >> shift) & mask;
                count[c + 1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            for (int i = bot; i <= top; i++) {
                int c = (a[i] >> shift) & mask;
                arr[count[c]++] = a[i];
            }

            for (int i = bot; i <= top; i++) {
                a[i] = arr[i - bot];
            }

            if (d == 4) {
                return;
            }

            if (count[0] > 0) {
                sort(a, bot, bot + count[0] - 1, d + 1, arr);
            }
            for (int r = 0; r < R; r++) {
                if (count[r + 1] > count[r]) {
                    sort(a, bot + count[r],
                            bot + count[r + 1] - 1, d + 1, arr);
                }
            }
        }

        @Override
        public String toString() {
            return "MSD Sort";
        }
    }

    /** Exchange A[I] and A[J]. */
    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
