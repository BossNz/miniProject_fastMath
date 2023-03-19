package fastmath;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends script {
    private JFrame f;

    // indexComponents
    private JLabel lbLogoGame;
    private JLabel lbBestScore;
    private JButton bPlay;
    private JButton bCredit;

    // playComponents
    private JLabel lbScore;
    private JLabel lbCountDown;
    private JLabel lbQuestion;
    private JButton bAns1;
    private JButton bAns2;
    private JButton bAns3;

    // tmpVariable
    private int score;
    private int bestScore;
    private int ans1;
    private int ans2;
    private int ans3;
    private int timeout;

    private boolean isEnd;

    public App() {
        f = new JFrame("fastMath 3kib.");
        f.setResizable(false);
        f.setSize(500, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        setComponents();
        indexComponents();
    }

    private void setComponents() {
        // play Components
        lbScore = new JLabel("SCORE : 0");
        lbCountDown = new JLabel("TIME : 0", SwingConstants.RIGHT);
        lbQuestion = new JLabel("0 + 0 = ?", SwingConstants.CENTER);

        bAns1 = new JButton("0");
        bAns2 = new JButton("0");
        bAns3 = new JButton("0");

        lbScore.setPreferredSize(new Dimension(200, 100));
        lbCountDown.setPreferredSize(new Dimension(200, 100));
        lbQuestion.setPreferredSize(new Dimension(500, 100));
        bAns1.setPreferredSize(new Dimension(500, 100));
        bAns2.setPreferredSize(new Dimension(500, 100));
        bAns3.setPreferredSize(new Dimension(500, 100));

        lbQuestion.setFont(new Font("Serif", Font.BOLD, 50));

        // index Components
        lbLogoGame = new JLabel("");
        lbLogoGame.setIcon(new ImageIcon("fastmath/logo.gif"));

        lbBestScore = new JLabel("BEST SCORE : 0", SwingConstants.CENTER);

        bPlay = new JButton("PLAY");
        bCredit = new JButton("CREDIT");

        lbBestScore.setFont(new Font("Serif", Font.BOLD, 20));

        lbLogoGame.setPreferredSize(new Dimension(400, 200));
        lbBestScore.setPreferredSize(new Dimension(500, 100));
        bPlay.setPreferredSize(new Dimension(500, 100));
        bCredit.setPreferredSize(new Dimension(500, 100));

        bPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPlayActionPerformed(evt);
            }
        });
        bAns1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAnswer(ans1);
            }
        });
        bAns2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAnswer(ans2);
            }
        });
        bAns3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAnswer(ans3);
            }
        });

        setTimeOut();

    }

    private void playComponents() {
        f.add(lbScore);
        f.add(lbCountDown);
        f.add(lbQuestion);
        f.add(bAns1);
        f.add(bAns2);
        f.add(bAns3);
        f.setVisible(true);
    }

    private void indexComponents() {
        f.add(lbLogoGame);
        f.add(lbBestScore);
        f.add(bPlay);
        f.add(bCredit);
        f.setVisible(true);
    }

    private void removeComponent() {
        f.getContentPane().removeAll();
        f.revalidate();
        f.repaint();
    }

    private void setUpQuestion() {
        lbScore.setText("SCORE : " + score);
        if (score < 10)
            lbQuestion.setText(generateQuestion(1));
        else if (score < 20)
            lbQuestion.setText(generateQuestion(2));
        else if (score < 30)
            lbQuestion.setText(generateQuestion(3));
        else
            lbQuestion.setText(generateQuestion(4));

        int[] answerList = getAnswerList();
        ans1 = answerList[0];
        ans2 = answerList[1];
        ans3 = answerList[2];
        bAns1.setText(String.valueOf(answerList[0]));
        bAns2.setText(String.valueOf(answerList[1]));
        bAns3.setText(String.valueOf(answerList[2]));

        timeout = 3;
        lbCountDown.setText("TIME : " + timeout);
    }

    private void endGame() {
        removeComponent();
        bestScore = score > bestScore ? score : bestScore;
        lbBestScore.setText("BEST SCORE : " + score);
        isEnd = true;
        indexComponents();
    }

    private void setTimeOut() {
        Runnable helloRunnable = new Runnable() {
            public void run() {
                if (!isEnd) {
                    if (timeout == 0) {
                        endGame();
                    } else {
                        lbCountDown.setText("TIME : " + timeout);
                        timeout--;
                    }
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    }

    private void bPlayActionPerformed(java.awt.event.ActionEvent evt) {
        removeComponent();
        isEnd = false;
        score = 0;
        setUpQuestion();
        playComponents();
    }

    private void checkAnswer(int ans) {
        if (checkSolve(ans)) {
            score++;
            setUpQuestion();
        } else {
            endGame();
        }
    }

}
