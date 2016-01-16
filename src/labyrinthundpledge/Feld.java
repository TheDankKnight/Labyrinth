/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthundpledge;
import java.util.ArrayList;
import java.awt.Color;
/**
 *
 * @author Ich
 */
public class Feld {
    static int count=0;
    private ArrayList<Kasten> kasten;
    public Color color;
    private int ID;
    public int size;
    Feld(Color c, Kasten k){
        this(c);
        kasten.add(k);
        size=1;
    }
    
    Feld(Color c){
        ++count;
        kasten = new ArrayList<>();
        ID = count;
        color = c;
        size=0;
    }
    
    public void add(Feld f){
        int s = f.kasten.size();
        for(int i=0; i<s; ++i){
            kasten.add(f.kasten.get(i));
            f.kasten.get(i).feld = this;
            ++size;
        }
    }
    
    public void delete(){
        --count;
    }
    
    public String toString(){
        return "" + ID;
    }
}
