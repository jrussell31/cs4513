package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.Direction;
import model.GameObject;
import model.Moveable.Ball;
import model.Moveable.Bug;
import model.Moveable.Fireball;
import model.Moveable.MoveableObject;
import model.Moveable.Glider;

public class Wall extends Tile {

    public BufferedImage image;

    public Wall(float x, float y) {
        super(x, y);

        try {
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Wall.png");
        } catch (Exception e) {
        }
    }

    @Override
    public void collide(GameObject O) {
        if (O instanceof MoveableObject) {
            ((MoveableObject) O).noMove();
            //Collide with Ball
            if(O instanceof Ball){
                ((Ball) O).turnAround();
            }
            //Collide with Fireball
            if(O instanceof Fireball){
                //((Fireball) O).turn(((Fireball) O).direction.turnCCW());
                ((Fireball) O).turn(((Fireball) O).direction.getOppositeDirection());
            }
            //Collision with Bug
            if(O instanceof Bug){
                ((Bug) O).turn(((Bug) O).direction.turnCW());
                
               /* if (((Bug) O).direction == direction.LEFT) {
                    ((Bug) O).turn(((Bug) O).direction.turnCW());                    
                }                                 
                 if (((Bug) O).direction == direction.RIGHT) {                    
                    ((Bug) O).turn(((Bug)O).direction.turnCCW());
                    
                }              
                if (((Bug) O).direction == direction.DOWN) {
                    ((Bug) O).turn(((Bug)O).direction.turnCW());
                }
                
                if (((Bug) O).direction == direction.UP) {
                    ((Bug) O).turn(((Bug)O).direction.turnCW());
                }*/ 
                
                
                //((Bug) O).turn(((Bug) O).direction.getOppositeDirection());
            }
           if(O instanceof Glider){
                ((Glider) O).turn(((Glider) O).direction.turnCW());
            }
            
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y, (int) WIDTH, (int) HEIGHT, null);

        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

}
