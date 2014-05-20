package panels;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The class <code>BackgroundPanel</code> represents the background of the main
 * menu. It extends the class <tt>GraphicalPanel</tt> and contains one method
 * which paints the background image on the graphical panel.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class BackgroundPanel extends GraphicalPanel {

	private static final long serialVersionUID = 1L;
	Image bg = new ImageIcon(getClass().getResource("/images/background2.jpg"))
			.getImage();

	/**
	 * This method takes as input a graphics panel and paints the background on
	 * the input panel.
	 * 
	 * @param g
	 *            It takes as input the graphics panel to be painted on.
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}
