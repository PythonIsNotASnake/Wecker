package de.kejukedor.gui;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TimerGui extends JFrame {

    private boolean target;
    private Timer t;
    private Timer bell;
    private long timeStamp;
    private long targetTime;
    private Player audioPlayer;

    public TimerGui() {

        this.setTitle("Coding Break Timer");
        this.setSize(400, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.target = false;
        this.targetTime = 1 * 60 * 1000;

        //String filePath = "datas/analogwatch.wav";
        //this.audioPlayer = new Player(filePath);

        Font schrift = new Font("Comic Sans MS", Font.BOLD, 90);

        JLabel timeCounter = new JLabel("00:00");
        timeCounter.setFont(schrift);

        JButton timerStarter = new JButton("Start");
        timerStarter.setFont(schrift);
        timerStarter.setBackground(Color.GREEN);
        timerStarter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed");
                timeStamp = System.currentTimeMillis();
                timer();
            }
        });

        t = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long tmp = calculateTime();
                if (tmp >= targetTime) {
                    target = true;
                }
                tmp = tmp / 1000;
                long minutes = tmp / 60;
                long seconds = tmp % 60;

                if (minutes < 10) {
                    if (seconds < 10) {
                        timeCounter.setText("0" + minutes + ":" + "0" + seconds);
                    } else {
                        timeCounter.setText("0" + minutes + ":" + seconds);
                    }
                } else {
                    if (seconds < 10) {
                        timeCounter.setText(minutes + ":" + "0" + seconds);
                    } else {
                        timeCounter.setText(minutes + ":" + seconds);
                    }
                }
                timer();
            }
        });

        panel.add(timeCounter, BorderLayout.NORTH);
        panel.add(timerStarter, BorderLayout.SOUTH);

        this.setContentPane(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void timer() {
        if (target == false) {

            if(this.audioPlayer != null){
                audioPlayer.stop();
            }

            if (t.isRunning() == false) {
                t.start();
            }

        } else if (target == true) {
            t.stop();

            try {
                String filePath = "datas/analogwatch.wav";
                this.audioPlayer = new Player(filePath);
                audioPlayer.play();
                audioPlayer.clip.loop(19);
            } catch (IOException f) {
                f.printStackTrace();
            } catch (UnsupportedAudioFileException f) {
                f.printStackTrace();
            } catch (LineUnavailableException f) {
                f.printStackTrace();
            }

            target = false;
        }
    }

    public long calculateTime() {
        long currentTime = System.currentTimeMillis();
        long time = currentTime - timeStamp;
        return time;
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        TimerGui timer = new TimerGui();
    }
}
