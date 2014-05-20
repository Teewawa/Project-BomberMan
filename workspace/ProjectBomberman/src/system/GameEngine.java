package system;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import panels.BackgroundPanel;
import panels.GraphicalPanel;
import panels.InGamePanel;
import panels.InstructionsBg;
import panels.InstructionsPanel;
import panels.MainMenuPanel;
import panels.NewGamePanel;
import actions.DownAction;
import actions.EscapeAction;
import actions.LeftAction;
import actions.RightAction;
import actions.SpaceAction;
import actions.UpAction;

/**
 * The class <code>GameEngine</code> represents the central hub of the Bomberman
 * game. It is where all the other classes are compiled together to execute the
 * game. It contains ten methods and they initialize the game and its functions
 * including single player, multiplayer, loading, saving, level up and gameOver.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class GameEngine {
	public static GameStatus gs;
	static GameGraphics gg;
	static GamePhysics gp;
	static Sound s;
	public static JFrame mainFrame = new JFrame("Project Bomberman");
	public static MainMenuPanel mainMenuPanel = new MainMenuPanel();
	public static boolean isPaused;

	public static GraphicalPanel gamePanel; // possible issue here
	public static NewGamePanel ngPanel;

	static Thread physicsThread;
	static Thread graphicsThread;

	/**
	 * This is the main method of the <tt>GameEngine</tt> class. It intializes a
	 * new <tt>GameStatus</tt>, <tt>GameGraphics</tt>, and <tt>GamePhysics</tt>.
	 * It also creates a thread for both the physics and graphics class. It also
	 * generates a new graphical panel which contains the main menu.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		gs = new GameStatus();
		gg = new GameGraphics(gs, mainFrame);
		gp = new GamePhysics(gs);
		s = new Sound();

		physicsThread = new Thread(gp);
		graphicsThread = new Thread(gg);

		s.playSound("/sounds/backgroundmusiclowqual.wav", true);

		setMenuBars(mainFrame);

		addPanel(mainFrame, generatePanel(mainMenuPanel));

		mainFrame.setSize(544, 460);

		mainFrame.setVisible(true);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * This method represents the options presented to the user when he wants to
	 * quit the game. It asks the user whether he wants to save or not prior to
	 * quitting.
	 */
	public static void quitGame() {
		int n = JOptionPane.showConfirmDialog(mainFrame,
				"Would you like to save before quitting?", "Save game?",
				JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			saveGame(gs);
		}
	}

	/**
	 * This method pauses the game.
	 */
	public static void pauseGame() {
		isPaused = true;
	}

	/**
	 * This method resumes the game from a pause.
	 * 
	 */
	public static void resumeGame() {
		isPaused = false;
	}

	/**
	 * This method creates a new file representing the current game status. It
	 * then gets the current x and y co-ordinates, score, lives, and powerups
	 * collected and saves them in the file created. It also creates a catch
	 * exception, in case the information is not found.
	 * 
	 * @param gs
	 *            This takes as input the current game status
	 */
	public static void saveGame(GameStatus gs) {
		// Attempts to create the new save file.
		File saveFile = new File("src/savedGame.bombSave");

		try {
			JFileChooser fc = new JFileChooser("src/");
			javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter(
					"Bomberman save files", "bombSave");
			fc.setFileFilter(filter);

			saveFile = new File("src/savedGame.bombSave");
			int returnVal = fc.showSaveDialog(mainFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				saveFile = new File(fc.getSelectedFile() + ".bombSave");
			}

			else {
				return;
			}

			// Sets up the stream to write to the file.
			BufferedWriter bWriter = new BufferedWriter(
					new FileWriter(saveFile));

			// Saving players
			Iterator<Player> iteratePlayer = gs.getPlayers().iterator();

			while (iteratePlayer.hasNext()) {
				Player currentPlayer = iteratePlayer.next();

				String readyToWrite = "" + currentPlayer.getxPos() + "#"
						+ currentPlayer.getyPos() + "#"
						+ currentPlayer.getScore() + "#"
						+ currentPlayer.getLevel() + "#"
						+ currentPlayer.getLives() + "#"
						+ currentPlayer.getMaxBombs() + "#"
						+ currentPlayer.getBlastRadius();

				bWriter.write(readyToWrite);
				bWriter.newLine();
			}

			bWriter.write("@");
			bWriter.newLine();

			// Saving Hard Blocks
			Iterator<HardBlock> iterateHBlocks = gs.getHardBlocks().iterator();

			while (iterateHBlocks.hasNext()) {
				HardBlock currentHBlock = iterateHBlocks.next();

				String readyToWrite = "" + currentHBlock.getxPos() + "#"
						+ currentHBlock.getyPos();

				bWriter.write(readyToWrite);
				bWriter.newLine();
			}

			bWriter.write("@");
			bWriter.newLine();

			// Saving Soft Blocks
			Iterator<SoftBlock> iterateSBlocks = gs.getSoftBlocks().iterator();

			while (iterateSBlocks.hasNext()) {
				SoftBlock currentSBlock = iterateSBlocks.next();

				String readyToWrite = "" + currentSBlock.getxPos() + "#"
						+ currentSBlock.getyPos();

				bWriter.write(readyToWrite);
				bWriter.newLine();
			}

			bWriter.write("@");
			bWriter.newLine();

			// Saving Enemies
			Iterator<Enemy> iterateEnemies = gs.getEnemies().iterator();

			while (iterateEnemies.hasNext()) {
				Enemy currentEnemy = iterateEnemies.next();

				String readyToWrite = "" + currentEnemy.getxPos() + "#"
						+ currentEnemy.getyPos();

				bWriter.write(readyToWrite);
				bWriter.newLine();
			}

			bWriter.write("@");
			bWriter.newLine();

			// Saving Powerup
			Iterator<Powerup> iteratePowerUp = gs.getPowerups().iterator();

			while (iteratePowerUp.hasNext()) {
				Powerup currentPowerup = iteratePowerUp.next();

				String readyToWrite = "" + currentPowerup.getxPos() + "#"
						+ currentPowerup.getyPos();

				bWriter.write(readyToWrite);
				bWriter.newLine();
			}

			bWriter.write("@");
			bWriter.newLine();

			// Closes the stream to avoid memory leaks.
			bWriter.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * This method reads from the saved high scores text file and adds them to
	 * an <tt>ArrayList</tt> which is suppose to contain all the scores saved.
	 * 
	 * @return loadedscores returns the <tt>ArrayList</tt> containing all the
	 *         scores read.
	 */
	public static ArrayList<String> loadScores() {
		ArrayList<String> loadedScores = new ArrayList<String>();

		try {
			BufferedReader bReader = new BufferedReader(new FileReader(
					"src/savedScores.txt"));

			String currentLine;
			while (!(currentLine = bReader.readLine()).equals(null)) {
				loadedScores.add(currentLine);
			}

			bReader.close();

			return loadedScores;

		} catch (Exception e) {
			File emptyDummy = new File("src/savedScores.txt");
			try {
				@SuppressWarnings("unused")
				boolean fileCreated = emptyDummy.createNewFile();
			}

			catch (IOException ioe) {

			}

			JOptionPane.showMessageDialog(null,
					"No scorefile was found, so a new one was created!");
		}

		return loadedScores;
	}

	/**
	 * This method creates a new file call highscores.bombSave. It then reads
	 * the scores from <tt>GameStatus</tt> and writes the scores into the text
	 * file.
	 */
	public static void saveScores() {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(
					"src/savedScores.txt"));

			Iterator<String> iterate = gs.getScores().iterator();

			while (iterate.hasNext()) {
				bWriter.write(iterate.next());
			}

			bWriter.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method loads the save file created in <tt>saveGame</tt>. It loads
	 * the file into a buffered reader and reads the integers stored in the
	 * file, representing the various attributes of the player at save.It then
	 * takes that information and writes them into the <tt>GameStatus</tt>.
	 */
	public static void loadGame() {
		GameStatus newgs = new GameStatus();
		try {

			JFileChooser fc = new JFileChooser("src/");
			javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter(
					"Bomberman save files", "bombSave");
			fc.setFileFilter(filter);

			File loadedFile = new File("src/savedGame.bombSave");
			int returnVal = fc.showOpenDialog(mainFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				loadedFile = fc.getSelectedFile();
			}

			else {
				return;
			}
			// Sets up the stream to read from the file.
			BufferedReader bReader = new BufferedReader(new FileReader(
					loadedFile));

			String currentLine;
			int counter = 0;

			// Loads players on file
			while (!(currentLine = bReader.readLine()).equals("@")) {
				if (currentLine.length() == 0)
					continue;
				String[] parsedContent = currentLine.split("#");

				newgs.addPlayer(new Player(Integer.parseInt(parsedContent[0]),
						Integer.parseInt(parsedContent[1])));
				newgs.getPlayers().get(counter)
						.setScore(Integer.parseInt(parsedContent[2]));
				newgs.getPlayers().get(counter)
						.setLevel(Integer.parseInt(parsedContent[3]));
				newgs.getPlayers().get(counter)
						.setLives(Integer.parseInt(parsedContent[4]));
				newgs.getPlayers().get(counter)
						.setMaxBombs(Integer.parseInt(parsedContent[5]));
				newgs.getPlayers().get(counter)
						.setBlastRadius(Integer.parseInt(parsedContent[6]));

			}

			// Skip the separator line to the next sector
			currentLine = bReader.readLine();

			// Loads HardBlocks
			while (!(currentLine = bReader.readLine()).equals("@")) {
				if (currentLine.length() == 0)
					continue;
				String[] parsedContent = currentLine.split("#");

				newgs.addHardBlock(new HardBlock(Integer
						.parseInt(parsedContent[0]), Integer
						.parseInt(parsedContent[1])));
			}

			// Skip the separator line to the next sector
			currentLine = bReader.readLine();

			// Loads SoftBlocks
			while (!(currentLine = bReader.readLine()).equals("@")) {
				if (currentLine.length() == 0)
					continue;
				String[] parsedContent = currentLine.split("#");

				newgs.addSoftBlock(new SoftBlock(Integer
						.parseInt(parsedContent[0]), Integer
						.parseInt(parsedContent[1])));
			}

			// Skip the separator line to the next sector
			currentLine = bReader.readLine();

			// Loads Enemies
			while (!(currentLine = bReader.readLine()).equals("@")) {
				if (currentLine.length() == 0)
					continue;
				String[] parsedContent = currentLine.split("#");

				newgs.addEnemy(new Enemy(Integer.parseInt(parsedContent[0]),
						Integer.parseInt(parsedContent[1])));
			}

			// Skip the separator line to the next sector
			currentLine = bReader.readLine();
			currentLine = bReader.readLine();

			/*
			 * // Loads Powerups while (currentLine != null && !(currentLine =
			 * bReader.readLine()).equals("@")) { if (currentLine.length() == 0)
			 * continue; String[] parsedContent = currentLine.split("#");
			 * 
			 * newgs.addPowerUp(new Powerup(Integer.parseInt(parsedContent[0]),
			 * Integer.parseInt(parsedContent[1]))); }
			 */

			// Closing the stream to avoid memory leaks.
			bReader.close();
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		GameEngine.mainFrame.getContentPane().removeAll();

		GraphicalPanel newgamePanel = new InGamePanel(newgs);
		gp = new GamePhysics(newgs);
		gg = new GameGraphics(newgs, mainFrame);
		GameEngine.gs = newgs;

		GameEngine.addPanel(GameEngine.mainFrame,
				GameEngine.generatePanel(newgamePanel));
		GameEngine.addBindings(newgamePanel);
		GameEngine.isPaused = false;
		gs.setGameOver(false);

		startThreads();
	}

	/**
	 * This method takes as input a player and takes the score of the player
	 * from the <tt>GameStatus</tt>. It then converts the score to a string and
	 * adds it into memeory in <tt>GameStatus</tt>.
	 * 
	 * @param player
	 *            the player whos score is being comited.
	 */
	public static void commitScore(Player player) {
		Iterator<String> iterate = gs.getScores().iterator();
		int insertionPoint;
		String playerScore = Integer.toString(player.getScore());
		String comparisonPoint, comparisonPointRef;
		while (iterate.hasNext()) {
			comparisonPointRef = iterate.next();
			comparisonPoint = comparisonPointRef.split("#")[1];
			if (comparisonPoint.compareTo(playerScore) <= 0) {
				insertionPoint = gs.getScores().indexOf(comparisonPointRef);
				gs.getScores().add(insertionPoint,
						player.getName() + "#" + playerScore);
				break;
			}
		}

		gs.getScores().add(player.getName() + "#" + playerScore);
	}

	/**
	 * This method calls one of two possible methdos in <tt>GameEngine</tt>
	 * depending on if the game is one player or two player.
	 */
	public static void startGame() {
		isPaused = false;
		gs.setGameOver(false);

		if (gs.isOnePlayer()) {
			startOnePlayerGame();
		} else {
			startTwoPlayerGame();
		}
	}

	/**
	 * This method starts a one player game by adding a new player at the
	 * starting location and starting the physics and graphics thread.
	 */
	public static void startOnePlayerGame() {
		if (gs.getPlayers().size() != 0)
			gs.getPlayers().clear();
		gs.setGameOver(false);

		gs.getPlayers().add(new Player(32, 32));
		gs.getPlayers().get(0)
				.setName(JOptionPane.showInputDialog("What is your name? "));

		gs.setScores(loadScores());
		startThreads();
	}

	/**
	 * This method starts a two player game by adding two players at the
	 * starting location and setting one of them to inActive.
	 */
	public static void startTwoPlayerGame() {
		if (gs.getPlayers().size() != 0) {
			gs.getPlayers().clear();
		}

		gs.setGameOver(false);

		gs.getPlayers().add(new Player(32, 32));
		gs.getPlayers().add(new Player(32, 32));

		gs.getPlayers()
				.get(0)
				.setName(
						JOptionPane
								.showInputDialog("What is player 1's name? "));

		gs.getPlayers()
				.get(1)
				.setName(
						JOptionPane
								.showInputDialog("What is player 2's name? "));

		gs.getPlayers().get(1).setActive(false);
		startThreads();
	}

	/**
	 * This method starts the physics and graphics thread
	 */
	public static void startThreads() {
		physicsThread.interrupt();
		graphicsThread.interrupt();

		physicsThread = new Thread(gp);
		graphicsThread = new Thread(gg);

		physicsThread.start();
		graphicsThread.start();
	}

	/**
	 * This method controls what happens when a player reaches the next level by
	 * repopulating the map and reseting the players co-ordinates.
	 */
	public static void levelUp() {
		gs.getActivePlayer().levelUp();
		gs.populateMap(gs.getActivePlayer().getLevel());
		gs.getActivePlayer().setCoordinates(32, 32);
		s.playSound("/sounds/cheer.wav", false);
		gs.getActivePlayer().incrementScore(100 * gs.getScoreIncrement());
	}

	/**
	 * This method controls what happens when the game is over by printing a
	 * Game Over line and then exiting.
	 */
	public static void gameOver() {
		gs.setGameOver(true);
		saveScores();
	}

	/**
	 * This method maps the up, down, left, right and space keys to their
	 * corresponding actions in the action package. It does this by adding it to
	 * an input map and then corresponding that to an action map. The action and
	 * input maps are taken from the input gamePanel.
	 * 
	 * @param gamePanel
	 *            This is the gamepanel from which the action and input maps are
	 *            taken.
	 * 
	 */
	public static void addBindings(GraphicalPanel gamePanel) {
		// Creates Action objects corresponding to commands keys
		UpAction upAction = new UpAction(gs);
		DownAction downAction = new DownAction(gs);
		LeftAction leftAction = new LeftAction(gs);
		RightAction rightAction = new RightAction(gs);
		SpaceAction spaceAction = new SpaceAction(gs);
		EscapeAction escapeAction = new EscapeAction(gs);

		// Gets the Input and Action maps associated with the game panel

		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap imap = gamePanel.getInputMap(mapName);
		ActionMap amap = gamePanel.getActionMap();

		// Creates Keystroke objects for the command keys
		KeyStroke UP = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
		KeyStroke DOWN = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
		KeyStroke LEFT = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
		KeyStroke RIGHT = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
		KeyStroke SPACE = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
		KeyStroke ESCAPE = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);

		// Adds the Keystroke objects to the Input map and associates
		// labels

		imap.put(UP, "Up");
		imap.put(DOWN, "Down");
		imap.put(LEFT, "Left");
		imap.put(RIGHT, "Right");
		imap.put(SPACE, "Space");
		imap.put(ESCAPE, "Escape");

		// Links the labels with Action objects in the Action map
		amap.put("Up", upAction);
		amap.put("Down", downAction);
		amap.put("Left", leftAction);
		amap.put("Right", rightAction);
		amap.put("Space", spaceAction);
		amap.put("Escape", escapeAction);

	}

	/**
	 * This method sets up the menu bars of the bomberman game. It takes as
	 * input a JFrame which is used to create the menu. It generates the buttons
	 * of the main menu from the Panels package and allows the player to
	 * navigate using arrow keys.
	 * 
	 * @param mainFrame
	 *            The JFrame to be used to create the main menu.
	 */
	public static void setMenuBars(final JFrame mainFrame) {
		/*
		 * Creates the menu bar and adds key bindings to the new game. Key
		 * bindings will be moved elsewhere if New Game functionality migrates.
		 */
		JMenuBar menubar = new JMenuBar();

		JMenu game = new JMenu("Game");

		JMenu help = new JMenu("Help");
		JMenuItem inst = new JMenuItem("Key Bindings");
		inst.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				mainFrame.getContentPane().removeAll();
				GameEngine.addPanel(mainFrame,
						generateInstPanel(new InstructionsPanel()));
			}

		});

		help.add(inst);

		JMenuItem fileNew = new JMenuItem("Quick Game");
		fileNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				mainFrame.getContentPane().removeAll();

				GraphicalPanel gamePanel = GameEngine
						.generatePanel(new InGamePanel(gs));
				addPanel(GameEngine.mainFrame, gamePanel);
				gs.setDifficulty(Difficulty.EASY);
				gs.populateMap(1);
				addBindings(gamePanel);
				startGame();

				// mainFrame.requestFocus();
				// mainFrame.setVisible(true);
			}

		}

		);

		JMenuItem fileLoad = new JMenuItem("Load Game");
		fileLoad.addActionListener(new ActionListener() {
			/*
			 * Allows for load program functionality
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				loadGame();
			}
		});

		JMenuItem fileSave = new JMenuItem("Save Game");
		fileSave.addActionListener(new ActionListener() {
			/*
			 * Allows for save program functionality
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				saveGame(gs);
			}
		});

		JMenuItem fileExit = new JMenuItem("Exit");
		fileExit.setToolTipText("Exit application");

		fileExit.addActionListener(new ActionListener() {
			/*
			 * Allows for exit program functionality
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				// saveScores();
				System.exit(0);
			}
		});

		// Menu consolidation
		game.add(fileNew);
		game.add(fileLoad);
		game.add(fileSave);
		game.addSeparator();
		game.add(fileExit);

		menubar.add(game);
		menubar.add(help);

		mainFrame.setJMenuBar(menubar);
	}

	/**
	 * This method takes as input a <tt>GraphicalPanel</tt> and contains a
	 * constructor for a new BackgroundPanel. It sets the input panel in the
	 * center of the new BackgroundPanel and returns the panel.
	 * 
	 * @param gp
	 *            This is the graphic panel to be centred.
	 * @return backgroundPanel Returns the input panel centred.
	 */
	public static GraphicalPanel generatePanel(GraphicalPanel gp) {
		GraphicalPanel backgroundPanel = new BackgroundPanel();
		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.add(gp, BorderLayout.CENTER);
		return backgroundPanel;
	}

	/**
	 * This method takes as input a <tt>GraphicalPanel</tt> and contains a
	 * constructor for a new instructions panel. .
	 * 
	 * @param gp
	 *            This is the graphic panel on which the instructions panel is
	 *            painted
	 * @return instructionsBg this is the instructions panel created
	 */
	public static GraphicalPanel generateInstPanel(GraphicalPanel gp) {
		GraphicalPanel instructionsBg = new InstructionsBg();
		instructionsBg.setLayout(new BorderLayout());
		instructionsBg.add(gp, BorderLayout.CENTER);
		return instructionsBg;
	}

	/**
	 * This method takes as input a GraphicalPanel and adds it to the frame.
	 * 
	 * @param frame
	 *            the javaFrame which will represent the input panel
	 * @param panel
	 *            the panel to be added onto the javaFrame
	 */
	public static void addPanel(JFrame frame, GraphicalPanel panel) {

		frame.add(panel);
		frame.requestFocus();
		frame.setVisible(true);
	}
}
