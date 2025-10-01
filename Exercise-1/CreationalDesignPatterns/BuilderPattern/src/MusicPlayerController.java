package src;
// Singleton MusicPlayerController class
public class MusicPlayerController {
    private static MusicPlayerController instance;

    private boolean isPlaying = false;
    private String currentSong = "";

    // Private constructor
    private MusicPlayerController() {
        System.out.println("Music Player Controller initialized...");
    }

    // Public method to get the singleton instance
    public static MusicPlayerController getInstance() {
        if (instance == null) {
            instance = new MusicPlayerController();
        }
        return instance;
    }

    // Play a song
    public void play(String song) {
        if (!isPlaying) {
            currentSong = song;
            isPlaying = true;
            System.out.println("Playing: " + currentSong);
        } else {
            System.out.println("Already playing: " + currentSong);
        }
    }

    // Pause the song
    public void pause() {
        if (isPlaying) {
            isPlaying = false;
            System.out.println("Paused: " + currentSong);
        } else {
            System.out.println("No song is currently playing.");
        }
    }
}
