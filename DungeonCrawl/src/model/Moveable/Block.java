/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Moveable;

import controller.ImageFinder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameData;
import model.GameObject;
import model.Immoveable.Tile.Fire;
import model.Immoveable.Tile.Wall;

/**
 *
 * @author russe_000
 */
public class Block extends MoveableObject {

    public BufferedImage blockImg;
    private float dx;
    private float dy;

    public Block(float x, float y) {
        super(x, y);

        try {
            blockImg = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Block.png");
        } catch (Exception e) {
        }

    }

    @Override
    public void render(Graphics2D g) {
        if (this.isAlive()) {
            g.drawImage(blockImg, (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT, null);


            //Draw Collision Box
            //g.setColor(Color.blue);
            //g.draw(this.getCollisionBox());        
        }
    }

    @Override
    public void update() {
        dx = x;
        dy = y;
    }

    @Override
    public void collide(GameObject O) {
        if (O instanceof Gamer) {
            float tempX = x + ((Gamer) O).x - ((Gamer) O).dx;
            float tempY = y + ((Gamer) O).y - ((Gamer) O).dy;
            if (isMovable(tempX / super.MOVEMENT, tempY / super.MOVEMENT)) {
                x = tempX;
                y = tempY;
            } 
            else {
                ((Gamer) O).noMove();
            }
        }
    }

    private boolean isMovable(float x, float y) {
        Block temp = new Block(x, y);
        for (GameObject o : GameData.gameObjects) {
            if (o instanceof Wall || o instanceof Block || o instanceof Monster || o instanceof Fire && o != this) {
                if (temp.getCollisionBox().intersects(o.getCollisionBox())) {
                    return false;
                }                
            }            
        }
        return true;
    }
}
