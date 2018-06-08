package screens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

public class HighScoresSelect extends Screen {

	private FreeTypeFontGenerator gen;
	private FreeTypeFontParameter param;
	private GlyphLayout glyph;
	private BitmapFont font45;
	private BitmapFont font;
	private Texture bA,bI;
	public HighScoresSelect(ScreenManager sm, AssetManager man) {
		super(sm, man);
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param = new FreeTypeFontParameter();
		param.size = 80;
		bI = man.get("backIdle.png",Texture.class);
		bA = man.get("backActive.png",Texture.class);
		font = gen.generateFont(param);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().markupEnabled = true;
		font.setColor(Color.BLACK);
		param.size = 45;
		font45 = gen.generateFont(param);
		font45.setColor(Color.BLACK);
		font45.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		glyph = new GlyphLayout();
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1))
			sm.push(new HighScoresScreen(sm,man,"one"));
		else if(Gdx.input.isKeyJustPressed(Keys.NUM_2))
			sm.push(new HighScoresScreen(sm,man,"two"));
		else if(Gdx.input.isKeyJustPressed(Keys.NUM_3))
			sm.push(new HighScoresScreen(sm,man,"three"));
		if(Gdx.input.justTouched()&&Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight())
			sm.pop();
		if(Gdx.input.isKeyJustPressed(Keys.R))
		{
			try {
				new PrintWriter(new BufferedWriter(new FileWriter("highscores/"+"one"+"scores.txt"))).close();
				new PrintWriter(new BufferedWriter(new FileWriter("highscores/"+"two"+"scores.txt"))).close();
				new PrintWriter(new BufferedWriter(new FileWriter("highscores/"+"three"+"scores.txt"))).close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		glyph.setText(font, "1. Level One: Healthy Eating");
		font.draw(s, glyph, 10, (JTCM.HEIGHT-glyph.height));
		glyph.setText(font, "2. Level Two: Addiction");
		font.draw(s, glyph, 10, (JTCM.HEIGHT-glyph.height*2)-30);
		glyph.setText(font, "3. Level Three: Social Skills");
		font.draw(s, glyph, 10, (JTCM.HEIGHT-glyph.height*3-60));
		s.draw(Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight()?bA:bI, 0, 0);
		glyph.setText(font45, "Press r to reset all highscores");
		font45.draw(s, glyph, bI.getWidth()+10, glyph.height);
		SettingsScreen.applyBrightness(s);
		s.end();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
