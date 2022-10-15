package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Player;

public class PlayerFactory extends Factory {    //TODO getinstance wie in TileFactory

    @Override
    public Player create(GameObjectType type){
        return null;
    }

    @Override
    public void initialize() {

    }

    @Override
    public GameObject[] getObjects() {
        return new GameObject[0];
    }
}
