package at.compus02.swd.ss2022.game.command;

import at.compus02.swd.ss2022.game.gameobjects.Player;

public class MoveRightCommand implements Commands {
    private Player player;

    public MoveRightCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        this.player.sprite.setPosition(this.player.sprite.getX()+1, this.player.sprite.getY());
    }
}
