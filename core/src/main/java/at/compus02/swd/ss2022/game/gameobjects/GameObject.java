package at.compus02.swd.ss2022.game.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {


    void act(float delta);

    void setPosition(float x, float y);

    void draw(SpriteBatch batch);


}
