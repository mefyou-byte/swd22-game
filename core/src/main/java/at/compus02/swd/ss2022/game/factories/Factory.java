package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.Game;

import java.util.ArrayList;

public abstract class Factory {


    protected ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    
    abstract GameObject create(GameObjectType type);
    
    abstract void initialize();
    
    abstract GameObject[] getObjects();

}
