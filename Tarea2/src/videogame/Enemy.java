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
    private final Player player;
    
    private int xVel;
    private int yVel;
    private int acc;
    private int maxVel;
    
    public Enemy(int x, int y, int width, int height, Game game, Player player) {
        super(x, y, width, height);
        this.game = game;
        this.xVel = 0;
        this.yVel = 0;
        this.acc = 2;
        this.maxVel = 15;
        this.player = player;
    }

    @Override
    public void tick() {
        
        if (player.getBody().getX() > getBody().getX()) {
            xVel += acc;
        } else {
            xVel -= acc;
        }
        
        if (player.getBody().getY() > getBody().getY()) {
            yVel += acc;
        } else {
            yVel -= acc;
        }
        
        if (xVel >= maxVel)
            xVel = maxVel;
        
        if (xVel <= -maxVel)
            xVel = -maxVel;
        
        if (yVel >= maxVel)
            yVel = maxVel;
        
        if (yVel <= -maxVel)
            yVel = -maxVel;
        
        getBody().setLocation((int) getBody().getX() + xVel, (int) getBody().getY() + yVel);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, (int) getBody().getX(), (int) getBody().getY(),
                (int) getBody().getWidth(), (int) getBody().getHeight(), null);
    }
    
}
