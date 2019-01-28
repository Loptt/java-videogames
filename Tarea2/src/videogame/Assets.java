/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author charles
 */
public class Assets {

    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage enemy;
    public static BufferedImage life;
    
    public static void init() {
        background = ImageLoader.loadImage("/images/space_background.png");
        player = ImageLoader.loadImage("/images/earth_player.png");
        enemy = ImageLoader.loadImage("/images/asteroid_enemy.png");
        life = ImageLoader.loadImage("/images/heart.png");
    }
}
