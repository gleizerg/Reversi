package bluma.example.com.reversi;


import java.util.ArrayList;

public class MatchLogic {
    private MatchBoard matchBoard;
    private ArrayList<MatchBoardSlot> legalPositions = new ArrayList<>();
    private ArrayList<MatchBoardSlot> updatedStones = new ArrayList<>();
    private BoardPosition seekToNorth, seekToNE, seekToEast, seekToSE, seekToSouth,
                            seekToSW, seekToWest, seekToNW;
    private boolean isAtNorth, isAtNE, isAtEast, isAtSE, isAtSouth, isAtSW, isAtWest, isAtNW;

    public MatchLogic() {
        matchBoard = MatchBoard.getMatchBoardInstance();
    }

    public ArrayList<MatchBoardSlot> getLegalPositions() {
        return legalPositions;
    }

    public ArrayList<MatchBoardSlot> getUpdatedStones() {
        return updatedStones;
    }

    public void cleanMatchBoard(){
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                matchBoard.setMatchBoardSlotColor(i, j, Color.Empty);
    }

    public void configureSlotsToStartingPosition(){
        matchBoard.setMatchBoardSlotColor(3, 3, Color.White);
        matchBoard.setMatchBoardSlotColor(3, 4, Color.Black);
        matchBoard.setMatchBoardSlotColor(4, 3, Color.Black);
        matchBoard.setMatchBoardSlotColor(4, 4, Color.White);
        setLegalPositions(Color.Black);//must send current players color, not always black
    }

    public void updateMatchBoard(int row, int column, Color playedStoneColor){
        if(!updatedStones.isEmpty())
            updatedStones.clear();
        if(!legalPositions.isEmpty())
            legalPositions.clear();
        updateStones(row, column, playedStoneColor);
        if(playedStoneColor == Color.Black)
            setLegalPositions(Color.White);
        else setLegalPositions(Color.Black);
    }

    private void updateStones(int row, int column, Color playedStoneColor) {
        if(isSameColorAtNorth(row, column, playedStoneColor))
            updateToNorth(new BoardPosition(--row, column), playedStoneColor);
        if(isSameColorAtNE(row, column, playedStoneColor))
            updateToNE(new BoardPosition(--row, ++column), playedStoneColor);
        if(isSameColorAtEast(row, column, playedStoneColor))
            updateToEast(new BoardPosition(row, ++column), playedStoneColor);
        if(isSameColorAtSE(row, column, playedStoneColor))
            updateToSE(new BoardPosition(++row, ++column), playedStoneColor);
        if(isSameColorAtSouth(row, column, playedStoneColor))
            updateToSouth(new BoardPosition(++row, column), playedStoneColor);
        if(isSameColorAtSW(row, column, playedStoneColor))
            updateToSW(new BoardPosition(++row, --column), playedStoneColor);
        if(isSameColorAtWest(row, column, playedStoneColor))
            updateToWest(new BoardPosition(row, --column), playedStoneColor);
        if(isSameColorAtNW(row, column, playedStoneColor))
            updateToNW(new BoardPosition(--row, --column), playedStoneColor);
    }


    private void setLegalPositions(Color currentPlayerStoneColor){

    }

    private boolean isSameColorAtNorth(int row, int column, Color playedStoneColor) {
        isAtNorth = false;
        int rowTemp = row-1;
        if(rowTemp>=1){
            if(matchBoard.isOppositeColorAtPosition(rowTemp, column, playedStoneColor)){
                for(--rowTemp; rowTemp>=0 && !(matchBoard.isEmptyAtPosition(rowTemp, column))
                                                            && isAtNorth == false; rowTemp--){
                    if(matchBoard.getMatchBoardSlotColor(rowTemp, column) == playedStoneColor) {
                        isAtNorth = true;
                        seekToNorth = new BoardPosition(++rowTemp, column);
                    }
                }
            }
        }
        return isAtNorth;
    }

    private boolean isSameColorAtNE(int row, int column, Color playedStoneColor) {
        isAtNE = false;
        int rowTemp = row-1;
        int columnTemp = column+1;
        if(rowTemp>=1 && columnTemp<=6)
            for(; rowTemp>=0 && columnTemp<=7
                    && matchBoard.isOppositeColorAtPosition(rowTemp, columnTemp, playedStoneColor);
                                                                            rowTemp--, columnTemp++);
        if(rowTemp>=0 && columnTemp<=7) {
            if (matchBoard.getMatchBoardSlotColor(rowTemp, columnTemp) == playedStoneColor) {
                isAtNE = true;
                seekToNE = new BoardPosition(++rowTemp, --columnTemp);
            }
        }
        return isAtNE;
    }

    private boolean isSameColorAtNW(int row, int column, Color playedStoneColor) {
        isAtNW = false;
        int rowTemp = row-1;
        int columnTemp = column-1;
        if(rowTemp>=1 && columnTemp>=1)
            for(; rowTemp>=0 && columnTemp>=0
                    && matchBoard.isOppositeColorAtPosition(rowTemp, columnTemp, playedStoneColor);
                rowTemp--, columnTemp--);
        if(rowTemp>=0 && columnTemp>=0) {
            if (matchBoard.getMatchBoardSlotColor(rowTemp, columnTemp) == playedStoneColor) {
                isAtNW = true;
                seekToNW = new BoardPosition(++rowTemp, ++columnTemp);
            }
        }
        return isAtNW;
    }

    private boolean isSameColorAtSouth(int row, int column, Color playedStoneColor) {
        isAtSouth = false;
        int rowTemp = row+1;
        if(rowTemp<=6)
            for(; rowTemp<=7 && matchBoard.isOppositeColorAtPosition(rowTemp, column, playedStoneColor); rowTemp++);
        if(rowTemp<=7) {
            if (matchBoard.getMatchBoardSlotColor(rowTemp, column) == playedStoneColor) {
                isAtSouth = true;
                seekToSouth = new BoardPosition(--rowTemp, column);
            }
        }
        return isAtSouth;
    }

    private boolean isSameColorAtSE(int row, int column, Color playedStoneColor) {
        isAtSE = false;
        int rowTemp = row+1;
        int columnTemp = column+1;
        if(rowTemp<=6 && columnTemp<=6)
            for(; rowTemp<=7 && columnTemp<=7
                    && matchBoard.isOppositeColorAtPosition(rowTemp, columnTemp, playedStoneColor);
                rowTemp++, columnTemp++);
        if(rowTemp<=7 && columnTemp<=7) {
            if (matchBoard.getMatchBoardSlotColor(rowTemp, columnTemp) == playedStoneColor) {
                isAtSE = true;
                seekToSE = new BoardPosition(--rowTemp, --columnTemp);
            }
        }
        return isAtSE;
    }

    private boolean isSameColorAtSW(int row, int column, Color playedStoneColor) {
        isAtSW = false;
        int rowTemp = row+1;
        int columnTemp = column-1;
        if(rowTemp<=6 && columnTemp>=1)
            for(; rowTemp<=7 && columnTemp>=0
                    && matchBoard.isOppositeColorAtPosition(rowTemp, columnTemp, playedStoneColor);
                rowTemp++, columnTemp--);
        if(rowTemp<=7 && columnTemp>=0) {
            if (matchBoard.getMatchBoardSlotColor(rowTemp, columnTemp) == playedStoneColor) {
                isAtSW = true;
                seekToSW = new BoardPosition(--rowTemp, ++columnTemp);
            }
        }
        return isAtSW;
    }

    private boolean isSameColorAtEast(int row, int column, Color playedStoneColor) {
        isAtEast = false;
        int columnTemp = column+1;
        if(columnTemp<=6)
            for(; columnTemp<=7 && matchBoard.isOppositeColorAtPosition(row, columnTemp, playedStoneColor); columnTemp++);
        if(columnTemp<=7) {
            if (matchBoard.getMatchBoardSlotColor(row, columnTemp) == playedStoneColor) {
                isAtEast = true;
                seekToEast = new BoardPosition(row, --columnTemp);
            }
        }
        return isAtEast;
    }

    private boolean isSameColorAtWest(int row, int column, Color playedStoneColor) {
        isAtWest = false;
        int columnTemp = column-1;
        if(columnTemp>=1)
            for(; columnTemp>=0 && matchBoard.isOppositeColorAtPosition(row, columnTemp, playedStoneColor); columnTemp--);
        if(columnTemp>=0) {
            if (matchBoard.getMatchBoardSlotColor(row, columnTemp) == playedStoneColor) {
                isAtWest = true;
                seekToWest = new BoardPosition(row, ++columnTemp);
            }
        }
        return isAtEast;
    }

    private void updateToNorth(BoardPosition startPos, Color color){
        int destRow = seekToNorth.getRow();
        int column = startPos.getColumn();
        for(int i=startPos.getRow(); i>=destRow; i--){
            matchBoard.setMatchBoardSlotColor(i, column, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(i, column));
        }
    }

    private void updateToNE(BoardPosition startPos, Color color){
        int destRow = seekToNE.getRow();
        for(int i=startPos.getRow(), j=startPos.getColumn(); i>=destRow; i--, j++){
            matchBoard.setMatchBoardSlotColor(i, j, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(i, j));
        }
    }

    private void updateToNW(BoardPosition startPos, Color color){
        int destRow = seekToNW.getRow();
        for(int i=startPos.getRow(), j=startPos.getColumn(); i>=destRow; i--, j--){
            matchBoard.setMatchBoardSlotColor(i, j, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(i, j));
        }
    }

    private void updateToSouth(BoardPosition startPos, Color color){
        int destRow = seekToSouth.getRow();
        int column = startPos.getColumn();
        for(int i=startPos.getRow(); i<=destRow; i++){
            matchBoard.setMatchBoardSlotColor(i, column, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(i, column));
        }
    }

    private void updateToSE(BoardPosition startPos, Color color){
        int destRow = seekToSE.getRow();
        for(int i=startPos.getRow(), j=startPos.getColumn(); i<=destRow; i++, j++){
            matchBoard.setMatchBoardSlotColor(i, j, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(i, j));
        }
    }

    private void updateToSW(BoardPosition startPos, Color color){
        int destRow = seekToSW.getRow();
        for(int i=startPos.getRow(), j=startPos.getColumn(); i<=destRow; i++, j--){
            matchBoard.setMatchBoardSlotColor(i, j, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(i, j));
        }
    }

    private void updateToEast(BoardPosition startPos, Color color){
        int destColumn = seekToEast.getColumn();
        int row = startPos.getRow();
        for(int i=startPos.getColumn(); i<=destColumn; i++){
            matchBoard.setMatchBoardSlotColor(row, i, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(row, i));
        }
    }

    private void updateToWest(BoardPosition startPos, Color color){
        int destColumn = seekToWest.getColumn();
        int row = startPos.getRow();
        for(int i=startPos.getColumn(); i>=destColumn; i--){
            matchBoard.setMatchBoardSlotColor(row, i, color);
            updatedStones.add(matchBoard.getMatcBoardSlot(row, i));
        }
    }

    private boolean isStraightLineToSameColorStone(int row, int column, Color stoneColor){
        return true;
    }

}
