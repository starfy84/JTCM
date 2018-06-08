package screens;

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

import text.TextProcessing;


public class NameScreen extends Screen {

	private Texture bg;
	private static TextProcessing tp;
	private BitmapFont font;
	private GlyphLayout glyph;
	private static String name="";
	public NameScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		// TODO Auto-generated constructor stub
		tp = new TextProcessing(25);
		Gdx.input.setInputProcessor(tp);
		glyph = new GlyphLayout();
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 85;
		font = gen.generateFont(param);
		font.setColor(Color.BLACK);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bg = man.get("back.png",Texture.class);
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		if(tp.enterPressed()) {
			sm.pop();
		}
	}

	@Override
	public void update(double t) {
		// TODO Auto-generated method stub
		getInput();
	}

	@Override
	public void render(SpriteBatch s) {
		// TODO Auto-generated method stub
		s.begin();
		s.draw(bg,0,0,JTCM.WIDTH,JTCM.HEIGHT);
		glyph.setText(font, tp.getString());
		font.draw(s, glyph, (JTCM.WIDTH-glyph.width)/2f,(JTCM.HEIGHT-glyph.height)/2);
		glyph.setText(font, "PLEASE ENTER YOUR NAME");
		font.draw(s, glyph, (JTCM.WIDTH-glyph.width)/2f,(JTCM.HEIGHT-glyph.height*2));
		s.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	public static String getName() {
		name = "[RED]"+tp.getString()+"[]";
		return name;
	}

}
