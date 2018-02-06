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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Gamer extends MoveableObject {
    private final int width = 16;
    private final int height = 24;
    
    private float dx;
    private float dy;
    
    public float moveSpeed;
    public float maxSpeed;
    public float stopSpeed;
    
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
    public void render(Graphics2D g) {
        g.drawImage(gamerMoves.getImage(), (int)super.x, (int)super.y, 50, 50, 
            null);
        g.setColor(Color.blue);
        g.draw(this.getCollisionBox());
    }

    @Override
    public void update() {
        dx = super.x;
        dy = super.y;
        if(left)
        {
            super.x -= 50;
            
            facing = 3;
            gamerMoves.setFrames(leftSprites);   
            left = false;
        } 
        else if(right)
        {
            super.x += 50;
            
            facing = 1;
            gamerMoves.setFrames(rightSprites);
            right = false;
        }
        
        if(down)
        {
            super.y += 50;
            
            facing = 2;
            gamerMoves.setFrames(downSprites);
            down = false;
        } 
        else if(up)
        {
            super.y -= 50;
            
            facing = 0;
            gamerMoves.setFrames(upSprites);
            up = false;
        }
        
        if (!(right && down && left && up))
        {
            switch(facing){
                case 0:
                    gamerMoves.setFrames(upIdle);
                    break;
                case 1:
                    gamerMoves.setFrames(rightIdle);
                    break;
                case 2:
                    gamerMoves.setFrames(downIdle);
                    break;
                case 3:
                    gamerMoves.setFrames(leftIdle);
                    break;
            }
            gamerMoves.setDelay(-1);
        }
        else
        {
            gamerMoves.setDelay(100);
        }
        
        gamerMoves.update();                
    }

    public void noMove() {
        super.x = dx;
        super.y = dy;
    }
}