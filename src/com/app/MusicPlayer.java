package com.app;

import com.sun.tools.javac.Main;

import javax.sound.sampled.*;
import java.io.*;

public class MusicPlayer {
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    System.out.println("Clip OK");
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("/home/kuze/IdeaProjects/Pangea/src/Audio/sleep.wav").getAbsoluteFile());

                    System.out.println("InputStream OK");
                    clip.open(inputStream);
                    System.out.println("Opened");
                    clip.start();
                    System.out.println("Started");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static void play(){
        try{
            File musicPath = new File("/home/kuze/IdeaProjects/Pangea/src/Audio/sleep.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
