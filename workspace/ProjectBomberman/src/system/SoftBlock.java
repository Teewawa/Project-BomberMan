package system;

/**
 * The class <code>SoftBlock</code> reprents the soft block object in the
 * bomberman game. It extends the class <tt>NonLivingUnit</tt> and implements
 * the interface <tt>Destructible</tt>. It contains two methods which sets the
 * location of the soft block as well as kills the soft block.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class SoftBlock extends NonLivingUnit implements Destructible {
	/**
	 * This method takes as input the location of the softblock and loads the
	 * softblock image at the location.
	 * 
	 * @param x
	 *            The x co-ordinate of the softblock.
	 * @param y
	 *            The y co-ordinate of the softblock.
	 */
	public SoftBlock(int x, int y) {
		super(x, y);
		setImage(loadImage(getClass().getResource("/images/softblock.png")));
	}

	/**
	 * This method destroys the softblock by setting it to dead.
	 */
	@Override
	public void die() {
		this.setDead(true);
	}
}
