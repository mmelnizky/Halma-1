package com.halma.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.halma.game.Handler;
import com.halma.game.utils.Assets;
import com.halma.game.utils.Controls;
import com.halma.game.utils.Triangle;

public class TriangleUnit extends GameObject {

    private Sprite sprite;
    private boolean isDown, isReal;
    private Triangle triangle;
    private boolean isSelected;

    public TriangleUnit(Handler handler, int x, int y, boolean isDown, boolean isReal) {
        super(handler, x, y);
        this.isDown = isDown;
        this.isReal = isReal;
        this.isSelected = false;
        init();
    }

    //Main Methods
    @Override
    public void init() {
        float scale = 1.3f;
        sprite = new Sprite(Assets.greenPieceUp);
        sprite.setSize(sprite.getWidth()*scale, sprite.getHeight()*scale);

        if (isDown) sprite.setTexture(Assets.greenPieceDown);

        triangle = new Triangle(new Vector2(sprite.getX(), sprite.getY()),
                new Vector2(sprite.getX()+sprite.getBoundingRectangle().width/2, sprite.getY()+sprite.getBoundingRectangle().height),
                new Vector2(sprite.getX()+sprite.getBoundingRectangle().width, sprite.getY()));
    }

    @Override
    public void update(float dt) {
        sprite.setX(x*21.24f+95);
        sprite.setY(y*36.7f+6);
        triangle.move(sprite.getX(), sprite.getY());
        selected();
    }

    @Override
    public void render(SpriteBatch batch) {
        if (isReal)
            sprite.draw(batch);
    }

    @Override
    public void dispose() {

    }

    //Other methods
    public void selected() {
        isSelected = triangle.contains(Controls.x, Controls.y);
    }

    //Getters and Setters
    public boolean isReal() {return isReal;}
    public boolean isSelected() {return isSelected;}
    public boolean isDown() {return isDown;}

}
