
// Legacy Music Player
class LegacyMusicPlayer {
    public void playSong(String songName) {
        System.out.println("Legacy Player: Now playing " + songName);
    }
}

// Modern Streaming Service Interface
interface StreamingService {
    void stream(String trackId);
}

// Adapter
class MusicPlayerAdapter implements StreamingService {
    private LegacyMusicPlayer legacyPlayer = new LegacyMusicPlayer();

    @Override
    public void stream(String trackId) {
        String songName = getSongNameFromId(trackId);
        legacyPlayer.playSong(songName);
    }

    private String getSongNameFromId(String trackId) {
        return switch (trackId) {
            case "T1001" -> "Bohemian Rhapsody";
            case "T1002" -> "Imagine";
            default -> "Unknown Track";
        };
    }
}

// Usage
public class MusicPlayerDemo {
    public static void main(String[] args) {
        StreamingService service = new MusicPlayerAdapter();
        service.stream("T1001");
        service.stream("T1002");
    }
}
