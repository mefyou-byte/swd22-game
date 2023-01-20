package at.compus02.swd.ss2022.game.gameobjects;

import at.compus02.swd.ss2022.game.GameObserver.PositionObserver;
import at.compus02.swd.ss2022.game.assetRepository.AssetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Enemy implements GameObject {
    private final Texture image;
    private final Sprite sprite;

    private int counter = 0;
    private int updatesPerSecond = 60;
    private List<PositionObserver> observers = new ArrayList<>();

    public Enemy() {
        AssetRepository repo = AssetRepository.getInstance();
        image = repo.getTexture("enemy");
        sprite = new Sprite(image);
        sprite.setScale((float) 0.125);


        setPosition(-100, -100);
        System.out.println("Enemy created");
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);

        for (PositionObserver observer : observers) {
            observer.update(x, y);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);

        if (counter > updatesPerSecond) {
            moveRandom();
            counter = 0;
        } else {
            counter++;
        }

    }


    public void moveRandom () {
        Random random = new Random();



        int randomDirection = random.nextInt(4);

        switch (randomDirection) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
               moveRight();
                break;
            case 3:
                moveLeft();
                break;
        }
    }

    public void moveUp() {
        setPosition(sprite.getX(), sprite.getY() + 10);
    }

    public void moveDown() {
        setPosition(sprite.getX(), sprite.getY() - 10);
    }

    public void moveRight() {
        setPosition(sprite.getX() + 10, sprite.getY());
    }

    public void moveLeft() {
        setPosition(sprite.getX() - 10, sprite.getY());
    }


    public void addObserver(PositionObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(PositionObserver observer) {
        observers.remove(observer);
    }
}
