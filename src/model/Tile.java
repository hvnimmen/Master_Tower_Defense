package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import view.*;
import controller.*;

import static view.GameView.SIZE;

public class Tile extends StackPane {

    private int x, y;
    private boolean occupied;
    private TileType type;

    private Image image;

    public Tile(int x, int y, TileType type){
        this.x = x;
        this.y = y;
        this.type = type;

        this.image = type.image;

        this.occupied = !type.buildable;
    }

    public void Draw(GraphicsContext gc) {
        gc.drawImage(image, x * SIZE, y * SIZE, SIZE, SIZE);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}