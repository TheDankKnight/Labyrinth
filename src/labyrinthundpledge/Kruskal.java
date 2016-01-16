
package labyrinthundpledge;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
    private int width, height;
    public ArrayList<Mauer> borderMauern, festeMauern; // alle noch lebenden Mauern sind hier drin
    public Mauer[] borderN, borderE, borderS, borderW;
    private Kasten[][] kasten;
    Kruskal(){
        this(8, 8);
    }
    Kruskal(int width, int height){
        this.width = width;
        this.height = height;
        kasten = new Kasten[width][height];
        borderMauern = new ArrayList<>();
        festeMauern = new ArrayList<>();
        borderN = new Mauer[width];
        borderS = new Mauer[width];
        borderE = new Mauer[height];
        borderW = new Mauer[height];
        int absColors = width*height;
        float colorDif = 1.0f/(absColors);
        /* Felder zwischen den Mauern generieren:
           erstmal die Felder, die sowohl vMauern als auch hMauern sind (nicht w+1 und h+1)
        
            | |
            | |
            | |
            | |
            V V <-- so iterieren wier
         */
        
        Mauer[][] vMauern = new Mauer[width][height],
                  hMauern = new Mauer[width][height];
        for(int x=0; x<width; ++x){
            for(int y=0; y<height; ++y){
                /*
                 __ h
                |   <- die beiden Mauern
                v
                */
                
                vMauern[x][y] = new Mauer(x, y,Mauer.richtungen.vertikal);
                hMauern[x][y] = new Mauer(x, y,Mauer.richtungen.horizontal);
                if(x>0){
                    vMauern[x][y].setRegion1(vMauern[x-1][y].getRegion2()); // das Ostfeld ist das Westfeld der Mauer links neben uns uns
                } else { // Wand am Rand // Phatter Rhyme, kommt gleich ins Rhymebook
                    borderW[y] = vMauern[x][y]; 
                }
                if(y>0){
                    hMauern[x][y].setRegion1(hMauern[x][y-1].getRegion2());
                } else {
                    borderN[x] = hMauern[x][y];
                }
                //Farbe für Feld berechenen:
                Kasten temp = new Kasten(x,y, Color.getHSBColor((x+y*width)*colorDif, 0.4f, 0.8f));
                kasten[x][y] = temp;
                vMauern[x][y].setRegion2(temp);
                hMauern[x][y].setRegion2(temp);
            }
        }
        
        /*              y
                        |
                        |
          hMauern       |
            |           | <- vMauern
            V           |
        x ---------------
        */
        
        for(int x=0; x<width; ++x){
            for(int y=0; y<height; ++y){
                borderMauern.add(vMauern[x][y]);
                borderMauern.add(hMauern[x][y]);
            }
        }
        
        // Aussenmauern setzen
        for(int x=0; x<width; ++x){ // Grenzen hinzufügen & löschen
            borderS[x] = new Mauer(x, height, Mauer.richtungen.horizontal, hMauern[x][height-1].getRegion2(), null); // kompliziert, heißt einfach: Mache eine Randmauer und gebe ihr das richtige Feld
            borderMauern.remove(borderN[x]);
        }
        for(int y = 0; y<height; ++y){
            borderE[y] = new Mauer(width, y, Mauer.richtungen.vertikal, vMauern[width-1][y].getRegion2(), null);
            borderMauern.remove(borderW[y]);
        }
        Collections.shuffle(borderMauern);
    }

    public Mauer step(){ // hier passiert die Logik.
        boolean gefunden = false;
        Mauer mauer;
        do{ // immer die erste Mauer im Deck nehmen
            mauer = borderMauern.get(0);
            if(mauer.isBorder()) { // wenn sie eine Grenze ist wird sie abgerissen
                gefunden = true;
            } else { // wenn nicht wird sie zu den festen Mauern geschoben.
                festeMauern.add(mauer);
                borderMauern.remove(0);
            }
        } while((!gefunden && borderMauern.size()>0));
        mauer.abreissen();
        borderMauern.remove(mauer); // und vergessen
        return mauer;
    }
    
    public boolean finished(){
        return Feld.count==1 || borderMauern.isEmpty();
    }
    
    public Kasten[][] getKasten(){
        return kasten;
    }
    
    public void cleanup(){
        Feld.count = 0;
    }
}
