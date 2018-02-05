package model.Immoveable.Collectible;

import controller.ImageFinder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Chip extends Collectible {
    private final int width = 32;
    private final int height = 32;
    
    public BufferedImage chip;
    
    public Chip(float x, float y) {
        super(x, y);
        
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Chip.png");
            chip = image;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    };

    @Override
    public void render(Graphics g) {
        if(this.isAlive() && this.isDisplayed){
            g.drawImage(chip, (int)super.x, (int)super.y, null);
        }
    }
}
