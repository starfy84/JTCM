/**
 * 
 */
package screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.main.game.JTCM;
/**
 * This class contains a blueprint of a screen in our game </br>
 * Teacher: Ms. Krasteva </br>
 * Date: 6/8/18 </br>
 * @version 1
 * @author Dereck
 */
public abstract class Screen {
	protected OrthographicCamera cam;
	protected Vector3 pointer;
	protected ScreenManager sm;
    protected AssetManager man;
    protected TmxMapLoader ml;
    protected TiledMap tm;
    protected OrthogonalTiledMapRenderer otmr;
    protected Viewport vp;
    protected World world;
	/**
	 * {@link Screen} Constructor
	 * 
	 * @param sm
	 *            Screen manager.
	 */
        public Screen(ScreenManager sm,AssetManager man) {
                this.sm = sm;
                this.man = man;
                cam = new OrthographicCamera(JTCM.WIDTH,JTCM.HEIGHT);
                vp = new FitViewport(JTCM.WIDTH,JTCM.HEIGHT,cam);
                //cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                pointer = new Vector3();
        }

	/**
	 * This method handles input.
	 */
	public abstract void getInput();

	/**
	 * This method will be run on a loop.
	 * 
	 * @param t
	 *            Delta time.
	 */
	public abstract void update(double t);

	/**
	 * This method draws my graphics
	 * 
	 * @param s
	 *            The needed sprite batch.
	 */
	public abstract void render(SpriteBatch s);

	/**
	 * This method disposes unneeded resources.
	 */
	public abstract void dispose();
}
