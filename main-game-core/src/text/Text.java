package text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.main.game.JTCM;

public class Text {
	private int i;
	private long lastT,currT;
	private FreeTypeFontGenerator gen;
	private FreeTypeFontParameter param;
	private BitmapFont font;
	private GlyphLayout glyph;
	private Texture blackdot;
	public Text(int fontSize,Color c,AssetManager man) {
		blackdot = man.get("blackdot.png",Texture.class);
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param= new FreeTypeFontParameter();
		param.size = fontSize;
		font = gen.generateFont(param);
		font.setColor(c);
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
		s.setColor(new Color(1,1,1,0.7f));
		s.draw(blackdot,350,10,830,200);
		s.setColor(Color.WHITE);
		font.draw(s, glyph, x, y);
	}
	public void resetPrint() {
		i = 0;
	}
}
