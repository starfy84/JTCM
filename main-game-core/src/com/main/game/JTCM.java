package com.main.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.*;

/**
 * This is the main class of our game, it starts the Main Menu screen. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * 
 * @author Dereck
 */
public class JTCM extends ApplicationAdapter {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Journey to Channelenmajour";

	private ScreenManager sm;
	private SpriteBatch batch;

	/**
	 * This method initializes the sprite batch and pushes the menu screen to the
	 * screen manager.
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		sm = new ScreenManager();
		Gdx.gl.glClearColor(1, 2, 1, 1);
		sm.push(new MenuScreen(sm));
	}

	/**
	 * This method draws my graphics
	 */
	@Override
	public void render() {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
		batch.begin();

		batch.end();
	}

	/**
	 * This method disposes uneeded resources.
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
