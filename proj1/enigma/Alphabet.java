package enigma;

import static enigma.EnigmaException.*;
/** An alphabet of encodable characters.  Provides a mapping from characters
 *  to and from indices into the alphabet.
 *  @author Matthew Sahim
 */
class Alphabet {

    /** A new alphabet containing CHARS.  Character number #k has index
     *  K (numbering from 0). No character may be duplicated. */
    Alphabet(String chars) {
        // FIXME
        _chars = chars;
    }

    /** A default alphabet of all upper-case characters. */
    Alphabet() {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    /** Returns the size of the alphabet. */
    int size() {
        // FIXME
        return _chars.length();
    }

    /** Returns true if CH is in this alphabet. */
    boolean contains(char ch) {
        // FIXME
        return _chars.contains(String.valueOf(ch));
    }

    /** Returns character number INDEX in the alphabet, where
     *  0 <= INDEX < size(). */
    char toChar(int index) {
        // FIXME
        if (index < 0 || index >= size()) {
            throw error("character index out of range");
        }
        return _chars.charAt(index);
    }

    /** Returns the index of character CH which must be in
     *  the alphabet. This is the inverse of toChar(). */
    int toInt(char ch) {
        // FIXME
        for (int i = 0; i < size(); i++) {
            if (_chars.charAt(i) == ch) {
                return i;
            }
        }
        throw error("character not in alphabet");
    }
    private String _chars;
}
