package enigma;

/** Class that represents a rotor that has no ratchet and does not advance.
 *  @author Matthew Sahim
 */
class FixedRotor extends Rotor {

    /** A non-moving rotor named NAME whose permutation at the 0 setting
     * is given by PERM. */
    FixedRotor(String name, Permutation perm) {
        super(name, perm);
    }

    /* Fixed rotors do not advance. */
    @Override
    void advance() {
    }
}
