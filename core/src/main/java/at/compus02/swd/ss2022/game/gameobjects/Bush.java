package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bush extends Tile{

    public Bush() {
        image = new Texture("tile_bush.png");
        sprite = new Sprite(image);
    }

    @Override
    public void act(float delta) {

    }
}
