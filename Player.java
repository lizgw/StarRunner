import greenfoot.*;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    int health;
    
    // shield keeps player from dying instantly when they collide with an obstacle
    // b/c act() checks for overlap every frame
    boolean shielded; // whether or not the player can be hit
    int shieldTimer; // how long the player has been shielded
    
    public Player() {
        health = 100;
        shielded = false;
        shieldTimer = 0;
    }
    
    public void act() 
    {
        moveLeft();
        handleShield();
        checkDeath();
    }    
    
    // constant backwards motion
    public void moveLeft() {
        Game world = (Game) getWorld();
        setLocation(getX() - world.currentSpeed, getY());
    }
    
    public void checkDeath() {
        if (!shielded) {
            Game world = (Game) getWorld();
            int diff = world.difficulty;
            
            if (getX() <= 1) {
                // instant death if player hits the left edge of the screen
                world.endGame("fell off the screen");
            } else if (getOneIntersectingObject(Comet.class) != null) {
                // subtract 30/40/50 health if collides with a comet
                switch (diff) {
                    case 0:
                        health -= 30;
                        break;
                    case 1: // fall through
                    default:
                        health -= 40;
                        break;
                    case 2:
                        health -= 50;
                        break;
                }
                if (health <= 0) {
                    world.endGame("crashed into a comet");
                } else {
                    shielded = true;
                    createExplosion();
                }
            } else if (getOneIntersectingObject(Alien.class) != null) {
                // subtract 50/60/70 health
                switch (diff) {
                    case 0:
                        health -= 50;
                        break;
                    case 1: // fall through
                    default:
                        health -= 60;
                        break;
                    case 2:
                        health -= 70;
                        break;
                }
                if (health <= 0) {
                    world.endGame("collided with an alien");
                } else {
                    shielded = true;
                    createExplosion();
                }
            } else if (getOneIntersectingObject(Asteroid.class) != null) {
                // subtract 20/30/40 health
                switch (diff) {
                    case 0:
                        health -= 20;
                        break;
                    case 1: // fall through
                    default:
                        health -= 30;
                        break;
                    case 2:
                        health -= 40;
                        break;
                }
                if (health <= 0) {
                    world.endGame("crashed into an asteroid belt");
                } else {
                    shielded = true;
                    createExplosion();
                }
            }
        }
    }
    
    public void handleShield() {
        if (shielded) {
            shieldTimer++; // increment timer
            // if player has been shielded for 60+ frames...
            if (shieldTimer >= 60) {
                shielded = false; // unshield
                shieldTimer = 0; // reset the timer
            }
        }
    }
    
    public void createExplosion() {
        Game world = (Game) getWorld();
        world.addObject(new Explosion(), getX(), getY());
    }
}
