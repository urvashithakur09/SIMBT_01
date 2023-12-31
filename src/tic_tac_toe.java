
import java.util.Random;

import java.util.Scanner;




// TicTacToe Game

class TicTacToe {

    static char[][] board;




    //Constructor with same name as class name

    public TicTacToe() {

        board = new char[3][3];

        initBoard();

    }




    //initialize the board

    //without using an object Using a class name we can access byb using static keyword

    void initBoard() {

        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board.length; j++) {

                board[i][j] = ' ';

            }

        }

    }




    //Displaying the empty board

    static void DisplayBoard() {

        System.out.println("-------------");

        for (int i = 0; i < board.length; i++) {

            System.out.print("| ");

            for (int j = 0; j < board.length; j++) {

                System.out.print(board[i][j] + " | ");

            }

            System.out.println();

            System.out.println("-------------");

        }

    }




    //How to place the mark

    static void PlaceMark(int row, int col, char mark) {

        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {

            board[row][col] = mark;

        } else {

            System.out.println("Invalid Position");

        }

    }




    //Winning condition along column

    static boolean CheckColWin() {

        for (int j = 0; j <= 2; j++) {

            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {

                return true;

            }

        }

        return false;

    }




    //Winning condition along row

    static boolean CheckRowWin() {

        for (int i = 0; i <= 2; i++) {

            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {

                return true;

            }

        }

        return false;

    }




    //Winning condition along diagonal

    static boolean CheckDiagWin() {

        for (int i = 0; i <= 2; i++) {

            if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] ||

                    board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {

                return true;

            }

        }

        return false;

    }




    static boolean CheckDrawCond() {

        for (int i = 0; i <= 2; i++) {

            for (int j = 0; j <= 2; j++) {

                if (board[i][j] == ' ') {

                    return false;

                }

            }

        }

        return true;

    }

}

//Parent class

abstract class Player{

    String name;

    char mark;

    abstract void MakeMove();

    boolean CheckValidMove(int row, int col) {

        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {

            if (TicTacToe.board[row][col] == ' ') {

                return true;

            } else

                System.out.println("Position already occupied !!!");

        }

        return false;

    }

}

class HumanPlayer extends Player {

 //constructor created...Constructor is a setter in java

    HumanPlayer(String name, char mark) {

        //To avoid Shadowing problem

        this.name = name;

        this.mark = mark;

    }

    void MakeMove() {

        Scanner sc = new Scanner(System.in);

        int row;

        int col;

        do {

            System.out.println("Enter the row and col");

            row = sc.nextInt();

            col = sc.nextInt();

        } while (!CheckValidMove(row, col));

        TicTacToe.PlaceMark(row, col, mark);

    }

}

class AIPlayer extends Player {

    //constructor created...Constructor is a setter in java

    AIPlayer(String name, char mark) {

        //To avoid Shadowing problem

        this.name = name;

        this.mark = mark;

    }

    void MakeMove() {

        Scanner sc = new Scanner(System.in);

        int row;

        int col;

        do {

            Random r= new Random();

           row= r.nextInt(3);// random integer less than 3

            col=r.nextInt(3);

        } while (!CheckValidMove(row, col));

        TicTacToe.PlaceMark(row, col, mark);

    }

}

public class tic_tac_toe {

    // main function

    public static void main(String[] args) {

        TicTacToe t = new TicTacToe();

        //User names input

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your names :-");

        String n1 = sc.nextLine();

        HumanPlayer p1 = new HumanPlayer(n1, 'X');

        AIPlayer p2=new AIPlayer("AI",'O');

//Mechanism to Track the current player(cp)

//Create the reference not object which is the type human player called as cp

        Player cp;

        cp = p1;            //refernce type assignment

        while (true) {

            System.out.println(cp.name + "'s turn");

            cp.MakeMove();

            TicTacToe.DisplayBoard();

            if (TicTacToe.CheckColWin() || TicTacToe.CheckDiagWin() || TicTacToe.CheckRowWin()) {

                System.out.println(cp.name + " has won");

                break;

            } else if (TicTacToe.CheckDrawCond()) {

                System.out.println("Game is draw");

                break;

            } else {

                if (cp == p1) {

                    cp = p2;

                } else {

                    cp = p1;

                }

            }

        }

    }

}