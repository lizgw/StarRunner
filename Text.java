import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    String value;
    int size;
    Color textColor;
    
    public Text() {
        value = "";
        size = 32;
        textColor = Color.WHITE;
        
        GreenfootImage g = new GreenfootImage(value, size, textColor, null);
        setImage(g);
    }
    
    public Text(String val, int s, Color col) {
        value = val;
        size = s;
        textColor = col;
        
        GreenfootImage g = new GreenfootImage(value, size, textColor, null);
        setImage(g);
    }
    
    public void act() 
    {
        GreenfootImage g = new GreenfootImage(value, size, textColor, null);
        setImage(g);
    }    
}
