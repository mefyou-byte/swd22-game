package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;

public class PlayerFactory extends Factory {

    private static PlayerFactory instance;

    public PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        if (instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }

    @Override
    public Player create(GameObjectType type) {

        Player player = null;

        if (type == GameObjectType.PLAYER) {
            player = new Player();
        } else {
            System.out.println("Wrong GameObjectType");
        }

        if (player != null) {
            gameObjects.add(player);
        }

        return player;
    }

    @Override
    public void initialize() {
    }

    @Override
    public GameObject[] getObjects() {
        return super.getObjects();
    }


}
