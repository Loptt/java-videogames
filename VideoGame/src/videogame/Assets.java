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
    
    public static BufferedImage sprites;
    public static BufferedImage playerUp[];
    public static BufferedImage playerLeft[];
    public static BufferedImage playerDown[];
    public static BufferedImage playerRight[];
    
    public static void init() {
        background = ImageLoader.loadImage("/images/background_mario.jpg");
        player = ImageLoader.loadImage("/images/sprite.png");
        
        sprites = ImageLoader.loadImage("/images/sprite.png");        
        SpriteSheet spritesheet = new SpriteSheet(sprites);
        
        playerUp = new BufferedImage[4];
        playerLeft = new BufferedImage[4];
        playerRight = new BufferedImage[4];
        playerDown = new BufferedImage[4];
        
        for (int i = 0; i < 4; i++) {
            playerUp[i] = spritesheet.crop(i * 66, 66, 66, 66);
            playerLeft[i] = spritesheet.crop(i * 66, 0, 66, 66);
            playerRight[i] = spritesheet.crop(i * 66, 66 *2, 66, 66);
            playerDown[i] = spritesheet.crop(i * 66, 66*3, 66, 66);
        }
    }
}
