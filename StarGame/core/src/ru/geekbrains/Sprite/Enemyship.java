package ru.geekbrains.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;


public class Enemyship extends Sprite {
    private Rect worldBounds;
    private Sound flight;
    private Vector2 v = new Vector2();




    public Enemyship(TextureAtlas atlas) {
        super(atlas.findRegion("enemy0"));
        setHeightProportion(0.15f);
        this.v.set(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f));
        flight = Gdx.audio.newSound(Gdx.files.internal("enemyship.mp3"));
    }

    public void set() {
        flight.setVolume(2, 0.4f);
        flight.play();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkAndHandleBounds();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }


    public void dispose() {
        flight.dispose();
    }

    private void checkAndHandleBounds() {
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight());
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());
    }

}