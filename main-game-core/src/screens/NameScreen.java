package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.main.game.JTCM;

import text.TextProcessing;


public class NameScreen extends Screen {

	TextProcessing tp;
	BitmapFont font;
	GlyphLayout glyph;
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
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
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
		glyph.setText(font, tp.getString());
		font.draw(s, glyph, (JTCM.WIDTH-glyph.width)/2f,(JTCM.HEIGHT-glyph.height)/2);
		s.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}