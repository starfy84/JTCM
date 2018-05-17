/**
 * 
 */
package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

/**
 * This is the menu screen.</br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * 
 * @author Dereck
 */
public class MenuScreen extends Screen {

	private Texture menu;

	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
	public MenuScreen(ScreenManager sm) {
		super(sm);
		menu = new Texture("menu.png");
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if (Gdx.input.isKeyJustPressed(Keys.NUM_1))
			sm.set(new LevelSelectScreen(sm));
		else if (Gdx.input.isKeyJustPressed(Keys.NUM_2))
			sm.set(new HighScoresScreen(sm));
		else if (Gdx.input.isKeyJustPressed(Keys.NUM_3))
			sm.set(new InstructionsScreen(sm));
		else if (Gdx.input.isKeyJustPressed(Keys.NUM_4))
		{
			Gdx.app.exit();
		}
	}

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	@Override
	public void update(double t) {
		getInput();

	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		s.begin();
		s.draw(menu, 0, 0, JTCM.WIDTH, JTCM.HEIGHT);
		s.end();

	}

	/**
	 * This method disposes uneeded resources.
	 */
	@Override
	public void dispose() {
		menu.dispose();
	}

}
