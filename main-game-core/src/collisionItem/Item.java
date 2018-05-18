package collisionItem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Item{
	private Rectangle bounds;
	private Vector2 coord;
	private Texture image;
	public Item(int x, int y, int width, int height,String path) {
		bounds = new Rectangle(x,y,width,height);
		coord = new Vector2(x,y);
		image = new Texture(path);
	}
	public Item(int x, int y, int width, int height) {
		bounds = new Rectangle(x,y,width,height);
		coord = new Vector2(x,y);
	}
	public Item(int x, int y) {
		coord = new Vector2(x,y);
	}
	public Item(String path) {
		image = new Texture(path);
	}
	public Item(int x, int y,String path) {
		coord = new Vector2(x,y);
		image = new Texture(path);
	}

}
