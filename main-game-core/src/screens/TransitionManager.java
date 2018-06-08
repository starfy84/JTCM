package screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.game.JTCM;

public class TransitionManager {
	
	//Base graphics variables
	private SpriteBatch s;
	private Texture blackdot;
	private float x,y;
	private boolean in,out;
	
	public TransitionManager(SpriteBatch sb,Texture img) {
		s = sb;
		blackdot = img;
		in = out = false;
		x = 0;
		y = 1;
	}
	
	public void reset() {
		x =0;
		y=1;
		in = out = false;
	}
	public void fadeOut() {
		s.setColor(new Color(1,1,1,Math.min(x+=0.01f, 1)));
		s.draw(blackdot, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
		s.setColor(Color.WHITE);
		if(x>=1)
			out=true;
	}
	
	public void fadeIn() {
		s.setColor(new Color(1,1,1,Math.max(0, y-=0.01f)));
		s.draw(blackdot, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
		s.setColor(Color.WHITE);
		if(y<=0)
			in=true;
	}
	
	public boolean inDone() {
		return in;
	}
	public boolean outDone() {
		return out;
	}

}
