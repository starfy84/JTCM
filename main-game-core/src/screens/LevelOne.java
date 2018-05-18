package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Disposable;
import com.main.game.JTCM;

public class LevelOne extends Screen implements ContactListener {
	BitmapFont font;
	Texture map;
	Sprite person;
	double xCoord = -2500, yCoord = -700;
	private int[][] pixels;
	public LevelOne(ScreenManager sm) {
		super(sm);
		map = new Texture("map.png");
		person = new Sprite(new Texture("person.png"));
		person.setSize(person.getWidth()*2, person.getHeight()*2);
		person.setPosition(JTCM.WIDTH/2-12, JTCM.HEIGHT/2-29);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			xCoord -= 3.5;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			xCoord += 3.5;
		if (Gdx.input.isKeyPressed(Keys.UP))
			yCoord -= 3.5;
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			yCoord += 3.5;
	}

	@Override
	public void update(double t) {
		getInput();

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

	@Override
	public void beginContact(Contact contact) {

		
	}

	@Override
	public void endContact(Contact contact) {

		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

		
	}

}
