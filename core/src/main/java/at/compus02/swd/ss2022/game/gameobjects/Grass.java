package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Grass extends Tile {


    public Grass() {
        image = new Texture("tile_gras.png");
        sprite = new Sprite(image);
    }
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub

    }

}
