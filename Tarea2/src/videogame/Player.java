/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author charles
 */
public class Player extends Item {

    private final Game game;
    private int lives;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.lives = 3;
    }

    @Override
    public void tick() {
       if (game.getMouseManager().isIzquierdo()) {
           
           Point p = new Point(game.getMouseManager().getX(), game.getMouseManager().getY());
           
           if (contains(p)) {
               setLocation(game.getMouseManager().getX() - (int) getWidth() / 2
                   , game.getMouseManager().getY() - (int) getHeight() / 2);
           }
       }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) getX(), (int) getY(),
                (int) getWidth(), (int) getHeight(), null);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
