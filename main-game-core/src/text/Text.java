package text;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

public class Text {
	private int i;
	private long lastT,currT;
	private BitmapFont font;
	private GlyphLayout glyph;
	public Text() {
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(2);
		glyph = new GlyphLayout(font,"");
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		i = 0;
		lastT = System.currentTimeMillis();
	}
	public void printText(String str, SpriteBatch s,float x,float y) {
		currT = System.currentTimeMillis();
		if(currT - lastT >=85)
		{
			glyph.setText(font, str.substring(0, Math.min(str.length(), i++)));
			lastT = currT;
			
		}
		
		font.draw(s, glyph, x, y);
	}
}
