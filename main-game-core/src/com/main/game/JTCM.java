package com.main.game;

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

	//Width of screen
	public static final int WIDTH = 1280/*800*/;
	
	//Height of screen
	public static final int HEIGHT = 720/*480*/;
	
	//Title of game
	public static final String TITLE = "Journey to Channelenmajour";
	
	//Image manager
    public static AssetManager man;
    
    //Screen manager
	private ScreenManager sm;
	
	//Graphics drawer
	public static SpriteBatch batch;
	
	//Settings screen
	private static SettingsScreen sc;
	
	//Clickable buttons
	private static final String[] buttons=new String[] {};
	
	//Selectable text
	private static final String[] text = new String[] {"bright","sound","exit","lvlslct","highscores","instructions","lvl3","bonus","lvl1","lvl2","Tut","left","right","back","backW","setting"};
	
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
                
                //Loading assets
                man.load("collisionDetection.png",Texture.class);
                man.load("Background.png", Texture.class);
                man.load("map.png",Texture.class);
                man.load("indoorMap.png",Texture.class);
                man.load("menu.png",Texture.class);
                man.load("blackdot.png",Texture.class);
                man.load("person.png",Texture.class);
                //man.load("lvlslct.jpg",Texture.class);
                //man.load("newMenu.png",Texture.class);
                man.load("back.png",Texture.class);
                man.load("ChallengerBackground.jpg",Texture.class);
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
                sc = new SettingsScreen(sm, man,null);
                
                //Starting menu screen
                sm.push(new MenuScreen(sm,man));
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
		man.dispose();
		batch.dispose();
		sc.dispose();
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
