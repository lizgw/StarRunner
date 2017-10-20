import greenfoot.*;

/**
 * Write a description of class About here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class About extends World
{
    public About() {    
        super(800, 450, 1); 
        
        Button backBtn = new Button(3);
        addObject(backBtn, getWidth() / 2, 375);
    }
}
