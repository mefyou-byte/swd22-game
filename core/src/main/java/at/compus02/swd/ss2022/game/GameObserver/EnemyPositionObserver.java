package at.compus02.swd.ss2022.game.GameObserver;

import at.compus02.swd.ss2022.game.common.Logger;

public class EnemyPositionObserver implements PositionObserver {

    private Logger logger;

    public EnemyPositionObserver(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void update(float x, float y) {
        this.logger.log("Enemy moved to: " + x + " " + y);
    }
}

