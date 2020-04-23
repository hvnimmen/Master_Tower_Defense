package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import static view.GameView.SIZE;

public class InfoLabel extends Label {

    public InfoLabel(int width, int height, String text){
        setPrefWidth(width);
        setPrefHeight(height);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("view/resources/metal_panel.png",
                width, height,false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 10, 10, 10));
        setText(text);
        setFont(Font.font("Verdana", SIZE*0.5));
        setAlignment(Pos.CENTER);
    }

}
