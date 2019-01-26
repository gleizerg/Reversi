package bluma.example.com.reversi;

import java.util.ArrayList;

public class MatchBoard {
    private ArrayList<MatchStone> matchBoard[][];

    public MatchBoard() {
        this.matchBoard = new ArrayList[8][8];
    }
    public ArrayList<MatchStone>[][] getMatchBoard() {
        return matchBoard;
    }
}
