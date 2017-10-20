import greenfoot.*;

/**
 * Write a description of class Comet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Comet extends Actor
{
    int speed;
    boolean turned;
    
    public Comet(int s) {
        speed = s;
        turned = false; // has the direction been set yet?
    }
    
    public void act() 
    {
        // only set direction once, don't follow player
        if (!turned) {
            turnToPlayer();
        }
        
        move(speed);
        checkBounds();
    }
    
    public void checkBounds() {
        if (getX() >= getWorld().getWidth() - 2 || getX() <= 1 ||
            getY() <= 1 || getY() >= getWorld().getHeight() - 1) {
            getWorld().removeObject(this);
        }
    }
    
    public void turnToPlayer() {
        Game world = (Game) getWorld();
        Player p = world.player;
        
        // turn towards the player with a bit of offset
        turnTowards(p.getX(), p.getY());
        int offset = Greenfoot.getRandomNumber(50) + 1;
        if (Greenfoot.getRandomNumber(2) < 1) {
            offset *= -1;
        }
        turn(offset);
        turned = true;
    }
}
