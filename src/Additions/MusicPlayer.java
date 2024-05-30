package Additions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MusicPlayer {
    private Map<String, Clip> clips = new HashMap<>();

    public void load(String name, String filePath) {
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                AudioFormat baseFormat = audioInput.getFormat();
                AudioFormat decodedFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        baseFormat.getSampleRate(),
                        16,
                        baseFormat.getChannels(),
                        baseFormat.getChannels() * 2,
                        baseFormat.getSampleRate(),
                        false
                );
                AudioInputStream decodedAudioInput = AudioSystem.getAudioInputStream(decodedFormat, audioInput);

                Clip clip = AudioSystem.getClip();
                clip.open(decodedAudioInput);
                clips.put(name, clip);
                System.out.println("Loaded: " + filePath);
            } else {
                System.out.println("Can't find file: " + filePath);
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading audio file: " + filePath);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable for audio playback: " + filePath);
            e.printStackTrace();
        }
    }

    public void play(String name) {
        Clip clip = clips.get(name);
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(String name) {
        Clip clip = clips.get(name);
        if (clip != null) {
            clip.stop();
        }
    }
}
