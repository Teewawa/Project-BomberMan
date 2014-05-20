package system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

/**
 * The <code>GamePhysics</code> represents all the physics within the Bomberman
 * game. This implements the interface <tt>Runnable</tt>. It contains thirteen
 * methods and the methods defines collisions between the player, bombs, and
 * blocks within the Bomberman game.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 */
public class GamePhysics implements Runnable {

	GameStatus gs;
	int increment = 32;

	Random r = new Random();

	/*
	 * Default constructor for the GamePhysics class, links the current
	 * GameStatus to the new GamePhysics instance
	 */
	/**
	 * This enum represents the various types of collisions in the game
	 */
	public enum CollisionType {
		UP, DOWN, LEFT, RIGHT, OVERLAP;
	}

	/**
	 * This method intakes the GameStatus to be used by the other methods
	 * 
	 * @param gs
	 *            this takes in the GameStatus to be used
	 */
	public GamePhysics(GameStatus gs) {
		this.gs = gs;
	}

	/**
	 * This method calls the other methods in the GamePhysics class and starts a
	 * timer which runs every 100 ms to check for actions in the Bomberman Game.
	 * It also moves enemies every 600 ms.
	 */
	@Override
	public void run() {

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkGameOver();

				if (!gs.isGameOver()) {
					checkGameWon();
					checkExplosionCollisions();
					checkPowerupCollisions();

				}
				removeDeadObjects();

			}

		};
		Timer timer = new Timer(100, listener);
		timer.start();

		ActionListener listener2 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveEnemies();
			}

		};
		Timer timer2 = new Timer(600, listener2);
		timer2.start();
	}

	/**
	 * This method checks collisions between map objects in the game and returns
	 * true if any of the collision types occur. Otherwise this returns false.
	 * 
	 * @param obj1
	 *            This is the first map object being checked
	 * @param obj2
	 *            This is the second map object being collided upon
	 * @param type
	 *            One of the collision type: UP,DOWN,LEFT,RIGHT,OVERLAP
	 * @return
	 */

	public boolean collidesWith(MapObject obj1, MapObject obj2,
			CollisionType type) {

		if (type == CollisionType.UP) {
			if (obj1.getxPos() == obj2.getxPos()
					&& obj1.getyPos() == obj2.getyPos() + increment) {
				return true;
			}
		}

		if (type == CollisionType.DOWN) {
			if (obj1.getxPos() == obj2.getxPos()
					&& obj1.getyPos() == obj2.getyPos() - increment) {
				return true;
			}
		}

		if (type == CollisionType.LEFT) {
			if (obj1.getyPos() == obj2.getyPos()
					&& obj1.getxPos() == obj2.getxPos() + increment) {
				return true;
			}
		}

		if (type == CollisionType.RIGHT) {
			if (obj1.getyPos() == obj2.getyPos()
					&& obj1.getxPos() == obj2.getxPos() - increment) {
				return true;
			}
		}

		if (type == CollisionType.OVERLAP) {
			if (obj1.getyPos() == obj2.getyPos()
					&& obj1.getxPos() == obj2.getxPos()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks if it is possible for the player or enemy to move in
	 * the game. If the player or the enemy is colliding with a bomb or a block,
	 * it is not possible to move.
	 * 
	 * @param obj
	 *            The player or enemy that is trying to move
	 * @param type
	 *            The direction which the player/enemy is trying to move
	 * @return If it possible to move
	 */
	public boolean canMove(MapObject obj, CollisionType type) {
		for (int i = 0; i < gs.getHardBlocks().size(); i++) {
			if (collidesWith(obj, gs.getHardBlocks().get(i), type)) {
				return false;
			}
		}

		if (!(obj instanceof Ghost)) {
			for (int i = 0; i < gs.getBombs().size(); i++) {
				if (collidesWith(obj, gs.getBombs().get(i), type)) {
					return false;
				}
			}
		}

		if (obj instanceof LivingUnit && !(obj instanceof Ghost)
				&& !(obj instanceof Boss)) {
			for (int i = 0; i < gs.getSoftBlocks().size(); i++) {
				if (collidesWith(obj, gs.getSoftBlocks().get(i), type)) {
					return false;
				}
			}
		}

		if (obj instanceof Enemy) {
			for (int i = 0; i < gs.getEnemies().size(); i++) {
				if (collidesWith(obj, gs.getEnemies().get(i), type)) {
					return false;
				}
			}
		}

		return true;

	}

	/**
	 * This method checks if it is possible for the player to place a bomb. It
	 * takes an input player and checks if the player is either overlapping with
	 * any objects in the game or over the bomb limit.
	 * 
	 * @param p
	 *            Takes an input the player
	 * @return If it is possible to place a bomb
	 */

	public boolean canPlaceBomb(Player p) {
		for (int i = 0; i < gs.getHardBlocks().size(); i++) {
			if (collidesWith(p, gs.getHardBlocks().get(i),
					CollisionType.OVERLAP)) {
				return false;
			}
		}

		for (int i = 0; i < gs.getSoftBlocks().size(); i++) {
			if (collidesWith(p, gs.getSoftBlocks().get(i),
					CollisionType.OVERLAP)) {
				return false;
			}
		}

		for (int i = 0; i < gs.getBombs().size(); i++) {
			if (collidesWith(p, gs.getBombs().get(i), CollisionType.OVERLAP)) {
				return false;
			}
		}

		int counter = 0;

		for (int i = 0; i < gs.getBombs().size(); i++) {
			if (!gs.getBombs().get(i).isBossBomb()) {
				counter++;
			}
		}
		if (p.getMaxBombs() == counter) {
			return false;
		}

		return true;
	}

	/**
	 * This method checks if an object has hit an enemy. It takes an input
	 * object and returns true if the object has collided with the enemy and
	 * false otherwise.
	 * 
	 * @param obj
	 *            Takes as input the object to be checked
	 * @return If the player has hit an enemy
	 */
	public boolean hitsEnemy(MapObject obj, CollisionType type) {
		for (int i = 0; i < gs.getEnemies().size(); i++) {
			if (collidesWith(obj, gs.getEnemies().get(i), type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks if the enemy has hit a player. It takes as input an
	 * enemy and returns true if the enemy has hit a player and false otherwise.
	 * 
	 * @param enemy
	 *            Takes as input the enemy being checked
	 * @return If the enemy has hit a player
	 */

	public boolean hitsPlayer(Enemy enemy) {

		if (collidesWith(enemy, gs.getActivePlayer(), CollisionType.OVERLAP)) {
			return true;
		}

		return false;
	}

	/**
	 * This method explodes the bomb and sets the size of the explosion. It
	 * takes as input an integer index which identifies the specific bomb. It
	 * also checks if the explosion is able to take place. At the end of the
	 * method it removes the bomb from the index to allow a new bomb to be
	 * placed.
	 * 
	 * @param index
	 *            This takes as input the index of the Bomb which identifies it.
	 */

	public void explodeBomb(int index) {

		boolean canExpUp = true;
		boolean canExpDown = true;
		boolean canExpLeft = true;
		boolean canExpRight = true;
		int playerBlastRadius = 2;
		int bossBlastRadius = 2;

		if (!gs.gameOver) {
			playerBlastRadius = gs.getActivePlayer().getBlastRadius();
			bossBlastRadius = (gs.getActivePlayer().getLevel() / 2) + 2;
		}

		// adds explosion outwards
		for (int i = 0; i < (gs.getBombs().get(index).isBossBomb() ? bossBlastRadius
				: playerBlastRadius); i++) {

			// up
			Explosion u = new Explosion(gs.getBombs().get(index).getxPos(), gs
					.getBombs().get(index).getyPos()
					- i * gs.yInc);

			if (canExpUp) {
				if (gs.getBombs().get(index).isBossBomb()) {
					u.setBossExplosion(true);
				}
				gs.addExplosion(u);
			}

			if (!canMove(u, CollisionType.UP)) {
				canExpUp = false;
			}

			// down
			Explosion d = new Explosion(gs.getBombs().get(index).getxPos(), gs
					.getBombs().get(index).getyPos()
					+ i * gs.yInc);

			if (canExpDown) {
				if (gs.getBombs().get(index).isBossBomb()) {
					d.setBossExplosion(true);
				}
				gs.addExplosion(d);
			}

			if (!canMove(d, CollisionType.DOWN)) {
				canExpDown = false;
			}

			// left
			Explosion l = new Explosion(gs.getBombs().get(index).getxPos() - i
					* gs.xInc, gs.getBombs().get(index).getyPos());

			if (canExpLeft) {
				if (gs.getBombs().get(index).isBossBomb()) {
					l.setBossExplosion(true);
				}
				gs.addExplosion(l);
			}

			if (!canMove(l, CollisionType.LEFT)) {
				canExpLeft = false;
			}

			// right
			Explosion r = new Explosion(gs.getBombs().get(index).getxPos() + i
					* gs.xInc, gs.getBombs().get(index).getyPos());

			if (canExpRight) {
				if (gs.getBombs().get(index).isBossBomb()) {
					r.setBossExplosion(true);
				}
				gs.addExplosion(r);
			}

			if (!canMove(r, CollisionType.RIGHT)) {
				canExpRight = false;
			}

		}

		gs.removeBomb(index);
	}

	/**
	 * This method checks if a player has collided with a powerup. If the player
	 * has collided with a powerup, it tells the player to consume the powerup
	 * and to take the powerup off the map.
	 */
	public void checkPowerupCollisions() {
		for (int i = 0; i < gs.getPowerups().size(); i++) {
			if (collidesWith(gs.getActivePlayer(), gs.getPowerups().get(i),
					CollisionType.OVERLAP)) {
				gs.getActivePlayer().consume(gs.getPowerups().get(i));
				gs.removePowerUp(i);
			}
		}
	}

	/**
	 * This method checks if the explosion collided with other objects on the
	 * map and destroys the object if it is an enemy, player, or a soft block.
	 */
	public void checkExplosionCollisions() {
		for (int i = 0; i < gs.getExplosions().size(); i++) {
			for (int j = 0; j < gs.getSoftBlocks().size(); j++) {
				if (collidesWith(gs.getExplosions().get(i), gs.getSoftBlocks()
						.get(j), CollisionType.OVERLAP)) {
					gs.getSoftBlocks().get(j).die();
				}
			}

			for (int j = 0; j < gs.getEnemies().size(); j++) {
				if (!gs.getExplosions().get(i).isBossExplosion()
						&& collidesWith(gs.getExplosions().get(i), gs
								.getEnemies().get(j), CollisionType.OVERLAP)) {
					gs.getEnemies().get(j).die();
				}
			}

			for (int j = 0; j < gs.getPlayers().size(); j++) {
				if (collidesWith(gs.getExplosions().get(i), gs.getPlayers()
						.get(j), CollisionType.OVERLAP)) {
					gs.getPlayers().get(j).die();
				}
			}
		}
	}

	/**
	 * This method removes all dead objects on the map as well as explodes the
	 * bomb if when the timer runs out. The method also switches players for
	 * multi-player mode if the player dies. Moreover, it plays a sound when the
	 * player dies.
	 */
	public void removeDeadObjects() {
		for (int i = 0; i < gs.getBombs().size(); i++) {
			if (gs.getBombs().get(i).isDead()) {
				explodeBomb(i);
			}
		}

		for (int i = 0; i < gs.getExplosions().size(); i++) {
			if (gs.getExplosions().get(i).isDead()) {
				gs.removeExplosion(i);
			}
		}

		for (int i = 0; i < gs.getEnemies().size(); i++) {
			if (gs.getEnemies().get(i).isDead()) {
				gs.removeEnemy(i);
			}
		}

		for (int i = 0; i < gs.getSoftBlocks().size(); i++) {
			if (gs.getSoftBlocks().get(i).isDead()) {
				gs.removeSoftBlock(i);
			}
		}

		if (gs.getPlayers().size() != 0 && gs.getActivePlayer().isDead()) {
			GameEngine.s.playSound("/sounds/death.wav", false);

			if (gs.getActivePlayer().getLives() <= 0) {
				gs.getPlayers().remove(gs.getActivePlayer());

				if (!gs.isOnePlayer()) {
					gs.setOnePlayer(true);
				}

			} else {

				if (!gs.isOnePlayer()) {
					gs.switchActivePlayers();
				}

				gs.getActivePlayer().setDead(false);

			}
		}
	}

	/**
	 * This method checks if the player has completed a level and goes to the
	 * next level if there are no more enemies on the map.
	 */

	public void checkGameWon() {
		if (gs.getEnemies().isEmpty()) {
			GameEngine.levelUp();
		}
	}

	/**
	 * This method checks if the player has run out of lives and if the player
	 * has, the method then ends the game.
	 */
	public void checkGameOver() {
		if (gs.getPlayers().isEmpty()) {
			GameEngine.gameOver();
			gs.setGameOver(true);
		}
	}

	/**
	 * This method runs through the enemies arry using a for loop and moves them
	 * randomly It also kills the player if the enemy moves into the player
	 */
	public void moveEnemies() {
		if (!GameEngine.isPaused) {
			for (int i = 0; i < gs.getEnemies().size(); i++) {
				moveRandom(gs.getEnemies().get(i));

				if (gs.getPlayers().size() != 0) {
					if (hitsPlayer(gs.getEnemies().get(i))) {
						gs.getActivePlayer().die();
					}
				}
			}
		}
	}

	/**
	 * This method moves an enemy randomly according to a random number
	 * generator with a 2/5 chance to not move at all and a 3/5 chance to move
	 * either up,down,left or right. This is only true if the direction in which
	 * the enemy is suppose to move does not result in a collision.
	 * 
	 * @param lu
	 *            takes as input a living unit to move randomly
	 */
	public void moveRandom(LivingUnit lu) {
		// populates options with available moves
		ArrayList<CollisionType> options = new ArrayList<CollisionType>();

		if (!(lu instanceof Boss)) {
			options.add(CollisionType.OVERLAP); // to make nothing happen
												// sometimes
			options.add(CollisionType.OVERLAP);
		}

		if (canMove(lu, CollisionType.UP)) {
			options.add(CollisionType.UP);
		}

		if (canMove(lu, CollisionType.DOWN)) {
			options.add(CollisionType.DOWN);
		}

		if (canMove(lu, CollisionType.LEFT)) {
			options.add(CollisionType.LEFT);
		}

		if (canMove(lu, CollisionType.RIGHT)) {
			options.add(CollisionType.RIGHT);
		}

		// chooses a direction to move in from options
		if (options.size() > 0) {
			CollisionType direction = options.get(r.nextInt(options.size()));
			if (direction == CollisionType.UP) {
				lu.moveUp();
			} else if (direction == CollisionType.DOWN) {
				lu.moveDown();
			} else if (direction == CollisionType.LEFT) {
				lu.moveLeft();
			} else if (direction == CollisionType.RIGHT) {
				lu.moveRight();
			}
		}
	}
}
