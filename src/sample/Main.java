package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
//        MainMenuView mainMenuView = new MainMenuView();
//        primaryStage = mainMenuView.getMainStage();
//        primaryStage.show();

//        GameView gameView = new GameView();
//        Game game = new Game(gameView);
//        GameClickHandler gameclickHandler = new GameClickHandler(game, gameView);
//        gameView.setClickHandler(clickHandler);
//        gameView.launchView();

        MainMenu mainMenu = new MainMenu();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
