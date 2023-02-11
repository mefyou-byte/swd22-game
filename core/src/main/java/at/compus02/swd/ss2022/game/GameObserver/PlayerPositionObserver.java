package at.compus02.swd.ss2022.game.GameObserver;

import at.compus02.swd.ss2022.game.common.Logger;

public class PlayerPositionObserver implements PositionObserver {

    private Logger logger;

    public PlayerPositionObserver(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void update(float x, float y) {
        //System.out.println("Player moved to: " + x + " " + y);
        this.logger.log("Player moved to: " + x + " " + y);

        
    }
}

