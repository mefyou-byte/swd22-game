package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.command.MoveDownCommand;
import at.compus02.swd.ss2022.game.command.MoveLeftCommand;
import at.compus02.swd.ss2022.game.command.MoveRightCommand;
import at.compus02.swd.ss2022.game.command.MoveUpCommand;
import at.compus02.swd.ss2022.game.input.GameInput;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player implements GameObject {
    private final Texture image;
    public final Sprite sprite;



    public Player() {
        image = new Texture("Player.png");
        sprite = new Sprite(image);
        sprite.setScale((float) 0.035);
        setPosition(-330, -330);
        System.out.println("Player created");
    }


    @Override
    public void act(float delta) {

        MoveUpCommand moveUp = new MoveUpCommand(this);
        MoveDownCommand moveDown = new MoveDownCommand(this);
        MoveRightCommand moveRight = new MoveRightCommand(this);
        MoveLeftCommand moveLeft = new MoveLeftCommand(this);

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
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
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

}
