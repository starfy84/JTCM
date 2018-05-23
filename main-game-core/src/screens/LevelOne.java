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
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class LevelOne extends Screen {
/**
 * This class runs level one of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/18/18 </br>
 * Rohit's Time spent: 4:00 (including associated graphics)</br>
 * Dereck's Time spent: 6:00 (Attempted unit collision)</br>
 * @version 0.3
 * @author Rohit
 */
	private Texture map;
	private Sprite person/*,detection,box*/;
	private double xCoord = -2500, yCoord = -700;
	private	BufferedImage img;
	private Color[][] collisionArr;
	
	public LevelOne(ScreenManager sm) {
		super(sm);
		map = new Texture("map.png");
		
		try {
		    img = ImageIO.read(new File("collisionDetection.png"));
		} catch (IOException e) {
		}
		
		collisionArr = new Color[img.getHeight()][img.getWidth()];
		for (int row = 0; row < img.getHeight(); row++) {
			for (int col = 0; col < img.getWidth(); col++) {
				collisionArr[row][col] = new Color(img.getRGB(col, row));
				System.out.print(collisionArr[row][col]);
			}
			System.out.println();
		}
		person = new Sprite(new Texture("person.png"));
		person.setSize(person.getWidth()*2, person.getHeight()*2);
		person.setPosition(JTCM.WIDTH/2-12, JTCM.HEIGHT/2-29);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		int originalX = (int)Math.round(Math.abs(xCoord)/(JTCM.WIDTH*5/img.getWidth()));
		int originalY = (int)Math.round(Math.abs(yCoord)/(JTCM.HEIGHT*5/img.getHeight()));
		if (Gdx.input.isKeyPressed(Keys.RIGHT) && collisionArr[originalX+1][originalY] != Color.BLACK)
			xCoord -= 5.5;
		if (Gdx.input.isKeyPressed(Keys.LEFT) && collisionArr[originalX-1][originalY] != Color.BLACK)
			xCoord += 5.5;
		if (Gdx.input.isKeyPressed(Keys.UP) && collisionArr[originalX][originalY+1] != Color.BLACK)
			yCoord -= 5.5;
		if (Gdx.input.isKeyPressed(Keys.DOWN) && collisionArr[originalX][originalY-1] != Color.BLACK)
			yCoord += 5.5;
	}

	@Override
	public void update(double t) {
		getInput();
		System.out.println(JTCM.WIDTH);
	}

	@Override
	public void render(SpriteBatch s) {
		s.begin();
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		s.draw(person, person.getX(), person.getY(),person.getWidth(),person.getHeight());
		s.end();

	}

	@Override
	public void dispose() {
		map.dispose();
	}

}
