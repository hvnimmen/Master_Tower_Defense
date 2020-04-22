package model;

import javafx.scene.image.Image;

import static view.GameView.SIZE;

public enum TileType {

    Grass("view/resources/grass_tile.png", true),
    Sand("view/resources/sand_tile.png", false),
    Water("view/resources/water_tile.png", false),
    NULL("file:water-block-2.png", false);

    Image image;
    boolean buildable;

    TileType(String fileName, boolean buildable){
        this.image = new Image(fileName, SIZE, SIZE, false, false);
        this.buildable = buildable;
    }

    public Image getImage() {
        return this.image;
    }

}
