package com.halma.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.halma.game.Handler;

import java.io.File;
import java.io.FileNotFoundException;

public class Board extends GameObject {

    private Piece[][] pieces;
    private TriangleUnit[][] board;

    public Board(Handler handler, int x, int y) {
        super(handler, x, y);
        init();
    }

    public void init() {

        //Read file to create a 6 star board
        String[] a;
        String[][] b = new String[16][23];
        FileHandle file = Gdx.files.internal("6StarBoard.txt");
        a = file.readString().split("\n");

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].split(" ").length; j++) {
                b[i][j] = a[i].split(" ")[j];
            }
        }

        // Initialize variables
        pieces = new Piece[16][23];
        board = new TriangleUnit[16][23];

        //Create the game board.
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[j].length; i++) {

                boolean isDown, isReal;

                //flip the triangles
                if (j%2==0 && i%2 == 1) isDown = true;
                else if (j%2==1 && i%2 == 1) isDown = false;
                else if (j%2==1 && i%2 == 0) isDown = true;
                else isDown = false;

                isReal = (b[j][i].equalsIgnoreCase("1"));

                board[j][i] = new TriangleUnit(handler, i, j, isDown, isReal);
            }
        }

    }

    @Override
    public void update(float dt) {
        for (TriangleUnit[] b : board) {
            for (TriangleUnit p : b) {
                if (p != null) p.update(dt);
            }
        }
        for (Piece[] b : pieces) {
            for (Piece p : b) {
                if (p != null) p.update(dt);
            }
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        for (TriangleUnit[] b : board) {
            for (TriangleUnit p : b) {
                if (p != null) p.render(batch);
            }
        }
        for (Piece[] b : pieces) {
            for (Piece p : b) {
                if (p != null) p.render(batch);
            }
        }


    }

    @Override
    public void dispose() {

    }

    // Getters and Setters
    public Piece[][] getPieces() {return pieces;}
    public TriangleUnit[][] getBoard() {return board;}

    public void setPiece(int x, int y, Piece p) {pieces[y][x] = p;}

}
