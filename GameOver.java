import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{
    int distance;
    String deathMessage;
    
    public GameOver(int dist, String msg, int diff) {
        super(800, 450, 1); 
        
        distance = dist;
        deathMessage = msg;
        
        Text gameOverText = new Text("Game Over!", 72, Color.WHITE);
        addObject(gameOverText, getWidth() / 2, 50);
        
        String statusString = "You flew " + distance + " light years\n" +
                              "and then " + deathMessage + "!";
        Text statusText = new Text(statusString, 46, Color.WHITE);
        addObject(statusText, getWidth() / 2, 150);
        
        Button replayBtn = new Button(diff + 6);
        addObject(replayBtn, getWidth() / 2, 280);
        replayBtn.setImage("btn_again.png");
        
        Button quitBtn = new Button(4);
        addObject(quitBtn, getWidth() / 2, 370);
    }
}
