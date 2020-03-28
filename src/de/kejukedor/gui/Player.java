package de.kejukedor.gui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Player {

    AudioInputStream audioInputStream;
    private String filePath;
    Clip clip;

    public Player(String filePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //filePath = "datas/bell.wav";
        this.filePath = filePath;
        // create AudioInputStream object
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String filePath = "datas/bell.wav";
        Player audioPlayer = new Player(filePath);
        audioPlayer.play();
        while (true) {

        }
    }

    public void play() {
        //start the clip
        clip.start();
    }

    public void stop(){
        //stop the clip
        clip.stop();
    }
}
