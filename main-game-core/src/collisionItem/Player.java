package collisionItem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.main.game.JTCM;

public class Player extends Sprite {
	public World world;
	public Body bdy;

	public Player(World world) {
		this.world = world;
		define();
	}
	
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
