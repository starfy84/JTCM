/**
 * 
 */
package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;
import com.badlogic.gdx.assets.AssetManager;
/**
 * This is the menu screen.</br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Time Spent: 2:30 (Listened for mouse button clicks and positions. Added active and idle states for each button.) </br>
 * @version 0.4
 * @author Dereck
 */
public class MenuScreen extends Screen {

	private Texture menu,lI,lA,hI,hA,iI,iA,eI,eA,sI,sA;
	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
        public MenuScreen(ScreenManager sm, AssetManager man) {
                super(sm,man);
                menu = man.get("ChallengerBackground.jpg",Texture.class);
                lI = man.get("lvlslctIdle.png",Texture.class);
                lA = man.get("lvlslctActive.png",Texture.class);
                
                hI = man.get("highscoresIdle.png",Texture.class);
                hA = man.get("highscoresActive.png",Texture.class);
                
                iI = man.get("instructionsIdle.png",Texture.class);
                iA = man.get("instructionsActive.png",Texture.class);
                
                eI = man.get("exitIdle.png",Texture.class);
                eA = man.get("exitActive.png",Texture.class);
                
                sI = man.get("settingIdle.png",Texture.class);
                sA = man.get("settingActive.png",Texture.class);
                
                
                
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-lI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+lI.getWidth()/2 && Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-300)&& Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-300)-lI.getHeight())
			sm.push(new LevelSelectScreen(sm,man));
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-hI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+hI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-400) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-400)-hI.getHeight())
			sm.push(new HighScoresScreen(sm,man));
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-iI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+iI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-500) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-500)-iI.getHeight())
			sm.push(new InstructionsScreen(sm,man));
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-eI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+eI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-600) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-600)-eI.getHeight())
			Gdx.app.exit();
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH/4-sI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/4+sI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-700) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-700)-sI.getHeight())
			sm.push(JTCM.getSettingsScreen());
		else if (Gdx.input.isKeyJustPressed(Keys.K))
			sm.push(new NameScreen(sm,man));
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
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-lI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+lI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-300)&& Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-300)-lI.getHeight()?lA:lI,JTCM.WIDTH/2-lI.getWidth()/2,JTCM.HEIGHT-300);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-hI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+hI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-400) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-400)-hI.getHeight()?hA:hI,JTCM.WIDTH/2-hI.getWidth()/2,JTCM.HEIGHT-400);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-iI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+iI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-500) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-500)-iI.getHeight()?iA:iI,JTCM.WIDTH/2-iI.getWidth()/2,JTCM.HEIGHT-500);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-eI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+eI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-600) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-600)-eI.getHeight()?eA:eI,JTCM.WIDTH/2-eI.getWidth()/2,JTCM.HEIGHT-600);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/4-sI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/4+sI.getWidth()/2&& Gdx.input.getY()<=JTCM.HEIGHT-(JTCM.HEIGHT-700) && Gdx.input.getY()>=JTCM.HEIGHT-(JTCM.HEIGHT-700)-sI.getHeight()?sA:sI,JTCM.WIDTH/4-sI.getWidth()/2,JTCM.HEIGHT-700);
		SettingsScreen.applyBrightness(s);
		s.end();

	}

	/**
	 * This method disposes uneeded resources.
	 */
	@Override
	public void dispose() {
		menu.dispose();
		lI.dispose();
		lA.dispose();
		hI.dispose();
		hA.dispose();
		iI.dispose();
		iA.dispose();
		eI.dispose();
		eA.dispose();
		sI.dispose();
		sA.dispose();
	}

}
