import src.*;

// Main class
public class Main {
    public static void main(String[] args) {
        MusicPlayerController player1 = MusicPlayerController.getInstance();
        MusicPlayerController player2 = MusicPlayerController.getInstance();

        player1.play("Song A");    // Play Song A
        player2.play("Song B");    // Cannot play Song B, Song A is already playing

        player1.pause();           // Pause Song A
        player2.play("Song B");    // Now Song B can play

        if (player1 == player2) {
            System.out.println("Both players are the same instance.");
        }
    }
}
