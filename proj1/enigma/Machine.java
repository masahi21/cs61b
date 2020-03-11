package enigma;

import java.util.HashMap;
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
        _allRotors = allRotors;
        _rotorArr = new Rotor[numRotors];
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {
        return _numRotors;
    }

    /** Return the number pawls (and thus rotating rotors) I have. */
    int numPawls() {
        return _pawls;
    }

    /** Return the array of rotors I have. */
    Rotor[] rotorArr() {
        return rotorArr;
    }

    /** Set my rotor slots to the rotors named ROTORS from my set of
     *  available rotors (ROTORS[0] names the reflector).
     *  Initially, all rotors are set at their 0 setting. */
    void insertRotors(String[] rotors) {
        rotorArr = new Rotor[numRotors()];
        HashMap<String, Rotor> rotorsMap = new HashMap<String, Rotor>();
        for (Rotor theRotor : _allRotors) {
            rotorsMap.put(theRotor.name().toUpperCase(), theRotor);
        }
        for (int i = 0; i < rotors.length; i += 1) {
            String searchKey = rotors[i];
            if (rotorsMap.containsKey(searchKey)) {
                rotorArr[i] = rotorsMap.get(searchKey);
            }
        }

    }

    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {
        if (setting.length() != _numRotors - 1) {
            throw error("Incorrect length.");
        }
        for (int i = 1; i < rotorArr.length; i++) {
            rotorArr[i].set(setting.charAt(i - 1));
        }
    }

    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        _plugboard = plugboard;
    }

    /** Returns the result of converting the input character C (as an
     *  index in the range 0..alphabet size - 1), after first advancing

     *  the machine. */
    int convert(int c) {
        boolean[] advance = new boolean[_rotorArr.length - 1];
        _rotorArr[_rotorArr.length - 1].advance();
        advance[_rotorArr.length - 2] = true;
        for (int i = _rotorArr.length - 1; i > 0; i--) {
            if ((advance[i - 1]) && (_rotorArr[i - 1].rotates())
                && ((_rotorArr[i].atNotch()) || (_rotorArr[i - 1].atNotch()))) {
                _rotorArr[i - 1].advance();
                advance[i - 2] = true;
            }
        }
        int convertOut = _plugboard.permute(c);
        for (int j = _rotorArr.length - 1; j > 0; j--) {
            convertOut = _rotorArr[j].convertForward(convertOut);
        }
        for (int k = 1; k < _rotorArr.length; k += 1) {
            convertOut = _rotorArr[k].convertBackward(convertOut);
        }
        convertOut = _plugboard.permute(convertOut);
        return convertOut;
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String result = "";
        for (int i = 0; i < msg.length(); i++) {
            char converted = _alphabet.toChar(convert(_alphabet.toInt(msg.charAt(i))));
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
    //private Object[] _allRotors;
    private Rotor[] rotorArr;
    private Collection<Rotor> _allRotors;
}
