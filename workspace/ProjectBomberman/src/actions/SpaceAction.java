package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import system.Bomb;
import system.GamePhysics;
import system.GameStatus;

/**
 * The <code>SpaceAction</code> class represents the code that is triggered when
 * the space key is pressed. It extends the <tt>AbstractAction</tt> class. It
 * corresponds the space key with various functions in <tt>GameStatus</tt>.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class SpaceAction extends AbstractAction {
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
	public SpaceAction(GameStatus gs) {
		this.gs = gs;
		this.gp = new GamePhysics(gs);
	}

	@Override
	/**
	 * This method corresponds the space key with the various functions in <tt>GameStatus</tt>. 
	 * It places a bomb if the player can place a bomb at the players position.
	 */
	public void actionPerformed(ActionEvent e) {
		if (gs.getPlayers().size() != 0
				&& gp.canPlaceBomb(gs.getActivePlayer())) {
			this.gs.addBomb(new Bomb(gs.getActivePlayer().getxPos(), gs
					.getActivePlayer().getyPos()));
		}
	}
}
