package screens;

import java.io.IOException;
import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
	private Skin skin;
	private Stage st;
	private String[] sliderNames;
	private SliderStyle style;
	private Table t;
	private Map<String, Slider> sliders;
	private int pos;
	public static float bright = 1, sound = 0.5f, res = 100;
	private Texture sI,sA,bI,bA;
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
		bI = man.get("brightIdle.png",Texture.class);
		bA = man.get("brightActive.png",Texture.class);
		sI = man.get("soundIdle.png",Texture.class);
		sA = man.get("soundActive.png",Texture.class);
		this.m = m;
		pos = 0;
		sliderNames = new String[] { "brightness", "sound"/*, "resolution" */};
		sliders = new HashMap<String, Slider>();
		st = new Stage();
		// Gdx.input.setInputProcessor(s);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"),
				new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));

		// t.top();

		style = skin.get("default-horizontal", SliderStyle.class);
		makeSlider(JTCM.HEIGHT - JTCM.HEIGHT / 4, sliderNames[0]);
		makeSlider(JTCM.HEIGHT - JTCM.HEIGHT / 2, sliderNames[1]);
		//makeSlider(JTCM.HEIGHT - JTCM.HEIGHT * 3 / 4, sliderNames[2]);
		sliders.get("brightness").setValue(bright);
		sliders.get("sound").setValue(sound);
		//sliders.get("resolution").setValue(res);
	}

	/**
	 * This method makes sliders
	 * @param yPos y position
	 * @param name name
	 */
	private void makeSlider(float yPos, String name) {
		Slider tempSlider = new Slider(0, 1, 0.01f, false, skin);
		t = new Table();
		tempSlider.setAnimateDuration(0.1f);
		tempSlider.setStyle(style);
		t.setPosition(JTCM.WIDTH - JTCM.WIDTH / 2f, yPos);
		t.add(tempSlider).width(JTCM.WIDTH / 2f);
		st.addActor(t);
		sliders.put(name, tempSlider);
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
		else if (Gdx.input.isKeyJustPressed(Keys.X)) {
			pos = 0;
			sm.pop();
		}
	}

	/**
	 * This method handles input for sliders.
	 * @param name slider
	 */
	public void getInput(String name) {
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			sliders.get(name).setValue(Math.max(0, sliders.get(name).getValue() - 0.01f));
		else if (Gdx.input.isKeyPressed(Keys.RIGHT))
			sliders.get(name).setValue(Math.min(1, sliders.get(name).getValue() + 0.01f));
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
		st.act(Gdx.graphics.getDeltaTime());
		st.draw();
		s.begin();
		s.draw(pos==0?bA:bI,0, JTCM.HEIGHT - JTCM.HEIGHT / 4,JTCM.WIDTH,JTCM.HEIGHT/10);
		s.draw(pos==1?sA:sI,0,JTCM.HEIGHT - JTCM.HEIGHT / 2,JTCM.WIDTH,JTCM.HEIGHT/10);
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
