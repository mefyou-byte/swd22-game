package at.compus02.swd.ss2022.game.assetRepository;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class AssetRepository {
    private static AssetRepository instance;
    private HashMap<String, Texture> textures;
    private AssetRepository(){}

    public static AssetRepository getInstance(){
        if (instance == null)
            instance = new AssetRepository();

        return instance;
    }

    public void preloadAssets(){
        textures = new HashMap<String, Texture>();
        textures.put("water", new Texture("tile_water.png"));
        textures.put("bush", new Texture("bush.png"));
        textures.put("gras", new Texture("tile_gras.png"));
        textures.put("player", new Texture("Player.png"));
        textures.put("sign", new Texture("sign.png"));
     //   textures.put("enemy", new Texture("Enemy.png"));
    }

    public Texture getTexture(String key){
        return textures.get(key);
    }

    public void dispose() {
        textures.clear();
    }
}
