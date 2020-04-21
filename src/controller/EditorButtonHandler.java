package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class EditorButtonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        System.out.println("the editor button is being pressed");
    }
}
