/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthundpledge;

import java.awt.Color;

/**
 *
 * @author Ich
 */
public class Kasten {
    public int x,y;
    Feld feld;
    Kasten(int x, int y, Color c){
        this.x = x;
        this.y = y;
        feld = new Feld(c, this);
    }
    
    public String toString(){
        return "(" + x + "|" + y + "), gehört " + feld;
    }
}
