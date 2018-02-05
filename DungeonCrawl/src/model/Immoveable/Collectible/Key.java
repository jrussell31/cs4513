package model.Immoveable.Collectible;

import model.LockType;
import controller.ImageFinder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Key extends Collectible {
    private final int width = 32;
    private final int height = 32;
    
    public LockType color;
    
    public BufferedImage[] keyImg;
    
    public Key(float x, float y, LockType k) {
        super(x, y);    
        this.color = k;
        
        keyImg = new BufferedImage[4];
        
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Blue_Key.png");
            keyImg[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Green_Key.png");
            keyImg[1] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Red_Key.png");
            keyImg[2] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Yellow_Key.png");
            keyImg[3] = image;
        } catch(Exception e){
            e.printStackTrace();
        }
    };

    @Override
    public void render(Graphics g) {
        if(this.isAlive()){
            BufferedImage image;
            switch(color){
                case BLUE:
                    image = keyImg[0];
                    break;
                case GREEN:
                    image = keyImg[1];
                    break;
                case RED:
                    image = keyImg[2];
                    break;
                default:
                    image = keyImg[3];
            }
            g.drawImage(image, (int)super.x, (int)super.y, 50, 50, null);
        }
    };

    
}
