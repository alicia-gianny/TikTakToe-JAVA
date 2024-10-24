/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tiktaktoe;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class TikTakToe {
    
    public static void showMatrix(String[][] matrix){ // function that show matrix on the screen  
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++){  
                if(matrix[i][j] == null){
                    System.out.print(". ");
                }else{
                    System.out.print(matrix[i][j] + " ");
                }
            } // end for in j
            System.out.println();
        } // end for in i 
    } // end function showMatrix

    public static void clearMatrix(String[][] matrix){ // finction that atribute null to matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = null; // Atribui null a cada posição da matriz
            }
        } // end for 
    } // end function clearMatrix
    
    public static String finishedGame(String [][] matrix, String finished){
        int lineX=0, lineO=0, columnX=0, columnO=0, diagP_X=0, diagP_O=0, diagS_X = 0, diagS_O = 0, tieCount=0;
        
        // verify if any player winned
        for(int i = 0; i < 3; i++){
            lineX = 0; lineO = 0; columnX = 0; columnO = 0;
            for(int j = 0; j < 3; j++){
                if (matrix[i][j] != null){ 
                   tieCount++;
                   if (matrix[i][j] == "X") lineX++;
                   if (matrix[i][j] == "O") lineO++;
                   if (i == j){
                       if (matrix[i][j]== "X") diagP_X++;
                       if (matrix[i][j]== "O") diagP_O++;
                   }
                   if (i + j == 2) {
                       if (matrix[i][j]== "X") diagS_X++;
                       if (matrix[i][j]== "O") diagS_O++;
                   }
                }
                
                if(matrix[j][i] != null){ 
                    if (matrix[j][i] == "X") columnX++;
                    if (matrix[j][i] == "O") columnO++;
                }  
             
            } // end for in j
            if(lineX == 3 || lineO == 3 || columnX == 3 || columnO == 3 || diagP_X == 3 || diagP_O ==3 || diagS_X == 3 || diagS_O == 3){
                        finished = "playerWon";
            } // end if for the confirmations 
        } // end for in i 
        
        if (tieCount == 9 && !finished.equals("playerWon")){ // verifies if it's a tie
            finished = "tie";
            System.out.println("\nIt's a tie!\n");
        }
        return finished;
    } // end function finishedGame

    public static void main(String[] args) {
        
        // Atributes
        int mode = 0;
        Scanner sc = new Scanner(System.in);
        String[][] matrix = new String[3][3];
        String finished = "notFin";
        int turn = 0, empty = 0;
        int player = 0;
        
        System.out.println("--------------------------------");
        System.out.println("Welcome to the Tik-Tak-Toe game!");
        System.out.println("--------------------------------");
        
        do{
            player = 0; mode = 0; turn = 0; finished = "notFin"; 
            clearMatrix(matrix);    
            
            do{
                System.out.println("-> play against the computer [press 1]"); 
                System.out.println("-> play with two players [press 2]");
                System.out.println("-> leave the game [press 3]");
                System.out.print("> ");
                mode = sc.nextInt();
            }while(mode == 0);
                
            if(mode == 1){ // mode against the computer
                System.out.println("\n>>> Against the computer mode <<< \n");
                showMatrix(matrix);
                do { // verify if the game finished
                   do { // if isn't finish, ask for a new play
                       if (turn % 2 == 0) { // player turn
                           System.out.print("PLAYER > insert the value for row and for the column (1-3): ");
                           int fila = sc.nextInt();
                           int column = sc.nextInt();
   
                           if (fila < 1 || fila > 3 || column < 1 || column > 3 || matrix[fila - 1][column - 1] != null) {
                               System.out.println("Invalid or occupied space, try again.");
                               empty = 0;
                           }else {
                                matrix[fila - 1][column - 1] = "X";
                                empty = 1;
                                showMatrix(matrix);
                                finished = finishedGame(matrix, finished);
                                if(finished.equals("playerWon")){
                                    System.out.println("\nPlayer won!\n");
                                }
                                turn++;
                            }                                 
                        } else{ // computer turn
                           int fila, column;
                            do {
                                fila = (int) (Math.random() * 3);
                                column = (int) (Math.random() * 3);
                            } while (matrix[fila][column] != null);
                            
                            System.out.println("COMPUTER >");
                            matrix[fila][column] = "0";
                            empty = 1;
                            showMatrix(matrix);
                            finished = finishedGame(matrix, finished);
                            if(finished.equals("playerWon")){
                                    System.out.println("\nPlayer won!\n");
                            }
                            turn++;
                        }// end else computer turn
                    } while (empty == 0);
                } while(finished.equals("notFin")); // end mode 1 
            }else if (mode == 2){ // mode with 2 players
                System.out.println("\n >>> 2 players mode <<< \n");
                showMatrix(matrix);
                do {
                    player = (turn % 2 == 0) ? 1 : 2;
                    String symbol = (player == 1) ? "X" : "O";

                    do {
                        System.out.print("PLAYER " + player +" > insert the value for row and for the column (1-3): ");
                           int fila = sc.nextInt();
                           int column = sc.nextInt();

                        if(fila < 1 || fila > 3 || column < 1 || column > 3 || matrix[fila-1][column-1] != null) {
                            System.out.println("Invalid or occupied space, try again.");
                            empty = 0;
                        } else {
                            matrix[fila-1][column-1] = symbol;
                            empty = 1;
                            showMatrix(matrix);
                            finishedGame(matrix, finished);
                            finished = finishedGame(matrix, finished);
                            if(finished.equals("playerWon")) {
                                System.out.println("\nPlayer " + player + " won!\n");
                            }
                            turn++;
                        } // end else
                    } while(empty == 0);
                } while(finished.equals("notFin"));
            } // end mode 2
        }while(mode != 3); 
        
        
    }
}
