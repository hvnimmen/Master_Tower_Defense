package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class QuitButtonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        System.exit(0);
    }
}
