package system;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * The class <code>MapObject</code> represents all objects on the map. It
 * contains eleven methods and sets the position of the objects on the map as
 * well as load their respective images.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public abstract class MapObject {

	private int xPos;
	private int yPos;
	private Image image;
	private boolean isDead;

	/**
	 * Sets the map objects position and sets the object to alive.
	 * 
	 * @param x
	 *            the x co-ordinate of the object.
	 * @param y
	 *            the y co-ordinate of the object.
	 */
	public MapObject(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		this.isDead = false;
	}

	/**
	 * Returns the x position of the map object.
	 * 
	 * @return Returns the x co-ordinate.
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * Sets the x position of the object.
	 * 
	 * @param xPos
	 *            Takes as input an integer representing the x co-ordinate.
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Returns the y position of the object.
	 * 
	 * @return Returns the y co-ordinate.
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * Sets the y position of the object.
	 * 
	 * @param yPos
	 *            Takes as input an integer representing the y co-ordinate of
	 *            the object.
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * Sets the co-ordinates of the object.
	 * 
	 * @param xPos
	 *            Takes as input the x co-ordinate.
	 * @param yPos
	 *            Takes as input the y co-ordinate.
	 */
	public void setCoordinates(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Returns the image of the object.
	 * 
	 * @return Returns the image of the object.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets the image of the object.
	 * 
	 * @param image
	 *            Takes as input the image of the map object.
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Loads the image of the object from a path file.
	 * 
	 * @param url
	 *            The path where the image is located.
	 * @return Returns the image that is loaded.
	 */
	public Image loadImage(URL url) {
		return new ImageIcon(url).getImage();
	}

	/**
	 * Returns whether the object is dead or not.
	 * 
	 * @return Returns true if dead, false if not.
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * Sets the object's state according to the <tt>isDead</tt> method.
	 * 
	 * @param isDead
	 *            Takes as input the objects state.
	 */
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

}
