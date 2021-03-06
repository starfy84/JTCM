package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;
import com.badlogic.gdx.assets.AssetManager;
/**
 * This is the level selection screen which lets the user choose which level to play </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/8/18 </br>
 * Dereck's Time spent: 0:30 </br>
 * @version 1
 * @author Dereck 
 */
public class LevelSelectScreen extends Screen {
	Texture bg,tutI,oneI,twoI,threeI,tutA,oneA,twoA,threeA,bI,bA;

	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
        public LevelSelectScreen(ScreenManager sm, AssetManager man) {
                super(sm,man);
                
                bg = man.get("ChallengerBackground.jpg",Texture.class);
                tutI = man.get("TutIdle.png",Texture.class);
                tutA = man.get("TutActive.png",Texture.class);
                oneI = man.get("lvl1Idle.png",Texture.class);
                oneA = man.get("lvl1Active.png",Texture.class);
                twoI = man.get("lvl2Idle.png",Texture.class);
                twoA = man.get("lvl2Active.png",Texture.class);
                threeI = man.get("lvl3Idle.png",Texture.class);
                threeA = man.get("lvl3Active.png",Texture.class);
        		bI = man.get("backWIdle.png",Texture.class);
        		bA = man.get("backWActive.png",Texture.class);
		
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if(Gdx.input.justTouched()&& Gdx.input.getX()>=JTCM.WIDTH/2-tutI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+tutI.getWidth()/2 && Gdx.input.getY()<=Gdx.graphics.getHeight()-500 && Gdx.input.getY()>=Gdx.graphics.getHeight()-500-tutI.getHeight())
		{
			MenuScreen.music.stop();
			sm.set(new Cutscene(sm,man));
			sm.push(new NameScreen(sm,man));
		}
		else if(Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-oneI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+oneI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-400 && Gdx.input.getY()>=Gdx.graphics.getHeight()-400-oneI.getHeight())
		{
			MenuScreen.music.stop();
			sm.set(new LevelOne(sm,man));
			sm.push(new NameScreen(sm,man));
		}
		else if(Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-twoI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+twoI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-300 && Gdx.input.getY()>=Gdx.graphics.getHeight()-300-twoI.getHeight())
		{
			MenuScreen.music.stop();
			sm.set(new LevelTwo(sm,man));
			sm.push(new NameScreen(sm,man));
		}
		else if(Gdx.input.justTouched()&& Gdx.input.getX()>=JTCM.WIDTH/2-threeI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+threeI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-200 && Gdx.input.getY()>=Gdx.graphics.getHeight()-200-threeI.getHeight())
		{
			MenuScreen.music.stop();
			sm.set(new LevelThree(sm,man));
			sm.push(new NameScreen(sm,man));
		}
		if(Gdx.input.justTouched()&&Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight())
			sm.pop();
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
		s.draw(bg, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-tutI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+tutI.getWidth()/2 && Gdx.input.getY()<=Gdx.graphics.getHeight()-500 && Gdx.input.getY()>=Gdx.graphics.getHeight()-500-tutI.getHeight()?tutA:tutI,JTCM.WIDTH/2-tutI.getWidth()/2,500);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-oneI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+oneI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-400 && Gdx.input.getY()>=Gdx.graphics.getHeight()-400-oneI.getHeight()?oneA:oneI,JTCM.WIDTH/2-oneI.getWidth()/2,400);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-twoI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+twoI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-300 && Gdx.input.getY()>=Gdx.graphics.getHeight()-300-twoI.getHeight()?twoA:twoI,JTCM.WIDTH/2-twoI.getWidth()/2,300);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-threeI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+threeI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-200 && Gdx.input.getY()>=Gdx.graphics.getHeight()-200-threeI.getHeight()?threeA:threeI,JTCM.WIDTH/2-threeI.getWidth()/2,200);
		s.draw(Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight()?bA:bI, 0, 0);

		SettingsScreen.applyBrightness(s);
		s.end();

	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
	}

}
