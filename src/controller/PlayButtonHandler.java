package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class PlayButtonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        System.out.println("the play button is being pressed");
    }
}
