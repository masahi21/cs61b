/**
 * TableFilter to filter for entries whose two columns match.
 *
 * @author Matthew Owen
 */
public class ColumnMatchFilter extends TableFilter {

    public ColumnMatchFilter(Table input, String colName1, String colName2) {
        super(input);
        // FIXME: Add your code here.
        this.colName1 = colName1;
        this.colName2 = colName2;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        while (colName1.) {

        }
        return false;
    }

    // FIXME: Add instance variables?
    public String colName1;
    public String colName2;
}
