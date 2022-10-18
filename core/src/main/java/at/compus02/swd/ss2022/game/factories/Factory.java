package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.Game;

import java.util.ArrayList;

public abstract class Factory {

    //TODO - Change to Interface?
    protected ArrayList<GameObject> gameObjects = new ArrayList<>();

    abstract GameObject create(GameObjectType type);

    abstract void initialize();

    GameObject[] getObjects() {
        GameObject[] list = new GameObject[gameObjects.size()];
        return gameObjects.toArray(list);
    }

}
