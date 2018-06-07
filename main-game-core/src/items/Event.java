package items;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.main.game.JTCM;

import text.Text;

public abstract class Event {

	protected String desc;
	protected Text text;
	public Event(String desc,Map<String,float[]> choices,float[] changes,float[] rate,String tip) {
		this.desc = desc;
		text = new Text(45, Color.WHITE,JTCM.man);
	}

}
