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
        while(!complete()){
            step();
        }
        return labyrinth;
    }
    
    public Field[][] step(){
        if(!complete())
            stepNoCheck();
        return labyrinth;
    }
    
    private void stepNoCheck(){
        int x, y, o; // wallFieldx, wallFieldy, wall Orientation
        do{
            x = (int)Math.round(Math.random()*width);
            y = (int)Math.round(Math.random()*height);
            o = (int)Math.round(Math.random()*4);
        }while(!labyrinth[x][y].wall[o]);
    }
    
    private boolean complete(){
        boolean allOneSet = true;
        Set compSet = labyrinth[0][0].set;
        for(int x=0; allOneSet && x<width; ++x){
            for(int y=0; allOneSet && y<height; ++y){
                allOneSet = compSet==labyrinth[x][y].set;
            }
        }
        return allOneSet;
    }
}
