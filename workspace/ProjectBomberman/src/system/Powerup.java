package system;

import java.util.Random;

/**
 * The class <code>Powerup</code> represents the types of powerups available in
 * the game. It extends the class <tt>NonLivingUnit</tt>. It contains three
 * methods which sets the location of the powerup and loads its respective
 * image.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class Powerup extends NonLivingUnit {
	Random r = new Random();
	private PowerupType type;

	/**
	 * This enum represents the two types of powerups.
	 * 
	 */
	enum PowerupType {
		BLAST, BOMB;
	}

	/**
	 * This method loads the image of the powerup at its location. It takes as
	 * input the x and y co-ordinate of the powerup.
	 * 
	 * @param x
	 *            The x co-ordinate of the powerup.
	 * @param y
	 *            The y co-ordinate of the powerup.
	 */
	public Powerup(int x, int y) {
		super(x, y);
		if (r.nextInt(100) < 50) {
			setType(PowerupType.BLAST);
			setImage(loadImage(getClass().getResource(
					"/images/blastpowerup.png")));
		} else {
			setType(PowerupType.BOMB);
			setImage(loadImage(getClass()
					.getResource("/images/bombpowerup.png")));
		}
	}

	/**
	 * This method gets the type of the power up.
	 * 
	 * @return Returns the type of the power up.
	 */
	public PowerupType getType() {
		return type;
	}

	/**
	 * This method sets the type of the power up. It takes as input the type of
	 * the power up.
	 * 
	 * @param type
	 *            This takes as input the type of the power up.
	 */
	public void setType(PowerupType type) {
		this.type = type;
	}
}
