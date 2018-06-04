package screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

public class TransitionManager {
	private static SpriteBatch s;
	private static Texture blackdot;
	
	public static void load(SpriteBatch sb,Texture img) {
		s = sb;
		blackdot = img;
	}
	
	public static void fadeOut() {
		s.begin();
		for(float x = 0; x <=1 ; x+=0.01f) {
			s.setColor(new Color(1,1,1,x));
			s.draw(blackdot, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
			s.setColor(Color.WHITE);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		s.end();
	}
	
	public static void fadeIn() {
		s.begin();
		for(float x = 1; x >=0 ; x-=0.01f) {
			s.setColor(new Color(1,1,1,x));
			s.draw(blackdot, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
			s.setColor(Color.WHITE);
		}
		s.end();
	}

}
