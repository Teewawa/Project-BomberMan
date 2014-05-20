package system;

import java.util.ArrayList;
import java.util.Random;

/**
 * The <code>GameStatus</code> class contains all the information about the game
 * at any given time. It contains thirty methods, and stores the number of
 * players, bombs, blocks, powerups, enemies, and explosions in the game.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class GameStatus {
	// fields for maximum x pos, maximum y pos, and the increments between walls
	int xMax = 544;
	int yMax = 416;
	int xInc = 32;
	int yInc = 32;

	private Difficulty difficulty;
	private boolean isOnePlayer;
	public boolean gameOver;
	private final ArrayList<Player> players;
	private ArrayList<HardBlock> hardBlocks;
	private ArrayList<SoftBlock> softBlocks;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bomb> bombs;
	private ArrayList<Powerup> powerups;
	private ArrayList<Explosion> explosions;
	private ArrayList<String> scores;

	private final Random r = new Random();

	/**
	 * This method initializes all the variables declared at the start of the
	 * class
	 */
	public GameStatus() {

		players = new ArrayList<Player>();
		bombs = new ArrayList<Bomb>();
		softBlocks = new ArrayList<SoftBlock>();
		hardBlocks = new ArrayList<HardBlock>();
		powerups = new ArrayList<Powerup>();
		enemies = new ArrayList<Enemy>();
		explosions = new ArrayList<Explosion>();
		scores = new ArrayList<String>();

		// defaults
		difficulty = Difficulty.EASY;
		populateMap(1);
		isOnePlayer = true;
	}

	/**
	 * This method adds a player to the game.
	 * 
	 * @param p
	 *            It takes as input the player to be added.
	 */
	public void addPlayer(Player p) {
		players.add(p);
	}

	/**
	 * This method adds a bomb to the game.
	 * 
	 * @param b
	 *            It takes as input the bomb to be added.
	 */
	public void addBomb(Bomb b) {
		bombs.add(b);
	}

	/**
	 * This method adds a soft block to the game.
	 * 
	 * @param br
	 *            It takes as input the soft block to be added.
	 */
	public void addSoftBlock(SoftBlock br) {
		softBlocks.add(br);
	}

	/**
	 * This method adds a hard block to the game.
	 * 
	 * @param w
	 *            It takes as input the hard block to be added.
	 */
	public void addHardBlock(HardBlock w) {
		hardBlocks.add(w);
	}

	/**
	 * This method adds a power up to the game.
	 * 
	 * @param p
	 *            It takes as input the power up to be added.
	 */
	public void addPowerUp(Powerup p) {
		powerups.add(p);
	}

	/**
	 * This method adds an enemy to the game.
	 * 
	 * @param e
	 *            It takes as input the enemy to be added.
	 */
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	/**
	 * This method adds an explosion to the game.
	 * 
	 * @param x
	 *            It takes as input the explosion to be added.
	 */
	public void addExplosion(Explosion x) {
		explosions.add(x);
	}

	/**
	 * This method removes a player from the game.
	 * 
	 * @param index
	 *            This is the index of the player to be removed.
	 */
	public void removePlayer(int index) {
		players.remove(index);
	}

	/**
	 * This method removes a bomb from the game. It also plays a sound when the
	 * bomb is removed.
	 * 
	 * @param index
	 *            This is the index of the bomb to be removed.
	 */

	public void removeBomb(int index) {
		bombs.remove(index);
		GameEngine.s.playSound("/sounds/sonicboom.wav", false);
	}

	public void removeSoftBlock(int index) {

		int chance;

		if (difficulty == Difficulty.EASY) {
			chance = 10;
		} else if (difficulty == Difficulty.MEDIUM) {
			chance = 8;
		} else {
			chance = 6;
		}

		if (r.nextInt(100) < chance) {
			powerups.add(new Powerup(softBlocks.get(index).getxPos(),
					softBlocks.get(index).getyPos()));
		}
		softBlocks.remove(index);

		if (players.size() != 0)
			getActivePlayer().incrementScore(10 * getScoreIncrement());
	}

	/**
	 * This method removes a hard block from the game.
	 * 
	 * @param index
	 *            This is the index of the hard block to be removed.
	 */
	public void removeHardBlock(int index) {
		hardBlocks.remove(index);
	}

	/**
	 * This method removes a power up from the game It also plays a sound when
	 * the power up is removed.
	 * 
	 * @param index
	 *            This is the index of the power up to be removed.
	 */
	public void removePowerUp(int index) {
		powerups.remove(index);
		GameEngine.s.playSound("/sounds/powerup.wav", false);
	}

	/**
	 * This method removes an enemy from the game.
	 * 
	 * @param index
	 *            This is the index of the enemy to be removed.
	 */
	public void removeEnemy(int index) {
		enemies.remove(index);
		GameEngine.s.playSound("/sounds/hadoken.wav", false);
		getActivePlayer().incrementScore(10 * getScoreIncrement());
	}

	/**
	 * This method removes an explosion from the game.
	 * 
	 * @param index
	 *            This is the index of the explosion to be removed.
	 */
	public void removeExplosion(int index) {
		explosions.remove(index);
	}

	/**
	 * This method returns the number of hard blocks
	 * 
	 * @return the number of hard blocks
	 */
	public ArrayList<HardBlock> getHardBlocks() {
		return hardBlocks;
	}

	/**
	 * This method returns the number of soft blocks
	 * 
	 * @return the number of soft blocks
	 */

	public ArrayList<SoftBlock> getSoftBlocks() {
		return softBlocks;
	}

	/**
	 * This method returns the number of players
	 * 
	 * @return the number of players
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * This method returns the active player
	 * 
	 * @return the active player
	 * 
	 */
	public Player getActivePlayer() {

		if (players.size() > 1 && players.get(1).isActive()) {
			return players.get(1);
		} else {
			return players.get(0);
		}
	}

	/**
	 * This method switches the active and inactive players
	 */
	public void switchActivePlayers() {
		if (players.size() == 2) {

			if (players.get(0).isActive()) {
				players.get(0).setActive(false);
				players.get(1).setActive(true);
			} else {
				players.get(0).setActive(true);
				players.get(1).setActive(false);
			}
		}
	}

	/**
	 * This method returns the number of bombs
	 * 
	 * @return the number of bombs
	 */
	public ArrayList<Bomb> getBombs() {
		return bombs;
	}

	/**
	 * This method returns the number of power ups
	 * 
	 * @return the number of power ups
	 */
	public ArrayList<Powerup> getPowerups() {
		return powerups;
	}

	/**
	 * This method returns the number of enemies
	 * 
	 * @return the number of enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * This method returns the explosions on the map
	 * 
	 * @return the explosions on the map
	 */
	public ArrayList<Explosion> getExplosions() {
		return explosions;
	}

	/**
	 * This method returns the score
	 * 
	 * @return the scores in the ArrayList <String>
	 */
	public ArrayList<String> getScores() {
		return scores;
	}

	/**
	 * Thie method sets the scores
	 */
	public void setScores(ArrayList<String> scores) {
		this.scores = scores;
	}

	/**
	 * This method returns the difficulty of the game
	 * 
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * This method sets the difficulty
	 * 
	 * @param difficulty
	 *            It takes as input the difficulty to be set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * This method returns the number of players
	 * 
	 * @return true if there is only one player, false otherwise
	 */
	public boolean isOnePlayer() {
		return isOnePlayer;
	}

	/**
	 * This method sets the game mode to single player
	 * 
	 * @param isOnePlayer
	 *            takes as input the number of players, true if there is only
	 *            one player, false otherwise
	 */
	public void setOnePlayer(boolean isOnePlayer) {
		this.isOnePlayer = isOnePlayer;
	}

	/**
	 * This method changes how the score is calculated depending on difficulty
	 * level. It increases the increment by 5 for every difficulty jump.
	 */
	public int getScoreIncrement() {
		int increment;
		if (difficulty == Difficulty.EASY) {
			increment = 10;
		} else if (difficulty == Difficulty.MEDIUM) {
			increment = 15;
		} else {
			increment = 20;
		}
		return increment;
	}

	/**
	 * This method generates the soft blocks, hard blocks, and enemies of the
	 * Bomberman game. It takes as input an integer 1v1 which corresponds to the
	 * type of enemy spawned.
	 * 
	 * @param lvl
	 *            the integer that helps decide what type of enemy to spawn.
	 */
	public void populateMap(int lvl) {
		bombs = new ArrayList<Bomb>();
		softBlocks = new ArrayList<SoftBlock>();
		hardBlocks = new ArrayList<HardBlock>();
		powerups = new ArrayList<Powerup>();
		enemies = new ArrayList<Enemy>();
		explosions = new ArrayList<Explosion>();
		int enemyChance;
		int softBlockChance;

		if (difficulty == Difficulty.EASY) {
			enemyChance = 5;
			softBlockChance = 50;
		} else if (difficulty == Difficulty.MEDIUM) {
			enemyChance = 10;
			softBlockChance = 40;
		} else {
			enemyChance = 15;
			softBlockChance = 30;
		}

		if (lvl % 2 == 0) {
			softBlockChance = 50;
		}

		// every 3rd level, spawn boss
		if (lvl % 3 == 0) {
			enemies.add(new Boss(480, 352));
		}

		for (int i = 0; i < xMax; i += xInc) {
			for (int j = 0; j < yMax; j += yInc) {
				// places edge blocks
				if (i % (xMax - xInc) == 0 || j % (yMax - yInc) == 0
				// places lattice
						|| i % (2 * xInc) == 0 && j % (2 * yInc) == 0) {
					hardBlocks.add(new HardBlock(i, j));
					// if doesnt block a player or boss spawn location
				} else if ((i > 3 * xInc) || (j > 3 * yInc) && i != 480
						&& j != 352) {
					// chance of softblock
					if (r.nextInt(100) < softBlockChance) {
						softBlocks.add(new SoftBlock(i, j));
						// chance of enemy
					} else if (r.nextInt(100) < enemyChance) {

						if (lvl % 2 == 0) { // every second level, ghosts

							enemies.add(new Ghost(i, j));
						} else {
							enemies.add(new Enemy(i, j));

						}
					}
				}
			}
		}
	}

	/**
	 * This method checks if the game is over
	 * 
	 * @return returns whether the game is over
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * This method sets whether the game is over or not
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
