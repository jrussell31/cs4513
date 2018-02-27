package model.Moveable;

import java.awt.geom.Rectangle2D;
import model.Direction;
import model.GameObject;

public abstract class MoveableObject implements GameObject {
    public float x, y, dx, dy;
    public Direction direction = Direction.NONE;
    public Direction moving = Direction.NONE;
    private boolean alive;
    private boolean sliding = false;
    
    public MoveableObject(float x, float y){
        this.x = x * OFFSET;
        this.y = y * OFFSET;
        this.alive = true;
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
    public void sliding(boolean b){
        sliding = b;
    }
    
    @Override
    public boolean isAlive(){
        return alive;
    }
    
    public boolean isSliding(){
        return sliding;
    }
    
    @Override
    public void update(){
        dx = x;
        dy = y;
    }
    
    @Override
    public Rectangle2D.Double getCollisionBox(){
        return new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
    }

    public void noMove() {
        x = dx;
        y = dy;
    }
    
    public void slide(Direction d){
        switch(d){
            case RIGHT:                
                this.x += MOVEMENT;
                break;
            case LEFT:
                this.x -= MOVEMENT;
                break;
            case UP:
                this.y -= MOVEMENT;
                break;
            case DOWN:
                this.y += MOVEMENT;
                break;
        }
        sliding(false);
    }
}
