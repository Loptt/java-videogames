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

/**
 *
 * @author charles
 */
public class Game implements Runnable {
    
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private Player player;
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private int enemyAmount;
    private int collisionAm;
    
    private ArrayList<Enemy> enemies;
    
    private boolean over;
    
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
        
        enemyAmount = (int) (Math.random() * 6 + 10);
        
        collisionAm = 0;
        
        over = false;
    }
    
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
    
    /**
    * initializing the display window of the game
    */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        player = new Player(getWidth() / 2 - 75, getHeight() / 2 - 75, 150, 150, this);
        
        enemies = new ArrayList<>();
        
        for (int i = 0; i < enemyAmount; i++) {
            Enemy enemy = new Enemy(0, 0, 75, 75, this);
            enemies.add(enemy);
        }

        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
    }
    
    private void tick() {
        if (over) {
            return;
        }
        
        keyManager.tick();
        player.tick();
        
        //Check every enemy
        for (int i = 0; i < enemyAmount; i++) {
            Enemy enemy = enemies.get(i);
            enemy.tick();
            
            //Check if intersects with player
            if (player.intersects(enemy)) {
                enemy.reset();
                player.setScore(player.getScore() + 10);
                
                Assets.coinSound.play();
                
                //Check if it is out of bounds
            } else if (enemy.getX() < - 5 || enemy.getX() + 75 >= getWidth() || enemy.getY() >= getHeight() - 75) {
                enemy.reset();
                collisionAm++; //Increase collision with bounds counter
                
                Assets.eliminateSound.play();
                
                //if it reaches 10, decrease one life 
                if (collisionAm >= 10) {
                    player.setLives(player.getLives() - 1);
                    collisionAm = 0;
                }
                
            }
        }
        
        if (player.getLives() <= 0) {
            over = true;
        }
        
    }
    
    private void render() {
        //Toolkit.getDefaultToolkit().sync(); //Linux
        bs = display.getCanvas().getBufferStrategy();
        
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else {
            g = bs.getDrawGraphics();
            g.clearRect(0, 0, width, height);
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            
            //Render every enemy 
            for (int i = 0; i < enemyAmount; i++) {
                Enemy enemy = enemies.get(i);
                enemy.render(g);
            }
            
            //Render the hearts representing lives remaining
            for (int i = 0; i < player.getLives(); i++) {
                g.drawImage(Assets.life, 30 + i * 40, getHeight() - 30, 30, 30, null);
            }
            
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
            g.setColor(Color.WHITE);
            
            if (over) {
                g.drawImage(Assets.over, 0, 0, width, height, null);
            }
            g.drawString("Score: " + Integer.toString(player.getScore()), getWidth() - 270, getHeight() - 30);
            
            bs.show();
            g.dispose();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
    
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
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
