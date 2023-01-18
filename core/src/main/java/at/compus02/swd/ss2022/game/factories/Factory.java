package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import java.util.ArrayList;

public abstract class Factory {

    // TODO - Change to Interface?
    protected ArrayList<GameObject> gameObjects = new ArrayList<>();

    abstract GameObject create(GameObjectType type);

    abstract void initialize();

    GameObject[] getObjects() {
        return gameObjects.toArray(new GameObject[0]);
    }

}
