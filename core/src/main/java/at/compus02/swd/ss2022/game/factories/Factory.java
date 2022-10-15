package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.Game;

public interface Factory {
    
    GameObjectType type = null;
    
    GameObject create();
    
    void initialize();
    
    GameObject[] getObjects();

}
