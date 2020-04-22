package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

import static view.GameView.SIZE;

public class SelectButton extends Button {

    private String name;

    public SelectButton(String name, Image image) {
        super("", new ImageView(image));
        this.name = name;
        setPadding(Insets.EMPTY);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("view/resources/grey_button.png", SIZE, SIZE,
                false, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                null);
        setBackground(new Background(backgroundImage));
        setPrefWidth(SIZE);
        setPrefHeight(SIZE);
    }

}
