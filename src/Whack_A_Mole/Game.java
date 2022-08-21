package Whack_A_Mole;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Objects;
import java.util.Random;

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

        //if val is 1 = mole. if val is 0 = empty hole
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