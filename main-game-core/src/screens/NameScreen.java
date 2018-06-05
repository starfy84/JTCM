package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;


public class NameScreen extends Screen {

	Stage stage;
	TextField txtfld;
	public NameScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		// TODO Auto-generated constructor stub
		stage = new Stage();
		Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"),
				new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));
		txtfld = new TextField("", skin);
		txtfld.setPosition(30, 30);
		stage.addActor(txtfld);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Keys.X))
			sm.pop();
	}

	@Override
	public void update(double t) {
		// TODO Auto-generated method stub
		getInput();
		System.out.println(txtfld.getText());
	}

	@Override
	public void render(SpriteBatch s) {
		// TODO Auto-generated method stub
		stage.draw();
		stage.act();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
