package bluma.example.com.reversi;

public class Player {
    private String playerName;
    private int score = 0;
    private boolean isComputer = false;
    private Color color;

    public Player(String playerName, int score, boolean isComputer, Color color) {
        this.playerName = playerName;
        this.score = score;
        this.isComputer = isComputer;
        this.color = color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
