package system;

/**
 * The class <code>Ghost</code> represents the ghost enemy in the Bomberman
 * Game. It extends class <tt>Enemy</tt>. It has one method which loads the
 * image at the position of the enemy in the game.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class Ghost extends Enemy {
	/**
	 * creates an ghost enemy at (x,y) and loads an image at that location
	 * 
	 * @param x
	 *            the x co-ordinate of the ghost
	 * @param y
	 *            the y co-ordinate of the ghost
	 */
	public Ghost(int x, int y) {
		super(x, y);
		setImage(loadImage(getClass().getResource("/images/ghost.png")));
	}

}
