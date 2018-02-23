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

import DungeonCrawl.DungeonCrawl;
import static model.GameData.MAP_WIDTH;
import java.awt.Color;
import model.GameData;
import model.Direction;
import java.awt.Graphics2D;
import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.image.BufferedImage;
import model.GameObject;
import model.Immoveable.Tile.Wall;
import model.Immoveable.Tile.Fire;
import model.Immoveable.Tile.Water;
import model.Level;

public class Tank extends Monster{
        
    public BufferedImage[] tank_S;
    public BufferedImage[] tank_N;
    public BufferedImage[] tank_W;
    public BufferedImage[] tank_E;
    
    int counter = 0;
    
    public float dx;
    public float dy;
    
    private final ObjectAnimator tankMoves;
    
    public Tank(float x, float y, Direction d) {
        super(x, y);        
        tankMoves = new ObjectAnimator();
        super.direction = d;
         
        tank_S = new BufferedImage[1];
        tank_N = new BufferedImage[1];
        tank_W = new BufferedImage[1];
        tank_E = new BufferedImage[1];
        
        try {
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_S.png");           
            tank_S[0] = image; 
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_N.png");           
            tank_N[0] = image; 
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_W.png");           
            tank_W[0] = image; 
            image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Tank_E.png");           
            tank_E[0] = image; 
        } catch(Exception e){
            e.printStackTrace();
        }
        tankMoves.setFrames(tank_S);
    }
        
    @Override
    public void render(Graphics2D g) {
        g.drawImage(tankMoves.getImage(), (int)super.x, (int)super.y, (int)super.HEIGHT, (int)super.WIDTH, 
            null);
        //Draw Collision Box
        g.setColor(Color.blue);
        g.draw(this.getCollisionBox());
    }
    
    @Override
    public void update() {
        dx = super.x;
        dy = super.y;        
        //tankMoves.setFrames(tank_S);
        
        if (Level.fLevelOne) {
            if (counter == 1000) {
                counter = 0;
                switch(direction) {
                    case LEFT:
                        tankMoves.setFrames(tank_W);
                        super.x -= super.MOVEMENT;
                        break;
                    case RIGHT:
                        tankMoves.setFrames(tank_E);
                        super.x += super.MOVEMENT;
                        break;
                }

            } else {
                counter += 100;
            }
            tankMoves.update();
        }
    }
    
    public void noMove() {
        super.x = dx;
        super.y = dy;
    }    
    
    @Override
    public void collide(GameObject O){       
        if(O instanceof Wall || O instanceof Block) {
            noMove();
        }         
        
        super.collide(O);
        if(O instanceof Water || O instanceof Fire) {
            this.setAlive(false);
        }
        
    }
}