package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.main.game.JTCM;

import collisionItem.Player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

/**
 * This class runs level two of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Time spent: 5:30 (Tried implementing new way to detect collision) </br>
 * 
 * @version 0.4
 * @author Dereck
 * 
 * NOT WORKING
 */
public class LevelTwo extends Screen {
	BitmapFont font;
	Texture map, person;
	double xCoord = -2500, yCoord = -700;
	Box2DDebugRenderer bdr;
	Player player;
	float speed =1000000;
	private Vector2 movement  = new Vector2(0,0);
	Music music;
	/**
	 * {@link LevelTwo} constructor
	 * @param sm screen manager to determine current screen
	 * @param man asset manager to load images
	 */
	public LevelTwo(ScreenManager sm, AssetManager man) {
		super(sm, man);
		
		map = man.get("map.png", Texture.class);
		person = man.get("person.png", Texture.class);
		ml = new TmxMapLoader();
		tm = ml.load("levelTwo.tmx");
		otmr = new OrthogonalTiledMapRenderer(tm);
		cam.position.set(vp.getWorldWidth() / 2 - 12, vp.getWorldHeight() / 2 - 29, 0);

		world = new World(new Vector2(0, 0), true);
		bdr = new Box2DDebugRenderer();

		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		for (MapObject obt : tm.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) obt).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);

			body = world.createBody(bdef);

			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
			fdef.shape = shape;

			body.createFixture(fdef);

		}
		
		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				switch(keycode) {
				case Keys.W:
					movement.y = speed;
					break;
				case Keys.A:
					movement.x = -speed;
					break;
				case Keys.S:
					movement.y = -speed;
					break;
				case Keys.D:
					movement.x = speed;
				}
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				switch(keycode) {
				case Keys.W:
				case Keys.S:
					movement.y = 0;
					break;
				case Keys.A:
				case Keys.D:
					movement.x =0;
				}
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
				
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		music = Gdx.audio.newMusic(Gdx.files.internal("Ocean - Man.mp3"));
		music.setLooping(true);
		music.setVolume(SettingsScreen.sound);
		music.play();
		player = new Player(world);
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		// if (Gdx.input.isKeyPressed(Keys.RIGHT))
		// xCoord -= 3.5;
		// else if (Gdx.input.isKeyPressed(Keys.LEFT))
		// xCoord += 3.5;
		// else if (Gdx.input.isKeyPressed(Keys.UP))
		// yCoord -= 3.5;
		// else if (Gdx.input.isKeyPressed(Keys.DOWN))
		// {
		// yCoord += 3.5;
		// }
//		if (Gdx.input.isKeyPressed(Keys.W))
//			movement.y = speed;
//			//player.bdy.applyLinearImpulse(new Vector2(0, 5.5f), player.bdy.getWorldCenter(), true);
//		if (Gdx.input.isKeyPressed(Keys.A))
//			movement.x = -speed;
//			//player.bdy.applyLinearImpulse(new Vector2(-5.5f, 0), player.bdy.getWorldCenter(), true);
//		if (Gdx.input.isKeyPressed(Keys.S))
//			movement.y = -speed;
//			//player.bdy.applyLinearImpulse(new Vector2(0, -5.5f), player.bdy.getWorldCenter(), true);
//		if (Gdx.input.isKeyPressed(Keys.D))
//			movement.x = speed;
			//player.bdy.applyLinearImpulse(new Vector2(5.5f, 0), player.bdy.getWorldCenter(), true);
		if (Gdx.input.isKeyJustPressed(Keys.X)) {
			sm.pop();
		}
		else if (Gdx.input.isKeyJustPressed(Keys.S))
		{
			JTCM.getSettingsScreen().setMusic(music);
			sm.push(JTCM.getSettingsScreen());
		}
		if(movement.x==0 && movement.y==0)
			player.bdy.setLinearVelocity(0, 0);
		cam.position.x = player.bdy.getPosition().x;
		cam.position.y = player.bdy.getPosition().y;
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
		world.step(1 / 60f, 6, 2);

		cam.update();
		otmr.setView(cam);

	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		// s.begin();
		// s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5,
		// JTCM.HEIGHT*5);
		// s.draw(person, JTCM.WIDTH/2-90, JTCM.HEIGHT/2-100, person.getWidth()*2,
		// person.getHeight()*2);
		// s.end();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		otmr.render();

		bdr.render(world, cam.combined);
		s.setProjectionMatrix(cam.combined);
		player.bdy.applyForceToCenter(movement, true);
		System.out.println(movement);
		s.begin();
		SettingsScreen.applyBrightness(s);
		s.end();
	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
//		map.dispose();
//		person.dispose();
//		this.bdr.dispose();
//		this.font.dispose();
//		this.map.dispose();
//		this.music.dispose();
//		this.world.dispose();
		music.dispose();

	}

}
