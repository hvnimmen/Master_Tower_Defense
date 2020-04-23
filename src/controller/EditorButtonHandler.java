package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.MainMenu;

public class EditorButtonHandler implements EventHandler<MouseEvent> {

    private MainMenu mainMenu;

    public EditorButtonHandler(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public void handle(MouseEvent event) {
        mainMenu.openEditor();
    }
}
