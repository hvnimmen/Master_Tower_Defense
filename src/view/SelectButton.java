package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

import controller.*;
import model.*;

import static view.GameView.SIZE;

public class SelectButton extends Button {

    private String name;
    private TowerType towerType;
    private TowerButtonHandler towerButtonHandler;
    private TowerHoverHandler towerHoverHandler;
    private ActionButtonHandler actionButtonHandler;
    private ActionHoverHandler actionHoverHandler;

    public SelectButton(String name, Image image, GameView gameView) {
        super("", new ImageView(image));
        this.name = name;
        initializeButton();
        actionButtonHandler = new ActionButtonHandler(this, gameView);
        actionHoverHandler = new ActionHoverHandler(this, gameView);
        setOnAction(actionButtonHandler);
        setOnMouseEntered(actionHoverHandler);
    }

    public SelectButton(TowerType towerType, Image image, GameView gameView) {
        super("", new ImageView(image));
        this.towerType = towerType;
        initializeButton();
        towerButtonHandler = new TowerButtonHandler(this, gameView);
        towerHoverHandler = new TowerHoverHandler(this, gameView);
        setOnAction(towerButtonHandler);
        setOnMouseEntered(towerHoverHandler);
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
