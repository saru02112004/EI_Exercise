// Singleton
class GameScoreboard {
    private static GameScoreboard instance;
    private int highScore;
    private static final Object lock = new Object();

    private GameScoreboard() {
        highScore = 0;
    }

    public static GameScoreboard getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new GameScoreboard();
                }
            }
        }
        return instance;
    }

    public void updateHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            System.out.println("New high score: " + highScore);
        }
    }

    public int getHighScore() {
        return highScore;
    }
}

// Usage
public class GameScoreboardDemo {
    public static void main(String[] args) {
        GameScoreboard scoreboard1 = GameScoreboard.getInstance();
        GameScoreboard scoreboard2 = GameScoreboard.getInstance();

        scoreboard1.updateHighScore(100);
        scoreboard2.updateHighScore(150);

        System.out.println("Current high score: " + scoreboard1.getHighScore());
        System.out.println("Same instance? " + (scoreboard1 == scoreboard2));
    }
}
