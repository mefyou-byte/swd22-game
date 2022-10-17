package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;

public class PlayerFactory extends Factory {


    private static PlayerFactory instance = null;

    @Override
    public Player create(GameObjectType type) {

        Player gameObject=null;

        switch (type){
            case Player:
                gameObject = new Player();
                System.out.println("Player "+ gameObjects.size()+" created");
                break;
            default:
                System.out.println("Wrong GameObjectType");
        }

        if(gameObject != null) {
            gameObjects.add(gameObject);
        }

        return gameObject;
    }

    @Override
    public void initialize() {

    }

    @Override
    public GameObject[] getObjects() {
        return super.getObjects();
    }

    public static PlayerFactory getInstance() {
        if(instance == null){
            return new PlayerFactory();
        }

        return instance;
    }
}
