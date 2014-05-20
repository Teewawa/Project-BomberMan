package panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;

import system.GameEngine;

/**
 * The class <code>HighscorePanel</code> represents the High scores list. It
 * extends the class <tt>GraphicalPanel</tt>. It contains one method which
 * creates the the high score list found when the user clicks on the panel.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class HighscorePanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This method creates a new High score panel and also takes the scores
	 * saved in the game and displays then when the panel is clicked.
	 */
	public HighscorePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
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
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 12;
		gbc_btnBack.anchor = GridBagConstraints.LAST_LINE_START;
		add(btnBack, gbc_btnBack);

		ArrayList<String> loadedScores = GameEngine.gs.getScores();

		Iterator<String> iterateScores = loadedScores.iterator();

		JLabel label = new JLabel();
		while (iterateScores.hasNext()) {
			String[] parsedScore = iterateScores.next().split("#");

			label = new JLabel(parsedScore[0] + "   " + parsedScore[1]);
			label.setHorizontalTextPosition(JLabel.CENTER);
			label.setFont(new Font("Serif", Font.PLAIN, 21));
			add(label);
		}

	}

}
