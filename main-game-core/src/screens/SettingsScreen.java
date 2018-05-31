package screens;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
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

public class SettingsScreen extends Screen {
	private Skin skin;
	private Stage st;
	private String[] sliderNames;
	private SliderStyle style;
	private Table t;
	private Map<String, Slider> sliders;
	private int pos;
	public static float bright = 1, sound = 100, res = 100;
	private Texture sI,sA,bI,bA;
	public SettingsScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		bI = man.get("brightIdle.png",Texture.class);
		bA = man.get("brightActive.png",Texture.class);
		sI = man.get("soundIdle.png",Texture.class);
		sA = man.get("soundActive.png",Texture.class);
		pos = 0;
		sliderNames = new String[] { "brightness", "sound"/*, "resolution" */};
		sliders = new HashMap<String, Slider>();
		st = new Stage();
		// Gdx.input.setInputProcessor(s);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"),
				new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));

		// t.top();

		style = skin.get("default-horizontal", SliderStyle.class);
		makeBright(JTCM.HEIGHT - JTCM.HEIGHT / 4, sliderNames[0]);
		makeSlider(JTCM.HEIGHT - JTCM.HEIGHT / 2, sliderNames[1]);
		//makeSlider(JTCM.HEIGHT - JTCM.HEIGHT * 3 / 4, sliderNames[2]);
		sliders.get("brightness").setValue(bright);
		sliders.get("sound").setValue(sound);
		//sliders.get("resolution").setValue(res);
	}

	private void makeBright(float yPos, String name) {
		Slider tempSlider = new Slider(0, 1, 0.01f, false, skin);
		t = new Table();
		tempSlider.setAnimateDuration(0.1f);
		tempSlider.setStyle(style);
		t.setPosition(JTCM.WIDTH - JTCM.WIDTH / 2f, yPos);
		t.add(tempSlider).width(JTCM.WIDTH / 2f);
		st.addActor(t);
		sliders.put(name, tempSlider);
	}

	private void makeSlider(float yPos, String name) {
		Slider tempSlider = new Slider(1, 100, 1, false, skin);
		t = new Table();
		tempSlider.setAnimateDuration(0.1f);
		tempSlider.setStyle(style);
		t.setPosition(JTCM.WIDTH - JTCM.WIDTH / 2f, yPos);
		t.add(tempSlider).width(JTCM.WIDTH / 2f);
		st.addActor(t);
		sliders.put(name, tempSlider);
	}

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

	public void getInput(String name) {
		if (!name.equals("brightness")) {
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				sliders.get(name).setValue(Math.max(1, sliders.get(name).getValue() - 1));
			else if (Gdx.input.isKeyPressed(Keys.RIGHT))
				sliders.get(name).setValue(Math.min(100, sliders.get(name).getValue() + 1));
		} else {
			if (Gdx.input.isKeyPressed(Keys.LEFT))
				sliders.get(name).setValue(Math.max(0, sliders.get(name).getValue() - 0.01f));
			else if (Gdx.input.isKeyPressed(Keys.RIGHT))
				sliders.get(name).setValue(Math.min(1, sliders.get(name).getValue() + 0.01f));
		}

		bright = sliders.get("brightness").getValue();
		sound =  sliders.get("sound").getValue();
		//res = sliders.get("resolution").getValue();
	}

	@Override
	public void update(double t) {
		// TODO Auto-generated method stub
		getInput();
		getInput(sliderNames[pos]);
	}

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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
