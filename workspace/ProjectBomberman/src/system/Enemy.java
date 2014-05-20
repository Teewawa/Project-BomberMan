package system;

/**
 * The <code>Enemy</code> class represents the enemies in the Bomberman Game. It
 * extends class <tt>LivingUnit</tt>. This class has two methods: one that
 * generates the enemies and one that destroys them.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class Enemy extends LivingUnit {
	/**
	 * creates an enemy at location (x,y) and loads an image at that location
	 * 
	 * @param x
	 *            the x co-ordinate of the enemy
	 * @param y
	 *            the y co-ordinate of the enemy
	 */
	public Enemy(int x, int y) {
		super(x, y);
		setImage(loadImage(getClass().getResource("/images/enemy.png")));
	}

	/**
	 * destroys the enemy
	 */
	@Override
	public void die() {
		this.setDead(true);
	}

}
