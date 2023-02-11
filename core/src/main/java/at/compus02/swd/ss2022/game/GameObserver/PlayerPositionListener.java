package at.compus02.swd.ss2022.game.GameObserver;

import java.awt.Point;


public interface PlayerPositionListener {
    void onPlayerPositionChanged(Point currentPosition);
}
