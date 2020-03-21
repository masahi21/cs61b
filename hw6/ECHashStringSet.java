import java.util.List;
import java.util.LinkedList;

/** A set of String values.
 *  @author
 */
class ECHashStringSet implements StringSet {
    private static double MIN_LOAD = 0.2;
    private static double MAX_LOAD = 5;

    public ECHashStringSet() {
        _size = 0;
        _store = new LinkedList[(int)(1/MIN_LOAD)];
    }

    @Override
    public void put(String s) {
        // FIXME
        if (s != null)
        {
            if(load() > MAX_LOAD)
                resize();

            int pos = hashToStore(s.hashCode());

            if(_store[pos] == null)
                _store[pos] = new LinkedList<String>();

            _store[pos].add(s);
            _size++;
        }
    }

    @Override
    public boolean contains(String s) {
        // FIXME
        if(s != null){
            int pos = hashToStore(s.hashCode());

            if(_store[pos] == null)
                return false;
            else
                return _store[pos].contains(s);
        }
        else
            return false;
    }
    public int size() {
        return _size;
    }

    private void resize(){
        LinkedList<String>[] old = _store;
        _store = new LinkedList[2*old.length];
        _size = 0;

        for(LinkedList<String> list : old)
            if(list != null)
                for(String e : list)
                    this.put(e);
    }

    private double load(){
        return ((double)_size)/((double)_store.length);
    }

    private int hashToStore(int hashCode){
        int lastBit = hashCode & 1;
        int  unsignedHash = (hashCode >>> 1) | lastBit;
        return unsignedHash % _store.length;
    }

    @Override
    public List<String> asList() {
        return null; // FIXME
    }

    private LinkedList<String>[] _store;
    private int _size;
}

