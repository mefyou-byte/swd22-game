package at.compus02.swd.ss2022.game.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserInterfaceLogger implements Logger{

    private static UserInterfaceLogger instance;
    private final BitmapFont font;
    private float xPos;
    private float yPos;
    private String message = "";


    public UserInterfaceLogger(float xPos, float yPos) {
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        //font.getData().setScale(2);
        //font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        this.xPos = xPos;
        this.yPos = yPos;

    }

    public static UserInterfaceLogger getInstance() {
        if (instance == null) {
            instance = new UserInterfaceLogger(-220, -220);
        }
        return instance;
    }


    public float getxPos() {
        return xPos;
    }
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }
    public float getyPos() {
        return yPos;
    }
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }


    @Override
    public void log(String message) {
        this.message = message;
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, message, xPos, yPos);
    }
}
