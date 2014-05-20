package panels;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The class <code>InstructionsBg</code> represents the instructions menu of the
 * game. It extends class <tt>GraphicalPanel</tt>.It takes the instructions
 * image and paints it on a background. It is fused with the
 * <tt>InstructionsPanel</tt> to create the entire instructions panel.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 */
public class InstructionsBg extends GraphicalPanel {
	private static final long serialVersionUID = 1L;
	Image ibg = new ImageIcon(getClass()
			.getResource("/images/instructions.jpg")).getImage();

	/**
	 * This method takes as input a graphics panel and paints the instructions
	 * background on the input panel.
	 * 
	 * @param g
	 *            It takes as input the graphics panel to be painted on.
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(ibg, 0, 0, getWidth(), getHeight(), this);
	}
}
