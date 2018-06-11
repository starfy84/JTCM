package screens;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.main.game.JTCM;

import text.Text;

/**
 * This class runsthe cutscene </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/8/18 </br>
 * Rohit's Time spent: 1:30 ()
 * @version 1
 * @author Rohit
 */
public class Cutscene extends Screen {

	// Map, Minimap, Dot on the minimap to show where the user is, Health bars 1,2,3
	// and 4, Action bars 1,2,3 and 4.
	private Texture background, map, indoorMap, minimap, blackdot, bar1, bar2, bar3, bar4, bar5, bar6, act1, act2, act3,
			act4, act5, act6;

	// Our text drawer
	private Text text;

	// Temporary font drawer
	private BitmapFont font, font1;

	// Collision Picture
	private BufferedImage collisionPic;

	// Player
	private Sprite person;

	// Array for collision
	private java.awt.Color[][] collisionArr;

	// X and Y of map
	private double xCoord = 0 - JTCM.WIDTH * 5 / 2.5, yCoord = 0 - JTCM.HEIGHT * 5 / 2.5;

	// Character's x and y on the array
	private double charX, charY, lastCharY;

	// Health values 1,2,3 and 4
	private float health, exercise, happiness, social, study, energy;

	// Song
	public static Music music;

	// Time for health depletion
	private long currentT, lastT, start, end;

	
	//Status booleans
	private boolean alive, paused, tipMsg;

	// EVENTS
	private boolean eventRun;
	private boolean[] bEvents = new boolean[11];
	private boolean[] eventText = new boolean[11];

	private boolean stop;
	private String[] sEvents = { "You have a power outage! Internet related activites\nhave been disabled.", // Passive
			"You have a stomach ache! Your health bar goes down by\n10.", // Instant
			"You have met an online friend recently who is asking\nfor your personal info. What do you do?", // Instant
			"You feel sharp pain while exercising. What do you do?", // Instant
			"Your parents are upset at you for something you don’t\nagree with! What do you do?", // Instant
			"Your friends are upset at you for something you don’t\nagree with! What do you do?" }; // Instant
	// "You are addicted to video games! Social and health\ndepletion rate
	// doubled.",
	// "You are having a hard time making friends and coping\nwith misfortunate
	// events. Social and health depletion\nrate doubled. Happiness depletion rate
	// tripled.",

	private int rand = 10, tipNum, panelNum = 0;
	// END EVENTS

	// TIPS
	private String[] tips = { "It's good to eat healthy but it's ok to break the\nrules once in a while.",
			"Exercising at the gym is really effective! Make sure\nyou don't overdo it though!",
			"Exercise is great for your body, but make sure you\ndon't overdo it!",
			"Arcades are really fun! Try not to get addicted!",
			"It's important to be happy with your life. Don't get\ncarried away though!",
			"Meeting freinds in real life is really enjoyable!\nTalking to frinds online is also fun and convenient.",
			"Talking to friends online is really convenient!\nMeeting freinds can be even more fun too!",
			"Studying in a work environment is really effective\nsince you can focus! Studying can be boring so make\nsure you relax too.",
			"Studying is really important in order to have a\nsucessful future. Studying can be boring so make sure\nyou relax too.",
			"Relaxing at home is really effective and\nrefreshing!",
			"Relaxing is important in order to reduce stress\nand fatigue." };

	//Cutscene instructions
	private String[]panels = {"Hi friend. It�s me, narrator! So how�s\nyour journey to Channelenmajour coming along?",
			"Wait what?!\nYou don�t know what Channelenmajour is?!",
			"Well put simply,\nChannelenmajour is the state where someone is doing\nextremely well in all aspects of life.",
			"After each level,\nyou can be one step closer to Channelenmajour.",
			"Now you are ready to embark on your\njourney to Channelenmajour. Good Luck!"};
	//Font variables
	private FreeTypeFontGenerator gen, gen1;
	private FreeTypeFontParameter param, param1;
	private GlyphLayout glyph, glyph1;

	private long eCurrT, eLastT;

	// Debugging mode
	private boolean DEBUG;

	/**
	 * {@link Cutscene} constructor
	 * 
	 * @param sm
	 *            screen manager to determine current screen
	 * @param man
	 *            asset manager to load images
	 */
	public Cutscene(ScreenManager sm, AssetManager man) {
		super(sm, man);
		alive = true;
		text = new Text(45, Color.WHITE, man);
		paused = false;
		health = 1f;
		exercise = 1f;
		happiness = 1f;
		social = 1f;
		study = 1f;
		energy = 1f;
		music = Gdx.audio.newMusic(Gdx.files.internal("lvlone.mp3"));
		music.setVolume(SettingsScreen.sound);
		music.setLooping(true);
		music.play();
		background = man.get("Background.png", Texture.class);
		map = man.get("map.png", Texture.class);
		indoorMap = man.get("indoorMap.png", Texture.class);
		minimap = man.get("map.png", Texture.class);
		blackdot = man.get("blackdot.png", Texture.class);
		
		//Status bars
		bar1 = man.get("blank.jpg", Texture.class);
		bar2 = man.get("blank.jpg", Texture.class);
		bar3 = man.get("blank.jpg", Texture.class);
		bar4 = man.get("blank.jpg", Texture.class);
		bar5 = man.get("blank.jpg", Texture.class);
		bar6 = man.get("blank.jpg", Texture.class);
		
		//Setting cogs used for collision purposes (no need to create unnecessary collision objects)
		act1 = man.get("settingIdle.png", Texture.class);
		act2 = man.get("settingIdle.png", Texture.class);
		act3 = man.get("settingIdle.png", Texture.class);
		act4 = man.get("settingIdle.png", Texture.class);
		act5 = man.get("settingIdle.png", Texture.class);
		act6 = man.get("settingIdle.png", Texture.class);

		try {
			collisionPic = ImageIO.read(new File("collisionDetection.png"));
		} catch (IOException e) {
		}

		// Transpose map to an array of colours
		collisionArr = new java.awt.Color[collisionPic.getHeight()][collisionPic.getWidth()];
		for (int row = 0; row < collisionPic.getHeight(); row++) {
			for (int col = 0; col < collisionPic.getWidth(); col++) {
				collisionArr[row][col] = new java.awt.Color(collisionPic.getRGB(col, row));
			}
		}

		// Character is in the centre of the array
		charX = collisionPic.getWidth() / 2;
		charY = collisionPic.getHeight() / 2;

		person = new Sprite(man.get("person.png", Texture.class));

		// Scale character
		person.setSize((int) (person.getWidth() * 1.5), (int) (person.getHeight() * 1.5));

		//Font variables
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param = new FreeTypeFontParameter();
		param.size = 40;
		font = gen.generateFont(param);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().markupEnabled = true;
		glyph = new GlyphLayout();

		//Font variables
		gen1 = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param1 = new FreeTypeFontParameter();
		param1.size = 15;
		font1 = gen.generateFont(param1);
		font1.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font1.getData().markupEnabled = true;
		glyph1 = new GlyphLayout();
		font1.setColor(Color.BLACK);

		// Character is in the centre of the screen
		person.setPosition(JTCM.WIDTH / 2 - person.getWidth() / 2, JTCM.HEIGHT / 2);
		lastT = System.currentTimeMillis();
		start = System.currentTimeMillis();
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput() {

	}

	/**
	 * This method checks if the user would like to stop an event message.
	 */

	/**
	 * Handles input for settings.
	 */
	private void checkSetting() {
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			JTCM.getSettingsScreen().setMusic(music); // Change music target
			sm.push(JTCM.getSettingsScreen());
		}
	}

	/**
	 * Handles input for exiting the game
	 */
	private void checkExit() {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)|| panelNum >= 5) {
			sm.pop();
		}
	}

	private void checkPaused() {
		if (Gdx.input.isKeyJustPressed(Keys.P)) {
			paused = !paused;
			if (music.isPlaying())
				music.pause();
			else
				music.play();
			System.out.println("Paused button was pressed: " + paused);
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
		// System.out.println(textRun);
		if (!paused) {
			
			// checkDialogue();
			handleText();
			checkSetting();
			checkExit();

			if (!alive)
				music.stop();
		}
		checkPaused();
		end = System.currentTimeMillis();
		if (end - start >= 180000)
			stop = true;
	}

	private void handleText() {
		if (text.done() && Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if (panelNum < 5) {
				text.resetPrint();
				panelNum++;
			}
		}
	}


	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		s.begin(); // Begins sprite batch
		if (!stop) {
			if (alive) {

				// Draw background
				s.draw(background, Math.round(xCoord - 750), Math.round(yCoord + 500), JTCM.WIDTH * 5, JTCM.HEIGHT * 5);
				s.draw(background, Math.round(xCoord + 750), Math.round(yCoord - 500), JTCM.WIDTH * 5, JTCM.HEIGHT * 5);

				// Check if player is indoors
				if ((!collisionArr[(int) Math.floor(charY)][(int) Math.round(charX)].equals(java.awt.Color.WHITE)
						&& !collisionArr[(int) Math.floor(charY)][(int) Math.round(charX)].equals(java.awt.Color.BLACK))
						|| (!collisionArr[(int) Math.ceil(charY)][(int) Math.round(charX)].equals(java.awt.Color.WHITE)
								&& !collisionArr[(int) Math.ceil(charY)][(int) Math.round(charX)]
										.equals(java.awt.Color.BLACK))) {
					// Indoor Map
					s.draw(indoorMap, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH * 5, JTCM.HEIGHT * 5);
				} else {
					// Map
					s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH * 5, JTCM.HEIGHT * 5);
				}

				// Rectangular border
				s.draw(blackdot, 0, 0, minimap.getWidth() / 5 + 5, minimap.getHeight() / 5 + 5);

				// Minimap
				s.draw(minimap, 0, 0, minimap.getWidth() / 5, minimap.getHeight() / 5);


				// Check if player is on the path for black dot drawing
				if (collisionArr[(int) Math.floor(charY)][(int) Math.round(charX)].equals(java.awt.Color.WHITE)
						|| collisionArr[(int) Math.ceil(charY)][(int) Math.round(charX - 7.5)]
								.equals(java.awt.Color.WHITE))
					lastCharY = charY;

				// Dot on minimap
				s.draw(blackdot, (float) (charX / 5 - 5), (float) (179 - lastCharY / 5 - 5), 10, 10);

				// Person
				s.draw(person, person.getX(), person.getY(), person.getWidth(), person.getHeight());

				// Changes colours of health bar and draws bars 1,2,3 and 4
				s.setColor(health > 0.7f ? Color.GREEN : health > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar1, 10, JTCM.HEIGHT - 20, JTCM.WIDTH / 4 * health, 10);
				s.setColor(exercise > 0.7f ? Color.GREEN : exercise > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar2, 10, JTCM.HEIGHT - 35, JTCM.WIDTH / 4 * exercise, 10);
				s.setColor(happiness > 0.7f ? Color.GREEN : happiness > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar3, 10, JTCM.HEIGHT - 50, JTCM.WIDTH / 4 * happiness, 10);
				s.setColor(social > 0.7f ? Color.GREEN : social > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar4, 10, JTCM.HEIGHT - 65, JTCM.WIDTH / 4 * social, 10);
				s.setColor(study > 0.7f ? Color.GREEN : study > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar5, 10, JTCM.HEIGHT - 80, JTCM.WIDTH / 4 * study, 10);
				s.setColor(energy > 0.7f ? Color.GREEN : energy > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar6, 10, JTCM.HEIGHT - 95, JTCM.WIDTH / 4 * energy, 10);

				// Reset tint
				s.setColor(Color.WHITE);

				// Start drawing action bar text
				glyph1.setText(font1, "Health");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 7);
				glyph1.setText(font1, "Exercise");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 22);
				glyph1.setText(font1, "Happiness");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 37);
				glyph1.setText(font1, "Social");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 52);
				glyph1.setText(font1, "Study");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 67);
				glyph1.setText(font1, "Energy");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 82);
				// End action bar text

				// Action bar holder
				s.setColor(new Color(1, 1, 1, 0.7f));
				s.draw(blackdot, JTCM.WIDTH - 150, 0, 125, JTCM.HEIGHT);
				s.setColor(Color.WHITE);

				glyph.setText(font, "Health");
				font.draw(s, glyph, JTCM.WIDTH - 125, JTCM.HEIGHT - 100);
				glyph.setText(font, "Exercise");
				font.draw(s, glyph, JTCM.WIDTH - 135, JTCM.HEIGHT - 200);
				glyph.setText(font, "Happiness");
				font.draw(s, glyph, JTCM.WIDTH - 145, JTCM.HEIGHT - 300);
				glyph.setText(font, "Social");
				font.draw(s, glyph, JTCM.WIDTH - 125, JTCM.HEIGHT - 400);
				glyph.setText(font, "Study");
				font.draw(s, glyph, JTCM.WIDTH - 125, JTCM.HEIGHT - 500);
				glyph.setText(font, "Energy");
				font.draw(s, glyph, JTCM.WIDTH - 130, JTCM.HEIGHT - 600);

				if (collisionArr[(int) Math.floor(charY)][(int) Math.round(charX)].equals(java.awt.Color.WHITE)
						|| collisionArr[(int) Math.ceil(charY)][(int) Math.round(charX - 7.5)]
								.equals(java.awt.Color.WHITE))
					lastCharY = charY;

				// Dot on minimap
				s.draw(blackdot, (float) (charX / 5 - 5), (float) (179 - lastCharY / 5 - 5), 10, 10);

				// Person
				s.draw(person, person.getX(), person.getY(), person.getWidth(), person.getHeight());

				// Changes colours of health bar and draws bars 1,2,3 and 4
				s.setColor(health > 0.7f ? Color.GREEN : health > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar1, 10, JTCM.HEIGHT - 20, JTCM.WIDTH / 4 * health, 10);
				s.setColor(exercise > 0.7f ? Color.GREEN : exercise > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar2, 10, JTCM.HEIGHT - 35, JTCM.WIDTH / 4 * exercise, 10);
				s.setColor(happiness > 0.7f ? Color.GREEN : happiness > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar3, 10, JTCM.HEIGHT - 50, JTCM.WIDTH / 4 * happiness, 10);
				s.setColor(social > 0.7f ? Color.GREEN : social > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar4, 10, JTCM.HEIGHT - 65, JTCM.WIDTH / 4 * social, 10);
				s.setColor(study > 0.7f ? Color.GREEN : study > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar5, 10, JTCM.HEIGHT - 80, JTCM.WIDTH / 4 * study, 10);
				s.setColor(energy > 0.7f ? Color.GREEN : energy > 0.3f ? Color.YELLOW : Color.RED);
				s.draw(bar6, 10, JTCM.HEIGHT - 95, JTCM.WIDTH / 4 * energy, 10);

				// Reset tint
				s.setColor(Color.WHITE);

				// Start drawing action bar text
				glyph1.setText(font1, "Health");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 7);
				glyph1.setText(font1, "Exercise");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 22);
				glyph1.setText(font1, "Happiness");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 37);
				glyph1.setText(font1, "Social");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 52);
				glyph1.setText(font1, "Study");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 67);
				glyph1.setText(font1, "Energy");
				font1.draw(s, glyph1, 10, JTCM.HEIGHT - 82);
				// End action bar text

				// Action bar holder
				s.setColor(new Color(1, 1, 1, 0.7f));
				s.draw(blackdot, JTCM.WIDTH - 150, 0, 125, JTCM.HEIGHT);
				s.setColor(Color.WHITE);

				// TEXT DRAWING AREA
				if (panelNum<5) {
					text.printText(panels[panelNum], s, 85, paused);
				}
				if (panelNum >= 5) {System.out.println("Done!");}
				if (eventRun && eventText[rand]) {
					text.printText(sEvents[rand], s, 85, paused);
				}

			// END TEXT DRAWING AREA
			} else {
				glyph.setText(font, "[RED]LEVEL ONE[]:[RED]FAILED[]\n[BLACK]CLICK TO CONTINUE[]");
				font.draw(s, glyph, JTCM.WIDTH / 2 - glyph.width / 2, JTCM.HEIGHT / 2 - glyph.height / 2);
				if(Gdx.input.justTouched())
				{
					HighScoresScreen hs = new HighScoresScreen(sm,man,"one");
					hs.insertScore(new Object[] {NameScreen.getName(),(this.health+this.energy+this.social+this.exercise+this.happiness+this.study)*100});
					sm.set(hs);
				}
			}
		} else {
			glyph.setText(font, "[RED]LEVEL ONE[]: [LIME]COMPLETE[]\n[BLACK]CLICK TO CONTINUE[]");
			font.draw(s, glyph, JTCM.WIDTH / 2 - glyph.width / 2, JTCM.HEIGHT / 2 - glyph.height / 2);
			if(Gdx.input.justTouched())
			{
				HighScoresScreen hs = new HighScoresScreen(sm,man,"one");
				hs.insertScore(new Object[] {NameScreen.getName(),(this.health+this.energy+this.social+this.exercise+this.happiness+this.study)*100});
				sm.set(hs);
			}
		}
		SettingsScreen.applyBrightness(s);
		if (paused) {
			s.setColor(1, 1, 1, 0.8f);
			s.draw(blackdot, 0, 0, JTCM.WIDTH, JTCM.HEIGHT);
			s.setColor(Color.WHITE);
			glyph.setText(font, "THE GAME IS PAUSED");
			font.draw(s, glyph, JTCM.WIDTH / 2 - glyph.width / 2, JTCM.HEIGHT / 2 - glyph.height / 2);
		}
		s.end(); // Ends sprite batch

	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		music.dispose();
	}

}
