package text;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

/**
 * This processes user input.</br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/8/18 </br>
 * Time Spent: 00:30 </br>
 * @version 1
 * @author Dereck
 */
public class TextProcessing implements InputProcessor{
	private String str;
	private boolean enter;
	private int bound;
	public TextProcessing(int bound) {
		str = "";
		enter = false;
		this.bound = bound;
	}

	public boolean enterPressed() {
		return enter;
	}
	
	public String getString() {
		return str;
	}
	@Override
	public boolean keyDown(int key) {
		if(!enter && str.length()<=bound&& str.length()>0)
			switch(key) {
			case Keys.ENTER:
				enter = true;
			}
		return false;
	}

	@Override
	public boolean keyTyped(char key) {
		if(!enter) {
			if(key == 8 && str.length()>0)
				str = str.substring(0,str.length()-1);
			else if(str.length()<bound&& ((key>=48&&key<=57)||(key>=65&&key<=90)||(key>=97&&key<=122)||(key==32&&str.length()>0)))
				str+=key;
		}
		return false;
	}

	@Override
	public boolean keyUp(int key) {
		// TODO Auto-generated method stub
//		switch(key) {
//		case Keys.ENTER:
//			enter = false;
//		}
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
