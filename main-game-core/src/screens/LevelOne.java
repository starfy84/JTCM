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

public class LevelOne extends Screen {
/**
 * This class runs level one of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/18/18 </br>
 * Rohit's Time spent: 4:00 (including associated graphics)</br>
 * Dereck's Time spent: 6:00 (Attempted unit collision)</br>
 * @version 0.2
 * @author Rohit
 */
	private Texture map;
	private Sprite person,detection,box;
	private double xCoord = -2500, yCoord = -700;
	//int[][][]boxes = {{{-437,-1838},{-1746,-1838},{-437,-1640},{-1746,-1640}}};
	//Rectangle rect = new Rectangle(-437,-1640,1309,198);
	Rectangle bound;
	public LevelOne(ScreenManager sm) {
		super(sm);
		map = new Texture("map.png");
//		collide = new Pixmap(Gdx.files.internal("collisionDetection.png"));
		person = new Sprite(new Texture("person.png"));
		person.setSize(person.getWidth()*2, person.getHeight()*2);
		person.setPosition(JTCM.WIDTH/2-12, JTCM.HEIGHT/2-29);
		detection = new Sprite(new Texture("collisionDetection.png"));
		detection.setSize(JTCM.WIDTH*5, JTCM.HEIGHT*5);
		detection.setPosition(Math.round(xCoord), Math.round(yCoord));
		bound = new Rectangle(-3545,-2058,1755,1105);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			xCoord -= 5.5;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			xCoord += 5.5;
		if (Gdx.input.isKeyPressed(Keys.UP))
			yCoord -= 5.5;
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			yCoord += 5.5;
		if(Gdx.input.isKeyJustPressed(Keys.SPACE))
			System.out.println(detection.getX()+" "+detection.getY());
	
	}

	@Override
	public void update(double t) {
		getInput();
//		System.out.println(bound);
		System.out.println(bound.overlaps(new Rectangle(detection.getX(),detection.getY(),person.getWidth(),person.getHeight())));
		System.out.println(detection.getX()+" "+ detection.getY());
//		System.out.println("Person: "+person.getX()+" "+person.getY());
//		System.out.println("Detection: "+detection.getX()+" "+detection.getY());
//		System.out.println(collide.getPixel((int)(detection.getX()-person.getX()), (int)(detection.getY()+person.getY())));
//		System.out.println((detection.getX()-person.getX())+" "+(detection.getY()-person.getY()));
	}

	@Override
	public void render(SpriteBatch s) {
		s.begin();
		s.draw(detection, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
		detection.setPosition(Math.round(xCoord), Math.round(yCoord));
		s.draw(person, person.getX(), person.getY(),person.getWidth(),person.getHeight());
		s.end();

	}

	@Override
	public void dispose() {
		map.dispose();
	}

}
