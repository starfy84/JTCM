/**
 * 
 */
package screens;

import java.io.*;
import java.util.*;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is the main class of our game, it shows a list of highscores. If there
 * arent any highscores, then there will be an error. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Time Spent: 1:00 (Wrote methods to handle scores)
 * @version 0.4
 * @author Dereck
 */
public class HighScoresScreen extends Screen {

	BitmapFont font;
	List<Object[]> scores = new ArrayList<Object[]>();

	/**
	 * {@link HighScoresScreen} constructor
	 * @param sm Screen manager to determine current screen
	 * @param man Asset manager to load images
	 */
	public HighScoresScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getData().setScale(2);
	}

	/**
	 * This method inserts the score into the array
	 * @param score Player score
	 */
	public void insertScore(Object[] score) {
		if(scores.size()==0) {
			scores.add(score);
			return;
		}
		for(int x = scores.size()-1;x>=0;x--)
			if((Integer)(score[1]) < (Integer)(scores.get(x)[1])) {
				scores.add(x+1, score);
				return;
			}
		scores.add(0,score);
	}
	
	/**
	 * This saves the highscores
	 */
	public void write() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highscores/scores.txt")));
			for(Object[] x : scores)
				out.println("{"+x[0]+"}"+" {"+x[1]+"}");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This reads in the highscores.
	 */
	public void read() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("highscores/scores.txt"));
			String[] tokens;
			String inp="";
			while((inp=in.readLine())!=null) {
				tokens = inp.split("} {");
				tokens[0] = tokens[0].substring(1);
				tokens[1] = tokens[1].substring(0, tokens[1].length()-1);
				scores.add(new Object[] {tokens[0],Integer.parseInt(tokens[1])});
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {

	}

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	@Override
	public void update(double t) {

	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		s.begin();
		font.draw(s, "High Scores", 50, 50);
		s.end();

	}

	/**
	 * This method disposes uneeded resources.
	 */
	@Override
	public void dispose() {
//		font.dispose();

	}

}
