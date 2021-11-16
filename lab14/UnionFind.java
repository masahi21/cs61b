
/** Disjoint sets of contiguous integers that allows (a) finding whether
 *  two integers are in the same set and (b) unioning two sets together.  
 *  At any given time, for a structure partitioning the integers 1 to N, 
 *  into sets, each set is represented by a unique member of that
 *  set, called its representative.
 *  @author Matthew Sahim
 */
public class UnionFind {

    /** A union-find structure consisting of the sets { 1 }, { 2 }, ... { N }.
     */
    public UnionFind(int N) {
        _set = new int[N];
        for (int i = 0; i < N; i++) {
            _set[i] = i + 1;
        }
    }

    /** Return the representative of the set currently containing V.
     *  Assumes V is contained in one of the sets.  */
    public int find(int v) {
        if (_set[v - 1] == v) {
            return v;
        }
        _set[v - 1] = find(_set[v - 1]);
        return _set[v - 1];
    }

    /** Return true iff U and V are in the same set. */
    public boolean samePartition(int u, int v) {
        return find(u) == find(v);
    }

    /** Union U and V into a single set, returning its representative. */
    public int union(int u, int v) {
        int parent = find(u);
        _set[find(v) - 1] = parent;
        return parent;
    }

    /** The array for parent of unionFind. */
    private int[] _set;
}
