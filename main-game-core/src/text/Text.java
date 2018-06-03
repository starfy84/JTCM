package text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Text {
	private int i;
	private long lastT,currT;
	private FreeTypeFontGenerator gen;
	private FreeTypeFontParameter param;
	private BitmapFont font;
	private GlyphLayout glyph;
	public Text(int fontSize) {
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param= new FreeTypeFontParameter();
		param.size = fontSize;
		font = gen.generateFont(param);
		font.setColor(Color.BLACK);
		glyph = new GlyphLayout(font,"");
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		i = 0;
		lastT = System.currentTimeMillis();
	}
	public void printText(String str, SpriteBatch s,float x,float y,long time) {
		currT = System.currentTimeMillis();
		if(currT - lastT >=time)
		{
			glyph.setText(font, str.substring(0, Math.min(str.length(), i++)));
			lastT = currT;
			
		}
		
		font.draw(s, glyph, x, y);
	}
}
