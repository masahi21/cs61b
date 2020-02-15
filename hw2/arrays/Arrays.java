package arrays;

/* NOTE: The file Arrays/Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */

/** Array utilities.
 *  @author Matthew Sahim
 */
class Arrays {

    /* C1. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        int[] C = new int[A.length + B.length];
        int i = 0;
        for (; i < A.length; i += 1) {
            C[i] = A[i];
        }
        for (int j = 0; j < B.length; j += 1, i += 1) {
            C[i] = B[j];
        }
        return C;
    }

    /* C2. */
    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. */
    static int[] remove(int[] A, int start, int len) {
        if (len <= 0 || A.length == 0) {
            return A;
        }
        int numLength = 0;
        if (start + len > A.length) {
            numLength = A.length - start;
        } else {
            numLength = len;
        }
        int[] B = new int[A.length - numLength];
        int j = 0;
        for ( ; j < start; j += 1) {
            B[j] = A[j];
        }
        for (int i = start + len; i < A.length; i += 1, j += 1) {
            B[j] = A[i];
        }
        return B;


    }

    /* C3. */
    /** Returns the array of arrays formed by breaking up A into
     *  maximal ascending lists, without reordering.
     *  For example, if A is {1, 3, 7, 5, 4, 6, 9, 10}, then
     *  returns the three-element array
     *  {{1, 3, 7}, {5}, {4, 6, 9, 10}}. */
    static int[][] naturalRuns(int[] A) {
        int numLists = 0;
        int j = A[0];
        for (int i = 0; i < A.length; i += 1) {
            if (j > A[i]) {
                numLists += 1;
            }
        }
        int[][] araAra = new int[numLists][];
        int[] ind = new int[numLists];
        int[] lens = new int[numLists + 1];
        int k = 0;
        for (int i = 0; i < numLists; i += 1) {
            int h = A[k];
            for ( ; k < A.length; k += 1) {
                if (h > A[j]) {
                    ind[i] = h;
                }
            }
        }
        return araAra;
    }
}
