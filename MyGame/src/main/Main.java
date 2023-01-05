package main;

import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.getDefaultCloseOperation();
        window.setResizable(false);
        window.setTitle("2D Horror");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setup();

        gamePanel.startGameThread();
    }
}