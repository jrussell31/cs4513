package model;

public enum Direction {
    LEFT, RIGHT, UP, DOWN,
    NE, NW, SE, SW, NONE;
    
    public Direction getOppositeDirection(){
        switch(this){
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;            
        }
        return null;
    }
}
