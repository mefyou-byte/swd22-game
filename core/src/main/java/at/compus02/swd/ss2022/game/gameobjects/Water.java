package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Water extends Tile{
    public Water() {
        image = new Texture("tile_water.png");
        sprite = new Sprite(image);
    }
    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub

    }
}
