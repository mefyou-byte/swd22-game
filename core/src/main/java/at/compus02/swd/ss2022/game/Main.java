package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Player;
import at.compus02.swd.ss2022.game.gameobjects.Position;
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

import java.util.ArrayList;
import java.util.Random;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
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
    private ArrayList<Position> waterTilesPositions = new ArrayList<>();
    private static final float TILE_WIDTH = 32;
    private static final float TILE_HEIGHT = 32;

    @Override
    public void create() {
        AssetRepository repository = AssetRepository.getInstance();
        repository.preloadAssets();
        batch = new SpriteBatch();

        fillFieldWithTiles();

        Sign sign = new Sign();
        sign.setPosition(-16, -16); // set sign exactly in the center of the game
        gameObjects.add(sign);


        createPlayer();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
    }

    private void createPlayer() {
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        playerFactory.create(GameObjectType.PLAYER);
        gameObjects.add(playerFactory.getObjects()[0]);
    }


    private void fillFieldWithTiles() {
        Random random = new Random();

        float x_from = -1 * viewport.getMinWorldWidth() / 2;
        float x_to = viewport.getMaxWorldWidth();
        float y_from = -1 * viewport.getMinWorldHeight() / 2;
        float y_to = viewport.getMaxWorldHeight();

        float x = x_from;



        System.out.println("x_from: " + x_from);
        System.out.println("x_to: " + x_to);
        System.out.println("y_from: " + y_from);
        System.out.println("y_to: " + y_to);

        while (x < x_to){
            float y = y_from;

            while (y < y_to){

                int randomInt = random.nextInt(100);

                if (randomInt < 15) {
                    TileFactory.getInstance().create(GameObjectType.WATER).setPosition(x, y);
                    waterTilesPositions.add(new Position(x, y));
                } else if (randomInt < 35) {
                    // add Grass below Bush --> looks better
                    TileFactory.getInstance().create(GameObjectType.GRASS).setPosition(x, y);
                    // TileFactory.getInstance().create(GameObjectType.BUSH).setPosition(i, j);
                } else {
                    TileFactory.getInstance().create(GameObjectType.GRASS).setPosition(x, y);
                }

                y += TILE_HEIGHT;
            }

            x += TILE_WIDTH;
        }

        
        for (GameObject gameObject : TileFactory.getInstance().getObjects()) {
            gameObjects.add(gameObject);
        }

    }







    private void act(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.act(delta, waterTilesPositions);
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