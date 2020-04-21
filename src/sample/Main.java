package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainMenuView mainMenuView = new MainMenuView();
        primaryStage = mainMenuView.getMainStage();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
