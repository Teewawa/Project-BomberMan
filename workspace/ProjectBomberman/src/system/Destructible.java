package system;

/**
 * This is the <code>Destructible</code> interface class that has the method
 * die. The user of this interface calls the die method which indicates that
 * something in the game should be destroyed.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 */
public interface Destructible {
	/**
	 * Destroys an object in the game
	 */
	void die();

}
