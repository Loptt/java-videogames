/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

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
    private Enemy enemy;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private int timer;
    private int timerCounter;
    
    private boolean isResetting;
    
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
        this.timer = 0;
        this.timerCounter = 0;
        this.isResetting = false;
        running = false;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    /**
    * initializing the display window of the game
    */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        player = new Player(100, 100, 150, 150, this);
        enemy = new Enemy(100, 100 , 150, 150, this, player);
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
        
        if (isResetting) {
            timerCounter++;
            
            if (timerCounter >= 50) {
                timer++;
                timerCounter = 0;
            }
            
            if (timer >= 2) {
                isResetting = false;
                timer = 0;
                timerCounter = 0;
            }
            
        } else {
            keyManager.tick();
            player.tick();
            enemy.tick();
        }
        
        if (player.intersects(enemy)) {
            setItemsPositions();
            isResetting = true;
            player.setLives(player.getLives() - 1);
        }
        
        if (player.getLives() <= 0) {
            //TODO: Game over
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
            enemy.render(g);
            
            //Render lives in the screen
            for (int i = 0; i < player.getLives(); i++) {
                g.drawImage(Assets.life, 20, 20 + i * 50, 40, 40, null);
            }
            
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * Initialize the positions of the current items
     */
    void setItemsPositions() {
        int xPlayerPos = (int) (Math.random() * getWidth() / 2 + getWidth() / 2);
        int yPlayerPos = (int) (Math.random() * getHeight() + 1);
        
        int xEnemyPos = (int) (Math.random() * getWidth() / 2);
        int yEnemyPos = (int) (Math.random() * getHeight() + 1);
            
        player.setLocation(xPlayerPos, yPlayerPos);
        enemy.setLocation(xEnemyPos, yEnemyPos);
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
