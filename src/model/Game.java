package model;

import view.*;

public class Game {

    private MainMenu mainMenu;
    private GameView gameView;
    private TileGrid grid;

    public Game(MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.gameView = new GameView(this);
    }

    public void exitGame(){
        mainMenu.getMainMenuView().getMainStage().show();
    }

}
