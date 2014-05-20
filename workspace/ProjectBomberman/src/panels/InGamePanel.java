package panels;

import system.GameEngine;
import system.GameGraphics;
import system.GameStatus;

/**
 * The class <code>inGamePanel</code> represents the panel of the main game. It
 * extends the class <tt>Graphical Panel</tt>. It contains one method which
 * links to the current game status and paints the game according to the current
 * game status.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 */
public class InGamePanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This method takes as input a GameStatus and then creates a 544x460 frame
	 * which paints what is in GameStatus.
	 * 
	 * @param gs
	 *            Takes as input the game status which is being painted.
	 */
	public InGamePanel(GameStatus gs) {

		super();
		this.add(new GameGraphics(gs, GameEngine.mainFrame));
		this.setSize(544, 460);
		this.setVisible(true);

	}

}
