package panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * The class <code>GraphicalPanel</code> represents the basic panel used by the
 * other classes in the <tt>Panels</tt> package. It contains one class which
 * creates a new grid to be used by the other classes of this package.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class GraphicalPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * The method <tt>GraphicalPanel</tt> is the superclass of every panel
	 * subclass. It provides a basic constructor with a Layout Manager
	 * definition.
	 */
	public GraphicalPanel() {
		super(new GridLayout(1, 1));
	}

}
