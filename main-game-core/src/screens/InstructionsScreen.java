package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.main.game.JTCM;

import text.Text;

/**
 * This class shows an instructions screen ---UNIMPLEMENTED--- </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * 
 * @version 0.1 Time spent: 00:15 </br>
 * @author Dereck
 */
public class InstructionsScreen extends Screen {

	//Font variables
	private FreeTypeFontGenerator gen;
	private FreeTypeFontParameter param;
	private BitmapFont font;
	private GlyphLayout glyph;
	private int pos;
	private String[] text;
	
	//Backgrounds
	private Texture bg,lI,lA,rI,rA,bI,bA;

	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
	public InstructionsScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		bg = man.get("back.png",Texture.class);
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param = new FreeTypeFontParameter();
		param.size = 40;
		font = gen.generateFont(param);
		font.setColor(Color.BLACK);
		glyph = new GlyphLayout(font, "");
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().markupEnabled = true;
		pos = 0;
		lI = man.get("leftIdle.png",Texture.class);
		lA = man.get("leftActive.png",Texture.class);
		rI = man.get("rightIdle.png",Texture.class);
		rA = man.get("rightActive.png",Texture.class);
		bI = man.get("backWIdle.png",Texture.class);
		bA = man.get("backWActive.png",Texture.class);
		text = new String[] { "[ROYAL]Goal[]: Have the highest score at the end of each level.",
				"You play as a student with a variety of [ROYAL]status bars[]",
				"[ROYAL]Status bars degrade consistently[] so you must try your hardest to keep them all high!",
				"To keep your status bars high, you must [ROYAL]perform activities[] from the [ROYAL]action bar[] by clicking on their\nrespective buttons.",
				"Be careful not to let your status bars drop below a certain level, or all the status bars will start to\n[RED]degrade faster[]!",
				"[ROYAL]Location matters[]! Performing certain actions at specific location can be [LIME]more effective[] or [RED]less effective[].",
				"There are specific events that show up randomly which can [ROYAL]restrict actions or change the rates[] of your\nstatus bars drop.",
				"At the end of [ROYAL]3 minutes[] the level ends and the total of your status bars is added together to give you a\nscore.",
				"If at anytime during the level, one of your status bars drops to zero, you will [RED]lose[]!",
				"At any point during the game press [RED]ESC[] to exit the game, [RED]S[] to open the settings screen,[RED]P[] to pause,\nand [RED]space[] to advance the dialogue. Click on the minimap to view a larger map." };
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if ((Gdx.input.isKeyJustPressed(Keys.LEFT))||(Gdx.input.justTouched()&&Gdx.input.getX()>=20 &&Gdx.input.getX()<=20+lI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)&& Gdx.input.getY()>=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)-lI.getHeight())) {
			pos = Math.max(0, pos - 1);
		}
		if ((Gdx.input.isKeyJustPressed(Keys.RIGHT))||(Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-rI.getWidth()-20 &&Gdx.input.getX()<=JTCM.WIDTH-20&& Gdx.input.getY()<=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)&& Gdx.input.getY()>=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)-rI.getHeight())) {
			pos = Math.min(pos + 1, text.length - 1);
		}
		if(Gdx.input.justTouched()&&Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight())
			sm.pop();
	}

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	@Override
	public void update(double t) {
		getInput();
	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		glyph.setText(font, text[pos]);
		s.begin();
		s.draw(bg, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
		if(pos>0)
			s.draw(Gdx.input.getX()>=20 &&Gdx.input.getX()<=20+lI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)&& Gdx.input.getY()>=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)-lI.getHeight()?lA:lI,20,(JTCM.HEIGHT-lI.getHeight())/2);
		if(pos<text.length-1)
			s.draw(Gdx.input.getX()>=JTCM.WIDTH-rI.getWidth()-20 &&Gdx.input.getX()<=JTCM.WIDTH-20&& Gdx.input.getY()<=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)&& Gdx.input.getY()>=JTCM.HEIGHT-((JTCM.HEIGHT-lI.getHeight())/2)-rI.getHeight()?rA:rI,JTCM.WIDTH-rI.getWidth()-20,(JTCM.HEIGHT-rI.getHeight())/2);
		s.draw(Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight()?bA:bI, 0, 0);		s.draw(Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight()?bA:bI, 0, 0);
		font.draw(s, glyph, 10, JTCM.HEIGHT - 10);
		SettingsScreen.applyBrightness(s);
		s.end();
	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		// font.dispose();
	}

}
