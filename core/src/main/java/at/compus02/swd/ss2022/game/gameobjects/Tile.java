package at.compus02.swd.ss2022.game.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile implements GameObject {

    protected Texture image;
    protected Sprite sprite;

    public Tile() {

    }

    @Override
    public abstract void act(float delta, ArrayList<Position> waterTilesPositions);

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
