package enigma;

/** Class that represents a rotating rotor in the enigma machine.
 *  @author Matthew Sahim
 */
class MovingRotor extends Rotor {

    /** A rotor named NAME whose permutation in its default setting is
     *  PERM, and whose notches are at the positions indicated in NOTCHES.
     *  The Rotor is initally in its 0 setting (first character of its
     *  alphabet).
     */
    MovingRotor(String name, Permutation perm, String notches) {
        super(name, perm);
        _notches = notches;
    }

    @Override
    boolean rotates() {
        return true;
    }

    @Override
    boolean atNotch() {
        for (int i = 0; i < _notches.length(); i++) {
            if (_notches.charAt(i)
                    == this.permutation().alphabet().toChar(this.setting())) {
                return true;
            }
        }
        return false;
    }

    @Override
    void advance() {
        this.set(permutation().wrap(this.setting() + 1));
    }

    /** String containing notches of the Rotor. */
    private String _notches;

}
