import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Music{
    private static Clip clipLoop;
    private static Clip clipSoundeffect;
    private static boolean musicPlaying;
    
    public static void playMusic(String musicLocation, boolean loop){
        try{
            File musicPath = new File(musicLocation);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);

                System.out.println("playing music");
                musicPlaying = true;

                if (loop){
                    clipLoop = AudioSystem.getClip();
                    clipLoop.open(audioInput);
                    clipLoop.start();
                    clipLoop.loop(Clip.LOOP_CONTINUOUSLY);
                }
                else{
                    clipSoundeffect = AudioSystem.getClip();
                    clipSoundeffect.open(audioInput);
                    clipSoundeffect.start();
                }


                //JOptionPane.showMessageDialog(null, "press ok to stop music");
            }
            else {
                System.out.println("Cant find file");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void stopMusic(){
        musicPlaying = false;
        clipLoop.stop();
    }

    public static boolean toogleMusic(String musicLocation){
        if (musicPlaying) {
            stopMusic();
        } else {
            playMusic(musicLocation, true);
        }
        return musicPlaying;
    }
}