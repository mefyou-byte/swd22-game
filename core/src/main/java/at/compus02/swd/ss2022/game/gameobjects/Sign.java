package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sign implements GameObject {
    private final Texture image;
    private final Sprite sprite;

    public Sign() {
        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("sign");
        sprite = new Sprite(image);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
