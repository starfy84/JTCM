package screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tutorial extends Screen {
	BitmapFont font;

	public Tutorial(ScreenManager sm) {
		super(sm);
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(2);
	}

	@Override
	public void getInput() {

	}

	@Override
	public void update(double t) {

	}

	@Override
	public void render(SpriteBatch s) {

		s.begin();
		font.draw(s, "Tutorial", 50, 50);
		s.end();
	}

	@Override
	public void dispose() {

		font.dispose();
		
	}

}
