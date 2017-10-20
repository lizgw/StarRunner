import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class Instructions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends World
{
    public Instructions() {
        super(800, 450, 1);
        
        Button backBtn = new Button(3);
        addObject(backBtn, getWidth() / 2, 395);
    }
}
