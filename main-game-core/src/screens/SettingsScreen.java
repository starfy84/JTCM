package screens;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.main.game.JTCM;
/**
 * This class allows adjustments of the settings </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Time spent: 5:00 (Implemented sliders that listen for keyboard input)</br>
 * @version 0.4
 * @author Dereck
 */
public class SettingsScreen extends Screen {
	private FreeTypeFontGenerator gen;
	private FreeTypeFontParameter param;
	private BitmapFont font;
	private GlyphLayout glyph;
	private Skin skin;
	private Stage st;
	private String[] sliderNames;
	private SliderStyle style;
	private Table t;
	private Map<String, Slider> sliders;
	private Map<String,Integer> vals;
	private int pos;
	public static float bright = 1f, sound = 0.5f, res = 100;
	private Texture sI,sA,bI,bA,bg,backA,backI;
	private static Texture blackdot;
	private Music m;
	/**
	 * {@link SettingsScreen} constructor
	 * @param sm screen manager to determine current screen
	 * @param man asset manager to load images
	 * @param m current music
	 */
	public SettingsScreen(ScreenManager sm, AssetManager man,Music m) {
		super(sm, man);
		Gdx.gl.glClearColor(1, 2, 1, 1);
		bg = man.get("back.png",Texture.class);
		bI = man.get("brightIdle.png",Texture.class);
		bA = man.get("brightActive.png",Texture.class);
		sI = man.get("soundIdle.png",Texture.class);
		sA = man.get("soundActive.png",Texture.class);
		blackdot = man.get("blackdot.png",Texture.class);
		this.m = m;
		pos = 0;
		sliderNames = new String[] { "brightness", "sound"/*, "resolution" */};
		sliders = new HashMap<String, Slider>();
		vals = new HashMap<String,Integer>();
		vals.put(sliderNames[0], 0);
		vals.put(sliderNames[1], 1);
		st = new Stage();
		// Gdx.input.setInputProcessor(s);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"),
				new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));

		// t.top();

		style = skin.get("default-horizontal", SliderStyle.class);
		makeSlider(JTCM.HEIGHT - JTCM.HEIGHT / 4, sliderNames[0],0.25f,1,0.01f);
		makeSlider(JTCM.HEIGHT - JTCM.HEIGHT / 2, sliderNames[1],0,1,0.01f);
		//makeSlider(JTCM.HEIGHT - JTCM.HEIGHT * 3 / 4, sliderNames[2]);
		sliders.get("brightness").setValue(bright);
		sliders.get("sound").setValue(sound);
		//sliders.get("resolution").setValue(res);
		//backI = man.get("backWIdle.png",Texture.class);
		//backA = man.get("backWActive.png",Texture.class);
		
    	gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
        param = new FreeTypeFontParameter();
        param.size = 10;
        font = gen.generateFont(param);
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        font.getData().markupEnabled = true;
        glyph = new GlyphLayout();
	}

	/**
	 * This method makes sliders
	 * @param yPos y position
	 * @param name name
	 */
	private void makeSlider(float yPos, String name,float start, float end, float step) {
		Slider tempSlider = new Slider(start, end, step, false, skin);
		t = new Table();
		tempSlider.setAnimateDuration(0.1f);
		tempSlider.setStyle(style);
		tempSlider.setName(name);
		t.setPosition(JTCM.WIDTH - JTCM.WIDTH / 2f, yPos);
		tempSlider.addListener(new ClickListener() {
	         @Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	        	 pos = vals.get(event.getTarget().getName());
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		t.add(tempSlider).width(JTCM.WIDTH / 2f);
		st.addActor(t);
		sliders.put(name, tempSlider);
	}

	public static void applyBrightness(SpriteBatch s) {
		s.setColor(new Color(1,1,1,1-SettingsScreen.bright));
		s.draw(blackdot,0,0,JTCM.WIDTH,JTCM.HEIGHT);
		s.setColor(Color.WHITE);
	}
	
	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if (Gdx.input.isKeyJustPressed(Keys.UP))
			pos = pos - 1 < 0 ? sliderNames.length - 1 : (pos - 1);
		else if (Gdx.input.isKeyJustPressed(Keys.DOWN))
			pos = (pos + 1) % sliderNames.length;
		else if(Gdx.input.justTouched()&&Gdx.input.getX()>=0 &&Gdx.input.getX()<=backI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-backI.getHeight())
		{	
			pos = 0;
			sm.pop();
		}
	}

	/**
	 * This method handles input for sliders.
	 * @param name slider
	 */
	public void getInput(String name) {
		Slider slider = sliders.get(name);
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			sliders.get(name).setValue(Math.max(slider.getMinValue(), slider.getValue() - slider.getStepSize()));
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			sliders.get(name).setValue(Math.min(slider.getMaxValue(), slider.getValue() + slider.getStepSize()));
		bright = sliders.get("brightness").getValue();
		sound =  sliders.get("sound").getValue();
		if(m!=null)
			m.setVolume(sound);
		//res = sliders.get("resolution").getValue();
	}

	/**
	 * @param m music
	 */
	public void setMusic(Music m) {
		this.m = m;
	}
	
	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	@Override
	public void update(double t) {
		// TODO Auto-generated method stub
		getInput();
		getInput(sliderNames[pos]);
		Gdx.input.setInputProcessor(st);
	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		// TODO Auto-generated method stub
		//Gdx.gl.glClearColor(0, 0, 0, 0);
		s.begin();
		s.draw(bg,0,0,JTCM.WIDTH,JTCM.HEIGHT);
		s.end();
		st.draw();
		st.act();
		s.begin();
		s.draw(pos==0?bA:bI,0, JTCM.HEIGHT - JTCM.HEIGHT / 4,JTCM.WIDTH,JTCM.HEIGHT/10);
		s.draw(pos==1?sA:sI,0,JTCM.HEIGHT - JTCM.HEIGHT / 2,JTCM.WIDTH,JTCM.HEIGHT/10);
		font.setColor(Gdx.input.getX()>=0 &&Gdx.input.getX()<=backI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-backI.getHeight()?Color.WHITE:Color.CYAN);
		glyph.setText(font, "BRIGHTNESS");
		font.draw(s, glyph, 100,100);
		applyBrightness(s);
		s.end();




	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
//		skin.dispose();;
//		st.dispose();
//		sI.dispose();
//		sA.dispose();
//		bI.dispose();
//		bA.dispose()	;
//		m.dispose();


	}

}
