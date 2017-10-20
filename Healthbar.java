import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class Healthbar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healthbar extends Actor
{
    public Healthbar() {
        // create a transparent image 700px x 25px
       GreenfootImage g = new GreenfootImage(700, 25); 
       g.setColor(Color.WHITE);
       
       // draw outline starting at (0, 0) with a full width and height
       g.drawRect(0, 0, 700, 15);
       // fill rect fully
       g.fillRect(0, 0, 700, 15);
       // set the image
       setImage(g);
    }
    
    public void act() 
    {
       double percent = getPlayerHealth();
       int width = (int) (700 * percent);
       
       // create a transparent image 700px x 25px
       GreenfootImage g = new GreenfootImage(700, 25); 
       
       if (percent >= .60) {
           g.setColor(Color.GREEN);
       } else if (percent < .60 && percent > .40) {
           g.setColor(Color.YELLOW);
       } else if (percent <= .40) {
           g.setColor(Color.RED);
       } else {
           g.setColor(Color.WHITE);
        }
       
       // draw outline starting at (0, 0) with a full width and height
       g.drawRect(0, 0, 699, 14);
       // fill rect according to player's health
       g.fillRect(0, 0, width, 15);
       // set the image
       setImage(g);
    }
    
    // returns the player's health as a percentage
    public double getPlayerHealth() {
        Game world = (Game) getWorld();
        double health = world.player.health / 100.0;
        return health;
    }
}
