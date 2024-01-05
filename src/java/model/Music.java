package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class Music
{
    private Clip c;
    private static Music instance;

    public static Music getInstance() {
        if (instance == null) {
            instance = new Music();
        }
        return instance;
    }

    public Music()
    {
        try {
            URL musicUrl = ClassLoader.getSystemResource("resources/music.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicUrl);
            c = AudioSystem.getClip();
            c.open(audioInputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public void playMusic() {
        c.loop(Clip.LOOP_CONTINUOUSLY); //la traccia .wav viene "ripetuta" in continuazione...
        c.start(); //fa partire la musica...
    }

    public void stopMusic() {
        c.stop(); //ferma la musica...
    }
}
