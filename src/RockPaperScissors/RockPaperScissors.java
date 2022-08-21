package RockPaperScissors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissors extends JFrame {

    RockPaperScissors() {
        this.setTitle("Rock Paper Scissors");
        this.setBounds(200, 200, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        this.setup();
        this.setResizable(false);
        this.setVisible(true);
    }

    JLabel computerChoice,result;
    ImageIcon image_rock, image_paper,image_scissors;
    public void setup(){
        image_rock = new ImageIcon(new ImageIcon("src/RockPaperScissors/Graphics/rock.png").getImage()
                .getScaledInstance(80,80,Image.SCALE_SMOOTH));
        image_paper= new ImageIcon(new ImageIcon("src/RockPaperScissors/Graphics/paper.png").getImage()
                .getScaledInstance(80,80,Image.SCALE_SMOOTH));
        image_scissors= new ImageIcon(new ImageIcon("src/RockPaperScissors/Graphics/scissor.png").getImage()
                .getScaledInstance(80,80,Image.SCALE_SMOOTH));

    computerChoice = new JLabel();
    computerChoice.setHorizontalTextPosition(JLabel.CENTER);
    computerChoice.setVerticalTextPosition(JLabel.BOTTOM);
    computerChoice.setBounds(200,70,100,100);

    JLabel label_rock = new JLabel();
    label_rock.setText("Rock");
    label_rock.setIcon(image_rock);
    label_rock.setHorizontalTextPosition(JLabel.CENTER);
    label_rock.setVerticalTextPosition(JLabel.BOTTOM);
    label_rock.setBounds(100,275,100,100);

    JLabel label_paper = new JLabel();
    label_paper.setText("Paper");
    label_paper.setIcon(image_paper);
    label_paper.setHorizontalTextPosition(JLabel.CENTER);
    label_paper.setVerticalTextPosition(JLabel.BOTTOM);
    label_paper.setBounds(200,275,100,100);

    JLabel label_scissors = new JLabel();
    label_scissors.setText("Scissors");
    label_scissors.setIcon(image_scissors);
    label_scissors.setHorizontalTextPosition(JLabel.CENTER);
    label_scissors.setVerticalTextPosition(JLabel.BOTTOM);
    label_scissors.setBounds(300,275,100,100);

    label_rock.addMouseListener((new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            calculate(label_rock.getText());
        }
    }));

        label_paper.addMouseListener((new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                calculate(label_paper.getText());
            }
        }));

        label_scissors.addMouseListener((new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                calculate(label_scissors.getText());
            }
        }));

   result = new JLabel();
   result.setFont(new Font("Serif",Font.PLAIN,17));
   result.setBounds(200,370,100,100);

   JLabel label_computer = new JLabel();
   label_computer.setText("Computer");
   label_computer.setBounds(210, 150,100,100);

   JLabel label_VS = new JLabel();
   label_VS.setText("VS");
   label_VS.setBounds(230, 170,100,100);

   JLabel label_player = new JLabel();
   label_player.setText("Player");
   label_player.setBounds(220, 190,100,100);

    add(label_rock);
    add(label_paper);
    add(label_scissors);
    add(computerChoice);
    add(result);
    add(label_computer);
    add(label_VS);
    add(label_player);
    setLayout(null);
    }

    public void calculate(String playerMove) {
        String res;
        String[] moves = {"Rock", "Paper", "Scissors"};
        String computerMove = moves[new Random().nextInt(moves.length)];
        computerChoice.setText(computerMove);
        if (computerMove.equals("Rock")) { computerChoice.setIcon(image_rock);}

        else if (computerMove.equals("Paper")) { computerChoice.setIcon(image_paper);}

        else { computerChoice.setIcon(image_scissors);}

        if (computerMove.equals(playerMove)) {
            res="IT'S A TIE";
        }
        else if (playerMove.equals("Rock")) {
            res = (computerMove.equals("Paper")) ? "YOU LOSE!" : "YOU WIN!";}

        else if (playerMove.equals("Paper")) {
            res = (computerMove.equals("Scissors")) ? "YOU LOSE!" : "YOU WIN!";}

        else {
            res = (computerMove.equals("Rock")) ? "YOU LOSE!" : "YOU WIN!";}

        result.setText(res);
    }


    public static void main(String [] args) {
        new RockPaperScissors();
   }
}
