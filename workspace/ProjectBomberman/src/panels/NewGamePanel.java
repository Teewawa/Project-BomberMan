package panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.GameEngine;

/**
 * The class <code>NewGamePanel</code> represents the new game menu. It extends
 * the class <tt>GraphicalPanel</tt>. It contains one method which creates the
 * three options found in the new game menu, 1Player, 2Player, and the back
 * button.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class NewGamePanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This method creates the three panels found in the new game menu. It
	 * creates a 1 player panel, a 2 player panel, and a back panel to bring the
	 * user back to the main menu. It also brings the user to the difficulty
	 * panel if the 1 player panel or 2 player panel is pressed.
	 */
	public NewGamePanel() {

		this.setSize(544, 460);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setOpaque(false);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.mainFrame.getContentPane().removeAll();

				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(new MainMenuPanel()));
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 12;
		gbc_btnBack.anchor = GridBagConstraints.LAST_LINE_START;
		add(btnBack, gbc_btnBack);

		JButton btnSinglePlayer = new JButton("1 Player");
		btnSinglePlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.gs.setOnePlayer(true);

				GameEngine.mainFrame.getContentPane().removeAll();

				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(new DifficultyPanel(true)));
			}
		});
		// btnSinglePlayer.setBackground(new Color(204, 51, 0));
		// btnSinglePlayer.setForeground(new Color(51, 204, 204));
		GridBagConstraints gbc_btnSinglePlayer = new GridBagConstraints();
		gbc_btnSinglePlayer.anchor = GridBagConstraints.CENTER;
		gbc_btnSinglePlayer.insets = new Insets(0, 0, 5, 5);
		gbc_btnSinglePlayer.gridx = 0;
		gbc_btnSinglePlayer.gridy = 0;
		gbc_btnSinglePlayer.anchor = GridBagConstraints.FIRST_LINE_START;

		add(btnSinglePlayer, gbc_btnSinglePlayer);

		JButton btnMultiPlayer = new JButton("2 Player");
		btnMultiPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				GameEngine.gs.setOnePlayer(false);

				GameEngine.mainFrame.getContentPane().removeAll();

				GameEngine.addPanel(GameEngine.mainFrame,
						GameEngine.generatePanel(new DifficultyPanel(false)));
			}
		});
		// btnMultiPlayer.setForeground(new Color(0, 204, 204));
		// btnMultiPlayer.setBackground(new Color(204, 51, 0));
		GridBagConstraints gbc_btnMultiPlayer = new GridBagConstraints();
		gbc_btnMultiPlayer.anchor = GridBagConstraints.CENTER;
		gbc_btnMultiPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_btnMultiPlayer.gridx = 1;
		gbc_btnMultiPlayer.gridy = 0;
		gbc_btnMultiPlayer.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnMultiPlayer, gbc_btnMultiPlayer);
		this.setVisible(true);
	}

}
