package bluma.example.com.reversi;



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

    public boolean isEmptyAtPosition(int row, int column){
        return(matchBoard[row][column].getStoneColor() == Color.Empty);
    }

    /*public MatchBoardSlot[][] getMatchBoard() {
        return matchBoard;
    }*/

    /*public void initiateMatchBoard(){
        clearMatchBoard();
        configureSlotsToStartingPosition();
    }



    private void clearMatchBoard(){
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                matchBoard[i][j].setStoneColor(Color.Empty);
    }

    private void configureSlotsToStartingPosition(){
        matchBoard[3][3].setStoneColor(Color.White);
        matchBoard[3][4].setStoneColor(Color.Black);
        matchBoard[4][3].setStoneColor(Color.Black);
        matchBoard[4][4].setStoneColor(Color.White);
    }*/
}
