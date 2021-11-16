/** HW0 max & 3sum
 * @author Matthew Sahim
 */

public class Hw0 {
    public static void main(String[] args) {

    }

    public static int max(int[] a) {
        int i = 0;
        int high = 0;
        while (i < a.length) {
            if (a[i] > high) {
                high = a[i];
                i += 1;
            }
        }
        return high;
    }

    public static boolean threeSum(int[] a) {
        for (int i = 0; i < a.length; i += 1) {
            for (int j = 0; j < a.length; j += 1) {
                for (int k = 0; k < a.length; k += 1) {
                    if (a[i] + a[j] + a[k] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean threeSumDisctint(int[] a) {
        for (int i = 0; i < a.length; i += 1) {
            for (int j = 0; j < a.length; j += 1) {
                for (int k = 0; k < a.length; k += 1) {
                    if (i != j && i != k && j != k) {
                        if (a[i] + a[j] + a[k] == 0) {
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }
}
