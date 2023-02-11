package at.compus02.swd.ss2022.game.command;

import at.compus02.swd.ss2022.game.gameobjects.Player;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class MoveDownCommand implements Commands {

    private Player player;

    public MoveDownCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        if(this.player.sprite.getY() <= -450){
            return;
        }
        this.player.setPosition(this.player.sprite.getX(), this.player.sprite.getY() - 1);
    }
}
