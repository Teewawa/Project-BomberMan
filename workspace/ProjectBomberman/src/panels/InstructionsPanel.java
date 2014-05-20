package panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.GameEngine;

/**
 * The class <code>InstructionsPanel</code> represents the instructions menu of
 * the game. It extends class <tt>GraphicalPanel</tt>.It creates a back button
 * for the instructions panel. This class is merged with the
 * <tt>InstructionsBg</tt> to create the entire instructions panel.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 */
public class InstructionsPanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This method creates the back button with all its functionalities to the
	 * instructions panel
	 */
	public InstructionsPanel() {
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

	}

	/**
	 * This method combines the back button created in the
	 * <tt>InstructionsPanel</tt> method with the image created in the
	 * <tt>InstructionsBg</tt> Class.
	 * 
	 * @param ip
	 *            the instructions panel on which the <tt>InstructionsBg</tt> is
	 *            added.
	 */
	public void addBackground(InstructionsPanel ip) {
		GraphicalPanel InstructionsBg = new InstructionsBg();
		InstructionsBg.setLayout(new BorderLayout());
		InstructionsBg.add(ip, BorderLayout.CENTER);
	}
}
