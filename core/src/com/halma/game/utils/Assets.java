package com.halma.game.utils;

import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture redPieceUp, redPieceDown;
    public static Texture greenPieceUp, greenPieceDown;

    public static void init() {
        redPieceUp = new Texture("RedPieceUp.png");
        redPieceDown = new Texture("RedPieceDown.png");
        greenPieceUp = new Texture("GreenPieceUp.png");
        greenPieceDown = new Texture("GreenPieceDown.png");
    }

    public void dispose() {
        redPieceUp.dispose();
        redPieceDown.dispose();
        greenPieceUp.dispose();
        greenPieceDown.dispose();
    }
}
