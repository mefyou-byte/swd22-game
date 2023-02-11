package at.compus02.swd.ss2022.game.GameObserver;

import at.compus02.swd.ss2022.game.common.Logger;

public class PlayerHealthObserver {

    private Logger logger;

    public PlayerHealthObserver(Logger logger) {
        this.logger = logger;
    }

    public void update(int health) {
        System.out.println("## Test");
        this.logger.log("Health: " + health + "/3");
    }
}