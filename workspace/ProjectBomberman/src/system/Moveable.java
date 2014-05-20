package system;

/**
 * This interface <code>Moveable</code> contains the methods needed to move an
 * object in the Bomberman game.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public interface Moveable {
	/**
	 * This methods moves an object up.
	 */
	void moveUp();

	/**
	 * This method moves an object down.
	 */
	void moveDown();

	/**
	 * This method moves an object left.
	 */
	void moveLeft();

	/**
	 * This method moves an object right.
	 */
	void moveRight();

}
