package treasure.hunt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import static treasure.hunt.TreasureHunt.isDigit;

/**
 *
 * @author José Felipe Flores da Silva
 * Group A
 * 2019405
 * 
 * Computer Programming
 * 
 */

public class TreasureMap {
    private final int grid[][];
    //this string array C will be used throughout the code in order to convert letters into numbers a vice-versa
    private static final String[] C = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
    private final ArrayList<String> SPECIAL;
    private int specials; //this int specials will be used to check whether there are special items left or not
    
    public TreasureMap(int n){ //the constructor will take the size of the grid depending on the difficulty chosen by the user
        this.grid = new int[n][n];
        this.SPECIAL = new ArrayList<>();
        this.specials = 0;
    }
    
    public void setGrid(){ //this method sets all the points in the grid to 0 for the game to start
        for (int i = 0; i < this.grid[0].length; i++) {
            for (int j = 0; j < this.grid.length; j++) {
                this.grid[i][j] = 0;
            }
        }
    }
    
    public void board(){ //this method will print the board based on the grid
        System.out.print("  |");
        for (int i = 0; i < this.grid[0].length; i++) { //for the players to easily choose where they are going to dig, the columns will be "labeled" with the letter based on the String array C
            System.out.print(C[i]+"|");
        }
        System.out.println("");
        for (int row = 0; row < this.grid[0].length; row++) { //each row will have a number 
            if (row<9) { //since numbers below 10 have only one character rather than 2, an if else statement was used to decide whether there should be a space before the number
                System.out.print(" "+(row+1)+"|");
            } else {
                System.out.print((row+1)+"|");
            }
            for (int col = 0; col < this.grid.length; col++) { //here is the bulk of the board, each point will be drawn according to the value set to them
                switch(grid[row][col]){
                    case -2: System.out.print("💀|"); //💀 shows after digging the item PIRATE THREAT
                        break;
                    case -1: System.out.print("💣|"); //the DIG POINT THREAT shows as 💣 after digging
                        break;
                    case 0: System.out.print("_|"); //if the grid hasn't been changed since the initial setting to 0, then a "BLANK" is printed
                        break;
                    case 1: System.out.print("#|"); //if NO SPECIAL ITEMS were placed in the chosen square, it'll become a # after being dug
                        break;
                    case 2: System.out.print("✘|"); //after digging a place with a TREASURE, ✘ will be displayed 
                        break;
                    case 3: System.out.print("♛|"); //♛ is displayed for the only SUPER TREASURE
                        break;
                    case 4: System.out.print("☸|"); //you will see ☸ if you just found an EXTRA SHOVEL
                        break;
                    case 5: System.out.print("*|"); //a MINI TREASURE is portrayed as a * 
                        break;
                }
            }
            System.out.println("");
        }
    }
    
    //the getter method will return the number of special items left undug
    public int getSpecials(){
        return this.specials;
    }
    
    public String check(int[] dig){ //this method checks if the place dug has any hidden special items
        int r = dig[0];
        int c = dig[1];
        String line;
        
        ArrayList<String> threats = new ArrayList<>(); // location of pirate points threats
        try {
            BufferedReader br = new BufferedReader(new FileReader("PirateThreat.txt")); //the program will try to read from the file and will store everything in an arraylist
            
            while((line=br.readLine())!=null){
                threats.add(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to reach PirateThreat.txt");
        }
        for (int i = 0; i < threats.size()-1; i+=2) { //then it will compare the point chosen by the player with the arraylist, checking 2 points at a time (i+=2)
            if (threats.get(i).equalsIgnoreCase(C[c]) && Integer.parseInt(threats.get(i+1))==r+1) {
                return "threat"; //the method will return a string representing the match found
            }
        }
        
        /**
         * this process will be repeated several times for the different threats and treasures
        **/
        
        ArrayList<String> shovelThreats = new ArrayList<>(); // location of dig points threats
        try {
            BufferedReader br = new BufferedReader(new FileReader("ShovelThreat.txt"));
            
            while((line=br.readLine())!=null){
                shovelThreats.add(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to reach ShovelThreat.txt");
        }
        for (int i = 0; i < shovelThreats.size()-1; i+=2) {
            if (shovelThreats.get(i).equalsIgnoreCase(C[c]) && Integer.parseInt(shovelThreats.get(i+1))==r+1) {
                return "shovelT";
            }
        }
        
        ArrayList<String> treasures = new ArrayList<>(); // will store the location of the regular treasures
        try {
            BufferedReader br = new BufferedReader(new FileReader("PiratePete.txt")); //in order to check it, a buffered reader will reach the file PiratePete.txt, where all treasure coordinates have been stored
            
            while((line=br.readLine())!=null){ //to reach all lines in the file, a while loop is used until the line read is empty
                treasures.add(line); //then that line is added to the ArrayList treasures
            }
        } catch (IOException e) {
            System.out.println("Unable to reach PiratePete.txt");
        }
        for (int i = 0; i < treasures.size()-1; i+=2) { //a for loop that skips a number in every run (i+=2) is used to read the 2 points needed for a coordinate
            if (treasures.get(i).equalsIgnoreCase(C[c]) && Integer.parseInt(treasures.get(i+1))==r+1) { //if the point being checked matches any of the points in the ArrayList, then there's a treasure there
                return "treasure";
            }
        }
        
        ArrayList<String> superTreasure = new ArrayList<>(); // will store the location of the one 40 Pirate Points treasure
        try {
            BufferedReader br = new BufferedReader(new FileReader("SuperTreasure.txt"));
            
            while((line=br.readLine())!=null){
                superTreasure.add(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to reach SuperTreasures.txt");
        }
        if (superTreasure.get(0).equalsIgnoreCase(C[c]) && Integer.parseInt(superTreasure.get(1))==r+1) {
            return "super";
        }
        
        ArrayList<String> extraDig = new ArrayList<>(); // location of extra shovels
        try {
            BufferedReader br = new BufferedReader(new FileReader("ExtraDig.txt"));
            
            while((line=br.readLine())!=null){
                extraDig.add(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to reach ExtraDig.txt");
        }
        for (int i = 0; i < extraDig.size()-1; i+=2) {
            if (extraDig.get(i).equalsIgnoreCase(C[c]) && Integer.parseInt(extraDig.get(i+1))==r+1) {
                return "extra";
            }
        }
        
        ArrayList<String> miniTreasures =  new ArrayList<>(); // will store the location of the smaller treasures
        try {
            BufferedReader br = new BufferedReader(new FileReader("MiniTreasure.txt"));
            
            while((line=br.readLine())!=null){
                miniTreasures.add(line);
            }
        } catch (IOException e) {
            System.out.println("Unable to reach MiniTreasure.txt");
        }
        for (int i = 0; i < miniTreasures.size(); i+=2) {
            if (miniTreasures.get(i).equalsIgnoreCase(C[c]) && Integer.parseInt(miniTreasures.get(i+1))==r+1) {
                return "mini";
            }
        }
        return "noSpecial"; //in case there's no match, "noSpecial" will be returned
    }
    
    public String dig(){
        String rString = "", cString = "", digInput; 
        boolean validDig;
        int[] result = {0, 0};
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Where will you dig? (letter number)");
        try{
            do {
                digInput = scan.readLine().trim().toUpperCase(); //to allow the user to add a space or not worry about lower or upper cases, the input is already set to trim and to become upper case
                if (digInput.length()>1 && isDigit(digInput.substring(1, digInput.length()))) { //the user needs to have put more than one digit for it to be a valid dig
                    rString = digInput.substring(1, digInput.length()); //the row will either have 1 or 2 digits so the length of the rString is set to vary according to what has been put by user
                    cString = digInput.substring(0,1); //the column is set to be the first letter entered by the user
                    validDig = true; //if there's more than 1 digit, the digInput is a valid dig
                } else {
                    validDig = false; //if not, an error message will be displayed and the user will be prompted to try again
                    System.out.println("Invalid square, please try again.");
                }
                
            } while (!isValidPoint(rString, cString, validDig)); //this will go on as long as it isn't a valid point until the user puts in a valid one
                for (int i = 0; i < C.length; i++) { //a for loop is used to reach every element in the String array col
                    if (cString.toUpperCase().equals(C[i])) { //if the column bit of the coordinates is equal to one of the letters
                        cString = String.valueOf(i); //the cString becomes the value of the position of that letter in col
                    }
                }
                
                int r = Integer.parseInt(rString)-1; //the "number" input by the user is from 1 to 10, so to transpose that to the place it is in the grid, it has to be deducted by 1
                int c = Integer.parseInt(cString);
                
                
                result[0] = r; 
                result[1] = c;
                
                /**
                 * deducting the number of specials left before check so it'll be added in case no special is found
                 * instead of deducting 1 in each one of the switch cases
                */
                this.specials-=1; 
                
                switch(check(result)){ //the method check will get the input and return a string referring to the special item placed in the spot dug
                    case "threat":
                        this.grid[r][c] = -2;
                        break;
                    case "shovelT":
                        this.grid[r][c] = -1;
                        break;
                    case "treasure":
                        this.grid[r][c] = 2;
                        break;
                    case "super":
                        this.grid[r][c] = 3;
                        break;
                    case "extra":
                        this.grid[r][c] = 4;
                        break;
                    case "mini":
                        this.grid[r][c] = 5;
                        break;
                    default:
                        this.grid[r][c] = 1;
                        this.specials+=1;
                        break;
                }
        } catch(IOException e){
            System.out.println("Unable to reach file");
        }
        
        return check(result); //this method dig will return a string that will be checked for the outcome of this dig
    }
    
    private boolean isValidPoint(String rString, String cString, boolean validDig){ //this method checks whether it is a valid point to dig or not
        if (validDig==false) { //if it's not a valid dig based on not being a valid input by the user (i.e. only numbers, no letters) then it's not a valid point
            return false;
        }
        
        int cNum=-1;
        
        for (int i = 0; i < C.length; i++) { //again, to reach all elements in col[] a for loop is used
            if (cString.equalsIgnoreCase(C[i])) { //if the column put by the user is equal to any of the letters in the String array, then that position is the column to be checked, otherwise it'll stay as -1
                cNum = i;
            }
        }
        int rNum = Integer.valueOf(rString)-1; //the row to be checked is the number put by the user -1
        if (cNum>=0 && cNum<=this.grid.length-1 && rNum>=0 && rNum<=this.grid.length-1 && !isUsed(rNum, cNum)) { //if they are between 0 and 9 and are not used, then it's a valid point
            return true;
        }
        System.out.println("Invalid square! Please select a valid one"); //else, it isn't a valid point and the user will be prompted to input a valid one
        return false;
    }
    
    private boolean isUsed(int r, int c){ //if the point is different than 0, then it has been dug before, therefore it's already been used
        return this.grid[r][c]!=0;
    }
    
    public void placeSpecials(){//this method will place the special items on the grid board
        Random rd = new Random();
        String specialC, specialR;
        int c, r;
        try {
            try (BufferedWriter mT = new BufferedWriter(new FileWriter("PiratePete.txt", false)) //this bufferedwriter will overwrite to PiratePete.txt
            ) {
                for (int i = 0; i <= rd.nextInt(3)+1; i++) {//this for loop will write 1 to 4 treasures to the file
                    do{
                        specialC = C[rd.nextInt(this.grid.length)]; //a random number will be generated for the system to find out what column it'll be and that number will get the correspondent letter from the String array col[]
                        specialR = String.valueOf(rd.nextInt(this.grid.length-1)+1); //and this will generate the row
                    } while(hasSpecial(specialC, specialR)); //this process will be redone until the place on the board has no special items placed on yet
                    
                    this.SPECIAL.add(specialC); //the coordinates will be store in an Array List that has all special items coordinates
                    this.SPECIAL.add(specialR);
                    this.specials++;
                    mT.write(specialC); //the coordinates will also be written to the file PiratePete.txt
                    mT.newLine();
                    mT.write(specialR);
                    mT.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to reach file PiratePete.txt");
        }
        
        /*
            while playing the game, the chances of finding a treasure were too low
            with that in mind I added some more features to it:
            mini treasure, super treasure, pirate points threat, shovel threat and the chance of getting an extra shovel
        */
        
        try {
            try (BufferedWriter miniT = new BufferedWriter(new FileWriter("MiniTreasure.txt", false)) //the mini treasures will be stored in MiniTreasure.txt
            ) {
                for (int i = 0; i < 10; i++) { //there'll always be 10 mini treasures per game that'll be randomly placed on the grid board in a similar way to the regular treasure
                    do{
                        specialC = C[rd.nextInt(this.grid.length)];
                        specialR = String.valueOf(rd.nextInt(this.grid.length-1)+1);
                    } while(hasSpecial(specialC, specialR));
                    
                    this.SPECIAL.add(specialC);
                    this.SPECIAL.add(specialR);
                    this.specials++;
                    miniT.write(specialC);
                    miniT.newLine();
                    miniT.write(specialR);
                    miniT.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to reach file MiniTreasure.txt");
        }
        
        try { 
            try (BufferedWriter mSuper = new BufferedWriter(new FileWriter("SuperTreasure.txt", false)) //there'll always only be one super treasure, randomly placed similarly writing to the file SuperTreasure.txt
            ) {
                do {
                    specialC = C[rd.nextInt(this.grid.length)];
                    specialR = String.valueOf(rd.nextInt(this.grid.length-1)+1);
                } while (hasSpecial(specialC, specialR));
                
                this.SPECIAL.add(specialC);
                this.SPECIAL.add(specialR);
                this.specials++;
                mSuper.write(specialC);
                mSuper.newLine();
                mSuper.write(specialR);
                mSuper.newLine();
            }
        } catch (IOException e) {
            System.out.println("Unable to reach file SuperTreasure.txt");
        }
        
        try {
            try (BufferedWriter mTh = new BufferedWriter(new FileWriter("PirateThreat.txt", false)) //the threat to player's pirate points will be stored in PirateThreat.txt
            ) {
                for (int i = 0; i < rd.nextInt(3)+1; i++) { //there'll be from 1 to 4
                    do {
                        specialC = C[rd.nextInt(this.grid.length)];
                        specialR = String.valueOf(rd.nextInt(this.grid.length-1)+1);
                    } while (hasSpecial(specialC, specialR));
                    
                    this.SPECIAL.add(specialC);
                    this.SPECIAL.add(specialR);
                    this.specials++;
                    mTh.write(specialC);
                    mTh.newLine();
                    mTh.write(specialR);
                    mTh.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to reach file PirateThreat.txt");
        }
        
        try {
            try (BufferedWriter mST = new BufferedWriter(new FileWriter("ShovelThreat.txt", false)) //the threat to players' dig poins will be stored in ShovelThreat.txt
            ) {
                for (int i = 0; i < rd.nextInt(2)+1; i++) {//there'll be from 1 to 3 each game
                    do {
                        specialC = C[rd.nextInt(this.grid.length)];
                        specialR = String.valueOf(rd.nextInt(this.grid.length-1)+1);
                    } while (hasSpecial(specialC, specialR));
                    
                    this.SPECIAL.add(specialC);
                    this.SPECIAL.add(specialR);
                    this.specials++;
                    mST.write(specialC);
                    mST.newLine();
                    mST.write(specialR);
                    mST.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to reach file ShovelThreat.txt");
        }
        
        try {
            try (BufferedWriter mED = new BufferedWriter(new FileWriter("ExtraDig.txt", false)) //the spare shovels' locations will be stored in ExtraDig.txt
            ) {
                for (int i = 0; i < rd.nextInt(2)+2; i++) {//there'll be from 2 to 4 shovels
                    do {
                        specialC = C[rd.nextInt(this.grid.length)];
                        specialR = String.valueOf(rd.nextInt(this.grid.length-1)+1);
                    } while (hasSpecial(specialC, specialR));
                    
                    this.SPECIAL.add(specialC);
                    this.SPECIAL.add(specialR);
                    this.specials++;
                    mED.write(specialC);
                    mED.newLine();
                    mED.write(specialR);
                    mED.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to reach ExtraDig.txt");
        }
    }
    
    private boolean hasSpecial(String treasureC, String treasureR){ //when placing the special items on the board, the system uses this method to check whether something has been placed there already
        for (int i = 0; i < this.SPECIAL.size()-1; i+=2) { //to read the coordinates in the Array List special, a for loop is used
            if (treasureC.equalsIgnoreCase(this.SPECIAL.get(i)) && treasureR.equalsIgnoreCase(this.SPECIAL.get(i+1))) { //if the point being checked matches any of the coordinates stored in the list, then there's a special there already
                return true;
            }
        }
        return false; //if there's no match, then there's no special item stored in that point on the grid board
    }
}