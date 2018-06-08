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
 * This class runs the bonus level of the game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/1/18 </br>
 * Dereck's Time spent: 1:30 (Implemented music and route to settings) </br>
 * 
 * @version 0.4
 * @author Rohit & Dereck
 */
public class BonusLevel extends Screen {

	//Map, Minimap, Dot on the minimap to show where the user is, Health bars 1,2,3 and 4, Action bars 1,2,3 and 4.
	private Texture background, map, indoorMap, minimap, blackdot, bar1, bar2, bar3, bar4, bar5, bar6, act1, act2, act3, act4, act5, act6;
	
	//Our text drawer
	private Text text;
	
	//Temporary font drawer
	private BitmapFont font,font1;
	
	//Collision Picture
	private	BufferedImage collisionPic;
	
	//Player
	private Sprite person;
	
	//Array for collision
	private java.awt.Color[][] collisionArr;
	
	//X and Y of map
	private double xCoord = 0-JTCM.WIDTH*5/2.5, yCoord = 0-JTCM.HEIGHT*5/2.5;
	
	//Character's x and y on the array
	private double charX, charY, lastCharY;
	
	//Health values 1,2,3 and 4
	private float health, exercise, happiness, social, study, energy;
	
	//Song
	public static Music music;
	
	//Time for health depletion 
	private long currentT, lastT;
	
	private boolean alive, paused, initScene, tipMsg;
	
	//EVENTS
	private boolean eventRun;
	private boolean[] bEvents = new boolean[11];
	private boolean[] eventText = new boolean[11];

	private String[] sEvents = {
			"You have a power outage! Internet related activites\nhave been disabled.", //Passive
			"You have a stomach ache! Your health bar goes down by\n10.", //Instant
			"You have met an online friend recently who is asking\nfor your personal info. What do you do?", //Instant
			"You feel sharp pain while exercising. What do you do?", //Instant
			"Your parents are upset at you for something you don’t\nagree with! What do you do?", //Instant
			"Your friends are upset at you for something you don’t\nagree with! What do you do?"}; //Instant
//	"You have exercised too much! Your energy bar goes\ndown by 10.",
//	"You are addicted to video games! Social and health\ndepletion rate doubled.",
//	"You are having a hard time making friends and coping\nwith misfortunate events. Social and health depletion\nrate doubled. Happiness depletion rate tripled.",
//	"You don’t like the taste of all this healthy food!\nYour happiness bar goes down by 10."};

	
	private int rand=10, tipNum;
	//END EVENTS
	
	//TIPS
	private String[] tips = {
			"It's good to eat healthy but it's ok to break the\nrules once in a while.",
			"Exercising at the gym is really effective! Make sure\nyou don't overdo it though!",
			"Exercise is great for your body, but make sure you\ndon't overdo it!",
			"Arcades are really fun! Try not to get addicted!",
			"It's important to be happy with your life. Don't get\ncarried away though!",
			"Meeting freinds in real life is really enjoyable!\nTalking to frinds online is also fun and convenient.",
			"Talking to friends online is really convenient!\nMeeting freinds can be even more fun too!",
			"Studying in a work environment is really effective\nsince you can focus! Studying can be boring so make\nsure you relax too.",
			"Studying is really important in order to have a\nsucessful future. Studying can be boring so make sure\nyou relax too.",
			"Relaxing at home is really effective and\nrefreshing!",
			"Relaxing is important in order to reduce stress\nand fatigue."};
	
	private FreeTypeFontGenerator gen,gen1;
	private FreeTypeFontParameter param,param1;
	private GlyphLayout glyph,glyph1;
	
	private long eCurrT,eLastT;

	//Debugging mode
	private final boolean DEBUG = true;
	
	
    /**
     * {@link BonusLevel} constructor
     * @param sm screen manager to determine current screen
     * @param man asset manager to load images
     */
    public BonusLevel(ScreenManager sm,AssetManager man) {
        super(sm, man);
        alive = true;
        text = new Text(45,Color.WHITE,man);
        paused=false;
        initScene = true;
        health = 1f;
        exercise = 1f;
        happiness = 1f;
        social = 1f;
        study = 1f;
        energy = 1f;
//        events = new ArrayList<Event>();
//        Map<String,float[]> temp = new HashMap<String,float[]>();
//        events.add(new Event("Your power went out! All internet related activites are disabled.", true));
//        events.add(new Event("You have a stomach ache! Your physical health decreases by 10.",null,new float[] {-0.1f,0,0,0,0},null,""));
//        events.add(new Event("You have exercised too much! Your energy decreases by 10.",null,new float[] {0,0,0,0,-0.1f},null,""));
        
        
//        temp.put("Tell your parents", new float[] {0f,0f,0f,0f,0f});
//        temp.put("Tell him your personal information", new float[] {0f,0f,0f,(int)(Math.random()*2)==0?0f:-0.5f,0f});
//        temp.put("Politely tell him that you won't tell him your address", new float[] {0f,0f,0f,0.1f,0f});
//        temp.put("Block him", new float[] {0f,0f,0f,-0.1f,0f});
//        
//        events.add(new Event("Met an online friend recently who is asking for your personal info. What do you do?",temp,null,null,"Never tell your personal info to someone you just met. They could be an online predator!"));
//        events.add(new Event("You're addicted to video games! Social loss doubled. Health loss doubled",null,null,new float[] {2,1,1,2,1},""));
//        events.add(new Event("You are having a hard time making friends and coping with misfortunate events. Social loss doubled. Health loss doubled. Happiness loss tripled.",null,null,new float[] {2,1,3,2,1},""));
//        
//        temp = new HashMap<String,float[]>();
//        temp.put("Keep going. No pain no gain!", new float[] {-0.2f,0f,0f,0f,0f});
//        temp.put("Stop exercising", new float[] {0f,0f,0f,10f,0f});
//        
//        events.add(new Event("Feeling sharp pain while exercising. What do you do?",temp,null,null,""));
//        
//        
//        temp = new HashMap<String,float[]>();
//        temp.put("Yell back", new float[] {0f,0f,-0.05f,-0.05f,0f});
//        temp.put("Take the punishment and say nothing", new float[] {0f,0f,-0.5f,0f,0f});
//        events.add(new Event("Parents are upset at you for something you don't agree with! What do you do?",temp,null,null,""));
//        events.add(new Event("Friends are upset at you for something you don't agree with! What do you do?",temp,null,null,""));
        
        
        music = Gdx.audio.newMusic(Gdx.files.internal("bonussong.mp3"));
        music.setVolume(SettingsScreen.sound);
        music.setLooping(true);
        music.play();
        background = man.get("Background.png",Texture.class);
        map = man.get("map.png",Texture.class); 
        indoorMap = man.get("indoorMap.png",Texture.class);
		minimap = man.get("map.png",Texture.class);
		blackdot = man.get("blackdot.png",Texture.class);
		bar1 = man.get("blank.jpg",Texture.class);
		bar2 = man.get("blank.jpg",Texture.class);
		bar3 = man.get("blank.jpg",Texture.class);
		bar4 = man.get("blank.jpg",Texture.class);
		bar5 = man.get("blank.jpg",Texture.class);
		bar6 = man.get("blank.jpg",Texture.class);
		act1 = man.get("settingIdle.png",Texture.class);
		act2 = man.get("settingIdle.png",Texture.class);
		act3 = man.get("settingIdle.png",Texture.class);
		act4 = man.get("settingIdle.png",Texture.class);
		act5 = man.get("settingIdle.png",Texture.class);
		act6 = man.get("settingIdle.png",Texture.class);
		try {
		    collisionPic = ImageIO.read(new File("collisionDetection.png"));
		} catch (IOException e) {
		}
		
		//Transpose map to an array of colours
		collisionArr = new java.awt.Color [collisionPic.getHeight()][collisionPic.getWidth()];
		for (int row = 0; row < collisionPic.getHeight(); row++ ) {
			for (int col = 0; col < collisionPic.getWidth(); col++) {
				collisionArr[row][col]= new java.awt.Color(collisionPic.getRGB(col, row));
			}
		}
		
		//Character is in the centre of the array
		charX = collisionPic.getWidth()/2;
		charY = collisionPic.getHeight()/2;
		
		person = new Sprite(man.get("person.png",Texture.class));
		
		//Scale character
		person.setSize((int)(person.getWidth()*1.5), (int)(person.getHeight()*1.5));
		
		gen = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param = new FreeTypeFontParameter();
		param.size = 65;
		font = gen.generateFont(param);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.getData().markupEnabled = true;
		glyph = new GlyphLayout();
		
		gen1 = new FreeTypeFontGenerator(Gdx.files.internal("HeadlinerNo.45 DEMO.ttf"));
		param1 = new FreeTypeFontParameter();
		param1.size = 15;
		font1 = gen.generateFont(param1);
		font1.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font1.getData().markupEnabled = true;
		glyph1 = new GlyphLayout();
		font1.setColor(Color.BLACK);
		
		//Character is in the centre of the screen
		person.setPosition(JTCM.WIDTH/2-person.getWidth()/2, JTCM.HEIGHT/2);
		lastT = System.currentTimeMillis();
	}

	/**
	 * This method handles input.
	 */
	@Override
	public void getInput()  {
		
		//START OF INPUT FOR CHARACTER MOVEMENT
		try {
			if (Gdx.input.isKeyPressed(Keys.RIGHT) && (!collisionArr[(int)Math.floor(charY)][(int)Math.round(charX+7.5)].equals(java.awt.Color.BLACK) || !collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX+7.5)].equals(java.awt.Color.BLACK))) {
				xCoord -= JTCM.WIDTH*5.0/map.getWidth()/2*5;
				charX += 2.5;
				energy = Math.max(0,energy-0.001f);
			}
			if (Gdx.input.isKeyPressed(Keys.LEFT) && (!collisionArr[(int)Math.floor(charY)][(int)Math.round(charX-10)].equals(java.awt.Color.BLACK) || !collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX-10)].equals(java.awt.Color.BLACK))) {
				xCoord += JTCM.WIDTH*5.0/map.getWidth()/2*5;
				charX -= 2.5;
				energy = Math.max(0,energy-0.001f);
			}
			if (Gdx.input.isKeyPressed(Keys.UP) && (!collisionArr[(int)Math.ceil(charY-5)][(int)Math.round(charX+5)].equals(java.awt.Color.BLACK) && !collisionArr[(int)Math.floor(charY)][(int)Math.round(charX-7.5)].equals(java.awt.Color.BLACK) && !collisionArr[(int)Math.ceil(charY-5)][(int)Math.round(charX-7.5)].equals(java.awt.Color.BLACK) && !collisionArr[(int)Math.floor(charY)][(int)Math.round(charX+5)].equals(java.awt.Color.BLACK))) {
				yCoord -= JTCM.HEIGHT*5.0/map.getHeight()/2*5;
				charY -= 2.5;
				energy = Math.max(0,energy-0.001f);
			}
			if (Gdx.input.isKeyPressed(Keys.DOWN) && (!collisionArr[(int)Math.floor(charY+2.5)][(int)Math.round(charX)].equals(java.awt.Color.BLACK) && !collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX-7.5)].equals(java.awt.Color.BLACK))) {
				yCoord += JTCM.HEIGHT*5.0/map.getHeight()/2*5;
				charY += 2.5;
				energy = Math.max(0,energy-0.001f);
			}
			if(Gdx.input.justTouched()&& Gdx.input.getX()>=0 && Gdx.input.getX()<=(minimap.getWidth()/5)+5 && Gdx.input.getY()>=JTCM.HEIGHT-minimap.getHeight()/5 && Gdx.input.getY()<=JTCM.HEIGHT)
				sm.push(new MinimapScreen(sm,man));
		} catch (ArrayIndexOutOfBoundsException e) {}
		//END OF INPUT FOR CHARACTER MOVEMENT
		
		//START OF INPUT FOR ACTION-BAR CLICKING
		if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act1.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-550 && Gdx.input.getY()>=JTCM.HEIGHT-550-act1.getHeight())
		{
			health = Math.min(1, health+0.05f);
			energy = Math.max(0,energy-0.01f);
			tipMsg = true;
			tipNum = 0;
		}
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act2.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-450 && Gdx.input.getY()>=JTCM.HEIGHT-450-act2.getHeight())
		{
			if (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.BLUE) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.BLUE))
			{
				exercise = Math.min(1, exercise+0.1f);
				tipNum = 1;	
			}
			else
			{
				exercise = Math.min(1, exercise+0.05f);
				tipNum = 2;
			}
			energy = Math.max(0,energy-0.01f);
			tipMsg = true;

		}
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act3.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-350 && Gdx.input.getY()>=JTCM.HEIGHT-350-act3.getHeight())
		{
			if (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.MAGENTA) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.MAGENTA))
			{
				happiness = Math.min(1, happiness+0.1f);
				tipNum = 3;	
			}
			else
			{
				happiness = Math.min(1, happiness+0.05f);
				tipNum = 4;
			}
			energy = Math.max(0,energy-0.01f);
			tipMsg = true;
			
		}
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act4.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-250 && Gdx.input.getY()>=JTCM.HEIGHT-250-act4.getHeight())
		{
			if (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.CYAN) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.CYAN))
			{
				social = Math.min(1, social+0.1f);
				tipNum = 5;
			}
			else
			{
				social = Math.min(1, social+0.05f);
				tipNum = 6;
			}
			energy = Math.max(0,energy-0.01f);
			happiness = Math.min(1, happiness+0.05f);
			tipMsg = true;
		}
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act5.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-150 && Gdx.input.getY()>=JTCM.HEIGHT-150-act5.getHeight())
		{
			if (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.YELLOW) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.YELLOW))
			{
				study = Math.min(1, study+0.1f);
				tipNum = 7;
			}
			else
			{
				study = Math.min(1, study+0.05f);
				tipNum = 8;	
			}
			happiness = Math.max(0, happiness-0.05f);
			tipMsg = true;
		}
			
		else if (Gdx.input.justTouched()&&Gdx.input.getX()>=JTCM.WIDTH-act6.getWidth()-5 && Gdx.input.getX()<=JTCM.WIDTH-5&& Gdx.input.getY()<=JTCM.HEIGHT-50 && Gdx.input.getY()>=JTCM.HEIGHT-50-act6.getHeight())
		{
			if (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.RED) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.RED))
			{
				energy = Math.min(1, energy+0.5f);
				tipNum = 9;
			}
			else
			{
				energy = Math.min(1, energy+0.1f);
				tipNum = 10;	
			}
			tipMsg = true;
		}
		//END OF INPUT FOR ACTION-BAR CLICKING
		
		//START OF DEBUG TOOLS
		if(DEBUG) {
			//Brings health down
			if(Gdx.input.isKeyPressed(Keys.NUM_1))
				health = Math.max(0,health-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_2))
				exercise = Math.max(0,exercise-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_3))
				happiness= Math.max(0,happiness-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_4))
				social = Math.max(0,social-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_5))
				study = Math.max(0, study-0.01f);
			if(Gdx.input.isKeyPressed(Keys.NUM_6))
				energy = Math.max(0, energy-0.01f);
			
			//Resets health
			if(Gdx.input.isKeyJustPressed(Keys.Q))
				health =1f;
			if(Gdx.input.isKeyJustPressed(Keys.W))
				exercise =1f;
			if(Gdx.input.isKeyJustPressed(Keys.E))
				happiness =1f;
			if(Gdx.input.isKeyJustPressed(Keys.R))
				social =1f;
			if(Gdx.input.isKeyJustPressed(Keys.T))
				study =1f;
			if(Gdx.input.isKeyJustPressed(Keys.Y))
				energy =1f;

			if(Gdx.input.isKeyJustPressed(Keys.P) && (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.GREEN) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.GREEN)))
				sm.push(new LibraryScreen(sm,man));
		}
		//END OF DEBUG TOOLS
	}
	
	/**
	 * This method checks if the user would like to stop an event message.
	 */


	/**
	 * Handles input for settings.
	 */
	private void checkSetting()
	{
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			JTCM.getSettingsScreen().setMusic(music); //Change music target
			sm.push(JTCM.getSettingsScreen());
		}
	}
	
	/**
	 * Handles input for exiting the game
	 */
	private void checkExit()
	{
		if(Gdx.input.isKeyJustPressed(Keys.X)) {
			sm.pop();
		}
	}
	
	/**
	 * Debugger method, prints the current score.
	 */
	private void printScore()
	{
		if(Gdx.input.isKeyJustPressed(Keys.L))
			System.out.println((health + exercise+happiness +social+energy)*100);
	}
	
	private void checkPaused() {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			paused = !paused;
			if(music.isPlaying())
				music.pause();
			else
				music.play();
			System.out.println("Paused button was pressed: "+paused);
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
		//System.out.println(textRun);
		if(!paused) {
			if(alive && !initScene&&!eventText[rand>=eventText.length?10:rand]&&!tipMsg){
				getInput();
				currentT = System.currentTimeMillis();
				
				if(!eventRun)
					eCurrT = System.currentTimeMillis();
				
				//Checks if 1 second has passed

				if(currentT-lastT>=1000)
				{
					float rate = getRate();
					health = Math.max(0,health-rate*(bEvents[4]||bEvents[5]?2:1));
					exercise = Math.max(0,exercise-rate);
					happiness = Math.max(0,happiness-rate*(bEvents[5]?3:1));
					social = Math.max(0,social-rate*(bEvents[4]||bEvents[5]?2:1));
					study = Math.max(0,study-rate);
					lastT = System.currentTimeMillis();
				}
				if(eCurrT-eLastT>=3000)
				{
					rand = (int)(Math.random()*75);
					System.out.println(rand);
					if(rand<6&&!eventRun) {
						System.out.println("event will run now");
						eventRun = true;
						eventText[rand] = true;
						bEvents[rand] = true;
					}
					eLastT = System.currentTimeMillis();
				}
				if(health <=0 || exercise<=0 || happiness<=0 || social<=0 || energy<=0)
					alive = false;
				
			}
			//checkDialogue();
			handleText();
			checkSetting();
			checkExit();
			
			if(!alive)
				music.stop();
		}
		checkPaused();
		if(DEBUG)
			printScore();

	}
	private void handleText() {
		if(text.done()&& Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if(initScene) {
				text.resetPrint();
				initScene = false;
			}
			if(tipMsg) {
				text.resetPrint();
				tipMsg = false;
			}
			else if(eventText[rand]) {
				text.resetPrint();
				eventText[rand] = false;
				if(bEvents[0]) {
					eventRun = false;
				}
				if(bEvents[1]) {
					if(!eventText[1]) {
						health-=0.1f;
						eventRun = false;
					}
					bEvents[1] = false;
				}
				else if(bEvents[2]) {
					bEvents[2] = false;
					if(!eventText[2]) {
						sm.push(new ChoiceScreen(sm,man,"Tell your parents","Tell him your personal info","Politely tell him that you won’t tell him your address","Block him"));
						if(ChoiceScreen.choice == 1)
						{}
						else if(ChoiceScreen.choice == 2)
						{}
						else if(ChoiceScreen.choice == 3)
						{}
						else if(ChoiceScreen.choice == 4)
						{}
						ChoiceScreen.choice=0;
						eventRun = false;
					}
				}
				else if(bEvents[3]) {
					bEvents[3] = false;
					if(!eventText[3]) {
						sm.push(new ChoiceScreen(sm,man,"Keep going. No pain no gain!","Stop exercising"));
						if(ChoiceScreen.choice == 1)
						{}
						else if(ChoiceScreen.choice == 2)
						{}
						else if(ChoiceScreen.choice == 3)
						{}
						ChoiceScreen.choice=0;
						eventRun = false;
					}
				}
				else if(bEvents[4]) {
					bEvents[4] = false;
					if(!eventText[4]) {
						sm.push(new ChoiceScreen(sm,man,"Yell back","Take the punishment and say nothing","Calmly and peacefully talk with your parents"));
						if(ChoiceScreen.choice == 1)
						{}
						else if(ChoiceScreen.choice == 2)
						{}
						else if(ChoiceScreen.choice == 3)
						{}
						ChoiceScreen.choice=0;
						eventRun = false;
					}
				}
				else if(bEvents[5]) {
					bEvents[5] =false;
					if(!eventText[5]) {
						sm.push(new ChoiceScreen(sm,man,"Yell back","Take the punishment and say nothing","Calmly and peacefully talk with your parents"));
						if(ChoiceScreen.choice == 1)
						{}
						else if(ChoiceScreen.choice == 2)
						{}
						else if(ChoiceScreen.choice == 3)
						{}
						ChoiceScreen.choice=0;
						eventRun = false;
					}
				}
			}
		}
	}
	
	/**
	 * This method finds out how fast each bar should deplete
	 * @return rate
	 */
	private float getRate() {
		int countGreen=0,countYellow=0,countRed=0;
		float[] arr = {health,exercise,happiness,social,energy};
		for(float x : arr) {
			if (x>0.7f)
				countGreen++;
			else if (x>0.3f)
				countYellow++;
			else
				countRed++;
		}
		return (countGreen>0?0.01f:0)+ 0.015f*countYellow + 0.02f*countRed;
	}

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	@Override
	public void render(SpriteBatch s) {
		s.begin(); //Begins sprite batch
		if(alive) {
			
			//Draw background
			s.draw(background,Math.round(xCoord-750),Math.round(yCoord+500),JTCM.WIDTH*5,JTCM.HEIGHT*5);
			s.draw(background,Math.round(xCoord+750),Math.round(yCoord-500),JTCM.WIDTH*5,JTCM.HEIGHT*5);
			
			//Check if player is indoors
			if ((!collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.WHITE) && !collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.BLACK)) || (!collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.WHITE)&&!collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX)].equals(java.awt.Color.BLACK)))
			{
				//Indoor Map
				s.draw(indoorMap, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
			}
			else
			{
				//Map
				s.draw(map, Math.round(xCoord), Math.round(yCoord), JTCM.WIDTH*5, JTCM.HEIGHT*5);
			}

			
			//Rectangular border
			s.draw(blackdot,0,0,minimap.getWidth()/5+5, minimap.getHeight()/5+5);
			
			//Minimap
			s.draw(minimap, 0, 0, minimap.getWidth()/5, minimap.getHeight()/5);
			
			//Check if player is on the path for black dot drawing
			if (collisionArr[(int)Math.floor(charY)][(int)Math.round(charX)].equals(java.awt.Color.WHITE) || collisionArr[(int)Math.ceil(charY)][(int)Math.round(charX-7.5)].equals(java.awt.Color.WHITE))
				lastCharY = charY;

			//Dot on minimap
			s.draw(blackdot, (float)(charX/5-5),(float)(179-lastCharY/5-5), 10, 10);
					
			//Person
			s.draw(person, person.getX(), person.getY(),person.getWidth(),person.getHeight());
			
			//Changes colours of health bar and draws bars 1,2,3 and 4
			s.setColor(health>0.7f?Color.GREEN:health>0.3f?Color.YELLOW:Color.RED);
			s.draw(bar1, 10, JTCM.HEIGHT-20,JTCM.WIDTH/4* health,10);
			s.setColor(exercise>0.7f?Color.GREEN:exercise>0.3f?Color.YELLOW:Color.RED);
			s.draw(bar2, 10, JTCM.HEIGHT-35,JTCM.WIDTH/4*exercise,10);
			s.setColor(happiness>0.7f?Color.GREEN:happiness>0.3f?Color.YELLOW:Color.RED);
			s.draw(bar3, 10, JTCM.HEIGHT-50,JTCM.WIDTH/4*happiness,10);
			s.setColor(social>0.7f?Color.GREEN:social>0.3f?Color.YELLOW:Color.RED);
			s.draw(bar4, 10, JTCM.HEIGHT-65,JTCM.WIDTH/4*social,10);
			s.setColor(study>0.7f?Color.GREEN:study>0.3f?Color.YELLOW:Color.RED);
			s.draw(bar5, 10, JTCM.HEIGHT-80,JTCM.WIDTH/4*study,10);
			s.setColor(energy>0.7f?Color.GREEN:energy>0.3f?Color.YELLOW:Color.RED);
			s.draw(bar6, 10, JTCM.HEIGHT-95,JTCM.WIDTH/4*energy,10);
			
			//Reset tint
			s.setColor(Color.WHITE);
			
			//Start drawing action bar text
			glyph1.setText(font1, "Health");
			font1.draw(s, glyph1, 10, JTCM.HEIGHT-7);
			glyph1.setText(font1, "Exercise");
			font1.draw(s, glyph1, 10, JTCM.HEIGHT-22);
			glyph1.setText(font1, "Happiness");
			font1.draw(s, glyph1, 10, JTCM.HEIGHT-37);
			glyph1.setText(font1, "Social");
			font1.draw(s, glyph1, 10, JTCM.HEIGHT-52);
			glyph1.setText(font1, "Study");
			font1.draw(s, glyph1, 10, JTCM.HEIGHT-67);
			glyph1.setText(font1, "Energy");
			font1.draw(s, glyph1, 10, JTCM.HEIGHT-82);
			//End action bar text			
			
			//Action bar holder
	        s.setColor(new Color(1,1,1,0.7f));
	        s.draw(blackdot,JTCM.WIDTH-150,0,125,JTCM.HEIGHT);
	        s.setColor(Color.WHITE);
			
			//Action bars 1,2,3 and 4
			s.draw(act1,JTCM.WIDTH-act1.getWidth()-40,550);
			s.draw(act2,JTCM.WIDTH-act2.getWidth()-40,450);
			s.draw(act3,JTCM.WIDTH-act3.getWidth()-40,350);
			s.draw(act4,JTCM.WIDTH-act4.getWidth()-40,250);
			s.draw(act5,JTCM.WIDTH-act5.getWidth()-40,150);
			s.draw(act6,JTCM.WIDTH-act6.getWidth()-40,50);
			
			//TEXT DRAWING AREA
			if(initScene) {
				text.printText("Hi "+NameScreen.getName()+",\nI'm the narrator... you have depression!", s, 85, paused);
			}
			if(tipMsg)
			{
				text.printText(tips[tipNum], s, 85, paused);
			}
			if(eventRun && eventText[rand]) {
				text.printText(sEvents[rand], s, 85, paused);
			}
//			else if(eventRun&&event.run()) {
//				events.get(currEvent).trigger();
//				events.get(currEvent).check();
//				
//			}

			//END TEXT DRAWING AREA
		}
		else
		{
			glyph.setText(font, "[RED]You are literally dead.[]");
			font.draw(s, glyph, JTCM.WIDTH/2-glyph.width/2, JTCM.HEIGHT/2-glyph.height/2);
		}
		SettingsScreen.applyBrightness(s);
		if(paused)
		{
			s.setColor(1,1,1,0.8f);
			s.draw(blackdot, 0,0,JTCM.WIDTH, JTCM.HEIGHT);
			s.setColor(Color.WHITE);
			glyph.setText(font, "THE GAME IS LITERALLY PAUSED");
			font.draw(s, glyph, JTCM.WIDTH/2-glyph.width/2, JTCM.HEIGHT/2-glyph.height/2);	
		}
		s.end(); //Ends sprite batch

	}

	/**
	 * This method disposes unneeded resources.
	 */
	@Override
	public void dispose() {
		music.dispose();
	}

}
