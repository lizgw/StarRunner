import greenfoot.*;

/**
 * Write a description of class Alien here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien extends Actor
{
    int speed;
    boolean oriented; // has the alien turned towards the player yet?
    
    public Alien(int s) {
        speed = s;
        oriented = false;
    }
    
    public void act()  {
        updateSpeed();
        
        // turn it towards the player once, but don't follow the player
        if (!oriented) {
            orient();
        }
        
        moveAround();
        checkBounds();
    }    
    
    public void orient() {
        Game world = (Game) getWorld();
        turnTowards(world.player.getX(), world.player.getY());
        oriented = true;
    }
    
    public void moveAround() {
        // 3% chance to change direction every frame
        if (Greenfoot.getRandomNumber(100) < 3) {
            turn(Greenfoot.getRandomNumber(40) + 10);
        }
        move(speed);
    }
    
    public void checkBounds() {
        Game world = (Game) getWorld();
        if (getX() >= world.getWidth() - 1 || getX() <= 1 ||
            getY() >= world.getHeight() - 1 || getY() <= 1) {
            world.removeObject(this);
        }
    }
    
    public void updateSpeed() {
        Game world = (Game) getWorld();
        speed = world.currentSpeed + 1;
    }
}
