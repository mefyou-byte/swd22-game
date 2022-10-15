package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;

import java.util.ArrayList;

public class TileFactory extends Factory {

    private static TileFactory instance = null;

    private TileFactory()
    {
        return;
    }

    @Override
    public Tile create(GameObjectType type) {

        Tile gameObject=null;

        switch (type){
            case Grass:
                gameObject = new Grass();
                System.out.println("Grass Tile created");
            case Water:
                gameObject = new Water();
                System.out.println("Water Tile created");
            case Bush:
                gameObject = new Bush();
                System.out.println("Bush Tile created");
            default:
                System.out.println("Wrong GameObjectType");
        };

        if(gameObject != null){
            gameObjects.add(gameObject);
        }

        return gameObject;
    }

    @Override
    void initialize() {

    }

    public static TileFactory getInstance()
    {
        if(instance == null){
            return new TileFactory();
        }

        return instance;
    }
}
