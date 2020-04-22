package model;

import view.*;

public class MainMenu {

    private MainMenuView mainMenuView;

    public MainMenu() {
        this.mainMenuView = new MainMenuView(this);
    }

    public void openNewGame() {
        Game game = new Game(this);
        mainMenuView.getMainStage().hide();
    }

    public MainMenuView getMainMenuView() {
        return this.mainMenuView;
    }

}
