package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import at.compus02.swd.ss2022.game.command.MoveDownCommand;
import at.compus02.swd.ss2022.game.command.MoveLeftCommand;
import at.compus02.swd.ss2022.game.command.MoveRightCommand;
import at.compus02.swd.ss2022.game.command.MoveUpCommand;
import at.compus02.swd.ss2022.game.command.SpaceBarCommand;
import at.compus02.swd.ss2022.game.input.GameInput;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player implements GameObject {
    private final Texture image;
    public final Sprite sprite;
    public final Sprite spriteBuff;

    private final int berserkerModeDuration = 5000;
    private boolean isBuffActivatedAndVisible = false;

    public Player() {
        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("player");
        sprite = new Sprite(image);
        sprite.setScale((float) 0.035);
        Texture imageBuff = repo.getTexture("player");
        spriteBuff = new Sprite(imageBuff);
        setPosition(-330, -330);
        System.out.println("Player created");
    }

    @Override
    public void act(float delta) {

        MoveUpCommand moveUp = new MoveUpCommand(this);
        MoveDownCommand moveDown = new MoveDownCommand(this);
        MoveRightCommand moveRight = new MoveRightCommand(this);
        MoveLeftCommand moveLeft = new MoveLeftCommand(this);
        SpaceBarCommand spaceBar = new SpaceBarCommand(this);

        if (GameInput.pressedKeys.contains(GameInput.keys.up)) {
            moveUp.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.down)) {
            moveDown.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.left)) {
            moveLeft.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.right)) {
            moveRight.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.space)) {
            spaceBar.execute();
            this.activateBerserkerMode();
        }
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        spriteBuff.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
        // spriteBuff.setAlpha(0);
        spriteBuff.draw(batch);
    }

    public void moveUp() {
        setPosition(sprite.getX(), sprite.getY() + 1);
    }

    public void moveDown() {
        setPosition(sprite.getX(), sprite.getY() - 1);
    }

    public void moveRight() {
        setPosition(sprite.getX() + 1, sprite.getY());
    }

    public void moveLeft() {
        setPosition(sprite.getX() - 1, sprite.getY());
    }

    private void activateBerserkerMode() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setIsBuffActivatedAndVisible(false);
                timer.cancel();
            }
        };
        timer.schedule(task, berserkerModeDuration);
    }

    public void setIsBuffActivatedAndVisible(boolean value) {
        this.isBuffActivatedAndVisible = value;
        if (value)
            this.spriteBuff.setAlpha(1);
        else
            this.spriteBuff.setAlpha(0);
    }
}
