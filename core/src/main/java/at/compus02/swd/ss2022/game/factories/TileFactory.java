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


    //TODO - Change create that it has input for x y position ?
    @Override
    public Tile create(GameObjectType type) {

        Tile tile = null;

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
                System.out.println("Wrong GameObjectType: " + type);
        }

        if (tile != null) {
            gameObjects.add(tile);
        }

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
