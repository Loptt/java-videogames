/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author charles
 */
public class Game implements Runnable {
    
    /**
     * start main game thread
     */
    @Override
    public void run() {
        init();
        
        int fps = 50;
        double timeTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timeTick;
            lastTime = now;
            
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        
        stop();
    }
    
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    
    String title;
    private int width;
    private int height;
    
    private Thread thread;
    private boolean running;
    
    private Player player;
    private ArrayList<Enemy> enemies;
    
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private int maxEnemyAmount;
    private int enemyIntervalFrames;
    private int enemyFramesCounter;
    private int collisionCounter;
    //private JLabel text;
    
    /**
    * to create title, width and height and set the game is still not running
    * @param title to set the title of the window
    * @param width to set the width of the window
    * @param height to set the height of the window
    */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        running = false;

        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        
        maxEnemyAmount = 15;
        enemyIntervalFrames = 20;
        enemyFramesCounter = 0;
        
        collisionCounter = 0;
    }
    
    /**
    * initializing the display window of the game
    */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        player = new Player(getWidth() / 2, getHeight() - 100, 100, 100, this);
        
        enemies = new ArrayList<>();
        
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        setItemsPositions();
    }
    
    /**
     * updates all objects on a frame
     */
    private void tick() {
        keyManager.tick();
        player.tick();
        
        enemyFramesCounter++;
        
        if (enemyFramesCounter > enemyIntervalFrames) {
            if (enemies.size() < maxEnemyAmount) {
                //Place the enemy in a random position in the x axis
                int xPos = (int) (Math.random() * (getWidth()-100));
                //Give the enemy a random speed from 1 to 5
                int speed = (int) (Math.random() * 4 + (6 - player.getLives())); 
                Enemy enemy = new Enemy(xPos, -100, 100, 100, speed, this);
                enemies.add(enemy);
            }
            enemyFramesCounter = 0;
        }
        
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
            
            //Check if the enemy has reached the end
            if (enemies.get(i).getY() > getHeight() - 100) {
                //Enemy is eliminated naturally
                enemies.remove(i);
                player.setScore(player.getScore() - 20);
                collisionCounter++;
                
                Assets.crashSound.play();
               
            //Check if the player has collided with the enemy
            } else if (player.intersects(enemies.get(i))) {
                /**
                 * check if the previous y coordinate of the player is greater than the current
                 * enemy y coordinate, this will ensure that the enemy is only removed
                 * when touched from below
                 */
                if (player.getPrevY() - 50 > enemies.get(i).getY()) {
                    //Enemy is eliminated by player
                    enemies.remove(i);
                    player.setScore(player.getScore() + 100);
                    
                    Assets.coinSound.play();
                }
            }
        }
        
        //If 10 enemies have reached the bottom, decrease one life and reset counter
        if (collisionCounter >= 10) {
            player.setLives(player.getLives() - 1);
            collisionCounter = 0;
            enemyIntervalFrames -= 3;
            maxEnemyAmount += 2;
        }
    }
    
    /**
     * renders all objects in a frame
     */
    private void render() {
        Toolkit.getDefaultToolkit().sync(); //Linux
        bs = display.getCanvas().getBufferStrategy();
        
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else {
            g = bs.getDrawGraphics();
            g.clearRect(0, 0, width, height);
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).render(g);
            }
            
            for (int i = 0; i < player.getLives(); i++) {
                g.drawImage(Assets.life, 20 + i*50, 20, 50, 50, null);
            }
            
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("Score: " + Integer.toString(player.getScore()), getWidth()- 270, 50);
            
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * Initialize the positions of the current items
     */
    void setItemsPositions() {
        
    }
    
    /**
     * to get width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * to get height
     * @return height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * to get key manager
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    /**
     * to get mouse manager
     * @return mouseManager
     */
    public MouseManager getMouseManager() {
        return mouseManager;
    }
    
    /**
     * start game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stop game
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
