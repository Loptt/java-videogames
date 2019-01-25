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
    
    /**
     * Inicializar objetos BufferedImage
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/background_mario.jpg");
        player = ImageLoader.loadImage("/images/dvd.png");
    }
}
