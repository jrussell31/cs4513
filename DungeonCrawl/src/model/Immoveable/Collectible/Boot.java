package model.Immoveable.Collectible;

import controller.ImageFinder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.BootType;

public class Boot extends Collectible{
    
    public BootType type;
    
    public BufferedImage[] bootImg;
    
    public Boot(float x, float y, BootType b) {
        super(x, y);
        this.type = b;
        
        bootImg = new BufferedImage[4];
        
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Fire_Boots.png");
            bootImg[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Suction_Boots.png");
            bootImg[1] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Ice_Skates.png");
            bootImg[2] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Flippers.png");
            bootImg[3] = image;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics2D g) {
        if(this.isAlive() && this.isDisplayed){
            BufferedImage image;
            switch(type){
                case FIRE:
                    image = bootImg[0];
                    break;
                case FORCE:
                    image = bootImg[1];
                    break;
                case ICE:
                    image = bootImg[2];
                    break;
                default:
                    image = bootImg[3];
            }
            g.drawImage(image, (int)super.x, (int)super.y, (int)super.width, (int)super.height, null);
            
            //Draw Collision Box
            g.setColor(Color.blue);
            g.draw(this.getCollisionBox());
        }
    }
}
