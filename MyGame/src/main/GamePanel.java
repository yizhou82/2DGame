package main;

import entity.Player;
import object.ObjectPlacer;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

/**
 * An instance is a graphical representation of a game
 */
public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16; // 16 x 16 tile for characters
    public final int scale = 3;

    public final int tileSize = originalTileSize * scale; //Will look like 48 x 48 on screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //768 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    TileManager tileM;
    protected KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Collision cChecker;
    public Player player;
    public SuperObject[] obj = new SuperObject[9];
    public ObjectPlacer objPlacer = new ObjectPlacer(this);
    public UI ui = new UI(this);

    /**
     * A constructor that creates an instance of GamePanel
     */
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

    /**
     * Runs the game loop using delta time
     */
    @Override
    public void run() {
        double drawInterval = (double) 1_000_000_000 / FPS;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {

        player.update();

        for (SuperObject superObject : obj) {
            if (superObject != null && superObject.animate)
                superObject.update();
        }

    }

    /**
     * Draws the different components of the game
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for (SuperObject superObject : obj) {
            if (superObject != null)
                superObject.draw(g2, this);
        }

        player.draw(g2);
        ui.draw(g2);

        g2.dispose();
    }
}
