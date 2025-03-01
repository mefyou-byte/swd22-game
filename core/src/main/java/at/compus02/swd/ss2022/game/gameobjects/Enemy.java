package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy implements GameObject {
    private final Texture image;
    private final Sprite sprite;

    public Enemy() {
        image = new Texture("Enemy.png");
        sprite = new Sprite(image);
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

    public void moveUp() {
        setPosition(sprite.getX(), sprite.getY() + 10);
    }

    public void moveDown() {
        setPosition(sprite.getX(), sprite.getY() - 10);
    }

    public void moveRight() {
        setPosition(sprite.getX() + 10, sprite.getY());
    }

    public void moveLeft() {
        setPosition(sprite.getX() - 10, sprite.getY());
    }

}
