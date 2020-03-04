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
        // FIXME
        _numRotors = numRotors;
        _pawls = pawls;
        _allRotors = allRotors;
    }

    /** Return the number of rotor slots I have. */
    int numRotors() {
        // FIXME
        return _numRotors;
    }

    /** Return the number pawls (and thus rotating rotors) I have. */
    int numPawls() {
        // FIXME
        return _pawls;
    }

    /** Set my rotor slots to the rotors named ROTORS from my set of
     *  available rotors (ROTORS[0] names the reflector).
     *  Initially, all rotors are set at their 0 setting. */
    void insertRotors(String[] rotors) {
        // FIXME
        rotorArr = new Rotor[numRotors()];
        HashMap<String, Rotor> rotorMap = new HashMap<String, Rotor>();
        for (Rotor currRotor : _allRotors) {
            rotorMap.put(currRotor.name().toUpperCase(), currRotor);
        }
        for (int i = 0; i < rotors.length; i++) {
            String rotorKey = rotors[i];
            if (rotorMap.containsKey(rotorKey)) {
                rotorArr[i] = rotorMap.get(rotorKey);
            }
        }
    }

    /** Set my rotors according to SETTING, which must be a string of
     *  numRotors()-1 characters in my alphabet. The first letter refers
     *  to the leftmost rotor setting (not counting the reflector).  */
    void setRotors(String setting) {
        // FIXME
        for (int i = 1; i < rotorArr.length; i++) {
            rotorArr[i].set(setting.charAt(i - 1));
        }
    }

    /** Set the plugboard to PLUGBOARD. */
    void setPlugboard(Permutation plugboard) {
        // FIXME
        _plugboard = plugboard;
    }

    /** Returns the result of converting the input character C (as an
     *  index in the range 0..alphabet size - 1), after first advancing

     *  the machine. */
    int convert(int c) {
        // FIXME
        boolean[] advance = new boolean[rotorArr.length - 1];
        rotorArr[rotorArr.length - 1].advance();
        advance[rotorArr.length - 2] = true;
        for (int i = rotorArr.length - 1; i > 0; i--) {
            if ((advance[i - 1]) && (rotorArr[i - 1].rotates())
                && ((rotorArr[i].atNotch()) || (rotorArr[i - 1].atNotch()))) {
                rotorArr[i - 1].advance();
                advance[i - 2] = true;
            }
        }
        int convertOut = _plugboard.permute(c);
        for (int j = rotorArr.length - 1; j > 0; j--) {
            convertOut = rotorArr[j].convertForward(convertOut);
        }
        for (int k = 1; k < rotorArr.length; k += 1) {
            convertOut = rotorArr[k].convertBackward(convertOut);
        }
        convertOut = _plugboard.permute(convertOut);
        return convertOut;
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        // FIXME
        msg = msg.replaceAll(" ", "");
        String[] msgArr = msg.split("");
        int[] intArr = new int[msgArr.length];
        for (int i = 0; i < msgArr.length; i++) {
            intArr[i] = _alphabet.toInt(msgArr[i].charAt(0));
        }
        String[] msgArrOut = new String[intArr.length];
        for (int j = 0; j < msgArr.length; j++) {
            msgArrOut[j] = Character.toString(_alphabet.toChar(convert(intArr[j])));
        }
        String converted = "";
        for (int k = 0; k < msgArrOut.length; k++) {
            converted += msgArrOut[k];
        }
        return converted;
    }

    /** Common alphabet of my rotors. */
    private final Alphabet _alphabet;

    // FIXME: ADDITIONAL FIELDS HERE, IF NEEDED.
    private final int _numRotors;
    private final int _pawls;
    private Permutation _plugboard;
    private Collection<Rotor> _allRotors;
    private Rotor[] rotorArr;
}
