/** Functions to increment and sum the elements of a WeirdList. */
class WeirdListClient {

    /** Return the result of adding N to each element of L. */
    static WeirdList add(WeirdList L, int n) {
        Add result = new Add(n);
        return L.map(result);
    }

    /** Return the sum of all the elements in L. */
    static int sum(WeirdList L) {
        Sum s = new Sum();
        L.map(s);
        return s.total;
    }

    /* IMPORTANT: YOU ARE NOT ALLOWED TO USE RECURSION IN ADD AND SUM
     *
     * As with WeirdList, you'll need to add an additional class or
     * perhaps more for WeirdListClient to work. Again, you may put
     * those classes either inside WeirdListClient as private static
     * classes, or in their own separate files.

     * You are still forbidden to use any of the following:
     *       if, switch, while, for, do, try, or the ?: operator.
     *
     * HINT: Try checking out the IntUnaryFunction interface.
     *       Can we use it somehow?
     */
    private static class Add implements IntUnaryFunction {
        public int plus;
        public Add(int num) {
            plus = num;
        }
        public int apply(int head) {
            head += plus;
            return head;
        }
    }

    private static class Sum implements IntUnaryFunction {
        public int total;
        public int apply(int x) {
            total += x;
            return x;
        }
    }
}
