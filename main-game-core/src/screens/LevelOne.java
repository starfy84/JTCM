package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

public class LevelOne extends Screen {
	BitmapFont font;
	Texture map, person;
	double xCoord = -2500, yCoord = -700;
	public LevelOne(ScreenManager sm) {
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
		s.draw(person, JTCM.WIDTH/2-90, JTCM.HEIGHT/2-100, person.getWidth()*5, person.getHeight()*5);
		s.end();

	}

	@Override
	public void dispose() {
		map.dispose();
		person.dispose();
	}

}
