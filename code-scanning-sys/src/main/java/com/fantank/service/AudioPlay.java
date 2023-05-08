package com.fantank.service;

import com.fantank.Configuration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.IOException;

public class AudioPlay implements Configuration,Runnable {
    static volatile int audioClip = 0;

    public static synchronized void playAudio(Integer i) {
        audioClip = i - 1;
        new Thread(new AudioPlay()).start();
    }

    @Override
    public void run() {
        try {
            Player player = new Player(new FileInputStream(audioPath[audioClip]));
            player.play();
            player.close();
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
