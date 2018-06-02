package com.main.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.main.game.JTCM;

/**
 * This class will configure the game and run the main class. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * 
 * @version 0.1
 * @author Dereck
 */
public class DesktopLauncher {
	/**
	 * This is the main method, it will setup and run the game.
	 * 
	 * @param arg
	 *            Command line arguments.
	 */
	public static void main(String[] arg) {
		LwjglApplicationConfiguration conf = new LwjglApplicationConfiguration();
		conf.title = JTCM.TITLE;
		conf.width = JTCM.WIDTH;
		conf.height = JTCM.HEIGHT;
		conf.forceExit = false;
		conf.resizable = false;
		
		conf.addIcon("icon-128.png", FileType.Internal);
		conf.addIcon("icon-32.png", FileType.Internal);
		conf.addIcon("icon-16.png", FileType.Internal);
		new LwjglApplication(new JTCM(), conf);
	}
}
