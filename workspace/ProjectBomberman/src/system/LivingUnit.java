package system;

/**
 * The class <code>LivingUnit</code> represents all living units in the
 * bomberman game (enemy, player) it extends class <tt>MapObject</tt> and
 * implements the interface <tt>Moveable</tt>. It has five methods, one that
 * sets the position of the unit, and four that move the unit.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public abstract class LivingUnit extends MapObject implements Moveable,
		Destructible {

	private final int moveInc = 32;

	/**
	 * This method sets the location of the living unit.
	 * 
	 * @param x
	 *            This is the x co-ordinate of the living unit.
	 * @param y
	 *            This is the y co-ordinate of the living unit.
	 */
	public LivingUnit(int x, int y) {
		super(x, y);
	}

	/**
	 * This method moves the unit up.
	 */
	@Override
	public void moveUp() {
		setyPos(getyPos() - moveInc);
	}

	/**
	 * This method moves the unit down.
	 */
	@Override
	public void moveDown() {
		setyPos(getyPos() + moveInc);
	}

	/**
	 * This method moves the unit left.
	 */
	@Override
	public void moveLeft() {
		setxPos(getxPos() - moveInc);
	}

	/**
	 * This method moves the unit right.
	 */
	@Override
	public void moveRight() {
		setxPos(getxPos() + moveInc);
	}

}
