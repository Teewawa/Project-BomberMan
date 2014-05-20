package system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * The <code>Bomb</code> class represents the Bomb object in the game and
 * detonates after a specific period of time in the game. It extends class
 * <tt>NonLivingUnit</tt> and implements the interface class
 * <tt>Destructible</tt>. The class contains two methods, one that initializes
 * the bomb and one that detonates the bomb.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 */
public class Bomb extends NonLivingUnit implements Destructible {

	private final ActionListener listener;
	private final Timer timer;
	private final int lifetime;
	private boolean isBossBomb;

	/**
	 * creates a bomb at location (x,y) with a 2 second lifespan
	 * 
	 * @param x
	 *            the x co-ordinate of the bomb
	 * @param y
	 *            the y co-ordinate of the bomb
	 */
	public Bomb(int x, int y) {
		super(x, y);
		setImage(loadImage(getClass().getResource("/images/bomb.png")));
		lifetime = 2000;

		this.listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				die();
			}
		};

		this.timer = new Timer(lifetime, listener); // 2.5 seconds before death
		this.timer.start();

	}

	/**
	 * Kills the bomb and stops the timer
	 */

	@Override
	public void die() {
		this.setDead(true);
		this.timer.stop();
	}

	/**
	 * This method checks if the bomb is a boss bomb
	 */
	public boolean isBossBomb() {
		return isBossBomb;
	}

	/**
	 * This method checks if the bomb is a boss bomb or a player bomb
	 * 
	 * @param isBossBomb
	 *            intakes the characteristic of the bomb
	 */
	public void setBossBomb(boolean isBossBomb) {
		this.isBossBomb = isBossBomb;
	}
}
