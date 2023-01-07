package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings, change as you like
    final int originalTileSize = 16; // 16 x 16 tile for characters
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale; //Will look like 48 x 48 on screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public Collision cChecker;
    public Player player;
    public SuperObject obj[] = new SuperObject[8];
    public ObjectPlacer objPlacer = new ObjectPlacer(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        player = new Player(this, keyH);
        tileM = new TileManager(this);
        cChecker = new Collision(this);
    }

    public void setup() {
        objPlacer.setObject();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }


//    Sleep GameLoop
//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/FPS; // 0.01666 sec
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while(gameThread != null) {
//
//            // 1 Update: update info such as character position
//            update();
//            // 2 Draw: draw updated info on screen
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }


    //Delta GameLoop
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null)
                obj[i].draw(g2,this);
        }

        player.draw(g2);

        g2.dispose();
    }
}
