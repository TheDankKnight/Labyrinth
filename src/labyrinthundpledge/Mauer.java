/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthundpledge;

/**
 *
 * @author Ich
 */
public class Mauer {
    public enum richtungen{
        vertikal,
        horizontal
    }
    private Kasten region1, region2; // region1 = w/n
    private richtungen o; //orientation
    
    public int xpos, ypos;
    
    Mauer(int x, int y, richtungen o){
        this(x, y, o, null, null);
    }
    
    Mauer(int x, int y, richtungen o, Kasten region1, Kasten region2){
        xpos = x;
        ypos = y;
        this.o = o;
        this.region1 = region1;
        this.region2 = region2;
    }
    
    public void setRegion1(Kasten r){
        region1 = r;
    }
    
    public void setRegion2(Kasten r){
        region2 = r;
    }
    
    public Kasten getRegion1(){
        return region1;
    }
    
    public Kasten getRegion2(){
        return region2;
    }
    
    public void abreissen(){
        if(region1 == null || region2 == null){
            throw new NullPointerException("Mauer an Position (" + xpos + "|" + ypos + ") hat die Regionen " + region1 + " und " + region2);
        }
        if(region1.feld.size > region2.feld.size){
            region1.feld.add(region2.feld);
            region2.feld.delete();
        } else if (region1.feld.size < region2.feld.size){
            region2.feld.add(region1.feld);
            region1.feld.delete();
        } else {
            if(0.5<Math.random()){
                region1.feld.add(region2.feld);
                region2.feld.delete();
            } else {
                region2.feld.add(region1.feld);
                region1.feld.delete();
            }
        }
    }
    
    public richtungen getOrientation(){
        return o;
    }

    public boolean isBorder(){
        return region1.feld!=region2.feld;
    }
    
    public String toString(){
        return o + " (" + xpos + "|" + ypos + "), Regionen: " + region1 + ", " + region2;
    }
}
