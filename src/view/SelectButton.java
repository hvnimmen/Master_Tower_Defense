package view;

import controller.ActionButtonHandler;
import controller.TowerButtonHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import model.Game;
import model.TileType;
import model.TowerType;

import static view.GameView.SIZE;

public class SelectButton extends Button {

    private String name;
    private TowerType towerType;
    private TowerButtonHandler towerButtonHandler;
    private ActionButtonHandler actionButtonHandler;

    public SelectButton(String name, Image image, GameView gameView) {
        super("", new ImageView(image));
        this.name = name;
        initializeButton();
        actionButtonHandler = new ActionButtonHandler(this, gameView);
        setOnAction(actionButtonHandler);
    }

    public SelectButton(TowerType towerType, Image image, GameView gameView) {
        super("", new ImageView(image));
        this.towerType = towerType;
        initializeButton();
        towerButtonHandler = new TowerButtonHandler(this, gameView);
        setOnAction(towerButtonHandler);
    }

    private void initializeButton() {
        setPadding(Insets.EMPTY);
        BackgroundImage backgroundImage = new BackgroundImage(new Image("view/resources/grey_button.png", SIZE, SIZE,
                false, false), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                null);
        setBackground(new Background(backgroundImage));
        setPrefWidth(SIZE);
        setPrefHeight(SIZE);
    }

    public String getName() {
        return this.name;
    }

    public TowerType getTowerType() {
        return this.towerType;
    }
}
