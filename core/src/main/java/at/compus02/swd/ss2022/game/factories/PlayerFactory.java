package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;

public class PlayerFactory extends Factory {


    private static PlayerFactory instance = null;

    private PlayerFactory()
    {
        return;
    }

    @Override
    public Player create(GameObjectType type){

        Player gameObject=null;

        switch (type){
            case Player:
                gameObject = new Player();
                System.out.println("Player "+ gameObjects.size()+" created");
            default:
                System.out.println("Wrong GameObjectType");
        };

        if(gameObject != null){
            gameObjects.add(gameObject);
        }

        return gameObject;
    }

    @Override
    public void initialize() {

    }

    public static PlayerFactory getInstance()
    {
        if(instance == null){
            return new PlayerFactory();
        }

        return instance;
    }
}
