package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import system.GamePhysics;
import system.GameStatus;

/**
 * The <code>UpAction</code> class represents the code that is triggered when
 * the up key is pressed. It extends the <tt>AbstractAction</tt> class. It
 * corresponds the up key with various functions in <tt>GameStatus</tt>.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class UpAction extends AbstractAction {
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
	public UpAction(GameStatus gs) {
		this.gs = gs;
		this.gp = new GamePhysics(gs);
	}

	@Override
	/**
	 * This method corresponds the up key with the various functions in <tt>GameStatus</tt>. 
	 * It moves the player up if its possible and kills the player if it hits an enemy.
	 * It also prevents the player from moving if it hits a wall or a soft block.
	 */
	public void actionPerformed(ActionEvent e) {

		if (gs.getPlayers().size() != 0
				&& gp.canMove(gs.getActivePlayer(),
						GamePhysics.CollisionType.UP)) {
			this.gs.getActivePlayer().moveUp();
			if (gp.hitsEnemy(gs.getActivePlayer(),
					GamePhysics.CollisionType.OVERLAP)) {
				gs.getActivePlayer().die();
			}
		}
	}
}
