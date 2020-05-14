package treasure.hunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

public class Pirate {
    
    //declaring variables used in the class
    private final String first, last;
    private final int age; 
    private int piratePoints;
    private final ArrayList<Shovel> shovels;
    
    public Pirate(String[] name, String age){ //initialising the object
        this.first = name[0];
        this.last = name[1];
        this.age = Integer.parseInt(age);
        this.piratePoints = 100;
        this.shovels = new ArrayList<>();
    }
    
    //the "getter" methods follow
    public String getFirst(){
        return this.first;
    }
    public String getLast(){
        return this.last;
    }
    public int getAge(){
        return this.age;
    }
    public int getShovel(){ 
        int n = 0;
        for (int i = 0; i < this.shovels.size(); i++) { //since players can have more than one shovel, then the dig points are all shovels' digs left
            n += this.shovels.get(i).getDigPointsLeft();
        }
        return n;
    }
    public int getPirate(){
        return this.piratePoints;
    }
    
    public void setPirate(){ //to set the pirate points, deduct the initial dig points times 5 from 100
        this.piratePoints -= (this.getShovel()*5);
    }
    public void changePirate(int num){ //to change the pirate points a pirate has, this method is used
        this.piratePoints+=num;
    }
    
    public void addShovel(int num){ 
        Shovel s = new Shovel(); //a new Shovel s is created
        s.setShovel(num); //that shovel's dig points are set to be 4 + a random number between 0 and the int num
        this.shovels.add(s); //the new shovel will then be added to the arraylist shovels
    }
    public void changeShovel(int num){
        if (this.shovels.size()>0) { //if there's at least one shovel left for the pirate, the Shovel method changeShovel is called, changing the dig points left in that shovel
            this.shovels.get(0).changeDigPointsLeft(num); 
            if (this.shovels.get(0).getDigPointsLeft()<1) { //if the dig points left is 0 or less, then that shovel is removed from the arraylist
                this.shovels.remove(0);
           }
        }
    }
    
    public void outcome(String result){ //this method will get the result from the method dig and decide what happens to the pirate who dug it
        Random rd = new Random();
        int num = rd.nextInt(2);
        switch(result){
            case "threat": //if there's a threat t the spot, they lose 15 pirate points
                changePirate(-15);
                System.out.println("Arghh... I confused some gold with rusty metal with mould. Pirate Pete laughed at me...\nPirate "+getFirst()+" now has "+getPirate()+" PIRATE POINTS");
                break;
            case "shovelT": //if there's a shovel threat, the players will lose 1 to 3 dig points on their current shovel
                changeShovel(-(num+1));
                System.out.println("Trying to dig "+getFirst()+"'s shovel got damaged. Now they only have "+getShovel()+" DIG POINTS left");
                break;
            case "treasure": //the treasures will add 20 pirate points each
                changePirate(20);
                System.out.println("Yo-ho-ho and a bottle of rum. I found me some pieces of eight!\nPirate "+getFirst()+" now has "+getPirate()+" PIRATE POINTS.");
                break;
            case "super": //the super treasure increases the pirate points by 40
                changePirate(40);
                System.out.println("Yo-ho-ho this is the greatest chest of gold!\nPirate "+getFirst()+" now has "+getPirate()+" PIRATE POINTS");
                break;
            case "extra": //if an extra shovel is found, then the method addFoundShovel will be called
                addShovel(1);
                System.out.println("I found me a new shovel!! I have "+getShovel()+" DIG POINTS left");
                break;
            case "mini": //if there's a mini treasure, 5 pirate points are added
                changePirate(5);
                System.out.println("A tiny bit of gold is what I found and now I got "+getFirst()+" PIRATE POINTS.");
                break;
            default: //if no special items are found, a message is prompted
                System.out.println("Walk the plank! There be no treasure here!");
                break;
        }
    }
    
    public void purchaseShovel(){ //this method will ask the player whether they want to buy a shovel using their pirate points
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ans = "";
        System.out.print("Pirate Pete: Pirate "+getFirst()+", you ran out of dig points. ");
        do { //this do while loop will run as long as the string ans isn't a valid answer
            System.out.println("Would you like to buy a new shovel (4-7 rounds) for 15 PIRATE POINTS?");
            try{ans = br.readLine();} 
            catch (IOException e){System.out.println("Unable to read input");}
            
            switch(ans.toLowerCase()){
                case "yes": //if the player wants to buy a shovel, they will lose 15 pirate points and get a shovel with 4-7 dig points
                case "y":
                    changePirate(-15);
                    addShovel(3);
                    System.out.println("You have "+getShovel()+" DIG POINTS left");
                    break;
                case "no": //if they don't, the code keeps running
                case "n":
                    break;
                default: //if it isn't a recognised answer, an error message is shown
                    System.out.println("Invalid input, please try again.\n");
                    break;
            }
        } while(!isValidAnswer(ans));
        
    }
    
    private boolean isValidAnswer(String ans){ //this method will check whether it is a valid answer
        switch(ans){
            case "yes":
            case "y":
            case "no":
            case "n":
                return true;
            default :
                return false;
        }
    }
}