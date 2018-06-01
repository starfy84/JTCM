package screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
/**
 * This class shows an instructions screen ---UNIMPLEMENTED--- </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * @version 0.1
 * Time spent: 00:15 </br>
 * @author Dereck
 */
public class InstructionsScreen extends Screen {

	BitmapFont font;

	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
        public InstructionsScreen(ScreenManager sm, AssetManager man) {
                super(sm,man);
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(2);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {

	}

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	@Override
	public void update(double t) {

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
		font.draw(s, "Intructions", 500, 50);
		s.end();
	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
//		font.dispose();
	}

}
