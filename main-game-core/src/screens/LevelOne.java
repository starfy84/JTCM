package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.awt.Color;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class LevelOne extends Screen {
/**
 * This class runs level one of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/18/18 </br>
 * Rohit's Time spent: 10:00 (finally successfully implemented unit collision)
 * @version 0.3
 * @author Rohit
 */
	private Texture map, minimap, blackdot;
	private	BufferedImage collisionPic;
	private Sprite person;
	private Color[][] collisionArr;
	
	private double xCoord = 0-JTCM.WIDTH*5/2.5, yCoord = 0-JTCM.HEIGHT*5/2.5;
	private double charX, charY;
	
	public static Music music;
    public LevelOne(ScreenManager sm,AssetManager man) {
        super(sm, man);
        music = Gdx.audio.newMusic(Gdx.files.internal("BTS - DNA.mp3"));
        music.setVolume(SettingsScreen.sound);
        music.setLooping(true);
        music.play();
        map = man.get("map.png",Texture.class);
		minimap = man.get("map.png",Texture.class);
		blackdot = man.get("blackdot.png",Texture.class);

		try {
		    collisionPic = ImageIO.read(new File("collisionDetection.png"));
		} catch (IOException e) {
		}
		collisionArr = new Color [collisionPic.getHeight()][collisionPic.getWidth()];
		for (int row = 0; row < collisionPic.getHeight(); row++ ) {
			for (int col = 0; col < collisionPic.getWidth(); col++) {
				collisionArr[row][col]= new Color(collisionPic.getRGB(col, row));
			}
		}
		charX = collisionPic.getWidth()/2;
		charY = collisionPic.getHeight()/2;
		
		person = new Sprite(man.get("person.png",Texture.class));
		person.setSize(person.getWidth()*2, person.getHeight()*2);
		person.setPosition(JTCM.WIDTH/2-person.getWidth()/2, JTCM.HEIGHT/2);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput()  {
		if (Gdx.input.isKeyPressed(Keys.RIGHT) && (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX+0.5)].equals(Color.WHITE) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX+0.5)].equals(Color.WHITE))) {
			xCoord -= JTCM.WIDTH*5.0/map.getWidth()/2;
			charX += 0.5;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT) && (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX-2)].equals(Color.WHITE) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX-2)].equals(Color.WHITE))) {
			xCoord += JTCM.WIDTH*5.0/map.getWidth()/2;
			charX -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Keys.UP) && collisionArr[(int)Math.ceil(charY-0.5)][(int)Math.round(charX)].equals(Color.WHITE) && collisionArr[(int)Math.floor(charY)][(int)Math.round(charX-1.5)].equals(Color.WHITE)) {
			yCoord -= JTCM.HEIGHT*5.0/map.getHeight()/2;
			charY -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN) && collisionArr[(int)Math.floor(charY+0.5)][(int)Math.round(charX)].equals(Color.WHITE) && collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX-1.5)].equals(Color.WHITE)) {
			yCoord += JTCM.HEIGHT*5.0/map.getHeight()/2;
			charY += 0.5;
		}
		if(Gdx.input.isTouched()&& Gdx.input.getX()>=0 && Gdx.input.getX()<=minimap.getWidth()+5 && Gdx.input.getY()<=JTCM.HEIGHT && Gdx.input.getY()>=(JTCM.HEIGHT-minimap.getHeight()+5))
		{
			//Open minimap screen
		}
		if(Gdx.input.isKeyJustPressed(Keys.X))
			sm.pop();
	}

	public void checkSetting()
	{
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			JTCM.getSettingsScreen().setMusic(music);
			sm.push(JTCM.getSettingsScreen());
		}
	}
	@Override
	public void update(double t) {
		getInput();
		checkSetting();
	}

	@Override
	public void render(SpriteBatch s) {
		s.begin();
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		s.draw(blackdot,0,0,minimap.getWidth()+5,minimap.getHeight()+5);
		s.draw(minimap, 0, 0);
		s.draw(blackdot, (float)(charX-5),(float)(179-charY-5), 10, 10);
		s.draw(person, person.getX(), person.getY(),person.getWidth(),person.getHeight());
		s.end();

	}

	@Override
	public void dispose() {
//		map.dispose();
		music.dispose();
	}

}
