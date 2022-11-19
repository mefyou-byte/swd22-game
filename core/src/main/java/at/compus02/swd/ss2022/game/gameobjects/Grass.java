package at.compus02.swd.ss2022.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Grass extends Tile {

    public Grass() {
        image = new Texture("tile_gras.png");
        sprite = new Sprite(image);
        System.out.println("Grass Tile created");
    }

    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
    }

}
