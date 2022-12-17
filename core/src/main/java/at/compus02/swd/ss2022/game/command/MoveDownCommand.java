package at.compus02.swd.ss2022.game.command;

import at.compus02.swd.ss2022.game.gameobjects.Player;

public class MoveDownCommand implements Commands {

    private Player player;

    public MoveDownCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        this.player.setPosition(this.player.sprite.getX(), this.player.sprite.getY() - 1);
    }
}
