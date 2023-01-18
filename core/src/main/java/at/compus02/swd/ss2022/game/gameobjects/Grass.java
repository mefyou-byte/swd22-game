package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Grass extends Tile {

    public Grass() {
        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("grass");
        sprite = new Sprite(image);
        System.out.println("Grass Tile created");
    }

    @Override
    public void act(float delta, ArrayList<Position> waterTilesPositions) {
        // TODO Auto-generated method stub
    }

}
