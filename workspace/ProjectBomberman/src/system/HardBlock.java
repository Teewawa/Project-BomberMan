package system;

/**
 * The class
 * <code>HardBlock/code> represents the Hard Block object in bomberman.
 * It extends the class <tt>NonLivingUnit</tt> and contains one method which creates
 * a hard block and loads an image to match its position.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class HardBlock extends NonLivingUnit {
	/**
	 * This method takes as input an x and y co-ordinate and places a hard block
	 * image at the location.
	 * 
	 * @param x
	 *            The x co-ordinate of the hard block
	 * @param y
	 *            The y co-ordinate of the hard block
	 */
	public HardBlock(int x, int y) {
		super(x, y);
		setImage(loadImage(getClass().getResource("/images/hardblock.png")));
	}
}
