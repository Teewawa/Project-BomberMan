package system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import system.Powerup.PowerupType;

/**
 * The class <code>Player</code> and represents all the characteristics of the
 * player. It extends the class <tt>LivingUnit</tt>. It sets the number of lives
 * the player has, the number of bombs the player has and the size of the
 * explosion.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class Player extends LivingUnit {
	private int score;
	private int level;
	private int lives;
	private int maxBombs;
	private int blastRadius;
	private boolean isActive;
	private String name;
	private boolean invulnerable;

	/**
	 * This method sets the initial statistics of the player as well as its
	 * starting location.
	 * 
	 * @param x
	 *            This is the starting x co-ordinate of the Player.
	 * @param y
	 *            This is the starting y co-ordinate of the Player.
	 */
	public Player(int x, int y) {
		super(x, y);
		lives = 3;
		maxBombs = 1;
		blastRadius = 2;
		level = 1;
		setImage(loadImage(getClass().getResource("/images/player.png")));
		// name = JOptionPane.showInputDialog(null, "Player name: ",
		// "Enter your name:", 1);
		name = "default";
		isActive = true;
		invulnerable = false;
	}

	/**
	 * This method returns the name of the player
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the number of lives the player has.
	 * 
	 * @return Returns the number of lives the player has.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * This method sets the number of lives the player has.
	 * 
	 * @param lives
	 *            This takes as input the number of lives of the player.
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * This method gets the max number of bombs the player can have.
	 * 
	 * @return Returns the max number of bombs.
	 */
	public int getMaxBombs() {
		return maxBombs;
	}

	/**
	 * This method sets the max number of bombs the player has.
	 * 
	 * @param maxBombs
	 *            This takes as input the max number of bombs the player has.
	 */
	public void setMaxBombs(int maxBombs) {
		this.maxBombs = maxBombs;
	}

	/**
	 * This method gets the blast radius of the player.
	 * 
	 * @return Returns the blast radius of the player.
	 */
	public int getBlastRadius() {
		return blastRadius;
	}

	/**
	 * This method sets the blast radius of the player.
	 * 
	 * @param blastRadius
	 *            This takes as input the blast radius of the player.
	 */
	public void setBlastRadius(int blastRadius) {
		this.blastRadius = blastRadius;
	}

	/**
	 * This method gets the score of the player.
	 * 
	 * @return Returns the score of the player.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * This method takes as input an integer and sets the score of the player as
	 * the input.
	 * 
	 * @param score
	 *            This takes as input the score of the player.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * This method icrements the score.
	 * 
	 * @param increment
	 *            this is the amount by which to increase the score.
	 */
	public void incrementScore(int increment) {
		this.score += increment;
	}

	/**
	 * This method sets the conditions of the player when the player dies. It
	 * resets the player's co-ordinates, decreases the number of lives and
	 * resets the blast radius and number of bombs.
	 */
	@Override
	public void die() {

		if (!invulnerable) {
			this.setDead(true);
			setCoordinates(32, 32);
			this.lives--;
			this.maxBombs = 1;
			this.blastRadius = 2;
			this.invulnerable = true;

			ActionListener listener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					invulnerable = false;
				}
			};

			Timer timer = new Timer(1000, listener);
			timer.setRepeats(false);
			timer.start();

		}

		GameEngine.commitScore(this);
	}

	/**
	 * This method gets the condition of the player.
	 * 
	 * @return Returns true if the player is the active player and false
	 *         otherwise.
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * This method sets the player condition depending on the isActive method.
	 * 
	 * @param isActive
	 *            This takes as input a boolean isActive and sets the player
	 *            Active if its true and false otherwise.
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * This method sets what happens when the player consumes a powerup.
	 * Depending on the powerup consumed, the player will increase either
	 * maxBombs or blastRadius.
	 * 
	 * @param powerup
	 *            It takes as input the powerup consumed.
	 */
	public void consume(Powerup powerup) {
		if (powerup.getType() == PowerupType.BLAST) {
			blastRadius++;
		} else {
			maxBombs++;
		}
	}

	/**
	 * This method gets the level of the player.
	 * 
	 * @return Returns the level of the player.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * This method sets the level of the player.
	 * 
	 * @param level
	 *            This takes as input the level of the player.
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * This method increases the level of the player.
	 */
	public void levelUp() {
		this.level++;
	}

	/**
	 * This method checks if the player is invulnerable or not
	 */
	public boolean isInvulnerable() {
		return invulnerable;
	}

	/**
	 * This method sets whether the player is invulnerable or not
	 */
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

}
