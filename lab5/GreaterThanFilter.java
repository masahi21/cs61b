/**
 * TableFilter to filter for entries greater than a given string.
 *
 * @author Matthew Owen
 */
public class GreaterThanFilter extends TableFilter {

    public GreaterThanFilter(Table input, String colName, String ref) {
        super(input);
        // FIXME: Add your code here.
        this.colName = colName;
        this.ref = ref;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        return colNameToIndex(colName) > ref;

    }

    // FIXME: Add instance variables?
    String colName;
    String ref;
}
