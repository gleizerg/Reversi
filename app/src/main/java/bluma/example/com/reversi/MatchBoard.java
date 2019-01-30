package bluma.example.com.reversi;


import java.util.ArrayList;

public class MatchBoard {
    private MatchBoardSlot matchBoard[][];
    private static MatchBoard matchBoardInstance = new MatchBoard();

    private MatchBoard() {
        this.matchBoard = new MatchBoardSlot[8][8];
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                matchBoard[i][j] = new MatchBoardSlot(Color.Empty, new BoardPosition(i, j));
    }

    public static MatchBoard getMatchBoardInstance(){
        return matchBoardInstance;
    }

    public MatchBoardSlot getMatcBoardSlot(int row, int column){
        return matchBoard[row][column];
    }

    public void setMatchBoardSlotColor(int row, int column, Color color){
        matchBoard[row][column].setStoneColor(color);
    }

    public Color getMatchBoardSlotColor(int row, int column){
        return matchBoard[row][column].getStoneColor();
    }

    public boolean isOppositeColorAtPosition(int row, int column, Color color){
        if(!isEmptyAtPosition(row, column)) {
            if (matchBoard[row][column].getStoneColor() == color)
                return false;
            else
                return true;
        }
        return false;
    }

    public boolean turnOverAtPosition(int row, int column){
        if(matchBoard[row][column].getStoneColor() == Color.Empty)
            return false;
        else{
            if(matchBoard[row][column].getStoneColor() == Color.Black)
                setMatchBoardSlotColor(row, column, Color.White);
            else
                setMatchBoardSlotColor(row, column, Color.Black);
            return true;
        }
    }

    public boolean isEmptyAtPosition(int row, int column){
        return(matchBoard[row][column].getStoneColor() == Color.Empty);
    }

    public ArrayList<MatchBoardSlot> getAllBlockedSlots(Color color){
        ArrayList<MatchBoardSlot> slotsWithOppositeColorNeighbors = new ArrayList<>();
        for(int tRow=0; tRow<8; tRow++){
            for(int tCol=0; tCol<8; tCol++){
                if(getMatchBoardSlotColor(tRow, tCol)!=Color.Empty){
                    if(countNeighbors(tRow, tCol, getReverseColor(color))>0)
                        slotsWithOppositeColorNeighbors.add(getMatcBoardSlot(tRow, tCol));
                }
            }
        }

        return slotsWithOppositeColorNeighbors;
    }

    public Color getReverseColor(Color colorToReverse){
        if(colorToReverse == Color.Black)
            return Color.White;
        else if(colorToReverse == Color.White)
            return Color.Black;
        else
            return Color.Empty;
    }

    private int countNeighbors(int row, int column, Color color){
        int numOfNeighborsAtPosition = 0;


        return numOfNeighborsAtPosition;
    }

}
