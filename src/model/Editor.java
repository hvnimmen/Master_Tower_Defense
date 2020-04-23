package model;

import view.*;

public class Editor {

    private MainMenu mainMenu;
    private EditorView editorView;

    public Editor(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.editorView = new EditorView(this);
    }

    public void exitEditor(){
        mainMenu.getMainMenuView().getMainStage().show();
    }

}
