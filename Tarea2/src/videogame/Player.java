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
public class Player extends Item {

    private final Game game;

    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }

    @Override
    public void tick() {
       if (game.getMouseManager().isIzquierdo()) {
           getBody().setLocation(game.getMouseManager().getX() - (int) getBody().getWidth() / 2
                   , game.getMouseManager().getY() - (int) getBody().getHeight() / 2);
       }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) getBody().getX(), (int) getBody().getY(),
                (int) getBody().getWidth(), (int) getBody().getHeight(), null);
    }
}
