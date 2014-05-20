package system;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The class <code>Sound</code> represents the sounds of the Bomberman Game. It
 * contains one method which reads a filepath and tries to play a sound from
 * that specific file path.
 * 
 * @author Brett Leighton, Leotard Niyonkuru, Marc-Andre Cataford, Mete
 *         Kemertas, Martin Zhang
 * @version 1.0
 * 
 */
public class Sound {
	/**
	 * This method takes in a file path and a boolean loop. The filepath
	 * indicates the sound to be played and the boolean loop determines whether
	 * to continuously play the sound or not. It also catches an exception in
	 * case the file does not work.
	 * 
	 * @param filepath
	 *            the sound file to be played
	 * @param loop
	 *            whether to loop or not
	 */

	public void playSound(String filepath, boolean loop) {

		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(getClass().getResource(filepath));

			clip.open(inputStream);
			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
