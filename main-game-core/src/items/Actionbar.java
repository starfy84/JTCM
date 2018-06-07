package items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Actionbar {

	private Texture skin;
	private float x,y;
	private boolean active;
	private String name;
	public Actionbar(Texture skin,float x, float y, String name, boolean active,Float val) {
		this.skin = skin;
		this.x = x;
		this.y =y;
		this.name=name;
		this.active=active;
	}
	public Actionbar(Texture skin,float x,float y,String name,Float val)
	{
		this(skin,x,y,name,true,val);
	}
	
	public String getName() {
		return name;
	}
	public void toggleActive() {
		active = !active;
	}
	public void draw(SpriteBatch s) {
		s.draw(skin, x, y);
	}

}
