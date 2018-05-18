package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

/**
 * This is the main class of our game, it starts the Main Menu screen. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * 
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
	public LevelSelectScreen(ScreenManager sm) {
		super(sm);
		bg = new Texture("lvlslct.jpg");
		tutI = new Texture("TutIdle.jpg");
		tutA = new Texture("TutActive.jpg");
		oneI = new Texture("lvl1Idle.jpg");
		oneA = new Texture("lvl1Active.jpg");
		twoI = new Texture("lvl2Idle.jpg");
		twoA = new Texture("lvl2Active.jpg");
		threeI = new Texture("lvl3Idle.jpg");
		threeA = new Texture("lvl3Active.jpg");
		bonusI = new Texture("bonusIdle.jpg");
		bonusA = new Texture("bonusActive.jpg");
		
		
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if(Gdx.input.isTouched()&& Gdx.input.getX()>=JTCM.WIDTH/2-tutI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+tutI.getWidth()/2 && Gdx.input.getY()<=Gdx.graphics.getHeight()-500 && Gdx.input.getY()>=Gdx.graphics.getHeight()-500-tutI.getHeight())
			sm.set(new Tutorial(sm));
		else if(Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-oneI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+oneI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-400 && Gdx.input.getY()>=Gdx.graphics.getHeight()-400-oneI.getHeight())
			sm.set(new LevelOne(sm));
		else if(Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-twoI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+twoI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-300 && Gdx.input.getY()>=Gdx.graphics.getHeight()-300-twoI.getHeight())
			sm.set(new LevelTwo(sm));
		else if(Gdx.input.isTouched()&& Gdx.input.getX()>=JTCM.WIDTH/2-threeI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+threeI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-200 && Gdx.input.getY()>=Gdx.graphics.getHeight()-200-threeI.getHeight())
			sm.set(new LevelThree(sm));
		else if(Gdx.input.isTouched()&&Gdx.input.getX()>=JTCM.WIDTH/2-bonusI.getWidth()/2 &&Gdx.input.getX()<=JTCM.WIDTH/2+bonusI.getWidth()/2&& Gdx.input.getY()<=Gdx.graphics.getHeight()-100 && Gdx.input.getY()>=Gdx.graphics.getHeight()-100-bonusI.getHeight())
			sm.set(new BonusLevel(sm));
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
	public void render(SpriteBatch s) {
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
