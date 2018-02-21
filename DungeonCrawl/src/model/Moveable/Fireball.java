/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Moveable;

import DungeonCrawl.DungeonCrawl;
import static DungeonCrawl.DungeonCrawl.gameData;
import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameData;
import model.GameObject;
import model.Immoveable.Tile.Fire;
import model.Immoveable.Tile.Wall;
import model.Immoveable.Tile.Water;
import model.Level;


/**
 *
 * @author russe_000
 */
public class Fireball extends Monster {
    
    public float dx;
    public float dy;
    
    private int counter = 0;
    
    public BufferedImage[] fireballSprites;
    
    public boolean turnRight, turnUp, turnDown = false; 
    public boolean turnLeft = true; 
    
    private final ObjectAnimator fireballMoves;
    
    public Fireball(float x, float y) {
        super(x, y);
        fireballMoves = new ObjectAnimator();
        
        fireballSprites = new BufferedImage[1];
        try{
            BufferedImage image = (BufferedImage)ImageFinder.getImage("ImagesFolder", "Fireball.png");           
            fireballSprites[0] = image;                       
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(fireballMoves.getImage(), (int)super.x, (int)super.y, (int)super.width, (int)super.height, 
            null);
        
        //Draw Collision Box
        g.setColor(Color.blue);
        g.draw(this.getCollisionBox());
    }
       
    @Override
    public void update() {
        dx = super.x;
        dy = super.y;
        
        fireballMoves.setFrames(fireballSprites);        
        if (Level.fLevelOne){
            
            if(super.x > 64 && super.y == 64){
                super.x -= super.MOVEMENT;                 
            }
            else if(super.y < 192 && super.x == 64){
                super.y += super.MOVEMENT;                
            }
            else if(super.x < 192 && super.y == 192){
                super.x += super.MOVEMENT;               
            }
            else if (super.x == 192){
                super.y -= super.MOVEMENT;               
            }
            /*if (turnLeft){
                if (gameData.time > 0){
                    if (counter == 1000){
                        counter = 0;                         
                        super.x -= super.MOVEMENT;                     
                        if (super.x <= 64) {
                            fireballMoves.setFrames(fireballSprites);
                            turnLeft = false; 
                            turnRight = false; 
                            turnUp = false; 
                            turnDown = true;
                            System.out.println("turn left: x: " + super.x + " y: " + super.y);
                        }                      
                    }
                    else{
                        counter += 100; 
                    }
                } 
            }
           else if(turnDown){
                if(gameData.time > 0){
                    if(counter == 1000){
                        counter = 0;                         
                        super.y += super.MOVEMENT;                        
                        if (super.y >= 192) {
                            fireballMoves.setFrames(fireballSprites);
                            turnLeft = false; 
                            turnRight = true; 
                            turnUp = false; 
                            turnDown = false;
                            System.out.println("turn down: y: " + super.y + "x: " + super.x);
                        }
                    }
                    else{
                        counter += 100;                         
                    }
                }   
            }
            else if(turnRight){
                if(gameData.time > 0){                    
                    if(counter == 1000){
                        counter = 0;                         
                        super.x += super.MOVEMENT;                         
                       if (super.x >= 192) {
                            fireballMoves.setFrames(fireballSprites);
                            turnLeft = false; 
                            turnRight = false; 
                            turnUp = true; 
                            turnDown = false;
                            System.out.println("turn right: x: " + super.x + " y: " + super.y);
                        }
                    }
                    else{
                        counter += 100; 
                    }
                }                    
            } 
            else if(turnUp){
                if(gameData.time > 0){                    
                    if(counter == 1000){
                        counter = 0;                         
                        super.y -= super.MOVEMENT;                               
                        if (super.y <= 64) {
                            fireballMoves.setFrames(fireballSprites);
                            turnLeft = true; 
                            turnRight = false; 
                            turnUp = false; 
                            turnDown = false;
                            System.out.println("turn up: y: " + super.y + " x: " + super.x);
                        }
                    }
                    else{
                        counter += 100; 
                    }
                }               
            }*/  
            fireballMoves.update();
        }
        else if (Level.fLevelTwo){
            if (turnLeft){
                if (gameData.time > 0){
                    if (counter == 1000){
                        counter = 0; 
                        super.x -= super.MOVEMENT;                                                
                        fireballMoves.setFrames(fireballSprites);                            
                    }                      
                    else{
                        counter += 100; 
                    }
                } 
            }
            else if(turnRight){
                if(gameData.time > 0){                    
                    if(counter == 1000){
                        counter = 0;                         
                        super.x += super.MOVEMENT;                                               
                        fireballMoves.setFrames(fireballSprites);                                                   
                    }
                    else{
                        counter += 100; 
                    }
                }                    
            }
        }    
        else{
            
        } 
    }
    
    public void noMove() {
        super.x = dx;
        super.y = dy;
    }
    
    @Override
    public void collide(GameObject O){           
        if(O instanceof Gamer){ 
           this.setAlive(false);
           GameData.gamer.setAlive(false);
           //GameData.gamer.isDisplayed = false;
           //this.isDisplayed = false;            
           GameData.killedMonsters.add(this);
           GameData.killedMonsters.add(GameData.gamer);
           DungeonCrawl.bannerPanel.setBannerText("You collided with the Fireball on Level  " + GameData.currentLevel.getLevelValue());
           GameData.levelInProgress = false;        
        } 
        else if(O instanceof Wall || O instanceof Fire || O instanceof Water){
           if(turnLeft){
              noMove(); 
              turnLeft = false; 
              turnRight = true; 
           }
           else if (turnRight){
              noMove(); 
              turnRight = false; 
              turnLeft = true; 
           }
           else if (turnUp){
               noMove(); 
               turnUp = false; 
               turnDown = true; 
           }
           else{
               noMove(); 
               turnDown = false; 
               turnUp = true; 
           }
        }
    }        
}
