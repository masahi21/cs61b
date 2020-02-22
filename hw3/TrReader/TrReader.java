import java.io.Reader;
import java.io.IOException;

/** Translating Reader: a stream that is a translation of an
 *  existing reader.
 *  @author Matthew Sahim
 */
public class TrReader extends Reader {
    /** A new TrReader that produces the stream of characters produced
     *  by STR, converting all characters that occur in FROM to the
     *  corresponding characters in TO.  That is, change occurrences of
     *  FROM.charAt(i) to TO.charAt(i), for all i, leaving other characters
     *  in STR unchanged.  FROM and TO must have the same length. */
    Reader str;
    String from;
    String to;

    public TrReader(Reader str, String from, String to) {
        // TODO: YOUR CODE HERE
        this.str = str;
        this.from = from;
        this.to = to;
    }
    public int read(char[] chars, int curr, int total) throws IOException{
        int count = curr;
        int amount = 0;
        for (int i = 0; i < total; i++) {
            int num = str.read();
            if (num == -1) {
                return -1;
            } else {
                char currChar = (char) num;
                if (from.indexOf(currChar) == -1) {
                    currChar = to.charAt(from.indexOf(currChar));
                }
                chars[count] = currChar;
                amount ++;
                count ++;
            }
        }
        return amount;
    }

    @Override
    public void close() {

    }

    /* TODO: IMPLEMENT ANY MISSING ABSTRACT METHODS HERE
     * NOTE: Until you fill in the necessary methods, the compiler will
     *       reject this file, saying that you must declare TrReader
     *       abstract. Don't do that; define the right methods instead!
     */
}
