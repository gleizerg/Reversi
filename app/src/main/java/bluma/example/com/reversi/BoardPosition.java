package bluma.example.com.reversi;

public class BoardPosition {
    private String horizontalPos;
    private int verticalPos;

    public BoardPosition(String horizontalPos, int verticalPos) {
        this.horizontalPos = horizontalPos;
        this.verticalPos = verticalPos;
    }

    public String getHorizontalPos() {
        return horizontalPos;
    }

    public void setHorizontalPos(String horizontalPos) {
        this.horizontalPos = horizontalPos;
    }

    public int getVerticalPos() {
        return verticalPos;
    }

    public void setVerticalPos(int verticalPos) {
        this.verticalPos = verticalPos;
    }
}
