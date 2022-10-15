package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.input.GameInput;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.tools.javac.util.Convert;

public class Player implements GameObject {
    private Texture image;
    private Sprite sprite;

    public Player() {
        image = new Texture("Player.png");
        sprite = new Sprite(image);
        sprite.setScale((float)0.5);
    }
    @Override
    public void act(float delta) {


    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void moveUp()
    {
        setPosition(sprite.getX(), sprite.getY()+10);
    }

    public void moveDown()
    {
        setPosition(sprite.getX(), sprite.getY()-10);
    }
    public void moveRight()
    {
        setPosition(sprite.getX()+10, sprite.getY());
    }
    public void moveLeft()
    {
        setPosition(sprite.getX()-10, sprite.getY());
    }

}
