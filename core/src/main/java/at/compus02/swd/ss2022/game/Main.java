package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Player;
import at.compus02.swd.ss2022.game.gameobjects.Sign;
import at.compus02.swd.ss2022.game.input.GameInput;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private final ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
    private final GameInput gameInput = new GameInput();
    private final Array<GameObject> gameObjects = new Array<>();
    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private SpriteBatch batch;
    private float deltaAccumulator = 0;
    private BitmapFont font;
    private Player player;

    @Override
    public void create() {
        AssetRepository repository = AssetRepository.getInstance();
        repository.preloadAssets();
        batch = new SpriteBatch();

        fillFieldWithTiles();

        Sign sign = new Sign();
        sign.setPosition(-16, -16); //set sign exactly in the center of the game
        gameObjects.add(sign);


        //TODO - move out to separate function ?
        // moved to function createPlayer
        createPlayer();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
    }

    private void createPlayer(){
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        playerFactory.create(GameObjectType.PLAYER);
        gameObjects.add(playerFactory.getObjects()[0]);
    }
    private void fillFieldWithTiles() {
        Random random = new Random();


        for (float i = -1 * viewport.getMinWorldWidth() / 2; i < viewport.getMaxWorldWidth(); i += 32) {
            for (float j = -1 * viewport.getMinWorldHeight() / 2; j < viewport.getMaxWorldHeight(); j += 32) {

                if (i == -16 && j == -16) {
                    //grass for sign // center of the field
                    TileFactory.getInstance().create(GameObjectType.GRASS).setPosition(i, j);
                    continue;
                }
                int randomInt = random.nextInt(100);

                if (randomInt < 15) {
                    TileFactory.getInstance().create(GameObjectType.WATER).setPosition(i, j);
                } else if (randomInt < 35) {
                    // add Grass below Bush --> looks better
                    TileFactory.getInstance().create(GameObjectType.GRASS).setPosition(i, j);
                    TileFactory.getInstance().create(GameObjectType.BUSH).setPosition(i, j);
                } else {
                    TileFactory.getInstance().create(GameObjectType.GRASS).setPosition(i, j);
                }
            }
        }

        for (GameObject gameObject : TileFactory.getInstance().getObjects()) {
            gameObjects.add(gameObject);
        }
    }

    private void act(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.act(delta);
        }
    }

    private void draw() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        for (GameObject gameObject : gameObjects) {
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
        while (deltaAccumulator > logicFrameTime) {
            deltaAccumulator -= logicFrameTime;
            act(logicFrameTime);
        }
        draw();
    }

    @Override
    public void dispose() {
        AssetRepository repository = AssetRepository.getInstance();
        repository.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}