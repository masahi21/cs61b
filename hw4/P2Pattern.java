/** P2Pattern class
 *  @author Josh Hug & Vivant Sakore
 */

public class P2Pattern {
    /** Pattern to match a valid date of the form MM/DD/YYYY. Eg: 9/22/2019 */
    public static String P1 =
            "^([0-9]|0[1-9]|1[0-2])\\/(\\d{1}|[0-2][0-9]|3[0-1])\\/\\d{4}$";
        //FIXME: Add your regex here

    /** Pattern to match 61b notation for literal IntLists. */
    public static String P2 =
            "\\((([0-9]\\d*)\\,(\\s*))*([0-9]\\d*)\\)";
        //FIXME: Add your regex here

    /** Pattern to match a valid domain name. Eg: www.support.facebook-login.com */
    public static String P3 =
            "^((?!-)[A-Za-z0-9-]+(?<!-)\\.)+[A-Za-z]{2,6}$";
        //FIXME: Add your regex here

    /** Pattern to match a valid java variable name. Eg: _child13$ */
    public static String P4 =
            "^[a-zA-Z$_][a-zA-Z0-9$_]*$";
        //FIXME: Add your regex here

    /** Pattern to match a valid IPv4 address. Eg: 127.0.0.1 */
    public static String P5 =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        //FIXME: Add your regex here

}
