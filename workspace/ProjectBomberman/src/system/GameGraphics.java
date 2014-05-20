package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The <code>GameGraphics</code> class represents all the graphics in the
 * bomberman game. It extends the <tt>JPanel</tt> class and implements the
 * interface <tt>Runnable</tt>. This class contains four methods, one that takes
 * in the <tt>GameStatus</tt> and <tt>JFrame</tt>, one that repaints the
 * graphics, one that threads the graphics, and one that draws the bomberman
 * objects
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class GameGraphics extends JPanel implements Runnable {
	GameStatus gs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ImageIcon b = new ImageIcon(getClass()
			.getResource("/images/background.png"));
	Image background = b.getImage();
	JFrame frame;

	/**
	 * This method takes in a GameStatus and JFrame to be used by the other
	 * methods of this class
	 * 
	 * @param gs
	 *            takes in the current GameStatus
	 * @param frame
	 *            takes in the current JFrame
	 */
	public GameGraphics(GameStatus gs, JFrame frame) {
		this.gs = gs;
		this.frame = frame;
	}

	/**
	 * This method repaints the frame every 10ms as long as the player is
	 * interacting with the game
	 */
	@Override
	public void run() {

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.repaint();
			}

		};
		Timer timer = new Timer(10, listener);
		timer.start();
	}

	/**
	 * This method takes in a <tt>Graphics</tt> object and paints game objects
	 * according to the <tt>GameStatus</tt> of the input. This include
	 * background, powerups, soft blocks, hard blocks, bombs, players, enemies,
	 * and explosions.
	 * 
	 * @param g
	 *            takes in a graphics object
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		// draws background
		g2d.drawImage(background, 0, 0, null);

		// draws hard blocks
		for (int i = 0; i < gs.getHardBlocks().size(); i++) {
			g2d.drawImage(gs.getHardBlocks().get(i).getImage(), gs
					.getHardBlocks().get(i).getxPos(), gs.getHardBlocks()
					.get(i).getyPos(), null);
		}

		// draws soft blocks
		for (int i = 0; i < gs.getSoftBlocks().size(); i++) {
			g2d.drawImage(gs.getSoftBlocks().get(i).getImage(), gs
					.getSoftBlocks().get(i).getxPos(), gs.getSoftBlocks()
					.get(i).getyPos(), null);
		}

		// draws powerups
		for (int i = 0; i < gs.getPowerups().size(); i++) {
			g2d.drawImage(gs.getPowerups().get(i).getImage(), gs.getPowerups()
					.get(i).getxPos(), gs.getPowerups().get(i).getyPos(), null);
		}

		// draws bombs
		for (int i = 0; i < gs.getBombs().size(); i++) {
			g2d.drawImage(gs.getBombs().get(i).getImage(), gs.getBombs().get(i)
					.getxPos(), gs.getBombs().get(i).getyPos(), null);
		}

		if (gs.getPlayers().isEmpty()) {
			GameEngine.gameOver();
			gs.setGameOver(true);

		} else {

			// draws players
			g2d.drawImage(gs.getActivePlayer().getImage(), gs.getActivePlayer()
					.getxPos(), gs.getActivePlayer().getyPos(), null);
		}

		// draws enemies
		for (int i = 0; i < gs.getEnemies().size(); i++) {
			g2d.drawImage(gs.getEnemies().get(i).getImage(), gs.getEnemies()
					.get(i).getxPos(), gs.getEnemies().get(i).getyPos(), null);
		}

		// draws explosions
		for (int i = 0; i < gs.getExplosions().size(); i++) {
			g2d.drawImage(gs.getExplosions().get(i).getImage(), gs
					.getExplosions().get(i).getxPos(), gs.getExplosions()
					.get(i).getyPos(), null);
		}

		g2d.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.PLAIN, 25);
		g2d.setFont(font);

		if (!gs.isGameOver()) {

			g2d.drawString("Score: " + gs.getActivePlayer().getScore(), 5, 24);
			g2d.drawString("Lives: " + gs.getActivePlayer().getLives(), 222, 24);
			g2d.drawString("Level: " + gs.getActivePlayer().getLevel(), 444, 24);
			g2d.drawString("Player: " + gs.getActivePlayer().getName(), 5, 408);
		} else {
			g2d.drawString("Game over.", 222, 200);
		}

	}

}
