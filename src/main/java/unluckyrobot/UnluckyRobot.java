/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author mgrdichderderian / 1830041
 */

public class UnluckyRobot {
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        int x = 0, y = 0, itrCount = 0, totalScore = 300;
        int reward;
        char direction = ' ';
       
        while (itrCount >= 0) {
            displayInfo(x, y, itrCount, totalScore);
            direction = inputDirection();
            
            if (!doesExceed(x, y, direction)) {
                switch (direction) {
                    case 'r':
                        totalScore -= 50;
                        x++;
                        break;
                    case 'l':
                        totalScore -= 50;
                        x--;
                        break;
                    case 'd':
                        totalScore -= 50;
                        y--;
                        break;
                    default:
                        totalScore -= 10;
                        y++;
                        break;
                }
            }
            else {
                switch (direction) {
                    case 'r':
                        totalScore -= 2050;
                        break;
                    case 'l':
                        totalScore -= 2050;
                        break;
                    case 'd':
                        totalScore -= 2050;
                        break;
                    default:
                        totalScore -= 2010;
                        break;
                } 
                System.out.println("Exceed boundary, -2000 damage applied");
            }
            itrCount++;
            reward = reward();
            reward = punishOrMercy(direction, reward);
            totalScore += reward;
            
            if (isGameOver(x, y, totalScore, itrCount)) {
            evaluation(totalScore);
            break;
            }
        }   
    }
    
    /**
     * Displays the info for the parameters, iterations and score
     * @param x the x parameter
     * @param y the y parameter
     * @param itrCount the number of iterations
     * @param totalScore the total score obtained
     */
    public static void displayInfo(int x, int y, int itrCount, int totalScore) {
        System.out.println("\nFor point (X=" + x + ", Y=" + y + ") at iteration"
                + ": " + itrCount + " the total score is: " + totalScore);
    }
    
    /**
     * Checks if either of the parameters exceeds/gets out of the grid
     * @param x the x parameter
     * @param y the y parameter
     * @param direction the direction chosen
     * @return true if it does exceed and false if it does not
     */
    public static boolean doesExceed(int x, int y, char direction) {
        if ((direction == 'u' && y >= 4) || (direction == 'd' && y <= 0))
            return true;
        else if ((direction == 'l' && x <= 0) || (direction == 'r' && x >= 4))
            return true;
        else
            return false;
    }
    
    /**
     * Randomly generates a number to roll a dice and give a reward accordingly
     * @return the amount of rewards won
     */
    public static int reward() {
        Random rand = new Random();
        int dice = rand.nextInt(6);
        int reward = 0;
        
        switch (dice) {
            case 1:
                reward += -100;
                System.out.println("Dice: 1, reward: -100");
                return reward;
            case 2:
                reward += -200;
                System.out.println("Dice: 2, reward: -200");
                return reward;
            case 3:
                reward += -300;
                System.out.println("Dice: 3, reward: -300");
                return reward;
            case 4:
                reward += 300;
                System.out.println("Dice: 4, reward: 300");
                return reward;
            case 5:
                reward += 400;
                System.out.println("Dice: 5, reward: 400");
                return reward;
            default:
                reward += 600;
                System.out.println("Dice: 6, reward: 600");
                return reward;
        }        
    }
    
    /**
     * Flips a coin and punishes or spares the player accordingly
     * @param direction the direction the player has chosen
     * @param reward the amount of the reward
     * @return returns the newly added amount of points (reward), pos or neg
     */
    public static int punishOrMercy(char direction, int reward) {
        if (reward < 0 && direction == 'u') {
            Random rand = new Random();
            int coin = rand.nextInt(2);
            
            if (coin == 0) {
                System.out.println("Coin: tail | Mercy, the negative reward is "
                        + "removed.");
                reward = 0;
                return reward;
            } else {
                System.out.println("Coin: head | No mercy, the negative "
                        + "reward is applied.");
                return reward;
            }
        }
        return reward;
    }
    
    /**
     * Switches the first letters of both the first and last names to title case
     * @param str the name of the player
     * @return the converted string
     */
    public static  String toTitleCase(String str) {
        str = str.toLowerCase();
        
        String name = Character.toTitleCase(str.charAt(0)) + str.substring(1, 
                str.indexOf(' ')) + " " + 
                Character.toTitleCase(str.charAt(str.indexOf(' ') + 1)) 
                + str.substring(str.indexOf(' ') + 2);
        
        return name;
    }
    
    /**
     * Evaluates the player based on how they have done during the game
     * @param totalScore the score accumulated by the player
     */
    public static void evaluation(int totalScore) {
        Scanner console = new Scanner(System.in);
        System.out.println("\nPlease enter your name (only two words): ");
        String name = console.nextLine();
        
        if (totalScore >= 2000)
            System.out.println("Victory! " + toTitleCase(name) + ", your score "
                    + "is " + totalScore);
        else
            System.out.println("Mission failed! " + toTitleCase(name) + 
                    ", your score is " + totalScore);
    }
    
    /**
     * Makes the player input a direction out of the 4 possible choices
     * @return returns the chosen direction
     */
    public static char inputDirection() {
        Scanner console = new Scanner(System.in);
        char direction = ' ';
        String validDirection = "udlr";
        
        do {
            System.out.println("Please input a valid direction: ");
            direction = console.next().charAt(0);
        } while (!validDirection.contains("" + direction));
        
        return direction;
    }

    /**
     * Checks if the game is over or not
     * @param x the x parameter
     * @param y the y parameter
     * @param totalScore the total score accumulated
     * @param itrCount the iteration count
     * @return returns the true or false answer (true if game is over,
     * false if it is not)
     */
    public static boolean isGameOver(int x, int y, int totalScore, 
            int itrCount) {
        if(itrCount == 20 || totalScore <= -1000 || totalScore >= 2000 || 
                (x == 4 && y == 4) || (x == 4 && y == 0))
            return true;
        else
            return false;
     }
}