/**
 * Scheme-like pairs that can be used to form a list of integers.
 *
 * @author P. N. Hilfinger; updated by Vivant Sakore (1/29/2020)
 */
public class IntDList {

    /**
     * First and last nodes of list.
     */
    protected DNode _front, _back;

    /**
     * An empty list.
     */
    public IntDList() {
        _front = _back = null;
    }

    /**
     * @param values the ints to be placed in the IntDList.
     */
    public IntDList(Integer... values) {
        _front = _back = null;
        for (int val : values) {
            insertBack(val);
        }
    }

    /**
     * @return The first value in this list.
     * Throws a NullPointerException if the list is empty.
     */
    public int getFront() {
        return _front._val;
    }

    /**
     * @return The last value in this list.
     * Throws a NullPointerException if the list is empty.
     */
    public int getBack() {
        return _back._val;
    }

    /**
     * @return The number of elements in this list.
     */
    public int size() {
        int num = 0;
        DNode temp = _front;
        while (temp != null) {
            num += 1;
            temp = temp._next;
        }
        return num;
    }

    /**
     * @param i index of element to return,
     *          where i = 0 returns the first element,
     *          i = 1 returns the second element,
     *          i = -1 returns the last element,
     *          i = -2 returns the second to last element, and so on.
     *          You can assume i will always be a valid index, i.e 0 <= i < size for positive indices
     *          and -size <= i <= -1 for negative indices.
     * @return The integer value at index i
     */
    public int get(int i) {
        if (i == 0) {
            return _front._val;
        }
        else if (i > 0) {
            DNode index = _front;
            while (i > 0) {
                index = index._next;
                i -= 1;
            }
            return index._val;
        }
        else if (i < 0) {
            DNode index = _back;
            while (i < 0) {
                index = index._prev;
                i += 1;
            }
            return index._val;
        }
        return -1;
    }

    /**
     * @param d value to be inserted in the front
     */
    public void insertFront(int d) {
        DNode insertF = new DNode(null, d, _front._prev);
        if (_front == null && _back == null) {
            _front = insertF;
        }
        _front._prev = insertF._next;
        _front = insertF;
    }

    /**
     * @param d value to be inserted in the back
     */
    public void insertBack(int d) {
        DNode insertB = new DNode(_back._next, d, null);
        if (_back != null) {
            _back._next = insertB;
        }
        _back = insertB;
        if (_front == null) {
            _front = insertB;
        }
    }
    /**
     * @param d     value to be inserted
     * @param index index at which the value should be inserted
     *              where index = 0 inserts at the front,
     *              index = 1 inserts at the second position,
     *              index = -1 inserts at the back,
     *              index = -2 inserts at the second to last position, and so on.
     *              You can assume index will always be a valid index,
     *              i.e 0 <= index <= size for positive indices (including insertions at front and back)
     *              and -(size+1) <= index <= -1 for negative indices (including insertions at front and back).
     */
    public void insertAtIndex(int d, int index) {
        if (index == 0) {
            insertFront(d);
        }
        else if (index > 0) {
            DNode curr = _front;
            while (index > 0) {
                curr = curr._next;
                index -= 1;
            }
            DNode insert = new DNode(curr._prev, d, curr);
            curr._prev._next = insert;
            curr._prev = insert;

        }
        else if (index < 0) {
            if (index == -1) {
                insertBack(d);
            }
            DNode curr = _back;
            while (index < -1) {
                curr = curr._prev;
                index += 1;
            }
            DNode insert = new DNode(curr._prev, d, curr._next);
            curr._prev._next = insert;
            curr._prev = insert;
        }
    }

    /**
     * Removes the first item in the IntDList and returns it.
     *
     * @return the item that was deleted
     */
    public int deleteFront() {
        DNode deleted = _front;
        if (deleted == null) {
            return -1;
        }
        else {
            _front = deleted._next;
            if (_back == deleted) {
                _back = null;
            }
            return deleted._val;
        }
    }

    /**
     * Removes the last item in the IntDList and returns it.
     *
     * @return the item that was deleted
     */
    public int deleteBack() {
        DNode deleted = _back;
        if (deleted == null) {
            return -1;
        }
        else {
            _back = deleted._next;
            if (_front == deleted) {
                _front = null;
            }
            return deleted._val;
        }
    }

    /**
     * @param index index of element to be deleted,
     *          where index = 0 returns the first element,
     *          index = 1 will delete the second element,
     *          index = -1 will delete the last element,
     *          index = -2 will delete the second to last element, and so on.
     *          You can assume index will always be a valid index,
     *              i.e 0 <= index < size for positive indices (including deletions at front and back)
     *              and -size <= index <= -1 for negative indices (including deletions at front and back).
     * @return the item that was deleted
     */
    public int deleteAtIndex(int index) {
        if (index == 0) {
            deleteFront();
        }
        else if (index > 0) {
            DNode curr = _front;
            while (index > 0) {
                curr = curr._next;
                index -= 1;
            }
            DNode deleted = curr;
            curr = curr._next;
            return deleted._val;

        }
        else if (index < 0) {
            if (index == -1) {
                deleteBack();
            }
            DNode curr = _back;
            while (index < -1) {
                curr = curr._prev;
                index += 1;
            }
            DNode deleted = curr;
            curr = curr._next;
            return deleted._val;
        }
        return -1;
    }

    /**
     * @return a string representation of the IntDList in the form
     * [] (empty list) or [1, 2], etc.
     * Hint:
     * String a = "a";
     * a += "b";
     * System.out.println(a); //prints ab
     */
    public String toString() {
        if (size() == 0) {
            return "[]";
        }
        String str = "[";
        DNode curr = _front;
        str += curr._val;
        for (; curr._next != null; curr = curr._next) {
            str += curr._val + ", ";
        }
        str += curr._val +"]";
        return str;
    }

    /**
     * DNode is a "static nested class", because we're only using it inside
     * IntDList, so there's no need to put it outside (and "pollute the
     * namespace" with it. This is also referred to as encapsulation.
     * Look it up for more information!
     */
    static class DNode {
        /** Previous DNode. */
        protected DNode _prev;
        /** Next DNode. */
        protected DNode _next;
        /** Value contained in DNode. */
        protected int _val;

        /**
         * @param val the int to be placed in DNode.
         */
        protected DNode(int val) {
            this(null, val, null);
        }

        /**
         * @param prev previous DNode.
         * @param val  value to be stored in DNode.
         * @param next next DNode.
         */
        protected DNode(DNode prev, int val, DNode next) {
            _prev = prev;
            _val = val;
            _next = next;
        }
    }

}
