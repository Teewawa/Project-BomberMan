package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.GameEngine;

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
public class MainMenuPanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This method creates the five different panels available in the main menu
	 * of the bomberman game. It creates a new game panel, a load game panel, an
	 * instructions panel, a High Scores panel, and an exit panel.
	 */
	public MainMenuPanel() {
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

		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.mainFrame.getContentPane().removeAll();

				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(new NewGamePanel()));
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Load Game");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameEngine.loadGame();
			}
		});
		add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Key Bindings");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 4;
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.mainFrame.getContentPane().removeAll();

				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generateInstPanel(new InstructionsPanel()));
			}
		});
		add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton_3 = new JButton("High Scores");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 5;
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				GameEngine.mainFrame.getContentPane().removeAll();
				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(new HighscorePanel()));

				GameEngine.mainFrame
						.add(GameEngine.gamePanel = new HighscorePanel());

				GameEngine.mainFrame.requestFocus();
				GameEngine.mainFrame.setVisible(true);

			}
		});
		add(btnNewButton_3, gbc_btnNewButton_3);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 1;
		gbc_btnExit.gridy = 6;
		add(btnExit, gbc_btnExit);

	}

}
