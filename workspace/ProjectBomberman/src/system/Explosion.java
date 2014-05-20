package system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * The class <code>Explosion</code> represents the event that occurs when the
 * Bomb dies. It extends the class <tt> NonLivingUnit</tt> and implements the
 * interface <tt>Destructible</tt> The class contains two methods, one that
 * creates the explosion and one that ends the explosion.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * 
 */
public class Explosion extends NonLivingUnit implements Destructible {

	private final ActionListener listener;
	private final Timer timer;
	private final int lifetime;
	private boolean isBossExplosion;

	/**
	 * This method creates the explosion at location (x,y) and loads the
	 * appropriate image. It also sets the lifespan of the explosion as .5
	 * seconds.
	 * 
	 * @param x
	 *            the x co-ordinate of the explosion
	 * @param y
	 *            the y co-ordinate of the explosion
	 */
	public Explosion(int x, int y) {
		super(x, y);
		setImage(loadImage(getClass().getResource("/images/explosion.png")));
		lifetime = 500;

		this.listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				die();
			}
		};

		this.timer = new Timer(lifetime, listener); // one seconds before death
		this.timer.start();

	}

	/**
	 * This method ends the explosion.
	 */
	@Override
	public void die() {
		this.setDead(true);
		this.timer.stop();
	}

	/**
	 * This method checks if the explosion is a boss explosion
	 * 
	 * @return whether the explosion is a boss explosion
	 */
	public boolean isBossExplosion() {
		return isBossExplosion;
	}

	/**
	 * This method sets the explosion to a boss explosion
	 */
	public void setBossExplosion(boolean isBossExplosion) {
		this.isBossExplosion = isBossExplosion;
	}
}
