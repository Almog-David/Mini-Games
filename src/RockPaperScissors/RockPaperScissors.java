package RockPaperScissors;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Rock Paper Scissors Game! you know the rules..");
            String[] moves = {"r", "p", "s"};
            String computerMove = moves[new Random().nextInt(moves.length)];
            String playerMove;
            while (true) {
                System.out.println("Enter your move: r - rock, p - paper, s - scissors");
                playerMove = scanner.nextLine();
                if (playerMove.equals("r") || playerMove.equals("p") || playerMove.equals("s")) {
                    break;
                }
                System.out.println(playerMove + " is not a valid move! try again");
            }
            System.out.println("the computer move is " + computerMove);
            if (computerMove.equals(playerMove)) {
                System.out.println("It's a tie");
            }
            else if (playerMove.equals("r")) {
                if (computerMove.equals("p")) {
                    System.out.println("you lose!");
                } else {
                    System.out.println("you win!");
                }
            }
            else if (playerMove.equals("p")) {
                if (computerMove.equals("s")) {
                    System.out.println("you lose!");
                } else {
                    System.out.println("you win!");
                }
            }
            else {
                if (computerMove.equals("r")) {
                    System.out.println("you lose!");
                } else {
                    System.out.println("you win!");
                }
            }
            System.out.println("play again? (y/n)");
            String playAgain = scanner.nextLine();
            if(!playAgain.equals("y")){ break;}
        }
    }
}
