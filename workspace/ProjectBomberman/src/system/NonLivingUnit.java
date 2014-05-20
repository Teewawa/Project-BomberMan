package system;

/**
 * The class <code>NonLivingUnit</code> represents the location of a non-living
 * object in the Bomberman game. It extends the class <tt>MapObject</tt>.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * 
 */
public abstract class NonLivingUnit extends MapObject {
	/**
	 * This method represents the location of the NonLivingUnit and takes in the
	 * x and y co-ordinates of the unit.
	 * 
	 * @param x
	 *            The x co-ordinate of the unit.
	 * @param y
	 *            The y co-ordinate of the unit.
	 */
	public NonLivingUnit(int x, int y) {
		super(x, y);
	}

}
