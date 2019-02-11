/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author charles
 */
public class Enemy extends Item {
    
    private final Game game;
    private int speed;
    private boolean exploding;
    
    public Enemy(int x, int y, int width, int height, int speed, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.speed = speed;
        exploding = false;
    }

    @Override
    public void tick() {
        setLocation((int) getX(), (int) getY() + speed);
        
        if (getY() >= game.getHeight() - 110)
            exploding = true;
    }

    @Override
    public void render(Graphics g) {
        if (!exploding) {
            g.drawImage(Assets.enemy, (int) getX(), (int) getY(),
                (int) getWidth(), (int) getHeight(), null);
        } else {
            g.drawImage(Assets.exploding, (int) getX(), (int) getY(),
                (int) getWidth(), (int) getHeight(), null);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isExploding() {
        return exploding;
    }
}
