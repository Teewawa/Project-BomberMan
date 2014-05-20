package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.GameEngine;
import system.GameStatus;

/**
 * 
 * The class <code>MainMenuPanel</code> represents the main menu of the
 * Bomberman game. It extends the class <tt>GraphicalPanel</tt>. It contains one
 * method which creates the five options found in the main menu.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 */
public class EscapeMenuPanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This method creates the five different panels available in the main menu
	 * of the bomberman game. It creates a new game panel, a load game panel, an
	 * instructions panel, a High Scores panel, and an exit panel.
	 */
	public EscapeMenuPanel(final GameStatus gs) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setOpaque(false);

		JButton btnNewButton = new JButton("Resume Game");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameEngine.mainFrame.getContentPane().removeAll();

				GraphicalPanel gamePanel = new InGamePanel(gs);
				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(gamePanel));
				GameEngine.addBindings(gamePanel);

				GameEngine.resumeGame();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Save Game");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameEngine.saveGame(gs);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JButton btnNewButton_4 = new JButton("Quit Game");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 4;
		add(btnNewButton_4, gbc_btnNewButton_4);

		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 1;
		gbc_btnExit.gridy = 6;
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameEngine.quitGame();
				GameEngine.mainFrame.getContentPane().removeAll();

				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(new MainMenuPanel()));
			}
		});
		add(btnExit, gbc_btnExit);
	}

}
