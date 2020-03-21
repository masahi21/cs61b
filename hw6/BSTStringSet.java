import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Implementation of a BST based String Set.
 * @author Matthew Sahim
 */
public class BSTStringSet implements SortedStringSet, Iterable<String> {
    /** Creates a new empty set. */
    public BSTStringSet() {
        _root = null;
    }

    @Override
    public void put(String s) {
        // FIXME: PART A
        _root = put(s, _root);
    }

    /** Helper method for put. Returns a BST rooted in P,
     *  but with S added to this BST.
     */
    private Node put(String s, Node p) {
        if (p == null) {
            return new Node(s);
        }
        int cmp = s.compareTo(p.s);
        if (cmp < 0) {
            p.left = put(s, p.left);
        }
        if (cmp > 0) {
            p.right = put(s, p.right);
        }
        return p;
    }

    @Override
    public boolean contains(String s) {
        // FIXME: PART A
        return contains(s, _root);
    }

    /** Returns true if String S is in the subset rooted in P. */
    private boolean contains(String s, Node p) {
        if (p == null) {
            return false;
        }
        int cmp = s.compareTo(p.s);
        if (cmp < 0) {
            return contains(s, p.left);
        }
        if (cmp > 0) {
            return contains(s, p.right);
        }

        return true;
    }

    @Override
    public List<String> asList() {
        // FIXME: PART A
        ArrayList<String> result = new ArrayList<>();
        for (String label : this) {
            result.add(label);
        }
        return result;
    }


    /** Represents a single Node of the tree. */
    private static class Node {
        /** String stored in this Node. */
        private String s;
        /** Left child of this Node. */
        private Node left;
        /** Right child of this Node. */
        private Node right;

        /** Creates a Node containing SP. */
        Node(String sp) {
            s = sp;
        }
    }

    /** An iterator over BSTs. */
    private static class BSTIterator implements Iterator<String> {
        /** Stack of nodes to be delivered.  The values to be delivered
         *  are (a) the label of the top of the stack, then (b)
         *  the labels of the right child of the top of the stack inorder,
         *  then (c) the nodes in the rest of the stack (i.e., the result
         *  of recursively applying this rule to the result of popping
         *  the stack. */
        private Stack<Node> _toDo = new Stack<>();

        /** A new iterator over the labels in NODE. */
        BSTIterator(Node node) {
            addTree(node);
        }

        @Override
        public boolean hasNext() {
            return !_toDo.empty();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node node = _toDo.pop();
            addTree(node.right);
            return node.s;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /** Add the relevant subtrees of the tree rooted at NODE. */
        private void addTree(Node node) {
            while (node != null) {
                _toDo.push(node);
                node = node.left;
            }
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new BSTIterator(_root);
    }

    // FIXME: UNCOMMENT THE NEXT LINE FOR PART B
    @Override
    public Iterator<String> iterator(String low, String high) {
        // FIXME: PART B
        return new BSTRangeIterator (_root, low, high);
    }

    private static class BSTRangeIterator implements Iterator<String> {
        /** stack of nodes that iterates through NODE from LOW to HIGH
         * in increasing order.*/
        private Stack<Node> _toDo = new Stack<>();

        BSTRangeIterator (Node node, String low, String high) {
            _low = low;
            _high = high;
            addTree(node);
        }

        @Override
        public boolean hasNext() {
            return !_toDo.empty();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = _toDo.pop();
            return node.s;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /** Add the relevant subtrees of the tree rooted at NODE. */
        private void addTree(Node node) {
            if (node != null) {
                String parent = node.s;
                if (parent.compareTo(_low) < 0) {
                    addTree(node.right);
                } else if (parent.compareTo(_high) > 0) {
                    addTree(node.left);
                } else if (parent.compareTo(_low) >= 0 && parent.compareTo(_high) < 0) {
                    addTree(node.right);
                    _toDo.push(node);
                    addTree(node.left);
                }
            }
        }

        private String _low, _high;
    }

    /** Root node of the tree. */
    private Node _root;
}
