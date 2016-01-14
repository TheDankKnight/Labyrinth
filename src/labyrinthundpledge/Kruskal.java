/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinthundpledge;

/**
 *
 * @author ole
 */
public class Kruskal {
    int width, height;
    Field[][] labyrinth;
    Kruskal(){
        this(8, 8);
    }
    Kruskal(int width, int height){
        this.width = width;
        this.height = height;
        labyrinth = new Field[width][height];
    }
    
    public Field[][] run(){
        
        return labyrinth;
    }
}
