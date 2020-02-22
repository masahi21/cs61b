/**
 * TableFilter to filter for entries equal to a given string.
 *
 * @author Matthew Owen
 */
public class EqualityFilter extends TableFilter {

    public EqualityFilter(Table input, String colName, String match) {
        super(input);
        // FIXME: Add your code here.
        this.colName = colName;
        this.match = match;

    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        return colName.equals(match);
    }

    // FIXME: Add instance variables?
    String colName;
    String match;
}
