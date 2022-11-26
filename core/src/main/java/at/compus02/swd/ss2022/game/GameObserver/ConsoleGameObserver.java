package at.compus02.swd.ss2022.game.GameObserver;


import at.compus02.swd.ss2022.game.input.GameInput;

public class ConsoleGameObserver implements GameObserver {
    @Override
    public void onPlayerMovedUp() {

        // TODO -> if Player moves up, log it out ->

        if (GameInput.pressedKeys.contains(GameInput.keys.up)) {
            System.out.println("UP");
        }
    }

    @Override
    public void onPlayerMovedDown() {
        if (GameInput.pressedKeys.contains(GameInput.keys.down)) {
            System.out.println("DOWN");
        }
    }

    @Override
    public void onPlayerMovedLeft() {
        if (GameInput.pressedKeys.contains(GameInput.keys.left)) {
            System.out.println("LEFT");
        }
    }

    @Override
    public void onPlayerMovedRight() {
        if (GameInput.pressedKeys.contains(GameInput.keys.right)) {
            System.out.println("RIGHT");
        }
    }


}
