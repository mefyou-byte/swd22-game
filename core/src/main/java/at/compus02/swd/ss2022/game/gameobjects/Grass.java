package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Grass extends Tile {

    public Grass() {
        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("gras");
        sprite = new Sprite(image);
        System.out.println("Grass Tile created");
    }

    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub
    }

}
