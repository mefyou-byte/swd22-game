package at.compus02.swd.ss2022.game.gameobjects;


import at.compus02.swd.ss2022.game.GameObserver.PositionObserver;
import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;
import at.compus02.swd.ss2022.game.command.MoveDownCommand;
import at.compus02.swd.ss2022.game.command.MoveLeftCommand;
import at.compus02.swd.ss2022.game.command.MoveRightCommand;
import at.compus02.swd.ss2022.game.command.MoveUpCommand;
import at.compus02.swd.ss2022.game.command.SpaceBarCommand;
import at.compus02.swd.ss2022.game.input.GameInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static at.compus02.swd.ss2022.game.Main.TILE_WIDTH;
import static at.compus02.swd.ss2022.game.Main.TILE_HEIGHT;


public class Player implements GameObject {
    private final Texture image;
    public final Sprite sprite;


    //private ParticleEffect particleEffect;
    private float posX;
    private float posY;


    public final Sprite spriteBuff;

    private final int berserkerModeDuration = 5000;
    private boolean isBuffActivatedAndVisible = false;
    private final int buffOffsetX = -20;
    private final int buffOffsetY = -100;



    private List<PositionObserver> observerList = new ArrayList<>();


    public Player() {



        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("player");
        sprite = new Sprite(image);
        sprite.setScale((float) 0.1);



        Texture imageBuff = repo.getTexture("fire");
        spriteBuff = new Sprite(imageBuff);
        spriteBuff.setScale((float) 0.025);
        spriteBuff.setAlpha(0);


        setPosition(-330, -330);
        System.out.println("Player created");





    }

    @Override
    public void act(float delta) {



        MoveUpCommand moveUp = new MoveUpCommand(this);
        MoveDownCommand moveDown = new MoveDownCommand(this);
        MoveRightCommand moveRight = new MoveRightCommand(this);
        MoveLeftCommand moveLeft = new MoveLeftCommand(this);
        SpaceBarCommand spaceBar = new SpaceBarCommand(this);

        if (GameInput.pressedKeys.contains(GameInput.keys.up)) {
            moveUp.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.down)) {
            moveDown.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.left)) {
            moveLeft.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.right)) {
            moveRight.execute();
        }
        if (GameInput.pressedKeys.contains(GameInput.keys.space)) {
            spaceBar.execute();
        }
    }

    @Override
    public void setPosition(float x, float y) {

        boolean isAllowedToMoveOnTile = true;

        if (!isAllowedToMoveOnTile)
            return;

        posX = x;
        posY = y;
        sprite.setPosition(x, y);

        spriteBuff.setPosition(x + buffOffsetX, y + buffOffsetY);

        for (PositionObserver observer : this.observerList) {
            observer.update(x, y);
        }

    }

    @Override
    public void draw(SpriteBatch batch) {

        sprite.draw(batch);


        // particleEffect - not working
        //if (particleEffect != null) {
        //    particleEffect.draw(batch);
        //}


        spriteBuff.draw(batch);
    }
    public void drawRadius(){
    }

    private void activateBerserkerMode() {



        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setIsBuffActivatedAndVisible(false);
                timer.cancel();
            }
        };
        timer.schedule(task, berserkerModeDuration);


    }

    public void setIsBuffActivatedAndVisible(boolean value) {
        this.isBuffActivatedAndVisible = value;

        if (value) {

            this.spriteBuff.setAlpha(1);


            // particleEffect - not working
            //ParticleEffect particleEffect = new ParticleEffect();
            //particleEffect.load(Gdx.files.internal("berserk.p"), Gdx.files.internal(""));
            //particleEffect.getEmitters().first().setPosition(posX, posY);
            //particleEffect.start();
            //this.particleEffect = particleEffect;


            this.activateBerserkerMode();
        } else {
            //this.particleEffect.dispose();

            this.spriteBuff.setAlpha(0);
        }


    }



    public void addObserver(PositionObserver observer) {
        this.observerList.add(observer);
    }

    public void removeObserver(PositionObserver observer) {
        this.observerList.remove(observer);
    }



    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}
