/**
 * 
 */
package screens;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.main.game.JTCM;

/**
 * This is the main class of our game, it shows a list of highscores. If there
 * arent any highscores, then there will be an error. </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/8/18 </br>
 * Time Spent: 1:30 (Wrote methods to handle scores)
 * @version 1
 * @author Dereck
 */
public class HighScoresScreen extends Screen {

	private BitmapFont font85;
	private BitmapFont font45;
	private GlyphLayout glyph;
	private List<Object[]> scores = new ArrayList<Object[]>();
	private float posa;
	private int posb;
	private Texture bA,bI;
	private String prefix;
	/**
	 * {@link HighScoresScreen} constructor
	 * @param sm Screen manager to determine current screen
	 * @param man Asset manager to load images
	 */
	public HighScoresScreen(ScreenManager sm, AssetManager man,String prefix) {
		super(sm, man);
		File s = new File("C:/highscores");
		if(!s.exists())
			s.mkdir();
		posa=341;
		this.prefix = prefix;
		posb=900;
		bI = man.get("backIdle.png",Texture.class);
		bA = man.get("backActive.png",Texture.class);
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.size = 85;
		font85 = gen.generateFont(param);
		font85.setColor(Color.BLACK);
		font85.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		param.size = 45;
		font45 = gen.generateFont(param);
		font45.setColor(Color.BLACK);
		font45.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		glyph = new GlyphLayout();
		read();

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
			if((Float)(score[1]) <= (Float)(scores.get(x)[1])) {
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

			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highscores/"+prefix+"scores.txt")));
			for(Object[] x : scores)
				out.println(x[0]+" "+x[1]);
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
			BufferedReader in = new BufferedReader(new FileReader("highscores/"+prefix+"scores.txt"));
			String[] tokens= new String[2];
			String inp="";
			while((inp=in.readLine())!=null) {
				tokens[0] = inp.substring(0, inp.lastIndexOf(" "));
				tokens[1] = inp.substring(inp.lastIndexOf(" ")+1);
				scores.add(new Object[] {tokens[0],Float.parseFloat(tokens[1])});
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if(Gdx.input.justTouched()&&Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight())
			sm.pop();
		if(Gdx.input.isKeyJustPressed(Keys.R)) {
			try {
				new PrintWriter(new BufferedWriter(new FileWriter("highscores/"+prefix+"scores.txt"))).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			scores.clear();
			read();	
		}
	}

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	@Override
	public void update(double t) {
		getInput();
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
		glyph.setText(font85, "Name");
		font85.draw(s,glyph, posa, JTCM.HEIGHT-10);
		glyph.setText(font85, "Score");
		font85.draw(s, glyph,posb,JTCM.HEIGHT-10);
		for(int x = 0 ;x < Math.min(10, scores.size());x++) {
			glyph.setText(font45,scores.get(x)[0]+"");
			font45.draw(s, glyph, posa,JTCM.HEIGHT-45*(x+3));
			glyph.setText(font45, scores.get(x)[1]+"");
			font45.draw(s, glyph, posb, JTCM.HEIGHT-45*(x+3));
		}
		s.draw(Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight()?bA:bI, 0, 0);
		glyph.setText(font45, "Press r to reset the highscores");
		font45.draw(s, glyph, bI.getWidth()+10, glyph.height);
		SettingsScreen.applyBrightness(s);
		s.end();

	}

	/**
	 * This method disposes uneeded resources.
	 */
	@Override
	public void dispose() {
		write();

	}

}
