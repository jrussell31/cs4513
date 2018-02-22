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
import model.Direction;
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

    private final ObjectAnimator fireballMoves;

    public Fireball(float x, float y) {
        super(x, y);
        fireballMoves = new ObjectAnimator();
        super.direction = Direction.LEFT;

        fireballSprites = new BufferedImage[1];
        try {
            BufferedImage image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Fireball.png");
            fireballSprites[0] = image;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(fireballMoves.getImage(), (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT,
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

        if (Level.fLevelOne) {
            if (counter == 1000) {
                counter = 0;
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
                /*switch (direction) {
                    case LEFT:
                        super.x -= super.MOVEMENT;
                        if (super.x == 64) {
                            direction = Direction.DOWN;
                        }
                        break;
                    case RIGHT:
                        super.x += super.MOVEMENT;
                        if (super.x == 192) {
                            direction = Direction.UP;
                        }
                        break;
                    case UP:
                        super.y -= super.MOVEMENT;
                        if (super.y == 64) {
                            direction = Direction.LEFT;
                        }
                        break;
                    case DOWN:
                        super.y += super.MOVEMENT;
                        if (super.y == 192) {
                            direction = Direction.RIGHT;
                        }
                }*/
            } else {
                counter += 100;
            }
            fireballMoves.update();
        } else if (Level.fLevelTwo) {
            if (counter == 1000) {
                counter = 0;
                switch (direction) {
                    case LEFT:

                        super.x -= super.MOVEMENT;
                        fireballMoves.setFrames(fireballSprites);

                        break;
                    case RIGHT:

                        super.x += super.MOVEMENT;
                        fireballMoves.setFrames(fireballSprites);

                        break;
                }
            }
        } else {
            counter += 100;
        }
    }

    public void noMove() {
        super.x = dx;
        super.y = dy;
    }

    @Override
    public void collide(GameObject O) 
    {
        if(O instanceof Gamer)
        {
        super.collide(O);
        }
            else if (O instanceof Water) 
            {
                this.setAlive(false);
            } 
            else if (O instanceof Wall || O instanceof Fire || O instanceof Block) 
            {
                noMove();
                direction = direction.getOppositeDirection();
            }
    }
}
