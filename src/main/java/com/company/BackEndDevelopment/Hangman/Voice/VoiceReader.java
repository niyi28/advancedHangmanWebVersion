package com.company.BackEndDevelopment.Hangman.Voice;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class VoiceReader {
    public static void readingVoice(String result, String language) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        String path = "src/main/java/com/company/BackEndDevelopment/Hangman/Voice/" + result + "_" + language + ".mp3";
        File yourFile = new File(path);
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;
        Clip clip;

        stream = AudioSystem.getAudioInputStream(yourFile);
        format = stream.getFormat();
        info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        clip.start();
        Thread.sleep(5000);
    }
}

