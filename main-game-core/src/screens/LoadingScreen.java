package screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;
/**
 * This class displays the loading screen --UNIMPLEMENTED-- </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Time spent: 0:15 </br>
 * @version 0.4
 * @author Dereck
 */
public class LoadingScreen extends Screen {

	private Texture load;
	/**
	 * {@link LoadingScreen} constructor
	 * @param sm screen manager to determine current screen
	 * @param man asset manager to load images
	 */
	public LoadingScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		load = man.get("loading.png",Texture.class);
		System.out.println("ping");
		
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		// TODO Auto-generated method stub

	}

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	@Override
	public void update(double t) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		// TODO Auto-generated method stub
		System.out.println("pong");
		s.begin();
		s.draw(load, 0,0,JTCM.WIDTH,JTCM.HEIGHT);
		s.end();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        sm.pop();
	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("done");
        load.dispose();
	}

}
