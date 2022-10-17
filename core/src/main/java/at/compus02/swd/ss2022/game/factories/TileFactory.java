package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;


public class TileFactory extends Factory {

    private static TileFactory instance = null;

    @Override
    public Tile create(GameObjectType type){

        Tile gameObject = null;


        switch (type){
            case Grass:
                gameObject = new Grass();
                System.out.println("Grass Tile created");
                break;
            case Water:
                gameObject = new Water();
                System.out.println("Water Tile created");
                break;
            case Bush:
                gameObject = new Bush();
                System.out.println("Bush Tile created");
                break;
            default:
                System.out.println("Wrong GameObjectType: " + type);
                break;
        }

        if(gameObject != null){
            gameObjects.add(gameObject);
        }

        return gameObject;
    }

    @Override
    public GameObject[] getObjects() {
        return super.getObjects();
    }

    @Override
    void initialize(){

    }


    public static TileFactory getInstance(){
        if(instance == null){
            return new TileFactory();
        }
        return instance;
    }
}
