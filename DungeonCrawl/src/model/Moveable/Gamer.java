/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author russe_000
 */
package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import model.GameData;
import model.GameObject;
import model.Immoveable.Collectible.Boot;
import model.Immoveable.Collectible.Chip;
import model.Immoveable.Collectible.Collectible;
import model.Immoveable.Collectible.Key;
import model.Immoveable.Tile.Button;

public class Gamer extends MoveableObject {
    private final int width = 16;
    private final int height = 24;
    
    private float dx;
    private float dy;
    
    public BufferedImage[] leftSprites;
    public BufferedImage[] rightSprites;
    public BufferedImage[] downSprites;
    public BufferedImage[] upSprites;
    public BufferedImage[] leftIdle;
    public BufferedImage[] rightIdle;
    public BufferedImage[] downIdle;
    public BufferedImage[] upIdle;
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    public int facing = 2; //0 = North, 1 = East, 2 = South, 3 = West,
    
    private final ObjectAnimator gamerMoves;
    
    public Gamer(float x, float y) {
        super(x, y);
        
        gamerMoves = new ObjectAnimator();
        gamerMoves.setDelay(100);
        
        upSprites = new BufferedImage[9];
        rightSprites = new BufferedImage[8];
        downSprites = new BufferedImage[9];
        leftSprites = new BufferedImage[8];
        upIdle = new BufferedImage[1];
        rightIdle = new BufferedImage[1];
        downIdle = new BufferedImage[1];
        leftIdle = new BufferedImage[1];
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkIdleDown.gif");
            
            downIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkIdleUp.gif");
            
            upIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkIdleLeft.gif");
            
            leftIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkIdleRight.gif");
            
            rightIdle[0] = image;
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkWalkDown.gif");
        
            for(int i = 0; i < downSprites.length; i++){
                downSprites[i] = image.getSubimage(i*width + i, 0, width, height);
            }
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkWalkLeft.gif");
            
            for(int i = 0; i < leftSprites.length; i++){
                leftSprites[i] = image.getSubimage(i*width + i, 0, width, height);
            }
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkWalkRight.gif");
            
            for(int i = 0; i < rightSprites.length; i++){
                rightSprites[i] = image.getSubimage(i*width + i, 0, width, height);
            }
            
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "LinkWalkUp.gif");
            
            for(int i = 0; i < upSprites.length; i++){
                upSprites[i] = image.getSubimage(i*width + i, 0, width, height);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setLeft(boolean b)
    {
        left = b;
    }
    
    public void setRight(boolean b)
    {
        right = b;
    }
    
    public void setUp(boolean b)
    {
        up = b;
    }
    
    public void setDown(boolean b)
    {
        down = b;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(gamerMoves.getImage(), (int)super.x, (int)super.y, 60, 60, 
            null);
    }

    @Override
    public Rectangle2D.Double getCollisionBox() {
        // May need to change to adjust to correct height and width
        return new Rectangle2D.Double(super.x, super.y, width*3.5, height*2.5);
    }

    @Override
    public void update() {
        if(left){
            gamerMoves.setFrames(leftSprites);
        }
        else if(right){
            gamerMoves.setFrames(rightSprites);
        }
        else if(up){
            gamerMoves.setFrames(upSprites);
        }
        else if(down){
            gamerMoves.setFrames(downSprites);
        }
        
        gamerMoves.setDelay(100);
        
        if (!(right && down && left && up)&&(dx == 0 && dy == 0))
        {
            switch(facing){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            gamerMoves.setDelay(-1);
        }
        
        gamerMoves.update();
        
        Gamer ghostGamer = new Gamer(super.x + dx, super.y - dy);
        
        // Need to check for collisions between ghostGamer and collidibles
        super.x += dx;
        super.y -= dy;
    }

    @Override
    public void findCollision() {
        for(GameObject object: GameData.gameObjects)
        {
            if(object instanceof Monster)
            {
                if(this.getCollisionBox().intersects(
                    object.getCollisionBox()))
                {
                    super.setAlive(false);
                }
            }
            else if(object instanceof Collectible)
            {
                if(object instanceof Key)
                {
                    if(this.getCollisionBox().intersects(
                        object.getCollisionBox()))
                    {
                        
                    }
                }
                else if(object instanceof Boot)
                {
                    if(this.getCollisionBox().intersects(
                        object.getCollisionBox()))
                    {
                    }
                }
                else if(object instanceof Chip)
                {
                    if(this.getCollisionBox().intersects(
                        object.getCollisionBox()))
                    {
                        
                    }
                }
            }
        }
    }
}