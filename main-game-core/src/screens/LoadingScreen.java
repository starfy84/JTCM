package screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

public class LoadingScreen extends Screen {

	private Texture load;
	public LoadingScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		load = man.get("loading.png",Texture.class);
		System.out.println("ping");
		
	}

	@Override
	public void getInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(double t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch s) {
		// TODO Auto-generated method stub
		System.out.println("pong");
		s.begin();
		s.draw(load, 0,0,JTCM.WIDTH,JTCM.HEIGHT);
		s.end();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        sm.pop();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		System.out.println("done");
        load.dispose();
	}

}
