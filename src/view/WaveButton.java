package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static view.GameView.SIZE;

public class WaveButton extends Button {

    public WaveButton(String text, int width, int height) {
        setText(text);
        setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, SIZE * 0.35));

        BackgroundImage backgroundImage = new BackgroundImage( new Image("view/resources/grey_button01.png", width,
                height, false, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);
        Background background = new Background(backgroundImage);
        setBackground(background);

        setPadding(new Insets(0, 0, 0, 0));

        setPrefWidth(width);
        setPrefHeight(height);
    }

}
