package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import system.GamePhysics;
import system.GameStatus;

/**
 * The <code>RightAction</code> class represents the code that is triggered when
 * the right key is pressed. It extends the <tt>AbstractAction</tt> class. It
 * corresponds the right key with various functions in <tt>GameStatus</tt>.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class RightAction extends AbstractAction {
	/**
     *
     */
	private static final long serialVersionUID = 1L;
	GameStatus gs;
	GamePhysics gp;
	JFrame mainFrame;

	/**
	 * This method initializes a game status class to be used by the other
	 * function.
	 * 
	 * @param gs
	 *            This is the game status class to be used by the other
	 *            functions.
	 */
	public RightAction(GameStatus gs) {
		this.gs = gs;
		this.gp = new GamePhysics(gs);
	}

	@Override
	/**
	 * This method corresponds the right key with the various functions in <tt>GameStatus</tt>. 
	 * It moves the player right if its possible and kills the player if it hits an enemy.
	 * It also prevents the player from moving if it hits a wall or a soft block.
	 */
	public void actionPerformed(ActionEvent e) {
		if (gs.getPlayers().size() != 0
				&& gp.canMove(gs.getActivePlayer(),
						GamePhysics.CollisionType.RIGHT)) {
			this.gs.getActivePlayer().moveRight();
			if (gp.hitsEnemy(gs.getActivePlayer(),
					GamePhysics.CollisionType.OVERLAP)) {
				gs.getActivePlayer().die();
			}
		}
	}
}
