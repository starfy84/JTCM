package screens;

import org.w3c.dom.css.Rect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Disposable;
import com.main.game.JTCM;

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
	private Texture map, minimap;
	private	BufferedImage collisionPic;
	private Sprite person;
	private Color[][] collisionArr;
	
	private double xCoord = 0-JTCM.WIDTH*5/2.5, yCoord = 0-JTCM.HEIGHT*5/2.5;
	private double charX, charY;
	
	public LevelOne(ScreenManager sm) {
		super(sm);
		map = new Texture("map.png");
		minimap = new Texture("map.png");

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
		
		person = new Sprite(new Texture("person.png"));
		person.setSize(person.getWidth()*2, person.getHeight()*2);
		person.setPosition(JTCM.WIDTH/2-person.getWidth()/2, JTCM.HEIGHT/2);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput()  {
		if (Gdx.input.isKeyPressed(Keys.RIGHT) && collisionArr[(int)Math.round(charY)][(int)Math.round(charX+1)].equals(Color.WHITE)) {
			xCoord -= JTCM.WIDTH*5.0/map.getWidth();
			charX += 1;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT) && collisionArr[(int)Math.round(charY)][(int)Math.round(charX-2)].equals(Color.WHITE)) {
			xCoord += JTCM.WIDTH*5.0/map.getWidth();
			charX -= 1;
		}
		if (Gdx.input.isKeyPressed(Keys.UP) && collisionArr[(int)Math.round(charY-1)][(int)Math.round(charX)].equals(Color.WHITE)) {
			yCoord -= JTCM.HEIGHT*5.0/map.getHeight();
			charY -= 1;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN) && collisionArr[(int)Math.round(charY+1)][(int)Math.round(charX)].equals(Color.WHITE)) {
			yCoord += JTCM.HEIGHT*5.0/map.getHeight();
			charY += 1;
		}
	}

	@Override
	public void update(double t) {
		getInput();
	}

	@Override
	public void render(SpriteBatch s) {
		s.begin();
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		s.draw(person, person.getX(), person.getY(),person.getWidth(),person.getHeight());
		s.end();

	}

	@Override
	public void dispose() {
		map.dispose();
	}

}
