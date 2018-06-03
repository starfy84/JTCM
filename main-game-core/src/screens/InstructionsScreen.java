package screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import text.Text;
/**
 * This class shows an instructions screen ---UNIMPLEMENTED--- </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * @version 0.1
 * Time spent: 00:15 </br>
 * @author Dereck
 */
public class InstructionsScreen extends Screen {

	private Text text;
	
	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
        public InstructionsScreen(ScreenManager sm, AssetManager man) {
        	super(sm,man);
        	text = new Text();
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
		text.printText("This is some scrolling text :D", s, 500, 150);
		SettingsScreen.applyBrightness(s);
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
