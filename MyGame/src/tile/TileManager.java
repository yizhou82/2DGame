package tile;

import main.GamePanel;

import javax.annotation.processing.Filer;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    Tile[] tile; //Different types of tiles

    int[][] mapTiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10]; //Change num as you like, just add rest in getTileImage
        mapTiles = new int[gp.maxScreenRow][gp.maxScreenCol];

        getTileImage();
        loadMap("MyGame/res/maps/map01.txt");
    }

    public void getTileImage() {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundTiles/WoodenFloor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundTiles/BrickWall.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {
        File file = new File(filePath);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String s;
        int row = 0;

        while (true) {
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] line = s.split(" ");

            for(int col = 0; col < line.length; col++) {
                mapTiles[row][col] = Integer.parseInt(line[col]);
            }
            row++;
        }

        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {

        int x = 0, y = 0;

        for(int i = 0; i < mapTiles.length; i++) {
            for(int j = 0; j < mapTiles[0].length; j++) {
                g2.drawImage(tile[mapTiles[i][j]].image, x, y, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize;
            }
            y += gp.tileSize;
            x = 0;
        }

    }
}
