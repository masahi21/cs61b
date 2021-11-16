package enigma;

import java.util.Collection;

import static enigma.EnigmaException.*;

/** Class that represents a complete enigma machine.
 *  @author Matthew Sahim
 */
class Machine {

    /** A new Enigma machine with alphabet ALPHA, 1 < NUMROTORS rotor slots,
     *  and 0 <= PAWLS < NUMROTORS pawls.  ALLROTORS contains all the
     *  available rotors. */
    Machine(Alphabet alpha, int numRotors, int pawls,
            Collection<Rotor> allRotors) {
        _alphabet = alpha;
        _numRotors = numRotors;
        _pawls = pawls;
        _allRotors = allRotors.toArray();
        _rotorArr = new Rotor[numRotors];
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {
        return _numRotors;
    }

    /** Set my rotor slots to the rotors named ROTORS from my set of
     *  available rotors (ROTORS[0] names the reflector).
     *  Initially, all rotors are set at their 0 setting. */
    void insertRotors(String[] rotors) {
        for (int i = 0; i < rotors.length; i++) {
            for (Object currRotor : _allRotors) {
                if (rotors[i].equals((((Rotor) currRotor).name()))) {
                    _rotorArr[i] = (Rotor) currRotor;
                }
            }
        }
        if (_rotorArr.length != rotors.length) {
            throw error("Incorrectly named rotors.");
        }
    }

    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {
        int j = 0;
        for (int i = 1; i < _rotorArr.length; i++) {
            _rotorArr[i].set(setting.charAt(i - 1));
            j++;
            if (j >= setting.length()) {
                break;
            }
        }
    }

    /** returns init rotor of my rotor array. */
    public Rotor get0() {
        return _rotorArr[0];
    }

    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        _plugboard = plugboard;
    }

    /** Returns the result of converting the input character C (as an
     *  index in the range 0..alphabet size - 1), after first advancing

     *  the machine. */
    int convert(int c) {
        boolean rotor4 = false;
        boolean rotor3 = false;
        if (_rotorArr[4].atNotch()) {
            rotor4 = true;
        }
        if (_rotorArr[3].atNotch()) {
            rotor3 = true;
        }
        if (rotor4) {
            _rotorArr[3].advance();
        }
        if (rotor3) {
            _rotorArr[2].advance();
            _rotorArr[3].advance();
        }
        _rotorArr[_numRotors - 1].advance();

        int result = _plugboard.permute(c);
        for (int i = _numRotors - 1; i >= 0; i--) {
            result = _rotorArr[i].convertForward(result);
        }
        for (int i = 1; i < _numRotors; i++) {
            result = _rotorArr[i].convertBackward(result);
        }
        result = _plugboard.permute(result);
        return result;
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String result = "";
        for (int i = 0; i < msg.length(); i++) {
            char converted =
                    _alphabet.toChar(convert(_alphabet.toInt(msg.charAt(i))));
            result += converted;
        }
        return result;
    }

    /** Common alphabet of my rotors. */
    private final Alphabet _alphabet;

    /** Total number of rotors. */
    private final int _numRotors;

    /** Total number of pawls. */
    private final int _pawls;

    /** The array of rotors that formats the machine. */
    private Rotor[] _rotorArr;

    /** The initial plugboard which includes steckered pairs. */
    private Permutation _plugboard;

    /** An ArrayList containing all possible rotors that can be used. */
    private Object[] _allRotors;
}
