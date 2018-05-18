package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

/**
 * This class runs level three of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/18/18 </br>
 * Time spent: 0:15 (Reused from levelOne)
 * @author Rohit
 */

public class LevelThree extends Screen {
	BitmapFont font;
	Texture map, person;
	double xCoord = -2500, yCoord = -700;
	public LevelThree(ScreenManager sm) {
		super(sm);
		map = new Texture("map.png");
		person = new Texture("person.png");
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
		else if (Gdx.input.isKeyPressed(Keys.DOWN))
		{
			yCoord += 3.5;
		}
	}

	@Override
	public void update(double t) {
		getInput();

	}

	@Override
	public void render(SpriteBatch s) {
		s.begin();
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		s.draw(person, JTCM.WIDTH/2-90, JTCM.HEIGHT/2-100, person.getWidth()*2, person.getHeight()*2);
		s.end();

	}

	@Override
	public void dispose() {
		map.dispose();
		person.dispose();
	}

}