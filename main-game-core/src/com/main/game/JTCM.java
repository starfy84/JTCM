package com.main.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import screens.*;

/**
 * This is the main class of our game, it starts the Main Menu screen. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * Time Spent: 1:45</br>
 * @version 0.1
 * @author Dereck
 */
public class JTCM extends ApplicationAdapter {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Journey to Channelenmajour";
    private AssetManager man;
	private ScreenManager sm;
	private ShaderBatch batch;
	private ShaderBatch sBatch;
	
	
	/**
	 * This method initializes the sprite batch and pushes the menu screen to the
	 * screen manager.
	 */
	@Override
        public void create() {
                man = new AssetManager();
                batch = new ShaderBatch(100);
                sm = new ScreenManager();
                Gdx.gl.glClearColor(1, 2, 1, 1);
                man.load("collisionDetection.png",Texture.class);
                man.load("map.png",Texture.class);
                man.load("menu.png",Texture.class);
                man.load("blackdot.png",Texture.class);
                man.load("person.png",Texture.class);
                man.load("bonusActive.jpg",Texture.class);
                man.load("bonusIdle.jpg",Texture.class);
                man.load("lvl1Active.jpg",Texture.class);
                man.load("lvl1Idle.jpg",Texture.class);
                man.load("lvl2Active.jpg",Texture.class);
                man.load("lvl2Idle.jpg",Texture.class);
                man.load("lvl3Active.jpg",Texture.class);
                man.load("lvl3Idle.jpg",Texture.class);
                man.load("lvlslct.jpg",Texture.class);
                man.load("TutActive.jpg",Texture.class);
                man.load("TutIdle.jpg",Texture.class);
                while(!man.update());
                sm.push(new MenuScreen(sm,man));
                
        }

	/**
	 * This method draws my graphics
	 */
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

	/**
	 * This resizes the window.
	 * 
	 * @param width
	 *            The width of the window.
	 * @param height
	 *            The height of the window.
	 */
	@Override
	public void resize(int width, int height) {
	}

	/**
	 * This pauses the frame.
	 */
	@Override
	public void pause() {
	}

	/**
	 * This resumes the frame.
	 */
	@Override
	public void resume() {
	}
}
