package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.main.game.JTCM;
import com.main.game.ShaderBatch;
import com.badlogic.gdx.assets.AssetManager;
/**
 * This is the main class of our game, it starts the Main Menu screen. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * Dereck's Time spent: 1:00 </br>
 * @version 0.2
 * @author Dereck 
 */
public class LevelSelectScreen extends Screen {
	Texture bg,tutI,oneI,twoI,threeI,bonusI,tutA,oneA,twoA,threeA,bonusA;

	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
        public LevelSelectScreen(ScreenManager sm, AssetManager man) {
                super(sm,man);
                bg = man.get("lvlslct.jpg",Texture.class);
                tutI = man.get("TutIdle.jpg",Texture.class);
                tutA = man.get("TutActive.jpg",Texture.class);
                oneI = man.get("lvl1Idle.jpg",Texture.class);
                oneA = man.get("lvl1Active.jpg",Texture.class);
                twoI = man.get("lvl2Idle.jpg",Texture.class);
                twoA = man.get("lvl2Active.jpg",Texture.class);
                threeI = man.get("lvl3Idle.jpg",Texture.class);
                threeA = man.get("lvl3Active.jpg",Texture.class);
                bonusI = man.get("bonusIdle.jpg",Texture.class);
                bonusA = man.get("bonusActive.jpg",Texture.class);
		
		
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if(Gdx.input.isTouched()&& Gdx.input.getX()>=JTCM.WIDTH/2-tutI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+tutI.getWidth()/2 && Gdx.input.getY()<=Gdx.graphics.getHeight()-500 && Gdx.input.getY()>=Gdx.graphics.getHeight()-500-tutI.getHeight())
			sm.set(new Tutorial(sm,man));
		else if(Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-oneI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+oneI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-400 && Gdx.input.getY()>=Gdx.graphics.getHeight()-400-oneI.getHeight())
			sm.set(new LevelOne(sm,man));
		else if(Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-twoI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+twoI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-300 && Gdx.input.getY()>=Gdx.graphics.getHeight()-300-twoI.getHeight())
			sm.set(new LevelTwo(sm,man));
		else if(Gdx.input.isTouched()&& Gdx.input.getX()>=JTCM.WIDTH/2-threeI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+threeI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-200 && Gdx.input.getY()>=Gdx.graphics.getHeight()-200-threeI.getHeight())
			sm.set(new LevelThree(sm,man));
		else if(Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-bonusI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+bonusI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-100 && Gdx.input.getY()>=Gdx.graphics.getHeight()-100-bonusI.getHeight())
			sm.set(new BonusLevel(sm,man));
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
		System.out.printf("x: %d\ny: %d\n\n",Gdx.input.getX(),Gdx.input.getY());
	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(ShaderBatch s) {
		s.begin();
		s.draw(bg, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-tutI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+tutI.getWidth()/2 && Gdx.input.getY()<=Gdx.graphics.getHeight()-500 && Gdx.input.getY()>=Gdx.graphics.getHeight()-500-tutI.getHeight()?tutA:tutI,JTCM.WIDTH/2-tutI.getWidth()/2,500);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-oneI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+oneI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-400 && Gdx.input.getY()>=Gdx.graphics.getHeight()-400-oneI.getHeight()?oneA:oneI,JTCM.WIDTH/2-oneI.getWidth()/2,400);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-twoI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+twoI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-300 && Gdx.input.getY()>=Gdx.graphics.getHeight()-300-twoI.getHeight()?twoA:twoI,JTCM.WIDTH/2-twoI.getWidth()/2,300);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-threeI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+threeI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-200 && Gdx.input.getY()>=Gdx.graphics.getHeight()-200-threeI.getHeight()?threeA:threeI,JTCM.WIDTH/2-threeI.getWidth()/2,200);
		s.draw(Gdx.input.getX()>=JTCM.WIDTH/2-bonusI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+bonusI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-100 && Gdx.input.getY()>=Gdx.graphics.getHeight()-100-bonusI.getHeight()?bonusA:bonusI,JTCM.WIDTH/2-bonusI.getWidth()/2,100);
		s.end();

	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		bg.dispose();
		tutI.dispose();
		tutA.dispose();
		oneI.dispose();
		oneA.dispose();
		twoI.dispose();
		twoA.dispose();
		threeI.dispose();
		threeA.dispose();
		bonusI.dispose();
		bonusA.dispose();
	}

}
