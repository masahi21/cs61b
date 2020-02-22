/**
 * TableFilter to filter for containing substrings.
 *
 * @author Matthew Owen
 */
public class SubstringFilter extends TableFilter {

    public SubstringFilter(Table input, String colName, String subStr) {
        super(input);
        // FIXME: Add your code here.
        this.colName = colName;
        this.subStr = subStr;
    }

    @Override
    protected boolean keep() {
        // FIXME: Replace this line with your code.
        return colName.contains(subStr);
    }

    // FIXME: Add instance variables?
    String colName;
    String subStr;
}
