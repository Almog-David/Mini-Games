package Whack_A_Mole;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Objects;
import java.util.Random;

//import java.awt.Color;
//import java.awt.Font;
//        import java.awt.Image;
//        import java.awt.Point;
//        import java.awt.Toolkit;
//        import java.awt.event.ActionEvent;
//        import java.awt.event.ActionListener;
//        import java.awt.event.MouseAdapter;
//        import java.awt.event.MouseEvent;
//        import java.io.BufferedReader;
//        import java.io.BufferedWriter;
//        import java.io.FileReader;
//        import java.io.FileWriter;
//        import java.io.IOException;
//
//        import javax.swing.ImageIcon;
//        import javax.swing.JButton;
//        import javax.swing.JFrame;
//        import javax.swing.JLabel;
//        import javax.swing.JOptionPane;
//        import javax.swing.JPanel;
//        import javax.swing.SwingConstants;
//        import javax.swing.Timer;


public class Game extends JFrame{

    private JPanel panel;
    private JLabel[] holes = new JLabel[16];
    private int[] board = new int[16];

    private int score = 0;
    private int timeLeft = 30;
    private int high_score = 0;

    private JLabel lblScore;
    private JLabel lblTimeLeft;
    private JLabel lblHighscore;

    private JButton Start;
    private Timer timer;

    private void loadHighscore(){
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/highscore.txt"));
            line = br.readLine();
            br.close();
        } catch (IOException e) {
            line = "";
        }

        if(!Objects.equals(line, "")){
            high_score = Integer.parseInt(line);
            lblHighscore.setText("Highscore: " + high_score);
        }
    }

    private void saveHighscore(){
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/highscore.txt", false)); //append - set to false
            bw.write("" + high_score);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error while saving highscore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gameOver(){
        Start.setEnabled(true);
        if(score > high_score){
            high_score = score;
            lblHighscore.setText("Highscore: " + high_score);
            JOptionPane.showMessageDialog(this, "Your final score is: " + score, "You beat the high score!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Your final score is: " + score, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
        }
        score = 0;
        timeLeft = 30;
        lblScore.setText("Score: 0");
        lblTimeLeft.setText("30");

        clearBoard();

        saveHighscore();
    }

    private void pressedButton(int id){
        int val = board[id];

        //if val is 1 = mole
        //if val is 0 = empty hole
        if(val==1){
            score++;
        }else{ //val==0
            score--;
        }

        lblScore.setText("Score: " + score); //update the score

        clearBoard();

        genRandMole();
    }

    private void initEvents(){
        for (JLabel hole : holes) {
            hole.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JLabel lbl = (JLabel) e.getSource();
                    int id = Integer.parseInt(lbl.getName());
                    pressedButton(id);
                }
            });
        }

        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Start.setEnabled(false);
                clearBoard();
                genRandMole();
                timer.start();
            }
        });

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(timeLeft == 0){
                    lblTimeLeft.setText("" + timeLeft);
                    timer.stop();
                    gameOver();
                }
                lblTimeLeft.setText("" + timeLeft);
                timeLeft--;
            }
        });
    }

    private void initGUI(){
        setTitle("Whack A Mole"); // sets the title of thr window
        setResizable(false); // can't change the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit thr program while pressing X
        setBounds(100, 100, 608, 720); // bounds of the window

        //JPanel contentPane = new JPanel();
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 51, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Whack A Mole");
        lblTitle.setForeground(new Color(153, 204, 0));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Century Gothic", Font.BOLD, 20));
        lblTitle.setBounds(0, 0, 602, 47);
        contentPane.add(lblTitle);

        panel = new JPanel();
        panel.setBackground(new Color(0, 102, 0));
        panel.setBounds(32, 105, 535, 546);
        panel.setLayout(null);
        panel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(loadImage("Graphics/hammer.png").getImage(),
                new Point(0,0),"custom cursor1"));
        contentPane.add(panel);

        int x_coordinate;
        int y_coordinate = 396;
        for(int i=0; i<4; i++){
            x_coordinate = 0;
            for( int j=0; j<4; j++){
                int current_index = i*4+j;
                holes[current_index] = new JLabel(String.valueOf(current_index));
                holes[current_index].setName(String.valueOf(current_index));
                holes[current_index].setBounds(x_coordinate, y_coordinate, 132, 132);
                panel.add(holes[current_index]);
                x_coordinate+=132;
            }
            y_coordinate-=132;
        }

//        holes[0] = new JLabel("0");
//        holes[0].setName("0");
//        holes[0].setBounds(0, 396, 132, 132);
//        panel.add(holes[0]);
//
//        holes[1] = new JLabel("1");
//        holes[1].setName("1");
//        holes[1].setBounds(132, 396, 132, 132);
//        panel.add(holes[1]);
//
//        holes[2] = new JLabel("2");
//        holes[2].setName("2");
//        holes[2].setBounds(264, 396, 132, 132);
//        panel.add(holes[2]);
//
//        holes[3] = new JLabel("3");
//        holes[3].setName("3");
//        holes[3].setBounds(396, 396, 132, 132);
//        panel.add(holes[3]);
//
//        holes[4] = new JLabel("4");
//        holes[4].setName("4");
//        holes[4].setBounds(0, 264, 132, 132);
//        panel.add(holes[4]);
//
//        holes[5] = new JLabel("5");
//        holes[5].setName("5");
//        holes[5].setBounds(132, 264, 132, 132);
//        panel.add(holes[5]);
//
//        holes[6] = new JLabel("6");
//        holes[6].setName("6");
//        holes[6].setBounds(264, 264, 132, 132);
//        panel.add(holes[6]);
//
//        holes[7] = new JLabel("7");
//        holes[7].setName("7");
//        holes[7].setBounds(396, 264, 132, 132);
//        panel.add(holes[7]);
//
//        holes[8] = new JLabel("8");
//        holes[8].setName("8");
//        holes[8].setBounds(0, 132, 132, 132);
//        panel.add(holes[8]);
//
//        holes[9] = new JLabel("9");
//        holes[9].setName("9");
//        holes[9].setBounds(132, 132, 132, 132);
//        panel.add(holes[9]);
//
//        holes[10] = new JLabel("10");
//        holes[10].setName("10");
//        holes[10].setBounds(264, 132, 132, 132);
//        panel.add(holes[10]);
//
//        holes[11] = new JLabel("11");
//        holes[11].setName("11");
//        holes[11].setBounds(396, 132, 132, 132);
//        panel.add(holes[11]);
//
//        holes[12] = new JLabel("12");
//        holes[12].setName("12");
//        holes[12].setBounds(0, 0, 132, 132);
//        panel.add(holes[12]);
//
//        holes[13] = new JLabel("13");
//        holes[13].setName("13");
//        holes[13].setBounds(132, 0, 132, 132);
//        panel.add(holes[13]);
//
//        holes[14] = new JLabel("14");
//        holes[14].setName("14");
//        holes[14].setBounds(264, 0, 132, 132);
//        panel.add(holes[14]);
//
//        holes[15] = new JLabel("15");
//        holes[15].setName("15");
//        holes[15].setBounds(396, 0, 132, 132);
//        panel.add(holes[15]);

        lblScore = new JLabel("Score: 0");
        lblScore.setHorizontalAlignment(SwingConstants.TRAILING);
        lblScore.setFont(new Font("Cambria", Font.BOLD, 14));
        lblScore.setForeground(new Color(135, 206, 250));
        lblScore.setBounds(423, 54, 144, 33);
        contentPane.add(lblScore);

        lblTimeLeft = new JLabel("30");
        lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimeLeft.setForeground(new Color(240, 128, 128));
        lblTimeLeft.setFont(new Font("Cambria Math", Font.BOLD, 24));
        lblTimeLeft.setBounds(232, 54, 144, 33);
        contentPane.add(lblTimeLeft);

        lblHighscore = new JLabel("Highscore: 0");
        lblHighscore.setHorizontalAlignment(SwingConstants.TRAILING);
        lblHighscore.setForeground(new Color(255, 255, 0));
        lblHighscore.setFont(new Font("Cambria", Font.BOLD, 14));
        lblHighscore.setBounds(433, 18, 134, 33);
        contentPane.add(lblHighscore);

        Start = new JButton("Start");
        Start.setBackground(Color.WHITE);
        Start.setBounds(32, 60, 110, 33);
        contentPane.add(Start);

        setContentPane(contentPane);
    }

    private void clearBoard(){
        for(int i = 0; i < 16; i++){
            holes[i].setIcon(loadImage("Graphics/moleIn.png"));
            board[i] = 0;
        }
    }

    private void genRandMole(){
        Random rnd = new Random(System.currentTimeMillis()); //seeding random with current time
        int moleID = rnd.nextInt(16);
        board[moleID] = 1;
        holes[moleID].setIcon(loadImage("Graphics/moleOut.png"));
    }

    private ImageIcon loadImage(String path){
        Image image = new ImageIcon(this.getClass().getResource(path)).getImage();
        Image scaledImage = image.getScaledInstance(132, 132,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public Game() {
        initGUI();
        clearBoard();
        initEvents();
        loadHighscore();
    }

    public static void main(String[] args) {
        Game frame = new Game();
        frame.setVisible(true);
    }
}