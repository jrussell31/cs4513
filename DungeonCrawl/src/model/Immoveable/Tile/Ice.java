/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.Direction;
import model.GameObject;
import model.Moveable.MoveableObject;

/**
 *
 * @author micha
 */
public class Ice extends Floor{
    public BufferedImage[] image;
    
    public Ice(float x, float y) {
        super(x, y);
        super.direction = Direction.NONE;
        
        loadImages();
    }
    public Ice(float x, float y, Direction d) {
        super(x, y);
        super.direction = d;
        
        loadImages();                
    }

    @Override
    public void collide(GameObject O) {
        O.slide();        
    }

    @Override
    public void render(Graphics2D g) {
        switch(direction){
            case NE:
                g.drawImage(image[1], (int)super.x, (int)super.y, (int)super.HEIGHT, (int)super.WIDTH, null);
                break;
            case NW:
                g.drawImage(image[2], (int)super.x, (int)super.y, (int)super.HEIGHT, (int)super.WIDTH, null);
                break;
            case SE:
                g.drawImage(image[3], (int)super.x, (int)super.y, (int)super.HEIGHT, (int)super.WIDTH, null);
                break;
            case SW:
                g.drawImage(image[4], (int)super.x, (int)super.y, (int)super.HEIGHT, (int)super.WIDTH, null);
                break;
            default:
                g.drawImage(image[0], (int)super.x, (int)super.y, (int)super.HEIGHT, (int)super.WIDTH, null);
                break;
        }
    }

    private void loadImages() {
        image = new BufferedImage[5];
        try{
            image[0] = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Ice.png");
            image[1] = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Ice_NE.png");
            image[2] = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Ice_NW.png");
            image[3] = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Ice_SE.png");
            image[4] = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Ice_SW.png");
        }
        catch(Exception e){}
    }
    
}
