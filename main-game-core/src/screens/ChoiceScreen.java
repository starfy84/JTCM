package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.main.game.JTCM;

import text.Text;

public class ChoiceScreen extends Screen {

	//Font variables
	private FreeTypeFontGenerator gen;
	private FreeTypeFontParameter param;
	private BitmapFont font;
	private GlyphLayout glyph;
	
	//Background
	private Texture bg;
	
	//Event variables
	private String[] choices;
	public static int choice = 0;
	private boolean draw;
	private Text text;
	private String[] tips;
	
	public ChoiceScreen(ScreenManager sm, AssetManager man, String[] tips, String... choices) {
		super(sm, man);
		this.tips = tips;
		text = new Text(45, Color.WHITE, man);
		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean keyDown(int key) {
				if (!draw)
					switch (key) {
					case Keys.NUM_1:
						choice = 1;
						break;
					case Keys.NUM_2:
						choice = 2;
						break;
					case Keys.NUM_3:
						choice = 3;
						break;
					case Keys.NUM_4:
						choice = 4;
						break;
					case Keys.NUM_5:
						choice = 5;
						break;
					case Keys.NUM_6:
						choice = 6;
						break;
					case Keys.NUM_7:
						choice = 7;
						break;
					case Keys.NUM_8:
						choice = 8;
						break;
					case Keys.NUM_9:
						choice = 9;
						break;

					}
				return false;
			}

			@Override
			public boolean keyTyped(char arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyUp(int arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean mouseMoved(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDragged(int arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param = new FreeTypeFontParameter();
		param.size = 50;
		glyph = new GlyphLayout();
		this.choices = choices;
		bg = man.get("back.png", Texture.class);
		font = gen.generateFont(param);

	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		if(text.done()&& Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			draw=false;
			text.resetPrint();
			sm.pop();
			
		}
	}

	@Override
	public void update(double t) {
		// TODO Auto-generated method stub
		getInput();
		System.out.println(choice);
	}

	@Override
	public void render(SpriteBatch s) {
		// TODO Auto-generated method stub
		s.begin();
		s.draw(bg, 0, 0, JTCM.WIDTH, JTCM.HEIGHT);
		for (int x = 0; x < choices.length; x++) {
			glyph.setText(font, (x + 1) + ". " + choices[x]);
			font.draw(s, glyph, 10, JTCM.HEIGHT - (100 + 100 * x));
		}
		if (choice > 0 && choice <= choices.length) {
			draw = true;
		}
		if(draw)
			text.printText(tips[choice-1], s, 85, false);
		s.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
