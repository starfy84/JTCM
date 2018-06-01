package com.main.game;

import java.io.IOException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import screens.*;

/**
 * This is the main class of our game, it starts the Main Menu screen. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Time Spent: 0:30 (Optimized asset loading)</br>
 * @version 0.4
 * @author Dereck
 */
public class JTCM extends ApplicationAdapter {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Journey to Channelenmajour";
    private AssetManager man;
	private ScreenManager sm;
	private SpriteBatch batch;
	private static SettingsScreen sc;
	public static final float PPM = 100;
	private static final String[] buttons=new String[] {"bonus","lvl1","lvl2","lvl3","Tut","lvlslct","highscores","instructions","exit","setting"};
	private static final String[] text = new String[] {"bright","sound"};
	/**
	 * This method initializes the sprite batch and pushes the menu screen to the
	 * screen manager.
	 */
	@Override
        public void create() {
                man = new AssetManager();
                batch = new SpriteBatch();
                sm = new ScreenManager();
                Gdx.gl.glClearColor(1, 2, 1, 1);
                man.load("collisionDetection.png",Texture.class);
                man.load("map.png",Texture.class);
                man.load("menu.png",Texture.class);
                man.load("blackdot.png",Texture.class);
                man.load("person.png",Texture.class);
                man.load("lvlslct.jpg",Texture.class);
                man.load("newMenu.png",Texture.class);
                man.load("blank.jpg",Texture.class);
                for(String x : buttons) {
                	man.load(x+"Active.jpg",Texture.class);
                	man.load(x+"Idle.jpg",Texture.class);
                }
                for(String x : text) {
                	man.load(x+"Active.png",Texture.class);
                	man.load(x+"Idle.png",Texture.class);
                }
                while(!man.update());
               sm.push(new MenuScreen(sm,man));
                sc = new SettingsScreen(sm, man,null);
        }

	/**
	 * @return SettingsScreen
	 */
	public static SettingsScreen getSettingsScreen() {
		return sc;
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
