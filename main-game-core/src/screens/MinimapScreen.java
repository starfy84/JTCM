package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

/**
 * This class displays an enlarged minimap </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Dereck's Time spent: 0:30 (Implemented music and route to settings) </br>
 * 
 * @version 0.4
 * @author Rohit & Dereck
 */
public class MinimapScreen extends Screen {

	Texture map,bI,bA;
	/**
	 * {@link MinimapScreen} constructor
	 * @param sm screen manager to determine current screen
	 * @param man asset manager to load images
	 */
	public MinimapScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		// TODO Auto-generated constructor stub
		map = man.get("map.png",Texture.class);
		bI = man.get("backIdle.png",Texture.class);
		bA = man.get("backActive.png",Texture.class);
		//cam.position.set(JTCM.WIDTH/2,JTCM.HEIGHT/2,0);
	}

	@Override
	public void getInput() {
		if(Gdx.input.justTouched()&&Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight())
			sm.pop();
	}

	@Override
	public void update(double t) {
		// TODO Auto-generated method stub
		getInput();

	}

	@Override
	public void render(SpriteBatch s) {
		// TODO Auto-generated method stub
		s.begin();
		s.draw(map, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
		s.draw(Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight()?bA:bI, 0, 0);
		SettingsScreen.applyBrightness(s);
		s.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
