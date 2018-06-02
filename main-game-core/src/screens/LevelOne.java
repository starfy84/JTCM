package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Color;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class LevelOne extends Screen {
/**
 * This class runs level one of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Dereck's Time spent: 1:30 (Implemented music and route to settings) </br>
 * 
 * @version 0.4
 * @author Rohit & Dereck
 */
	//Map, Minimap, Dot on the minimap to show where the user is, Health bars 1,2,3 and 4, Action bars 1,2,3 and 4.
	private Texture map, minimap, blackdot,bar,bar2,bar3,bar4,act1,act2,act3,act4;
	
	//Collision Picture
	private	BufferedImage collisionPic;
	
	//Player --PLEASE CHANGE SPRITE--
	private Sprite person;
	
	//Array for collision
	private Color[][] collisionArr;
	
	//X and Y of map
	private double xCoord = 0-JTCM.WIDTH*5/2.5, yCoord = 0-JTCM.HEIGHT*5/2.5;
	
	//Character's x and y on the array
	private double charX, charY;
	
	//Health values 1,2,3 and 4
	private float health,health2,health3,health4;
	
	//Song
	public static Music music;
	
	//Time for health depletion 
	private long currentT,lastT;
	
	//Debugging mode
	private final boolean DEBUG = true;
    /**
     * {@link LevelOne} constructor
     * @param sm screen manager to determine current screen
     * @param man asset manager to load images
     */
    public LevelOne(ScreenManager sm,AssetManager man) {
        super(sm, man);
        health = 1;
        health2 = 1;
        health3 = 1;
        health4 = 1;
        music = Gdx.audio.newMusic(Gdx.files.internal("BTS - DNA.mp3"));
        music.setVolume(SettingsScreen.sound);
        music.setLooping(true);
        music.play();
        map = man.get("map.png",Texture.class);
		minimap = man.get("map.png",Texture.class);
		blackdot = man.get("blackdot.png",Texture.class);
		bar = man.get("blank.jpg",Texture.class);
		bar2 = man.get("blank.jpg",Texture.class);
		bar3 = man.get("blank.jpg",Texture.class);
		bar4 = man.get("blank.jpg",Texture.class);
		act1 = man.get("settingIdle.jpg",Texture.class);
		act2 = man.get("settingIdle.jpg",Texture.class);
		act3 = man.get("settingIdle.jpg",Texture.class);
		act4 = man.get("settingIdle.jpg",Texture.class);
		try {
		    collisionPic = ImageIO.read(new File("collisionDetection.png"));
		} catch (IOException e) {
		}
		
		//Transpose map to an array of colours
		collisionArr = new Color [collisionPic.getHeight()][collisionPic.getWidth()];
		for (int row = 0; row < collisionPic.getHeight(); row++ ) {
			for (int col = 0; col < collisionPic.getWidth(); col++) {
				collisionArr[row][col]= new Color(collisionPic.getRGB(col, row));
			}
		}
		
		//Character is in the centre of the array
		charX = collisionPic.getWidth()/2;
		charY = collisionPic.getHeight()/2;
		
		person = new Sprite(man.get("person.png",Texture.class));
		
		//Scale character
		person.setSize(person.getWidth()*2, person.getHeight()*2);
		
		//Character is in the centre of the screen
		person.setPosition(JTCM.WIDTH/2-person.getWidth()/2, JTCM.HEIGHT/2);
		lastT = System.currentTimeMillis();
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput()  {
		
		//START OF INPUT FOR CHARACTER MOVEMENT
		if (Gdx.input.isKeyPressed(Keys.RIGHT) && (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX+0.5)].equals(Color.WHITE) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX+0.5)].equals(Color.WHITE))) {
			xCoord -= JTCM.WIDTH*5.0/map.getWidth()/2*5;
			charX += 0.5;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT) && (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX-2)].equals(Color.WHITE) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX-2)].equals(Color.WHITE))) {
			xCoord += JTCM.WIDTH*5.0/map.getWidth()/2*5;
			charX -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Keys.UP) && collisionArr[(int)Math.ceil(charY-0.5)][(int)Math.round(charX)].equals(Color.WHITE) && collisionArr[(int)Math.floor(charY)][(int)Math.round(charX-1.5)].equals(Color.WHITE)) {
			yCoord -= JTCM.HEIGHT*5.0/map.getHeight()/2*5;
			charY -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN) && collisionArr[(int)Math.floor(charY+0.5)][(int)Math.round(charX)].equals(Color.WHITE) && collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX-1.5)].equals(Color.WHITE)) {
			yCoord += JTCM.HEIGHT*5.0/map.getHeight()/2*5;
			charY += 0.5;
		}
		if(Gdx.input.justTouched()&& Gdx.input.getX()>=0 && Gdx.input.getX()<=(minimap.getWidth()/5)+5 && Gdx.input.getY()>=JTCM.HEIGHT-minimap.getHeight()/5 && Gdx.input.getY()<=JTCM.HEIGHT)
		{
			System.out.println(Gdx.input.getX()+">=0 && "+ Gdx.input.getX()+"<="+(minimap.getWidth()/5+5) +"&&"+ Gdx.input.getY()+"<="+minimap.getHeight()/5+"&&"+Gdx.input.getY()+">=0");
			System.out.println("hit!");
			sm.push(new MinimapScreen(sm,man));
		}
		//END OF INPUT FOR CHARACTER MOVEMENT
		
		//START OF INPUT FOR ACTION-BAR CLICKING
		if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act1.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-100 && Gdx.input.getY()>=JTCM.HEIGHT-100-act1.getHeight())
			health4 = Math.min(1, health4+0.05f);
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act2.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-200 && Gdx.input.getY()>=JTCM.HEIGHT-200-act2.getHeight())
			health3 = Math.min(1, health3+0.05f);
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act3.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-300 && Gdx.input.getY()>=JTCM.HEIGHT-300-act3.getHeight())
			health2 = Math.min(1, health2+0.05f);
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act4.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-400 && Gdx.input.getY()>=JTCM.HEIGHT-400-act4.getHeight())
			health = Math.min(1, health+0.05f);
		//END OF INPUT FOR ACTION-BAR CLICKING
		
		//START OF DEBUG TOOLS
		if(DEBUG) {
			//Brings health down
			if(Gdx.input.isKeyPressed(Keys.NUM_1))
				health = Math.max(0,health-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_2))
				health2 = Math.max(0,health2-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_3))
				health3 = Math.max(0,health3-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_4))
				health4 = Math.max(0,health4-0.01f);
			
			//Resets health
			if(Gdx.input.isKeyJustPressed(Keys.Q))
				health =1;
			if(Gdx.input.isKeyJustPressed(Keys.W))
				health2 =1;
			if(Gdx.input.isKeyJustPressed(Keys.E))
				health3 =1;
			if(Gdx.input.isKeyJustPressed(Keys.R))
				health4 =1;
			if(Gdx.input.isKeyJustPressed(Keys.X))
				sm.pop();
		}
		//END OF DEBUG TOOLS
	}

	/**
	 * Handles input for settings.
	 */
	public void checkSetting()
	{
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			JTCM.getSettingsScreen().setMusic(music); //Change music target
			sm.push(JTCM.getSettingsScreen());
		}
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
		checkSetting();
		
		currentT = System.currentTimeMillis();
		
		//Checks if 1 second has passed
		if(currentT-lastT>=1000)
		{
			float rate = getRate();
			health = Math.max(0,health-rate);
			health2 = Math.max(0,health2-rate);
			health3 = Math.max(0,health3-rate);
			health4 = Math.max(0,health4-rate);
			lastT = System.currentTimeMillis();
			System.out.println(rate);
		}
	}
	
	/**
	 * This method finds out how fast each bar should deplete
	 * @return rate
	 */
	private float getRate() {
		int countGreen=0,countYellow=0,countRed=0;
		float[] arr = {health,health2,health3,health4};
		for(float x : arr) {
			if (x>0.7f)
				countGreen++;
			else if (x>0.3f)
				countYellow++;
			else
				countRed++;
		}
		return (countGreen>0?0.01f:0)+ 0.015f*countYellow + 0.02f*countRed;
	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		s.begin(); //Begins sprite batch
		
		//Map
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		
		//Rectangular border
		s.draw(blackdot,0,0,minimap.getWidth()/5+5, minimap.getHeight()/5+5);
		
		//Minimap
		s.draw(minimap, 0, 0, minimap.getWidth()/5, minimap.getHeight()/5);
		
		//Dot on minimap
		s.draw(blackdot, (float)(charX-5),(float)(179-charY-5), 10, 10);
		
		//Person
		s.draw(person, person.getX(), person.getY(),person.getWidth(),person.getHeight());
		
		//Changes colours of health bar and draws bars 1,2,3 and 4
		s.setColor(health>0.7f?Color.GREEN:health>0.3f?Color.YELLOW:Color.RED);
		s.draw(bar, 10, JTCM.HEIGHT-20,JTCM.WIDTH/4* health,10);
		s.setColor(health2>0.7f?Color.GREEN:health2>0.3f?Color.YELLOW:Color.RED);
		s.draw(bar2, 10, JTCM.HEIGHT-35,JTCM.WIDTH/4*health2,10);
		s.setColor(health3>0.7f?Color.GREEN:health3>0.3f?Color.YELLOW:Color.RED);
		s.draw(bar3, 10, JTCM.HEIGHT-50,JTCM.WIDTH/4*health3,10);
		s.setColor(health4>0.7f?Color.GREEN:health4>0.3f?Color.YELLOW:Color.RED);
		s.draw(bar4, 10, JTCM.HEIGHT-65,JTCM.WIDTH/4*health4,10);
		
		//Reset tint
		s.setColor(Color.WHITE);
		
		//Action bars 1,2,3 and 4
		s.draw(act1,JTCM.WIDTH-act1.getWidth()-5,100);
		s.draw(act2,JTCM.WIDTH-act2.getWidth()-5,200);
		s.draw(act3,JTCM.WIDTH-act3.getWidth()-5,300);
		s.draw(act4,JTCM.WIDTH-act4.getWidth()-5,400);
		
		s.end(); //Ends sprite batch

	}

	/**
	 * This method disposes uneeded resources.
	 */
	@Override
	public void dispose() {
		music.dispose();
	}

}
