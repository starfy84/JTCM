package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;
import com.badlogic.gdx.assets.AssetManager;

/**
 * This class runs the tutorial of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/18/18 </br>
 * Time spent: 0:15 (Reused from levelOne)
 * 
 * @version 0.1
 * @author Rohit
 */

public class Tutorial extends Screen {
	BitmapFont font;
	Texture map, person;
	double xCoord = -2500, yCoord = -700;

	/**
	 * {@link Tutorial} constructor
	 * @param sm screen manager to determine current screen
	 * @param man asset manager to load images
	 */
	public Tutorial(ScreenManager sm, AssetManager man) {
		super(sm, man);
		map = man.get("map.png", Texture.class);
		person = man.get("person.png", Texture.class);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			xCoord -= 3.5;
		else if (Gdx.input.isKeyPressed(Keys.LEFT))
			xCoord += 3.5;
		else if (Gdx.input.isKeyPressed(Keys.UP))
			yCoord -= 3.5;
		else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			yCoord += 3.5;
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
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH * 5, JTCM.HEIGHT * 5);
		s.draw(person, JTCM.WIDTH / 2 - 90, JTCM.HEIGHT / 2 - 100, person.getWidth() * 2, person.getHeight() * 2);
		s.end();

	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		// map.dispose();
		// person.dispose();
	}

}
