package treasure.hunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author José Felipe Flores da Silva
 * Group A
 * 2019405
 * 
 * Computer Programming
 * 
 * ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 * ░░░░░░▓▓▓░░░░▓░░░▓▓▓▓▓░░░░▓▓▓▓▓░░░░▓▓▓▓▓░░░░▓▓▓▓░░░░░░
 * ░░░░░░▓░░▓░░░░░░░▓░░░▓░░░░▓░░░▓░░░░░░▓░░░░░░▓░░░░░░░░░
 * ░░░░░░▓░░▓░░░▓░░░▓░░░▓░░░░▓░░░▓░░░░░░▓░░░░░░▓░░░░░░░░░
 * ░░░░░░▓▓▓░░░░▓░░░▓▓▓▓░░░░░▓▓▓▓▓░░░░░░▓░░░░░░▓▓▓▓░░░░░░
 * ░░░░░░▓░░░░░░▓░░░▓░░▓░░░░░▓░░░▓░░░░░░▓░░░░░░▓░░░░░░░░░
 * ░░░░░░▓░░░░░░▓░░░▓░░░▓░░░░▓░░░▓░░░░░░▓░░░░░░▓░░░░░░░░░
 * ░░░░░░▓░░░░░░▓░░░▓░░░░▓░░░▓░░░▓░░░░░░▓░░░░░░▓▓▓▓░░░░░░
 * ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 * ░░░░░░░░░░░▓▓▓░░░░░░▓▓▓▓░░░░░▓▓▓▓▓░░░░░▓▓▓▓░░░░░░░░░░░
 * ░░░░░░░░░░░▓░░▓░░░░░▓░░░░░░░░░░▓░░░░░░░▓░░░░░░░░░░░░░░
 * ░░░░░░░░░░░▓░░▓░░░░░▓░░░░░░░░░░▓░░░░░░░▓░░░░░░░░░░░░░░
 * ░░░░░░░░░░░▓▓▓░░░░░░▓▓▓▓░░░░░░░▓░░░░░░░▓▓▓▓░░░░░░░░░░░
 * ░░░░░░░░░░░▓░░░░░░░░▓░░░░░░░░░░▓░░░░░░░▓░░░░░░░░░░░░░░
 * ░░░░░░░░░░░▓░░░░░░░░▓░░░░░░░░░░▓░░░░░░░▓░░░░░░░░░░░░░░
 * ░░░░░░░░░░░▓░░░░░░░░▓▓▓▓░░░░░░░▓░░░░░░░▓▓▓▓░░░░░░░░░░░
 * ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
 * 
 */

public class TreasureHunt {

    public static void main(String[] args) {

        int players = setNumberOfPlayers(); //the integer players will be defined through the function setNumberOfPlayers, that returns the number desired by the user
        int difficulty = setDifficulty(); //the difficulty int is the size of the board, it is defined by the function setDifficulty
        Pirate[] pirate = new Pirate[players]; //the Pirate array will be defined to have the length of the number of players
        setPirates(pirate, players); //the method setPirates will take the Pirate array pirate and the number of players to create the objects pirate

        gameRules(); //the rules of the game will be displayed to the players

        TreasureMap tm = new TreasureMap(difficulty); //a new TreasureMap tm is created having different size depending on the difficulty chosen
        tm.setGrid(); //then the method setGrid will set thr 2D array to 0 to start the game
        tm.placeSpecials(); //this method will place all the special items in the .txt files
        int c = 0; //a counter is set to 0 in order for the players to know which round the game is
        while (roundsLeft(pirate, players) > 0 && tm.getSpecials() > 0) { //as long as there are still rounds left and there are specials left, the game will continue
            c++;
            System.out.println("-----------\n|ROUND: " + c + "!|\n-----------"); //before the players' rounds start, the main round the game is in is displayed
            for (int i = 0; i < players; i++) { //this for loop will run for as many players as there are in the game
                if (tm.getSpecials() > 0) { //each round will only start if there are special items left on the board
                    if (pirate[i].getShovel() > 0) { //if the pirate's shovel still has dig points left, then they will get the chance to dig for a treasure
                        tm.board(); //the method board will display the board in use so the user can easily choose where they will dig
                        getInfo(pirate, i); //the pirate's info will be displayed
                        pirate[i].changeShovel(-1); //this method will add dig points to the shovel in use by the pirate, in this case it will decrease the dig points by one, since they were granted the chance to dig
                        /*
                        the next line will call the method dig from the tm object
                        based on the result of that action dig, the method outcome will be called from the pirate object
                        that will change the pirate's pirate points or dig points depending on what has been found
                         */
                        pirate[i].outcome(tm.dig());
                        getInfo(pirate, i); //the pirate's info will be prompted to the user so they can see their updated pirate points and dig points

                        if (pirate[i].getShovel() < 1) { //if after digging, the pirate's shovel goes below 1, their shovel has broken
                            shovelBroke(pirate, i); //the method shovelBroke will be called
                        }
                    } else { //if the pirate has no dig points left, they won't have the chance to dig for this round
                        getInfo(pirate, i); //their information will be displayed
                        if (pirate[i].getPirate() >= 15) {
                            pirate[i].purchaseShovel(); //if the pirate has at least 15 pirate points, they have the chance to buy a new shovel
                        } else { //if they don't have enough pirate points a message will be prompted letting them know they won't be able to buy any shovels
                            System.out.println("Pirate " + pirate[i].getFirst() + " has no DIG POINTS left and doesn't have enough PIRATE POINTS to get a new shovel");
                        }
                    }
                }
            }
        }

        getWinner(pirate, players); //finally, the winner will be shown through the method getWinner

    }

    public static int setNumberOfPlayers() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pString = "";
        System.out.println("How many pirates will join this hunt? (2 to 4 players)");
        do { //this do while loop will run as long as the string isn't a valid number of players
            try {
                pString = br.readLine().toLowerCase(); //the pString will be set by the buffered reader user input
            } catch (IOException e) {
                System.out.println("Unable to read input");
            }
            if (!isValidNumberOfPlayers(pString)) { //in case it isn't a valid number of players, an error message will be prompted and the user will be asked to try again
                System.out.println("Invalid number of players, pease try again");
            }
        } while (!isValidNumberOfPlayers(pString));

        switch (pString) { //this switch will change the spelled numbers into digits in order to parse it as an int
            case "two":
                pString = "2";
                break;
            case "three":
                pString = "3";
                break;
            case "four":
                pString = "4";
                break;
            default:
                break;
        }

        return Integer.parseInt(pString);
    }

    public static boolean isValidNumberOfPlayers(String s) {
        switch (s) { //if the value of the string is between two and four, it's a valid number
            case "2":
            case "two":
            case "3":
            case "three":
            case "4":
            case "four":
                return true;
            default:
                return false;
        }
    }

    public static int setDifficulty() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String difficulty = "";
        System.out.println("What difficulty do you want to play?\n1. Normal\n2. Hard\n3. Extreme");
        do {     //this do while loop will run as long as dif isn't a valid difficulty
            try {
                difficulty = br.readLine().toLowerCase();
            } catch (IOException e) {
                System.out.println("Unable to read input");
            }
            if (!isValidDifficulty(difficulty)) { //if it isn't valid, an error message will be shown
                System.out.println("Invalid difficulty, please try again.");
            }
        } while (!isValidDifficulty(difficulty));

        switch (difficulty) { //if dif is a valid difficulty, the loop ends and the int is set differently depending on the difficulty
            case "one":
            case "1":
            case "normal":
                return 10; //a 10x10 grid is set for the normal difficulty
            case "two":
            case "2":
            case "hard":
                return 15; //15x15 for the hard one
            default:
                return 20; //and 20x20 for the extreme
        }
    }

    public static boolean isValidDifficulty(String difficulty) {
        switch (difficulty) { //if the string dif is any of the options, it is a valid difficulty, otherwise it isn't
            case "one":
            case "1":
            case "normal":
            case "two":
            case "2":
            case "hard":
            case "three":
            case "3":
            case "extreme":
            case "xtreme":
            case "x-treme":
                return true;
            default:
                return false;
        }
    }

    public static void setPirates(Pirate[] pirate, int players) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nArray; //this string array nArray will be used to split the first name and the last name of the pirates
        for (int i = 0; i < players; i++) { //this for loop will run as many times as there are players, to create the number of pirates needed for the game
            String name = "name";

            System.out.println("Enter name of pirate #" + (i + 1));
            while (!name.contains(" ") || !isAlphabetic(name)) { //this while loop will run as long as the name input by the user doesn't have a space and isn't alphabetic
                try {
                    name = br.readLine();
                } catch (IOException e) {
                    System.out.println("Unable to read input");
                }
                if (!name.contains(" ")) { //if the input doesn't contain a space, the player will be asked to enter their full name
                    System.out.println("Please enter pirate's full name");
                } else if (!isAlphabetic(name)) { //if it contains a number, a message will be prompted telling the user to try again
                    System.out.println("Invalid name, please try again (no numbers allowed in the name)");
                }
            }
            nArray = name.split(" "); //the nArray will be populated with the user input split by a space

            String age = "age";
            int a = 0;
            do {    //this do while loop will run as long as the input isn't only digits and isn't 12 or greater 
                System.out.println("How old is pirate " + nArray[0] + "?");
                try {
                    age = br.readLine();
                    if (!isDigit(age)) { //if it isn't a digit, an error message will be shown
                        System.out.println("Invalid age, please try again (only numbers allowed)");
                    } else if (age.length()>0) { //if it is a digit, then that will become the int used to check whether the number is 12 or greater
                        a = Integer.parseInt(age);
                    }
                    if (a < 12 && isDigit(age)) { //an error message will be shown if the age is smaller than 12
                        System.out.println("Only pirates older than 12 years can join this crew...");
                    }
                } catch (IOException e) {
                    System.out.println("Unable to read input");
                }
            } while (!isDigit(age) || a < 12);
            pirate[i] = new Pirate(nArray, age); //a new pirate is created in the Pirate array with the information provided by the user
            pirate[i].addShovel(3); //this method adds an object shovel to the pirate
            pirate[i].setPirate(); //this will set the pirate points based on their initial dig points

            System.out.println("Pirate " + pirate[i].getFirst() + " " + pirate[i].getLast() + ", " + pirate[i].getAge() + " years old.\nPIRATE POINTS: " + pirate[i].getPirate() + ".\nDIG POINTS: " + pirate[i].getShovel());
        }
    }

    public static boolean isAlphabetic(String name) {
        char n;
        for (int i = 0; i < name.length(); i++) { //this for loop will check all characters in the string to check weather it's alphabetic
            n = name.charAt(i);
            if (!Character.isAlphabetic(n) && n != ' ') { //if the character isn't alphabetic and isn't a space, then it's not alphabetic
                return false;
            }
        }
        return true; //otherwise, it is alphabetic
    }

    public static boolean isDigit(String age) { //similarly to the isAlphabetic method, this will check all characters in the string to check weather it is a digit or not
        char n;
        for (int i = 0; i < age.length(); i++) {
            n = age.charAt(i);
            if (!Character.isDigit(n)) {
                return false;
            }
        }
        return true;
    }

    public static void gameRules() { //this method prints out the rules of the game
        System.out.println("\n- Players can only dig once each of the valid squares."
                + "\n- All pirates will start the game with a random number of DIG POINTS."
                + "\n- All pirates start the game with a random number of PIRATE POINTS based on their initial DIG POINTS"
                + "\n- There are multiple treasures and multiple threats."
                + "\n- Each treasure dug by a pirate increases their PIRATE POINTS by 20."
                + "\n- Each super treasure dug by a pirate increases their PIRATE POINTS by 40."
                + "\n- Every attempt will deduct 1 to your DIG POINTS."
                + "\n- The pirate who has most PIRATE POINTS will take Pirate Pete's place as CAPTAIN."
                + "\n");
    }

    public static int roundsLeft(Pirate[] pirate, int players) { //this method will return how many rounds are there left based on the dig points of the players
        ArrayList<Integer> dig = new ArrayList<>();
        for (int i = 0; i < players; i++) { //everyone's dig points will be stored in the arraylist dig
            dig.add(pirate[i].getShovel());
        }

        while (dig.size() > 1) { //this loop will run until the greatest number on the list is left, removing the smaller ones
            if (dig.get(0) < dig.get(1)) {
                dig.remove(0);
            } else {
                dig.remove(1);
            }
        }

        return dig.get(0); //the rounds left will be defined by the biggest dig points among the crew
    }

    public static void getInfo(Pirate[] pirate, int i) { //this method will display the pirate's first name, pirate points and dig points
        System.out.println("Pirate " + pirate[i].getFirst() + ":\nPIRATE POINTS: " + pirate[i].getPirate() + "\nDIG POINTS: " + pirate[i].getShovel() + "\n");
    }

    public static void shovelBroke(Pirate[] pirate, int i) { //if the pirate's shovel breaks, a message is prompted to the user
        System.out.println("Ay ay, Captain Pete, me shovel has broke and I've a pain on me knee! No DIG POINTS left, nothing can be done... only thing left be me bottle of rum!");
        if (pirate[i].getPirate() >= 15) {
            pirate[i].purchaseShovel(); //if they have enough pirate points to buy a shovel, that option will be given
        } else { //otherwise, a message will warn the player they have no pirate points left to buy a shovel
            System.out.println("Pirate " + pirate[i].getFirst() + " has no DIG POINTS left and doesn't have enough PIRATE POINTS to get a new shovel");
        }
    }

    public static void getWinner(Pirate[] pirate, int players) {
        ArrayList<Integer> points = new ArrayList<>();
        System.out.println("");
        for (int i = 0; i < players; i++) { //this for loop will add all pirate's pirate points to the array list points and show the users everyone's final pirate points
            points.add(pirate[i].getPirate());
            System.out.println("Pirate " + pirate[i].getFirst() + " has " + pirate[i].getPirate() + " PIRATE POINTS");
        }
        System.out.println("");

        while (points.size() > 1) { //this while loop will go on as long as the arraylist is greater than 1
            if (points.get(0) < points.get(1)) { //it'll remove the smaller number between the 2 numbers being checked
                points.remove(0);
            } else {
                points.remove(1);
            }
        }

        int c = 0; //this counter will be used to check how many pirates have the greatest number (the one that was left in the arraylist)
        for (int i = 0; i < players; i++) { //this for loop will check all pirates and increase the counter if the number left is the same as their pirate points
            if (points.get(0) == pirate[i].getPirate()) {
                c++;
            }
        }
        ArrayList<Integer> digs = new ArrayList<>(); //in case there's a draw, the dig points will need to be checked in a similar fashion
        if (c == 1) { //if there is only one c, then we have a winner
            for (int i = 0; i < players; i++) {
                if (points.get(0) == pirate[i].getPirate()) {
                    System.out.println("Pirate " + pirate[i].getFirst() + " is the greatest pirate of the crew and became the new Captain with " + pirate[i].getPirate() + " PIRATE POINTS");
                }
            }
        } else { //otherwise, we have more than one
            for (int i = 0; i < players; i++) { //this for loop will add the dig points left for each pirate to the arraylist digs
                digs.add(pirate[i].getShovel());
                System.out.println("Pirate " + pirate[i].getFirst() + " has " + pirate[i].getShovel() + " DIG POINTS");
            }
            while (digs.size() > 1) { //this loop will go on as long as the arraylist is greater than 1
                if (digs.get(0) < digs.get(1)) { //this will remove the smaller number between the 2 numbers being checked
                    digs.remove(0);
                } else {
                    digs.remove(1);
                }
            }
            c = 0; //the counter is reset to 0
            String draw[] = {};
            for (int i = 0; i < players - 1; i++) { //the for loop will check all pirates and increase the counter if the number left is the same as their dig points and add that name to a String array that will be used in case of a draw
                if (digs.get(0) == pirate[i].getShovel()) {
                    draw[c] = pirate[i].getFirst();
                    c++;
                }
            }

            if (c == 1) { //if there is only one match, then they will be the winners
                for (int i = 0; i < players; i++) {
                    if (digs.get(0) == pirate[i].getShovel()) {
                        System.out.println("Pirate " + pirate[i].getFirst() + " is the greatest pirate of the crew and became the new Captain with " + pirate[i].getPirate() + " PIRATE POINTS and " + pirate[i].getShovel() + " DIG POINTS!");
                    }
                }
            } else { //if there is more than one, then it's a draw
                for (int i = 0; i < draw.length; i++) {
                    System.out.println("Pirate " + draw[i] + " has " + points.get(0) + " PIRATE POINTS and " + digs.get(0) + " DIG POINTS.");
                }
                System.out.println("Tis but a draw between them... Keelhaul the rest of them!\n Pirate Pete will have to find a new successor on his next adventure.");
            }
        }

    }

}
