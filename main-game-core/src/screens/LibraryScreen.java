package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.main.game.JTCM;

import text.Text;

/**
 * This class shows an instructions screen ---UNIMPLEMENTED--- </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 5/14/18 </br>
 * 
 * @version 0.1 Time spent: 00:15 </br>
 * @author Dereck
 */
public class LibraryScreen extends Screen {

	private FreeTypeFontGenerator gen;
	private FreeTypeFontParameter param;
	private BitmapFont textFont,titleFont;
	private GlyphLayout titleGlyph,textGlyph;
	private int pos;
	private String[] text,title;
	private Texture bg,lI,lA,rI,rA,bI,bA;

	/**
	 * {@link MenuScreen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
	public LibraryScreen(ScreenManager sm, AssetManager man) {
		super(sm, man);
		bg = man.get("back.png",Texture.class);
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param = new FreeTypeFontParameter();
		param.size = 80;
		titleFont = gen.generateFont(param);
		titleFont.setColor(Color.BLACK);
		param.size = 30;
		textFont = gen.generateFont(param);
		textFont.setColor(Color.BLACK);
		textGlyph = new GlyphLayout(textFont, "");
		titleGlyph = new GlyphLayout(titleFont, "");
		textFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		textFont.getData().markupEnabled = true;
		titleFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		titleFont.getData().markupEnabled = true;
		pos = 0;
		lI = man.get("leftIdle.png",Texture.class);
		lA = man.get("leftActive.png",Texture.class);
		rI = man.get("rightIdle.png",Texture.class);
		rA = man.get("rightActive.png",Texture.class);
		bI = man.get("backWIdle.png",Texture.class);
		bA = man.get("backWActive.png",Texture.class);
		text = new String[] {"Exercise is known to help people lose weight and stay healthy by lowering the risk of diseases such as obesity, diabetes, and high blood pressure.\nExercise causes the body to produce chemicals, which make a person feel good both mentally and physically.\nThe person will be happier, and will also feel like they have more energy.\nExercise can even help people sleep better, help people who have mild depression and promote better self esteem.\n" + 
				"Around 30 minutes of moderate intensity exercise is enough per day in order to prevent the negative effects listed above.\nDepending on the person, there is such a thing as too much exercise.",
				
				"Good nutrition is essential for good growth, proper tissue repair and good muscle tissue creation. The entire body will benefit from good\nnutrition. \n" + 
				"It is important to understand nutrition labels when buying and eating packaged food. Make sure that you have the correct amount of all\nnutrients in your diet.\nThere is a difference between added and natural nutrients. If buying processed food is mandatory, try to buy foods with the least fat, sugar,\ncalories and sodium while also having the highest vitamin values.\nNatural foods have a good amount of nutrients already, so just try to eat a variety of natural food.\n" + 
				"Eating healthy is a problem many people have in the 21st century. It often helps to start small and stick to your goal.\nFor example, eat a little less processed food and a little more fruits and vegetables every week.\nEventually, you will be able to eat only fresh foods without much hassle.",
				
				"Gaming addiction has emotional and physical downsides. People may feel irritable when not playing games, or they may isolate themselves from\nothers in order to play more video games. There are many physical symptoms as well, such as fatigue, eye strain and carpal tunnel syndrome.\nThe biggest consequence of a gaming addiction is the fact that some people may value gaming over other important things such as friends,\nsleeping or even eating.\n" + 
				"There are many solutions to stop gaming addiction, including but not limited to: lowering amount of playtime, reducing the number of\ngaming systems owned, avoiding MMO games, taking breaks every hour, playing movement based games such as Wii Sports, seeking\noutside help from parents or from professional therapy and finding other hobbies. Usually, trying to stop gaming addiction by completely\nstopping playing suddenly is ineffective.",
				
				"Depression is a common medical illness which causes excessive feelings of sadness and loss of interest in activities a person once enjoyed.\nThere are many factors which can cause depression including genetics, personality and environmental factors.\n" + 
				"Symptoms of depression can range from mild to serious, and consist of: feeling sad, a loss of interest in activities once enjoyed, changes in\nappetite, trouble sleeping or sleeping too much, increased fatigue, increase in purposeless physical activity, becoming less active, feeling\nworthless or guilty, difficulty concentrating or thinking and in extreme cases thoughts of death or suicide.\n" + 
				"Luckily, depression has a very high treatment rate, since between 80 percent to 90 percent of people respond well to treatment. Medication,\npsychotherapy and Electroconvulsive Therapy are all ways to treat depression. There are also things that an individual can do to help cure\ntheir own depression such as getting enough sleep, eating healthy and avoiding alcohol.",
				"Social issues are quite common in peoples day to day lives. Regardless of if its a simple misunderstanding or a larger conflict, it is always good\nto be on good terms with the people around you."+
				"\nThe solution for social issues, whether its between your parents or your peers, are relatively the same.\nFirstly, let the situation cool down if the conflict was recent.\nNext, calm down and try to think logically, without letting anger get in your way.\nThen, reflect on the problem; maybe the cause of the problem was you.\nRegardless of the fault, the next step is to reach out and apologize for the conflict.\nAt this point you should calmly and peacefully voice your concerns. Try to please the other party, even if they were wrong. Being nice has\nnothing to do with righteousness.\nAfterwards, eliminate the blame, by providing forgiveness.\nThe final step is to not explicitly expect results. Although you may have done everything right, as per the steps, the other party may not be as\nreasonable.\nFor the sake of appeasement, don't pry any further or the situation might get worse."};
		
		title = new String[] {"[RED]Exercise[]","[BLUE]Nutrition[]","[LIME]Gaming Addiction[]","[GOLD]Depression[]","[PURPLE]Dealing with Social Issues[]"};
		
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {
		if ((Gdx.input.isKeyJustPressed(Keys.LEFT))||(Gdx.input.justTouched()&&Gdx.input.getX()>=20 &&Gdx.input.getX()<=20+lI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT-(bI.getHeight()+10)&& Gdx.input.getY()>=JTCM.HEIGHT-(bI.getHeight()+10)-lI.getHeight())) {
			pos = Math.max(0, pos - 1);
		}
		if ((Gdx.input.isKeyJustPressed(Keys.RIGHT))||(Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-rI.getWidth()-20 &&Gdx.input.getX()<=JTCM.WIDTH-20&& Gdx.input.getY()<=JTCM.HEIGHT-(bI.getHeight()+10)&& Gdx.input.getY()>=JTCM.HEIGHT-(bI.getHeight()+10)-rI.getHeight())) {
			pos = Math.min(pos + 1, text.length - 1);
		}
		if(Gdx.input.justTouched()&&Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight())
			sm.pop();
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
		textGlyph.setText(textFont, text[pos]);
		titleGlyph.setText(titleFont, title[pos]);
		s.begin();
		s.draw(bg, 0, 0,JTCM.WIDTH,JTCM.HEIGHT);
		if(pos>0)
			s.draw(Gdx.input.getX()>=20 &&Gdx.input.getX()<=20+lI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT-(bI.getHeight()+10)&& Gdx.input.getY()>=JTCM.HEIGHT-(bI.getHeight()+10)-lI.getHeight()?lA:lI,20,bI.getHeight()+10);
		if(pos<text.length-1)
			s.draw(Gdx.input.getX()>=JTCM.WIDTH-rI.getWidth()-20 &&Gdx.input.getX()<=JTCM.WIDTH-20&& Gdx.input.getY()<=JTCM.HEIGHT-(bI.getHeight()+10)&& Gdx.input.getY()>=JTCM.HEIGHT-(bI.getHeight()+10)-rI.getHeight()?rA:rI,JTCM.WIDTH-rI.getWidth()-20,bI.getHeight()+10);
		s.draw(Gdx.input.getX()>=0 &&Gdx.input.getX()<=bI.getWidth()&& Gdx.input.getY()<=JTCM.HEIGHT&& Gdx.input.getY()>=JTCM.HEIGHT-bI.getHeight()?bA:bI, 0, 0);
		titleFont.draw(s, titleGlyph, 10, JTCM.HEIGHT - 10);
		textFont.draw(s,textGlyph,10,JTCM.HEIGHT-titleGlyph.height*2);
		SettingsScreen.applyBrightness(s);
		s.end();
	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		// font.dispose();
	}

}
