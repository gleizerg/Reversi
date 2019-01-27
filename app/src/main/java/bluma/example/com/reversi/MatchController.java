package bluma.example.com.reversi;

import java.util.ArrayList;

public class MatchController {
    private MatchLogic matchLogic;
    private MatchBoard matchBoard;
    private static MatchController matchController = new MatchController();

    private MatchController(){
        matchBoard = MatchBoard.getMatchBoardInstance();
        matchLogic = new MatchLogic();
    }

    public MatchController getMatchControllerInstance(){
        return matchController;
    }

    public void initiateMatchBoard(){
        matchLogic.cleanMatchBoard();
        matchLogic.configureSlotsToStartingPosition();
    }

    public void updateMatchBoard(int row, int column, Color color){
        matchLogic.updateMatchBoard(row, column, color);
    }

    public ArrayList<MatchBoardSlot> getUpdatedStones(){
        return matchLogic.getUpdatedStones();
    }

    public ArrayList<MatchBoardSlot> getLegalPositions(){
        return matchLogic.getLegalPositions();
    }

}
