package collisionItem;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.main.game.JTCM;

/**
 * This class contains the blueprint for a player </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Time spent: 0:30
 * @version 0.4
 * @author Dereck
 */
public class Player extends Sprite {
	public World world;
	public Body bdy;

	/**
	 * {@link Player} constructor
	 * @param world Current world
	 */
	public Player(World world) {
		this.world = world;
		define();
	}
	
	
	/**
	 * This method defines the body of the player, making collision boxes.
	 */
	public void define() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(JTCM.WIDTH/2+60, JTCM.HEIGHT/2);
		bdef.type = BodyDef.BodyType.DynamicBody;
		
		bdy = world.createBody(bdef);
		bdy.setLinearVelocity(0, 0);
		FixtureDef fdef = new FixtureDef();
		PolygonShape box = new PolygonShape();
		box.setAsBox(10, 20);
		
		fdef.shape = box;
//		fdef.friction = 0.3f;
		bdy.createFixture(fdef);
		
	}

}
