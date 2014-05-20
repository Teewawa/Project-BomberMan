package system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * This <code>Boss</code> class represents the Boss enemy in Java. It extends
 * the <tt>Enemy</tt> class. This method creates a boss that can drop bombs and
 * has three lives.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class Boss extends Ghost {

	private final ActionListener listener;
	private final Timer timer;
	private int bombPlaceDelay;

	/**
	 * This method creates a boss monster and initializes its position at (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Boss(int x, int y) {

		super(x, y);
		setImage(loadImage(getClass().getResource("/images/boss.png")));

		if (GameEngine.gs.getDifficulty() == Difficulty.EASY) {
			bombPlaceDelay = 2500;
		} else if (GameEngine.gs.getDifficulty() == Difficulty.MEDIUM) {
			bombPlaceDelay = 2000;
		} else {
			bombPlaceDelay = 1500;
		}

		this.listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Bomb bossBomb = new Bomb(getxPos(), getyPos());
				bossBomb.setBossBomb(true);
				GameEngine.gs.addBomb(bossBomb);
			}
		};

		this.timer = new Timer(bombPlaceDelay, listener);
		this.timer.start();
	}

	/**
	 * This method kills the boss monster.
	 */
	@Override
	public void die() {
		this.setDead(true);
		timer.stop();
	}

}
