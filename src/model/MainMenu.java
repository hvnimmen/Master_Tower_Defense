package model;

import view.*;

public class MainMenu {

    private MainMenuView mainMenuView;

    public MainMenu() {
        this.mainMenuView = new MainMenuView(this);
    }

    public void openNewGame(String fileURL) {
        Game game = new Game(this, fileURL);
        mainMenuView.getMainStage().hide();
    }

    public void openEditor() {
        Editor editor = new Editor(this);
        mainMenuView.getMainStage().hide();
    }

    public MainMenuView getMainMenuView() {
        return this.mainMenuView;
    }

}
