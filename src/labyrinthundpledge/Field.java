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
public class Field {
    enum orientation{
        n,
        e,
        w,
        s
    }
    boolean wall[] = new boolean[4];
    Set set;
    Field(){
        for(int i=0; i<4; ++i)
            wall[i] = true;
        set = new Set();
    }
}
