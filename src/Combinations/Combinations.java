package Combinations;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combinations {
    int [][] board;
    boolean isActive;
    int score;

    Combinations(){
        this.board = new int[4][4];
            this.isActive = true;
            this.score = 0;
            Game();
        }

    void Game() {
        StartGame();
        PrintBoard();
        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the action you wold like to do: LEFT, RIGHT, UP, DOWN or EXIT");
            String action = scan.next();
            switch (action) {
                case "LEFT":
                    MoveLeft();
                    break;
                case "RIGHT":
                    MoveRight();
                    break;
                case "UP":
                    MoveUp();
                    break;
                case "DOWN":
                    MoveDown();
                    break;
                case "EXIT":
                    return;
            }
            PrintBoard();
            if (isGameOver()){
                this.isActive = false;
                System.out.println("Game Over! your score is: " + this.score + " points");
            }

        }while (this.isActive);
    }

    void StartGame(){
        int first_number = (Math.random()< 0.5) ? 2 : 4;
        int first_location = (int) (Math.random()* 15);
        int second_number = (Math.random()< 0.5) ? 2 : 4;
        int second_location = (int) (Math.random()* 15);
        this.board[first_location/4][first_location%4] = first_number;
        if(this.board[second_location/4][second_location%4] != 0) {
            this.board[second_location / 4][second_location - 1 % 4] = second_number;
        }
            else{
            this.board[second_location / 4][second_location% 4] = second_number;
            }
    }

    void MoveLeft(){
        LEFT();
        for(int row=0; row<4; row++){
            for(int column=0; column<3; column++){
                if(this.board[row][column] == this.board[row][column+1]){
                    this.board[row][column] += this.board[row][column+1];
                    this.score += this.board[row][column];
                    this.board[row][column+1] = 0;
                }
            }
        }
        LEFT();
        AddRandom();
    }

    private void LEFT(){
        for(int row=0; row<4; row++){
            int move = 0;
            for(int column=0; column<4; column++){
                if(this.board[row][column] == 0) {
                    move++;
                }
                else{
                    if(move!=0) {
                        this.board[row][column - move] = this.board[row][column];
                        this.board[row][column] = 0;
                    }
                }
            }
        }
    }

    void MoveRight(){
        RIGHT();
        for(int row=0; row<4; row++){
            for(int column=3; column>0; column--){
                if(this.board[row][column] == this.board[row][column-1]){
                    this.board[row][column] += this.board[row][column-1];
                    this.score += this.board[row][column];
                    this.board[row][column-1] = 0;
                }
            }
        }
        RIGHT();
        AddRandom();
    }
    private void RIGHT(){
        for(int row=0; row<4; row++){
            int move = 0;
            for(int column=3; column>=0; column--){
                if(this.board[row][column] == 0) {
                    move++;
                }
                else{
                    if(move!=0) {
                        this.board[row][column + move] = this.board[row][column];
                        this.board[row][column] = 0;
                    }
                }
            }
        }
    }

    void MoveUp(){
        UP();
        for(int row=0; row<3; row++){
            for(int column=0; column<4; column++){
                if(this.board[row][column] == this.board[row+1][column]){
                    this.board[row][column] += this.board[row+1][column];
                    this.score += this.board[row][column];
                    this.board[row+1][column] = 0;
                }
            }
        }
        UP();
        AddRandom();
    }

    private void UP() {
        for (int row = 0; row < 4; row++) {
            int move = 0;
            for (int column = 0; column < 4; column++) {
                if (this.board[column][row] == 0) {
                    move++;
                } else {
                    if (move != 0) {
                        this.board[column - move][row] = this.board[column][row];
                        this.board[column][row] = 0;
                    }
                }
            }
        }
    }


    void MoveDown(){
        DOWN();
        for(int row=3; row>0; row--){
            for(int column=0; column<4; column++){
                if(this.board[row][column] == this.board[row-1][column]){
                    this.board[row][column] += this.board[row-1][column];
                    this.score += this.board[row][column];
                    this.board[row-1][column] = 0;
                }
            }
        }
        DOWN();
        AddRandom();
    }

    private void DOWN() {
        for (int row = 0; row <= 3; row++) {
            int move = 0;
            for (int column = 3; column >= 0; column--) {
                if (this.board[column][row] == 0) {
                    move++;
                } else {
                    if (move != 0) {
                        this.board[column + move][row] = this.board[column][row];
                        this.board[column][row] = 0;
                    }
                }
            }
        }
    }

    void AddRandom() {
        List<Integer> empty = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.board[i][j] == 0) {
                    empty.add(i * 4 + j);
                }
            }
        }
        if (!empty.isEmpty()) {
            int index = (int) (Math.random() * empty.size() - 1);
            int location = empty.get(index);
            int randomNumber = (Math.random() < 0.5) ? 2 : 4;
            this.board[location / 4][location % 4] = randomNumber;
            System.out.println("a new cube was added to square number " + location);

        }
    }
    Boolean isGameOver(){
        return (!CanMoveLeft() && !CanMoveRight() && !CanMoveUp() && !CanMoveDown());
    }

    boolean CanMoveLeft(){
        for(int row=0; row<4; row++){
            for(int column=0; column<3; column++){
                if(this.board[row][column] == this.board[row][column+1] ){
                    return true;
                }
                if(this.board[row][column] == 0 && this.board[row][column+1]!=0) {
                    return true;
                }
            }
        }
        return false;
    }
    boolean CanMoveRight(){
        for(int row=0; row<4; row++){
            for(int column=3; column>0; column--){
                if(this.board[row][column] == this.board[row][column-1]) {
                    return true;
                }
                if(this.board[row][column] == 0 && this.board[row][column-1]!= 0) {
                    return true;
                }
            }
        }
        return false;
    }
    boolean CanMoveUp(){
        for(int row=0; row<3; row++){
            for(int column=0; column<4; column++){
                if(this.board[row][column] == this.board[row+1][column]){
                    return true;
                }
                if(this.board[row][column] == 0 && this.board[row+1][column]!= 0){
                    return true;
                }
            }
        }
        return false;
    }
    boolean CanMoveDown(){
        for(int row=3; row>0; row--){
            for(int column=0; column<4; column++){
                if(this.board[row][column] == this.board[row-1][column]) {
                    return true;
                }
                if(this.board[row][column] == 0 && this.board[row-1][column]!= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    void PrintBoard(){
        for (int [] row : this.board) {
            System.out.println("|  " + row[0] + "  |  " + row[1] + "  |  " + row[2] + "  |  " +  row[3] + "  |");
        }
        System.out.println();
        System.out.println("your score: " + this.score);
    }

    public static void main(String[] args) {
        new Combinations();
    }
}

