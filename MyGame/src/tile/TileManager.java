package tile;

import main.GamePanel;

import javax.annotation.processing.Filer;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile; //Different types of tiles

    public int[][] mapTiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10]; //Change num as you like, just add rest in getTileImage
        mapTiles = new int[gp.maxWorldRow][gp.maxWorldCol];

        getTileImage();
        loadMap("MyGame/res/maps/world01.txt");
    }

    public void getTileImage() {

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundTiles/WoodenFloor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundTiles/BrickWall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundTiles/WoodenFloorWeb.png"));
            tile[2].effect_slow = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundTiles/Void.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgroundTiles/VoidEye.png"));
            tile[4].collision = true;

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

        System.out.println(mapTiles.toString());

        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {

        int worldRow = 0;
        int worldCol = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTiles[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            //Boundary
            if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                    worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                    worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                    worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
