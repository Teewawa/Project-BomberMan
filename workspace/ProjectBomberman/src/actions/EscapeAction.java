package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panels.EscapeMenuPanel;
import system.GameEngine;
import system.GameStatus;

/**
 * The <code>EscapeAction</code> class represents the code that is triggered
 * when the escape key is pressed. It extends the <tt>AbstractAction</tt> class.
 * It corresponds the down key with various functions in <tt>GameStatus</tt>.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class EscapeAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	GameStatus gs;

	/**
	 * This method initializes a game status class to be used by the other
	 * function.
	 * 
	 * @param gs
	 *            This is the game status class to be used by the other
	 *            functions.
	 */
	public EscapeAction(GameStatus gs) {
		this.gs = gs;
	}

	/**
	 * This method corresponds the escape key with the various functions in
	 * <tt>GameStatus</tt>. It pauses the game when the escape key is pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		GameEngine.pauseGame();
		GameEngine.mainFrame.getContentPane().removeAll();

		GameEngine.addPanel(GameEngine.mainFrame,
				GameEngine.generatePanel(new EscapeMenuPanel(gs)));

	}
}
