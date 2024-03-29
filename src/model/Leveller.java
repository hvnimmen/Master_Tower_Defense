package model;

import java.io.*;

public class Leveller {

    public static void SaveMap(String mapName, TileGrid grid){
        String mapData = "";
        for (int i = 0; i < grid.getxTiles(); i++){
            for (int j = 0; j < grid.getyTiles(); j++){
                mapData += getTileID(grid.getTile(i, j));
            }
        }
        try {
            File file = new File(mapName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapData);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TileGrid LoadMap(String mapName) {
        TileGrid grid = new TileGrid();
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapName));
            String data = br.readLine();
            for (int i = 0; i < grid.getxTiles(); i++) {
                for (int j = 0; j < grid.getyTiles(); j++) {
                    grid.setTile(i, j, getTileType(data.substring(i * grid.getyTiles() + j, i * grid.getyTiles() + j + 1)));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grid;
    }

    public static TileType getTileType(String ID){
        TileType type = TileType.NULL;
        switch(ID){
            case "0":
                type = TileType.Grass;
                break;
            case "1":
                type = TileType.Sand;
                break;
            case "2":
                type = TileType.Water;
                break;
        }
        return type;
    }

    public static String getTileID(Tile t){
        String ID = "E";
        switch (t.getType()){
            case Grass:
                ID = "0";
                break;
            case Sand:
                ID = "1";
                break;
            case Water:
                ID = "2";
                break;
        }

        return ID;
    }

}
