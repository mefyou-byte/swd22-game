package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bush extends Tile {

    public Bush() {
        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("bush");
        sprite = new Sprite(image);
        System.out.println("Bush Tile created");
    }

    @Override
    public void act(float delta, ArrayList<Position> waterTilesPositions) {

    }
}
