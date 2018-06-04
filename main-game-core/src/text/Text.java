package text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	private String str;
	private boolean done;
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
		long rate=time;
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			rate = time/5;
		}
		else
			rate = time; 
		currT = System.currentTimeMillis();
		if(currT - lastT >=rate)
		{
			glyph.setText(font, str.substring(0, Math.min(str.length(), i++)));
			lastT = currT;
		}
		s.setColor(new Color(1,1,1,0.7f));
		s.draw(blackdot,350,10,830,200);
		s.setColor(Color.WHITE);
		font.draw(s, glyph, x, y);
		if (i == str.length()) {
			done = true;
		}
	}
	private void resetPrint(int a) {
		i = 0;
	}
	public void resetPrint() {
		resetPrint(1);
		done = false;
	}
	public boolean done() {
		return done;
	}
}