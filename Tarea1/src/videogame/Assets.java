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
    public static BufferedImage playerSkins[];
    public static BufferedImage colliding;
    
    /**
     * initialize BufferedImage objects
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/psy_background.gif");
        
        playerSkins = new BufferedImage[5];
        
        playerSkins[0] = ImageLoader.loadImage("/images/dvd.png");
        playerSkins[1] = ImageLoader.loadImage("/images/dvd_blue.png");
        playerSkins[2] = ImageLoader.loadImage("/images/dvd_green.png");
        playerSkins[3] = ImageLoader.loadImage("/images/dvd_red.png");
        playerSkins[4] = ImageLoader.loadImage("/images/dvd_white.png");
        
        colliding = ImageLoader.loadImage("/images/mario.png");
    }
}
