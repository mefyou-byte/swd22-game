package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Water extends Tile {
    public Water() {
        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("water");
        sprite = new Sprite(image);
        System.out.println("Water Tile created");
    }

    @Override
    public void act(float delta) {
        // TODO Auto-generated method stub

    }
}
