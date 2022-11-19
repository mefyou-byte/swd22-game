package at.compus02.swd.ss2022.game.command;

import at.compus02.swd.ss2022.game.gameobjects.Player;

public class MoveUpCommand implements Commands {

    private Player player;

    public MoveUpCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        this.player.sprite.setPosition(this.player.sprite.getX(), this.player.sprite.getY() +1);
    }
}
