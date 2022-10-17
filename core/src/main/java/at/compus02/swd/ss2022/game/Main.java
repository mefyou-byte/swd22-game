package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.factories.Factory;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Player;
import at.compus02.swd.ss2022.game.gameobjects.Sign;
import at.compus02.swd.ss2022.game.gameobjects.Tile;
import at.compus02.swd.ss2022.game.input.GameInput;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
	private GameInput gameInput = new GameInput();

	private Array<GameObject> gameObjects = new Array<>();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;
	private Player player;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gameObjects.add(new Sign());


		//TODO move to new function?
		//TODO set position in Tile Class? Similar to Player but random?
		TileFactory tileFactory = TileFactory.getInstance();
		tileFactory.create(GameObjectType.Water).setPosition(50, 50);
		tileFactory.create(GameObjectType.Bush).setPosition(75, 75);
		tileFactory.create(GameObjectType.Grass).setPosition(100, 100);

		//TODO fill complete map with tiles

		for (GameObject gameObject : tileFactory.getObjects()) {
			gameObjects.add(gameObject);
		}


		//player = new Player();
		//gameObjects.add(player);

		PlayerFactory playerFactory = PlayerFactory.getInstance();
		playerFactory.create(GameObjectType.Player);
		gameObjects.add(playerFactory.getObjects()[0]);



		font = new BitmapFont();
		font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(this.gameInput);
	}

	private void act(float delta) {
		for(GameObject gameObject : gameObjects) {
			gameObject.act(delta);
		}
	}

	private void draw() {
		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		for(GameObject gameObject : gameObjects) {
			gameObject.draw(batch);
		}
		font.draw(batch, "Hello Game", -220, -220);
		batch.end();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		float delta = Gdx.graphics.getDeltaTime();
		deltaAccumulator += delta;
		while(deltaAccumulator > logicFrameTime) {
			deltaAccumulator -= logicFrameTime;
			act(logicFrameTime);
		}
		draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}
}