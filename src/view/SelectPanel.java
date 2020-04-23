package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static view.GameView.SIZE;

public class SelectPanel extends Pane {

    private int width, height, buttonNumber = 0;
    private ArrayList<Button> buttonList;

    public SelectPanel(int width, int height) {
        setWidth(width);
        setHeight(height);
        buttonList = new ArrayList<>();
        getChildren().add(new ImageView(new Image("view/resources/metal_panel.png", width, height, false, false)));
    }

    public void quickAdd(SelectButton selectButton) {
        setButton(selectButton);
    }

    private void setButton(Button b) {
        b.setLayoutX(SIZE * 0.35 + SIZE * (buttonNumber / 2) * 1.15);
        b.setLayoutY(SIZE * 0.85 + SIZE * (buttonNumber % 2) * 1.15);
        buttonNumber++;
        buttonList.add(b);
        getChildren().add(b);
    }

//    public void addButton(Button b){
//        menuButtons.add(b);
//    }
//
//    public void quickAdd(String name, Image image) {
//        Button b = new Button(name, image, 0, 0);
//        setButton(b);
//    }
//
//    private void setButton(Button b){
//        if (optionsWidth != 0) {
//            b.setDisplayY(y + (TILE_SIZE/2) + (buttonNumber / 3) * TILE_SIZE);
//        }
//        b.setDisplayX(x + (TILE_SIZE/2) + (buttonNumber % 3) * TILE_SIZE);
//        buttonNumber++;
//        menuButtons.add(b);
//    }

}
