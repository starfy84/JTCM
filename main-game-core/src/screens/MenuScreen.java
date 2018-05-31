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
 * Date: 5/14/18 </br>
 * Time Spent: 15 mins </br>
 * @version 0.1
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
                menu = man.get("newMenu.png",Texture.class);
                lI = man.get("lvlslctIdle.jpg",Texture.class);
                lA = man.get("lvlslctActive.jpg",Texture.class);
                
                hI = man.get("highscoresIdle.jpg",Texture.class);
                hA = man.get("highscoresActive.jpg",Texture.class);
                
                iI = man.get("instructionsIdle.jpg",Texture.class);
                iA = man.get("instructionsActive.jpg",Texture.class);
                
                eI = man.get("exitIdle.jpg",Texture.class);
                eA = man.get("exitActive.jpg",Texture.class);
                
                sI = man.get("settingIdle.jpg",Texture.class);
                sA = man.get("settingActive.jpg",Texture.class);
                
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if (Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-lI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+lI.getWidth()/2 && Gdx.input.getY()<=Gdx.graphics.getHeight()-500 && Gdx.input.getY()>=Gdx.graphics.getHeight()-500-lI.getHeight())
			sm.set(new LevelSelectScreen(sm,man));
		else if (Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-hI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+hI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-400 && Gdx.input.getY()>=Gdx.graphics.getHeight()-400-hI.getHeight())
			sm.set(new HighScoresScreen(sm,man));
		else if (Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-iI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+iI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-300 && Gdx.input.getY()>=Gdx.graphics.getHeight()-300-iI.getHeight())
			sm.set(new InstructionsScreen(sm,man));
		else if (Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-eI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+eI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-200 && Gdx.input.getY()>=Gdx.graphics.getHeight()-200-eI.getHeight())
			Gdx.app.exit();
		else if (Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/4-sI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/4+sI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-100 && Gdx.input.getY()>=Gdx.graphics.getHeight()-100-sI.getHeight())
			sm.push(JTCM.getSettingsScreen());
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
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-lI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+lI.getWidth()/2 && Gdx.input.getY()<=Gdx.graphics.getHeight()-500 && Gdx.input.getY()>=Gdx.graphics.getHeight()-500-lI.getHeight()?lA:lI,JTCM.WIDTH/2-lI.getWidth()/2,500);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-hI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+hI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-400 && Gdx.input.getY()>=Gdx.graphics.getHeight()-400-hI.getHeight()?hA:hI,JTCM.WIDTH/2-hI.getWidth()/2,400);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-iI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+iI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-300 && Gdx.input.getY()>=Gdx.graphics.getHeight()-300-iI.getHeight()?iA:iI,JTCM.WIDTH/2-iI.getWidth()/2,300);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-eI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+eI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-200 && Gdx.input.getY()>=Gdx.graphics.getHeight()-200-eI.getHeight()?eA:eI,JTCM.WIDTH/2-eI.getWidth()/2,200);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/4-sI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/4+sI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-100 && Gdx.input.getY()>=Gdx.graphics.getHeight()-100-sI.getHeight()?sA:sI,JTCM.WIDTH/4-sI.getWidth()/2,100);
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
