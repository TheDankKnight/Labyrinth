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

public class Player {
    public static final int N = 0, E = 1, S = 2, W = 3;
    private boolean walls[][][];
    private int x, y;
    private int o; // N = 0, E = 1, S = 2, W = 3
    private int turnCount;
    private boolean following;
    private boolean turning;
    
    Player(int x, int y, int startingOrientation, boolean walls[][][]){
        this(x, y, walls);
        o = startingOrientation;
    }
    
    Player(int x, int y, boolean walls[][][]){
        this(walls);
        this.x = x;
        this.y = y;
    }
    
    Player(boolean walls[][][]){
        this.walls = walls;
        turnCount = 0;
        following = false;
    }
    
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getOrientation(){
        return o;
    }
    
    public void setOrientation(int orientation){
        o = orientation;
    }
    
    public void setTurnCount(int turnCount){
        this.turnCount = turnCount;
    }
    
    public int getTurnCount(){
        return turnCount;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void step(){
        if(following){
            if(!turning){
                if(!walls[x][y][getRight()]){ // immer der rechten Wand folgen
                    right();
                    turning = true;
                } else if(!walls[x][y][o]) {
                    move();
                } else { // entweder links ist frei oder man ist in einem Kessel, Wand rechtsbehalten also linksrum.
                    left();
                    // turning braucht man hier nicht:
                    // entweder wir fahren ein _| hoch, gerade aus wird dann vor links gecheckt
                    // oder wir haben ein =], hier wollen wir zweimal links drehen.
                }
            } else {
                if(!walls[x][y][o]){
                    move();
                } else {
                    System.err.println("Ich hab mich sinnlosgedreht. Erschießt mich.");
                    left();
                }
                turning = false;
            }
        } else {
            if(!walls[x][y][o]){
                move();
            } else {
                left(); // wand ist jetzt rechts von uns
                following = true;
            }
        }
    }
    
    public void move(){
        switch(o){
            case N:
                --y;
                break;
            case E:
                ++x;
                break;
            case S:
                ++y;
                break;
            case W:
               --x;
                break;
            default:
                System.err.println("Orientation hat ungültigen Wert:" + o);
                break;
        }
    }
    
    public void right(){
        o = (o+1)%4;
        ++turnCount;
        
    }
    
    private int getRight(){
        return (o+1)%4;
    }
    
    public void left(){
        o = (o+3)%4;
        --turnCount;
    }
    
    private int getLeft(){
        return (o+3)%4;
    }
    
    
    public String toString(){
        return "(" + x + "|" + y + "), tc: " + turnCount + ", o: " + o + ((turning)? ", turning" : ", not turning") + ((following)? ", following" : ", not following");
    }
}
