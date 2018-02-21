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
            switch (direction) {
                case LEFT:
                    if (counter == 1000) {
                        counter = 0;
                        super.x -= super.MOVEMENT;
                        if (super.x <= 64) {
                            fireballMoves.setFrames(fireballSprites);
                            direction = Direction.DOWN;
                        }
                    } else {
                        counter += 100;
                    }
                    break;
                case DOWN:
                    if (counter == 1000) {
                        counter = 0;
                        super.y += super.MOVEMENT;
                        if (super.y >= 192) {
                            fireballMoves.setFrames(fireballSprites);
                            direction = Direction.RIGHT;
                        }
                    } else {
                        counter += 100;
                    }
                    break;
                case RIGHT:
                    if (counter == 1000) {
                        counter = 0;
                        super.x += super.MOVEMENT;
                        if (super.x >= 192) {
                            fireballMoves.setFrames(fireballSprites);
                            direction = Direction.UP;
                        }
                    } else {
                        counter += 100;
                    }
                    break;
                case UP:
                    if (counter == 1000) {
                        counter = 0;
                        super.y -= super.MOVEMENT;
                        if (super.y <= 64) {
                            fireballMoves.setFrames(fireballSprites);
                            direction = Direction.LEFT;
                        }
                    } else {
                        counter += 100;
                    }
                    break;
            }
            fireballMoves.update();
        } else if (Level.fLevelTwo) {
            switch (direction) {
                case LEFT:
                    if (gameData.time > 0) {
                        if (counter == 1000) {
                            counter = 0;
                            super.x -= super.MOVEMENT;
                            fireballMoves.setFrames(fireballSprites);
                        } else {
                            counter += 100;
                        }
                    }
                    break;
                case RIGHT:
                    if (gameData.time > 0) {
                        if (counter == 1000) {
                            counter = 0;
                            super.x += super.MOVEMENT;
                            fireballMoves.setFrames(fireballSprites);
                        } else {
                            counter += 100;
                        }
                    }
                    break;
            }
        }
    }

    public void noMove() {
        super.x = dx;
        super.y = dy;
    }

    @Override
    public void collide(GameObject O) {
        if (O instanceof Gamer) {
            this.setAlive(false);
            ((Gamer)O).setAlive(false);
            DungeonCrawl.bannerPanel.setBannerText("You collided with the Fireball on Level  " + GameData.currentLevel.getLevelValue());
        } else if (O instanceof Wall || O instanceof Fire || O instanceof Water || O instanceof Block) {
            noMove();
            direction = direction.getOppositeDirection();
        }
    }
}
