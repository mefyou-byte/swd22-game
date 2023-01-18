package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;


public class TileFactory extends Factory {

    private static TileFactory instance;

    public TileFactory() {
    }

    public static TileFactory getInstance() {
        if (instance == null) {
            instance = new TileFactory();
        }
        return instance;
    }

    @Override
    public Tile create(GameObjectType type) {

        Tile tile;

        switch (type) {
            case GRASS:
                tile = new Grass();
                break;
            case WATER:
                tile = new Water();
                break;
            case BUSH:
                tile = new Bush();
                break;
            default:
                throw new IllegalArgumentException("Invalid GameObjectType: " + type);
        }

        gameObjects.add(tile);

        return tile;
    }

    @Override
    void initialize() {
    }

    @Override
    public GameObject[] getObjects() {
        return super.getObjects();
    }




}
