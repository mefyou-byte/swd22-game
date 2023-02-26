package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;

public class EnemyFactory extends Factory {

    private static EnemyFactory instance;

    public EnemyFactory() {
    }

    public static EnemyFactory getInstance() {
        if (instance == null) {
            instance = new EnemyFactory();
        }
        return instance;
    }

    @Override
    public Enemy create(GameObjectType type) {

        Enemy enemy;

        if (type == GameObjectType.ENEMY) {
            enemy = new Enemy();
        } else {
            throw new IllegalArgumentException("Invalid GameObjectType: " + type);
        }

        gameObjects.add(enemy);

        return enemy;
    }

    @Override
    public void initialize() {
    }

    @Override
    public GameObject[] getObjects() {
        return super.getObjects();
    }
}
