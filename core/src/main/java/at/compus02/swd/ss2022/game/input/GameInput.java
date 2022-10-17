package at.compus02.swd.ss2022.game.input;

import com.badlogic.gdx.InputAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class GameInput extends InputAdapter {

    public static ArrayList<keys> pressedKeys = new ArrayList<>();

    @Override
    public boolean keyDown(int keycode) {

        for (keys allowedKey:keys.values())
        {
            if(allowedKey.getKeycode() == keycode && !pressedKeys.contains(allowedKey))
            {
                pressedKeys.add(allowedKey);
                System.out.println("Key Down: " + allowedKey);
            }
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        //System.out.println("Key Down: " + keycode);

        for (keys allowedKey:keys.values())
        {
            if(allowedKey.getKeycode() == keycode && pressedKeys.contains(allowedKey))
            {
                pressedKeys.remove(allowedKey);
                System.out.println("Key Up: " + allowedKey);
            }
        }

        return true;
    }


    public enum keys{
        up(19),
        down(20),
        left(21),
        right(22);

        private int keycode;

        keys(int keycode) { this.keycode = keycode; }

        public int getKeycode() { return keycode; }
    }
}
