package at.compus02.swd.ss2022.game.command;

import at.compus02.swd.ss2022.game.gameobjects.Player;

public class SpaceBarCommand implements Commands {

    private Player player;

    public SpaceBarCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        this.player.setIsBuffActivatedAndVisible(true);
        this.player.killClosedEnemyIfInRange();
    }

}
