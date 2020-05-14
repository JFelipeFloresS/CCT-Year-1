package treasure.hunt;

import java.util.Random;

/**
 *
 * @author José Felipe Flores da Silva
 * Group A
 * 2019405
 * 
 * Computer Programming
 * 
 */

public class Shovel {
    private int digLeft;
    
    public Shovel(){ //initialising the object
        this.digLeft = 4;
    }
    
    public void setShovel(int num){ //the number of dig points in the shovel is 4 + a random number between 0 and the number limit set
        Random rd = new Random();
        int r = rd.nextInt(num);
        this.digLeft += r;
    }
    public int getDigPointsLeft(){ //the "getter" method to reach the dig points left in the shovel
        return this.digLeft;
    }
    public void changeDigPointsLeft(int num){ //in order to vary the dig points left in the shovel, this method has to be called
        this.digLeft+=num;
    }
}