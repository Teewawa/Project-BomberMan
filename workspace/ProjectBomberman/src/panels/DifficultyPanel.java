package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.Difficulty;
import system.GameEngine;

/**
 * The class <code>DifficultyPanel</code> represents the difficulty menu which
 * extends from the main menu of the game. It extends class
 * <tt>GraphicalPanel</tt> and contains one method which sets up three panels
 * representing the three difficulties of the game.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class DifficultyPanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This method creates three buttons EASY, MODERATE, and HARD which
	 * represent the three difficulty levels of the Bomberman game. It also
	 * starts the game when the panel is pressed in the menu.
	 */
	public DifficultyPanel(boolean isSinglePlayer) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setOpaque(false);

		JButton btnEasy = new JButton("Easy");
		btnEasy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.mainFrame.getContentPane().removeAll();

				GraphicalPanel gamePanel = GameEngine
						.generatePanel(new InGamePanel(GameEngine.gs));
				GameEngine.addPanel(GameEngine.mainFrame, gamePanel);
				GameEngine.gs.setDifficulty(Difficulty.EASY);
				GameEngine.addBindings(gamePanel);
				GameEngine.startGame();

			}
		});
		GridBagConstraints gbc_btnEasyButton = new GridBagConstraints();
		gbc_btnEasyButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnEasyButton.gridx = 2;
		gbc_btnEasyButton.gridy = 4;
		add(btnEasy, gbc_btnEasyButton);

		JButton btnModerate = new JButton("Moderate");
		btnModerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.mainFrame.getContentPane().removeAll();

				GraphicalPanel gamePanel = GameEngine
						.generatePanel(new InGamePanel(GameEngine.gs));
				GameEngine.addPanel(GameEngine.mainFrame, gamePanel);
				GameEngine.gs.setDifficulty(Difficulty.MEDIUM);
				GameEngine.addBindings(gamePanel);
				GameEngine.startGame();
			}
		});
		GridBagConstraints gbc_btnModButton = new GridBagConstraints();
		gbc_btnModButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnModButton.gridx = 4;
		gbc_btnModButton.gridy = 4;
		add(btnModerate, gbc_btnModButton);

		JButton btnHard = new JButton("Hard");
		btnHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.mainFrame.getContentPane().removeAll();

				GraphicalPanel gamePanel = GameEngine
						.generatePanel(new InGamePanel(GameEngine.gs));
				GameEngine.addPanel(GameEngine.mainFrame, gamePanel);
				GameEngine.gs.setDifficulty(Difficulty.HARD);
				GameEngine.addBindings(gamePanel);
				GameEngine.startGame();
			}
		});
		GridBagConstraints gbc_btnHard = new GridBagConstraints();
		gbc_btnHard.gridx = 6;
		gbc_btnHard.gridy = 4;
		add(btnHard, gbc_btnHard);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.mainFrame.getContentPane().removeAll();

				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(new NewGamePanel()));
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 12;
		gbc_btnBack.anchor = GridBagConstraints.LAST_LINE_START;
		add(btnBack, gbc_btnBack);

	}

}
