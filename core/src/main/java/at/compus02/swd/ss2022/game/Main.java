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

import java.util.Random;

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

		//TODO --> MOVE to own function // calls which only handles filling game with objects
		//Calculation of positioning is based on game field size of 480x480
		TileFactory tileFactory = TileFactory.getInstance();

		Random random = new Random();

		for (int i = -240; i < 240; i += 32) {
			for (int j = -240; j < 240; j += 32) {

				if (i == -16 && j == -16) {
					//grass for sign
					tileFactory.create(GameObjectType.Grass).setPosition(i, j);
					continue;
				}
				int randomInt = random.nextInt(100);

				if (randomInt < 15) {
					tileFactory.create(GameObjectType.Water).setPosition(i, j);
				} else if (randomInt < 35) {
					// add Grass below Bush --> looks better
					tileFactory.create(GameObjectType.Grass).setPosition(i, j);
					tileFactory.create(GameObjectType.Bush).setPosition(i, j);
				} else {
					tileFactory.create(GameObjectType.Grass).setPosition(i, j);
				}
			}
		}

		for (GameObject gameObject : tileFactory.getObjects()) {
			gameObjects.add(gameObject);
		}


		//set sign exactly in the center of the game
		Sign sign = new Sign();
		sign.setPosition(-16, -16);
		gameObjects.add(sign);


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