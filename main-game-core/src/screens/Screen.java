/**
 * 
 */
package screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * This class contains a blueprint of a screen in our game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * 
 * @author Dereck
 */
public abstract class Screen {
	protected OrthographicCamera cam;
	protected Vector3 pointer;
	protected ScreenManager sm;

	/**
	 * {@link Screen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
	public Screen(ScreenManager sm) {
		this.sm = sm;
		cam = new OrthographicCamera();
		pointer = new Vector3();
	}

	/**
	 * This method handles input.
	 */
	public abstract void getInput();

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	public abstract void update(double t);

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	public abstract void render(SpriteBatch s);

	/**
	 * This method disposes uneeded resources.
	 */
	public abstract void dispose();
}
