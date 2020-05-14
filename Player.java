/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehuntgui;

import java.util.Random;

/**
 *
 * @author José Felipe Flores da Silva
 */
public class Player {
    private int piratepoints, digpoints;
    
    public Player(){
    }
    
    public int getPiratePoints(){
        return this.piratepoints;
    }
    
    public int getDigPoints(){
        return this.digpoints;
    }
    
    public void setPiratePoints(int num){
        this.piratepoints += num;
    }
    
    public void setDigPoints(int num){
        this.digpoints += num;
    }
    
    public void createPirate(){
        Random rd = new Random();
        this.digpoints = 4+rd.nextInt(3);
        this.piratepoints = 100 - (this.digpoints*5);
    }
}
