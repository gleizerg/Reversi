package bluma.example.com.reversi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityMatch extends AppCompatActivity {
    MatchController matchController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        matchController = MatchController.getMatchControllerInstance();


    }

    public static void updateSlotsOnDisplay(BoardPosition position, ArrayList<MatchBoardSlot> slotsToUpdate, Color turn){}//todo
    
    public static void displayLegalPositions(ArrayList<MatchBoardSlot> legalPositions){}//todo

    public static void displaySkippedTurnMessage(Color turnSkipped){}//todo

    public static void initiateGameOverProcess(){}//todo



    /*

    anonym class{
        if(notEndOfGame){
            matchController.playMove(position);


     */


}
