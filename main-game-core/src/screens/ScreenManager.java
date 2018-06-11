package screens;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is the main class of our game, it starts the Main Menu screen. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/8/18 </br>
 * @version 1
 * @author Dereck
 */
public class ScreenManager {
	
	//Stack of screens that can be manipulated (removed, added)
	private Stack<Screen> screens;

	/**
	 * {@link ScreenManager} Constructor
	 */
	public ScreenManager() {
		screens = new Stack<Screen>();
	}

	/**
	 * @return Our screens stack
	 */
	public Stack<Screen> getScreens() {
		return screens;
	}

	/**
	 * This method pops and disposes a screen.
	 */
	public void pop() {
		screens.pop().dispose();
	}

	/**
	 * This method pushes a screen to the stack.
	 * 
	 * @param scr
	 *            Screen.
	 */
	public void push(Screen scr) {
		screens.push(scr);
	}

	/**
	 * This method replaces a screen with another screen.
	 * 
	 * @param scr
	 *            Screen.
	 */
	public void set(Screen scr) {
		pop();
		push(scr);
	}

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	public void update(double t) {
		screens.peek().update(t);
	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	public void render(SpriteBatch s) {
		screens.peek().render(s);
	}
}
