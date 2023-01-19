package at.compus02.swd.ss2022.game;

import at.compus02.swd.ss2022.game.GameObserver.EnemyPositionObserver;
import at.compus02.swd.ss2022.game.GameObserver.PlayerPositionObserver;
import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import at.compus02.swd.ss2022.game.common.ConsoleLogger;
import at.compus02.swd.ss2022.game.common.UserInterfaceLogger;
import at.compus02.swd.ss2022.game.factories.EnemyFactory;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.factories.TileFactory;
import at.compus02.swd.ss2022.game.gameobjects.*;
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
    private ArrayList<Position> waterTilesPositions = new ArrayList<>();
    public static final float TILE_WIDTH = 32;
    public static final float TILE_HEIGHT = 32;
    private UserInterfaceLogger userInterfaceLogger;
    private ConsoleLogger consoleLogger;


    @Override
    public void create() {
        AssetRepository repository = AssetRepository.getInstance();
        repository.preloadAssets();
        batch = new SpriteBatch();

        fillFieldWithTiles();
        createPlayer();
        createEnemies();
        initializeLogger();
        initializeObservers();

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
    }

    private void createPlayer() {
        PlayerFactory playerFactory = PlayerFactory.getInstance();
        playerFactory.create(GameObjectType.PLAYER);
        gameObjects.add(playerFactory.getObjects()[0]);
    }

    private void createEnemies() {
        EnemyFactory enemyFactory = EnemyFactory.getInstance();
        for (int i = 0; i < 5; i++) {
            enemyFactory.create(GameObjectType.ENEMY);
        }
        for (GameObject object : enemyFactory.getObjects()) {
            gameObjects.add(object);
        }
    }

    private void initializeLogger() {
        userInterfaceLogger = UserInterfaceLogger.getInstance();
        consoleLogger = ConsoleLogger.getInstance();
    }

    private void initializeObservers() {
        Player player = (Player) PlayerFactory.getInstance().getObjects()[0];
        PlayerPositionObserver playerPositionObserverUI = new PlayerPositionObserver(userInterfaceLogger);
        PlayerPositionObserver playerPositionObserverConsole = new PlayerPositionObserver(consoleLogger);
        player.addObserver(playerPositionObserverUI);
        player.addObserver(playerPositionObserverConsole);

        Enemy enemy = (Enemy) EnemyFactory.getInstance().getObjects()[0];
        EnemyPositionObserver enemyPositionObserverConsole = new EnemyPositionObserver(consoleLogger);
        enemy.addObserver(enemyPositionObserverConsole);
    }

    private void fillFieldWithTiles() {
        Random random = new Random();

        int seed = 93;
        random.setSeed(seed);

        float x_from = -1 * viewport.getMinWorldWidth() / 2;
        float x_to = viewport.getMaxWorldWidth();
        float y_from = -1 * viewport.getMinWorldHeight() / 2;
        float y_to = viewport.getMaxWorldHeight();

        float x = x_from;

        TileFactory tileFactory = TileFactory.getInstance();

        while (x < x_to){
            float y = y_from;

            while (y < y_to){

                int randomInt = random.nextInt(100);

                if (randomInt < 15) {
                    tileFactory.create(GameObjectType.WATER).setPosition(x, y);
                    waterTilesPositions.add(new Position(x, y));
                } else if (randomInt < 35) {
                    // add Grass below Bush --> looks better
                    tileFactory.create(GameObjectType.GRASS).setPosition(x, y);
                    //TileFactory.getInstance().create(GameObjectType.BUSH).setPosition(x, y);
                } else {
                    tileFactory.create(GameObjectType.GRASS).setPosition(x, y);
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

        userInterfaceLogger.draw(batch);

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