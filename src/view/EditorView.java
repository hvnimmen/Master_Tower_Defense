package view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Editor;
import model.Tile;
import model.TileGrid;
import model.TileType;

import static model.Leveller.LoadMap;
import static model.Leveller.SaveMap;
import static view.GameView.*;

public class EditorView {

    private Editor editor;
    private BorderPane editorPane;
    private TileGrid grid;
    private TileType heldTile;

    private ImageView crosshair = new ImageView(new Image("view/resources/crosshair.png", SIZE, SIZE, false, false));

    public EditorView(Editor editor) {
        this.editor = editor;
        this.grid = LoadMap("src/model/map");
        this.heldTile = TileType.Grass;

        setupMap();

        drawMap();
    }

    private void setupMap() {

        editorPane = new BorderPane();
        Pane mapPane = new Pane();
        VBox select = new VBox();

        editorPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getX()/SIZE < X_TILES && event.getY()/SIZE < Y_TILES) {
                setTile((int)event.getX()/SIZE, (int)event.getY()/SIZE);
            }
        });

        editorPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            if (event.getX()/SIZE < X_TILES && event.getY()/SIZE < Y_TILES) {
                setTile((int)event.getX()/SIZE, (int)event.getY()/SIZE);
            }
        });

        Button grass = new Button("Grass", new ImageView(TileType.Grass.getImage()));
        grass.setOnMouseClicked(event -> heldTile = TileType.Grass);
        Button sand = new Button("Sand", new ImageView(TileType.Sand.getImage()));
        sand.setOnMouseClicked(event -> heldTile = TileType.Sand);
        Button water = new Button("Water", new ImageView(TileType.Water.getImage()));
        water.setOnMouseClicked(event -> heldTile = TileType.Water);
        Button save = new Button("Save");
        save.setOnMouseClicked(event -> SaveMap("src/model/map", grid));
        select.getChildren().addAll(grass, sand, water, save);

        editorPane.setCenter(mapPane);
        editorPane.setRight(select);
//        editorPane.setBackground(new Background(new BackgroundImage(TileType.Water.getImage(), BackgroundRepeat.REPEAT,
//                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null)));

        Scene editorScene = new Scene(editorPane, (X_TILES + MENU_X_TILES) * SIZE, Y_TILES * SIZE);
        Stage editorStage = new Stage();
        editorStage.setScene(editorScene);
        editorStage.show();

    }

    private void drawMap() {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 12; j++) {
                ImageView tileImage = new ImageView(grid.map[i][j].getType().getImage());
                tileImage.setLayoutX(i*SIZE);
                tileImage.setLayoutY(j*SIZE);
                editorPane.getChildren().add(tileImage);
            }
        }
    }

    private void setTile(int x, int y) {
        grid.setTile(x, y, heldTile);
        ImageView tileImageView = new ImageView(heldTile.getImage());
        tileImageView.setLayoutX(x * SIZE);
        tileImageView.setLayoutY(y * SIZE);
        editorPane.getChildren().add(tileImageView);
    }

}
