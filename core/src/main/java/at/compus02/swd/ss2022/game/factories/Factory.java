package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.Game;

import java.util.ArrayList;

public abstract class Factory {


    protected ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    
    abstract GameObject create(GameObjectType type);
    
    abstract void initialize();
    
    GameObject[] getObjects()
    {
        GameObject[] list = new GameObject[gameObjects.size()];
        return gameObjects.toArray(list);
    }

}
