package ObjectOrientedProgramming;

import java.util.Random;
import java.util.Scanner;

public class BattleShip {
    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();
    public static void main (String[] args) {
        // a 10 by 10 grid representing the ocean
        char[][] grid = new char[10][10];
        Intro(); OceanMap(grid); playerDeploy(grid); computerDeploy(grid); OceanMap(grid);
        while (battleGround(grid) != 0) {
            playerTurn(grid); computerTurn(grid);
        }
        finalMap(grid);
    }
    public static void Intro() {
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, the sea is empty");
    }
    public static void OceanMap(char[][] map) {
        System.out.println("   0123456789   ");
        for(int i = 0; i < map.length; i++) {
            System.out.print(i + " |");
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] != '1' && map[i][j] != '2') {
                    map[i][j] = ' ';
                    System.out.print(map[i][j]);
                } else if(map[i][j] == '1'){
                    System.out.print('@');
                } else if(map[i][j] == '2') {
                    System.out.print(' ');
                }
            }
            System.out.println("| " + i);
        }
        System.out.println("   0123456789   ");
    }
    public static int playerCoordinateX() {
        int x = input.nextInt();
        while(x > 9 || x < 0) {
            System.out.println("Invalid co-ordinate");
            System.out.print("Re-Enter X coordinate for your ship: ");
            x = input.nextInt();
        }
        return x;
    }
    public static int playerCoordinateY() {
        int y = input.nextInt();
        while(y > 9 || y < 0) {
            System.out.println("Invalid co-ordinate");
            System.out.print("Re-Enter Y coordinate for your ship: ");
            y = input.nextInt();
        }
        return y;
    }
    public static void playerDeploy(char[][] map) {
        int count = 5;
        while(count > 0) {
            System.out.print("Enter X coordinate for your ship: ");
            int x = playerCoordinateX();
            System.out.print("Enter Y coordinate for your ship: ");
            int y = playerCoordinateY();
            if(map[x][y] == '1' || map[x][y] == '2') {
                System.out.println("A ship has already been deployed there.");
                System.out.print("Enter another X coordinate for ship: ");
                x = playerCoordinateX();
                System.out.print("Enter another Y coordinate for ship: ");
                y = playerCoordinateY();
            }
            map[x][y] = '1';
            count--;
        }
    }
    public static int computerCoordinateX() {
        int x = rand.nextInt(10);
        return x;
    }

    public static int computerCordinateY() {
        int y = rand.nextInt(10);
        return y;
    }
    public static void computerDeploy(char[][] map) {
        System.out.println("Computer is deploying ships....");
        int count = 5;
        int num = 1;
        while(count > 0) {
            int x = computerCoordinateX();
            int y = computerCordinateY();
            if(map[x][y] == '1' || map[x][y] == '2') {
                System.out.println("A ship has already been deployed there.");
                x = computerCoordinateX();
                y = computerCordinateY();
            }
            map[x][y] = '2';
            System.out.println(num + ". ship DEPLOYED");
            count--;
            num ++;
        }
        System.out.println();
    }
    public static void playerTurn (char[][] map) {
        System.out.println("YOUR TURN"); System.out.println("Guess one of the coordinates of the opponent ship");
        System.out.print("Enter X coordinate: ");
        int x = playerCoordinateX();
        System.out.print("Enter Y coordinate: ");
        int y = playerCoordinateY();
        if(map[x][y] == '1') {
            System.out.println("Oh no, you sunk your own ship :(");
            map[x][y] = 'x';
        } else if(map[x][y] == '2') {
            System.out.println("Boom! You sunk the ship!");
            map[x][y] = '!';
        } if(map[x][y] == ' ') {
            System.out.println("Sorry, you missed");
            map[x][y] = '-';
        }
    }
    public static void computerTurn (char[][] map) {
        System.out.println("COMPUTER'S TURN");
        int x = computerCoordinateX(); int y = computerCordinateY();
        if(map[x][y] == '1') {
            System.out.println("The Computer sunk one of your ships!");
            map[x][y] = 'x';
        } else if(map[x][y] == '2') {
            System.out.println("The Computer sunk one of its own ships!");
            map[x][y] = '!';
        } if(map[x][y] == ' ') {
            System.out.println("Computer missed");
            map[x][y] = '-';
        }
    }
    public static int battleGround(char[][] map) {
        int playerScore = 0; int computerScore = 0;
        System.out.println("   0123456789   ");
        for(int i = 0; i < map.length; i++) {
            System.out.print(i + " |");
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == '!' || map[i][j] == '-' || map[i][j] == 'x') {
                    System.out.print(map[i][j]);
                } else if(map[i][j] == '1'){
                    playerScore++;
                    System.out.print('@');
                } else if(map[i][j] == '2') {
                    computerScore++;
                    System.out.print(' ');
                } else {
                    map[i][j] = ' ';
                    System.out.print(map[i][j]);
                }
            }
            System.out.println("| " + i);
        }
        System.out.println("   0123456789   ");
        if (playerScore == 0) {
            System.out.println("Your ships: "+ playerScore + " | Computer ships: " + computerScore);
            System.out.println("Computer wins the battle");
            return playerScore;
        }
        else if (computerScore == 0) {
            System.out.println("Your ships: "+ playerScore + " | Computer ships: " + computerScore);
            System.out.println("Hooray! You won the battle");
            return computerScore;
        }
        else {
            System.out.println("Your ships: "+ playerScore + " | Computer ships: " + computerScore);
            return playerScore + computerScore;
        }
    }
    public static void finalMap(char[][] map) {
        System.out.println("   0123456789   ");
        for(int i = 0; i < map.length; i++) {
            System.out.print(i + " |");
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 'x') {
                    System.out.print(map[i][j]);
                } else if(map[i][j] == '1'){
                    System.out.print('@');
                } else if(map[i][j] == '2') {
                    System.out.print(' ');
                } else {
                    map[i][j] = ' ';
                    System.out.print(map[i][j]);
                }
            }
            System.out.println("| " + i);
        }
        System.out.println("   0123456789   ");
    }
}