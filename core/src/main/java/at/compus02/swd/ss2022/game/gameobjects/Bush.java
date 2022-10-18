package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bush extends Tile {

    public Bush() {
        image = new Texture("bush.png");
        sprite = new Sprite(image);
        System.out.println("Bush Tile created");
    }

    @Override
    public void act(float delta) {

    }
}
