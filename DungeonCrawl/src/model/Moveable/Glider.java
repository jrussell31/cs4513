package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.Direction;
import model.GameObject;
import model.Immoveable.Tile.Ice;
import model.Level;
import model.Immoveable.Tile.Wall;

public class Glider extends Monster {
    
    public BufferedImage[] glider_N;
    public BufferedImage[] glider_E;
    public BufferedImage[] glider_S;
    public BufferedImage[] glider_W;

    private final ObjectAnimator gliderMoves;
    private int counter;
    
    public Glider(float x, float y, Direction d) {
        super(x, y);
        gliderMoves = new ObjectAnimator();
        super.direction = d;
        moving = d;

        glider_N = new BufferedImage[1];
        glider_E = new BufferedImage[1];
        glider_S = new BufferedImage[1];
        glider_W = new BufferedImage[1];
        
        try {
            BufferedImage image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Glider_N.png");
            glider_N[0] = image;
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Glider_E.png");
            glider_E[0] = image;
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Glider_W.png");
            glider_W[0] = image;
            image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Glider_S.png");
            glider_S[0] = image;
        } catch (Exception e) {
        }
        gliderMoves.setFrames(glider_N);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(gliderMoves.getImage(), (int) super.x, (int) super.y, (int) HEIGHT, (int) WIDTH,
                null);
        
        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

    @Override
    public void update() {
        super.update();
        
        if(isSliding()){
            slide(moving);
        }else {
             if (counter == 1000) {
                counter = 0;
                switch (direction) {
                    case LEFT:
                        gliderMoves.setFrames(glider_W);
                        super.x -= MOVEMENT;
                        break;
                    case RIGHT:
                        gliderMoves.setFrames(glider_E);
                        super.x += MOVEMENT;
                        break;
                    case UP:
                        gliderMoves.setFrames(glider_N);
                        super.y -= MOVEMENT;
                        break;
                    case DOWN:
                        gliderMoves.setFrames(glider_S);
                        super.y += MOVEMENT;
                        break;
                }
            } else {
                counter += 100;
            }
        }
        
        switch (direction) {
                    case LEFT:
                        gliderMoves.setFrames(glider_W); 
                        break;
                    case RIGHT:
                        gliderMoves.setFrames(glider_E);
                        break;
                    case UP:
                        gliderMoves.setFrames(glider_N);
                        break;
                    case DOWN:
                        gliderMoves.setFrames(glider_S);
                        break;
                }
    }
    
    public void turn(Direction d) {
        direction = d;
        moving = d;
    }
    //This collide never gets called for walls. 
    @Override
     public void collide(GameObject O){
         super.collide(O);
         if(O instanceof Ice)
         {
             this.direction = this.direction.getOppositeDirection();
         }
         //super.collide(O);
        // if(O instanceof Wall){
         //    direction= direction.getOppositeDirection();
         //    moving= direction;
        // }
     }
}
