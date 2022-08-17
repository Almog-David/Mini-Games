package TicTacToe;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    char[][] board;
    char player;
    int turns_left;
    boolean isActive;

    TicTacToe() {
        this.board = new char[3][3];
        for (char [] row : this.board) {
            Arrays.fill(row, ' ');
        }
        this.player = (Math.random()< 0.5) ? 'X' : 'O';
        this.turns_left = 9;
        this.isActive = true;
        Game();
    }

    void Game() {
        while (this.isActive && this.turns_left > 0) {
            System.out.println("It's " + this.player + " turn");
            Turn();
            PrintBoard();
            if (CheckWinning()) {
                this.isActive = false;
            } else {
                this.player = (this.player == 'X') ? 'O' : 'X';
            }
            if (this.turns_left == 0) {
                System.out.println("Game Over! its a tie");
            }
        }
    }

    void Turn() {
        Scanner scan = new Scanner(System.in);
        boolean placed = false;
        System.out.println("Enter a square number (0-8, left to right) in order to place your character:");
        int num = scan.nextInt();
        while (!placed) {
            if(num < 0 || num > 8) {
                boolean valid = false;
                while (!valid) {
                    System.out.println("incorrect square number! choose new number:");
                    num = scan.nextInt();
                    if (num >= 0 && num <= 8) { valid = true;}
                }
            }
            int x = num / 3;
            int y = num % 3;
            if (this.board[x][y] != ' ') {
                System.out.println("This square is taken! choose new number:");
                num = scan.nextInt();
            } else {
                this.board[x][y] = player;
                this.turns_left--;
                placed = true;
            }
        }
    }
    void PrintBoard(){
        for (char [] row : this.board) {
            System.out.println("| " + row[0] + " | " + row[1] + " | " + row[2] + " |");
        }
        System.out.println();
    }

    boolean CheckWinning() {
        if (this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2] && this.board[1][1] != ' ') {
            System.out.println("Game Over! " + this.player + " is the winner");
            return true;
        }
        if (this.board[0][2] == this.board[1][1] && this.board[0][2] == this.board[2][0] && this.board[1][1] != ' ') {
            System.out.println("Game Over! " + this.player + " is the winner");
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] == this.board[i][1] && this.board[i][0] == this.board[i][2] && this.board[i][0] != ' ') {
                System.out.println("Game Over! " + this.player + " is the winner");
                return true;
            }
            if (this.board[0][i] == this.board[1][i] && this.board[0][i] == this.board[2][i] && this.board[0][i] != ' ') {
                System.out.println("Game Over! " + this.player + " is the winner");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}


