package bluma.example.com.reversi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import java.util.ArrayList;

public class ActivityMatch extends AppCompatActivity {
    MatchController matchController;
    LinearLayout timerLayout;
    GridLayout matchBoardLayout;
    ImageView startTimer;
    ImageView[][] imageViewBoard = new ImageView[8][8];
    int currentRow, currentCol;
    ArrayList<MatchBoardSlot> legalPositions;
    AnimationDrawable flipTheSlotAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        matchController = MatchController.getMatchControllerInstance();
        matchBoardLayout.findViewById(R.id.main_grid);
        for(currentRow=0; currentRow<8; currentRow++){
            for(currentCol=0; currentCol<8; currentCol++) {
                imageViewBoard[currentRow][currentCol] = (ImageView) matchBoardLayout.getChildAt(getIndexAtPos(currentRow, currentCol));
                imageViewBoard[currentRow][currentCol].setOnClickListener(new View.OnClickListener() {
                    private final int row = currentRow;
                    private final int col = currentCol;
                    @Override
                    public void onClick(View view) {
                        if(matchController.updateMatchBoard(row, col)) {//match board updated and appropriate stones turned over
                            ((ImageView)view).setImageResource(R.drawable.gb);//set the stone on the board
                            flipSlots();
                        }
                    }
                });
            }
        }
        matchController.initiateMatchBoard();
        //show starting match state
        while(!matchController.gameOver()){
            if(!matchController.isAITurn()){
                legalPositions = matchController.getLegalPositions();
                for (MatchBoardSlot legalSlot: legalPositions) {
                    imageViewBoard[legalSlot.getBoardPositionRow]
                            [legalSlot.getBoardPositionColumn]
                            .setImageResource(R.drawable.red_dot);
                }
            }
        }
        findViewByIflipper = imageViewBoard[stonesToFlip.getBoardPositionRow()]
                [stonesToFlip.getBoardPositionColumn()].setImageResource
                (R.drawable.black_to_white_flip);
        startTimer =findViewById(R.id.start_timer);
        timerLayout = findViewById(R.id.timer_layout);
        Switch switchBtn = findViewById(R.id.switch_btn);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                timerLayout.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
            }
        });
        /*findViewById(R.id.a1).setOnClickListener(this);
        findViewById(R.id.a2).setOnClickListener(this);
        findViewById(R.id.a3).setOnClickListener(this);
        findViewById(R.id.a4).setOnClickListener(this);
        findViewById(R.id.a5).setOnClickListener(this);
        findViewById(R.id.a6).setOnClickListener(this);
        findViewById(R.id.a7).setOnClickListener(this);
        findViewById(R.id.a8).setOnClickListener(this);

        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);
        findViewById(R.id.b3).setOnClickListener(this);
        findViewById(R.id.b4).setOnClickListener(this);
        findViewById(R.id.b5).setOnClickListener(this);
        findViewById(R.id.b6).setOnClickListener(this);
        findViewById(R.id.b7).setOnClickListener(this);
        findViewById(R.id.b8).setOnClickListener(this);

        findViewById(R.id.c1).setOnClickListener(this);
        findViewById(R.id.c2).setOnClickListener(this);
        findViewById(R.id.c3).setOnClickListener(this);
        findViewById(R.id.c4).setOnClickListener(this);
        findViewById(R.id.c5).setOnClickListener(this);
        findViewById(R.id.c6).setOnClickListener(this);
        findViewById(R.id.c7).setOnClickListener(this);
        findViewById(R.id.c8).setOnClickListener(this);

        findViewById(R.id.d1).setOnClickListener(this);
        findViewById(R.id.d2).setOnClickListener(this);
        findViewById(R.id.d3).setOnClickListener(this);
        findViewById(R.id.d4).setOnClickListener(this);
        findViewById(R.id.d5).setOnClickListener(this);
        findViewById(R.id.d6).setOnClickListener(this);
        findViewById(R.id.d7).setOnClickListener(this);
        findViewById(R.id.d8).setOnClickListener(this);

        findViewById(R.id.e1).setOnClickListener(this);
        findViewById(R.id.e2).setOnClickListener(this);
        findViewById(R.id.e3).setOnClickListener(this);
        findViewById(R.id.e4).setOnClickListener(this);
        findViewById(R.id.e5).setOnClickListener(this);
        findViewById(R.id.e6).setOnClickListener(this);
        findViewById(R.id.e7).setOnClickListener(this);
        findViewById(R.id.e8).setOnClickListener(this);

        findViewById(R.id.f1).setOnClickListener(this);
        findViewById(R.id.f2).setOnClickListener(this);
        findViewById(R.id.f3).setOnClickListener(this);
        findViewById(R.id.f4).setOnClickListener(this);
        findViewById(R.id.f5).setOnClickListener(this);
        findViewById(R.id.f6).setOnClickListener(this);
        findViewById(R.id.f7).setOnClickListener(this);
        findViewById(R.id.f8).setOnClickListener(this);

        findViewById(R.id.g1).setOnClickListener(this);
        findViewById(R.id.g2).setOnClickListener(this);
        findViewById(R.id.g3).setOnClickListener(this);
        findViewById(R.id.g4).setOnClickListener(this);
        findViewById(R.id.g5).setOnClickListener(this);
        findViewById(R.id.g6).setOnClickListener(this);
        findViewById(R.id.g7).setOnClickListener(this);
        findViewById(R.id.g8).setOnClickListener(this);

        findViewById(R.id.h1).setOnClickListener(this);
        findViewById(R.id.h2).setOnClickListener(this);
        findViewById(R.id.h3).setOnClickListener(this);
        findViewById(R.id.h4).setOnClickListener(this);
        findViewById(R.id.h5).setOnClickListener(this);
        findViewById(R.id.h6).setOnClickListener(this);
        findViewById(R.id.h7).setOnClickListener(this);
        findViewById(R.id.h8).setOnClickListener(this);*/

        ImageButton pauseBtn = findViewById(R.id.pause_btn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.pause_dialog, null);
                builder.setView(dialogView).show();
            }
        });
    }

    @Override
    public void onClick(final View view) {
        //Toast.makeText(this, "you cant move there", Toast.LENGTH_SHORT).show();
        AnimationDrawable animationDrawable1,animationDrawable2;
        switch (view.getId()) {
            case R.id.a1:
                ImageView a1 = findViewById(R.id.a1);
                animationDrawable1 = (AnimationDrawable)a1.getDrawable();
                animationDrawable1.start();

                //animation(R.id.a1);
        /*        ObjectAnimator invisible = ObjectAnimator.ofFloat(R.id.a1, "scaleX", 1f, 0f).setDuration(10000);
                final ObjectAnimator visible = ObjectAnimator.ofFloat(R.id.a1, "scaleX", 0f, 1f).setDuration(10000);
                invisible.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ImageView a1 = findViewById(R.id.a1);
                        a1.setImageResource(R.drawable.aw);
                        visible.start();
                    }
                });
                invisible.start();*/
                break;
            case R.id.a2:
                ImageView a2 = findViewById(R.id.a2);
                a2.setImageResource(R.drawable.black_to_white_flip);
                animationDrawable2 = (AnimationDrawable)a2.getDrawable();
                animationDrawable2.start();
                break;
            case R.id.a3:

                break;
            case R.id.a4:
                break;
            case R.id.a5:
                break;
            case R.id.a6:
                break;
            case R.id.a7:
                break;
            case R.id.a8:
                break;
            case R.id.b1:
                break;
            case R.id.b2:
                break;
            case R.id.b3:
                break;
            case R.id.b4:
                break;
            case R.id.b5:
                break;
            case R.id.b6:
                break;
            case R.id.b7:
                break;
            case R.id.b8:
                break;
            case R.id.c1:
                break;
            case R.id.c2:
                break;
            case R.id.c3:
                break;
            case R.id.c4:
                break;
            case R.id.c5:
                break;
            case R.id.c6:
                break;
            case R.id.c7:
                break;
            case R.id.c8:
                break;
            case R.id.d1:
                break;
            case R.id.d2:
                break;
            case R.id.d3:
                break;
            case R.id.d4:
                break;
            case R.id.d5:
                break;
            case R.id.d6:
                break;
            case R.id.d7:
                break;
            case R.id.d8:
                break;
            case R.id.e1:
                break;
            case R.id.e2:
                break;
            case R.id.e3:
                break;
            case R.id.e4:
                break;
            case R.id.e5:
                break;
            case R.id.e6:
                break;
            case R.id.e7:
                break;
            case R.id.e8:
                break;
            case R.id.f1:
                break;
            case R.id.f2:
                break;
            case R.id.f3:
                break;
            case R.id.f4:
                break;
            case R.id.f5:
                break;
            case R.id.f6:
                break;
            case R.id.f7:
                break;
            case R.id.f8:
                break;
            case R.id.g1:
                break;
            case R.id.g2:
                break;
            case R.id.g3:
                break;
            case R.id.g4:
                break;
            case R.id.g5:
                break;
            case R.id.g6:
                break;
            case R.id.g7:
                break;
            case R.id.g8:
                break;
            case R.id.h1:
                break;
            case R.id.h2:
                break;
            case R.id.h3:
                Toast.makeText(this, "you can't move there!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.h4:
                Toast.makeText(this, "you can't move there!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.h5:
                Toast.makeText(this, "you can't move there!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.h6:
                Toast.makeText(this, "you can't move there!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.h7:
                Toast.makeText(this, "you can't move there!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.h8:
                Toast.makeText(this, "you can't move there!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private int getIndexAtPos(int row, int col){
        int indexAtPosition = 0;

        indexAtPosition = (row*8) + col;
        return indexAtPosition;
    }

    private void flipSlots(){
        ImageView flipper;
        ArrayList<MatchBoardSlot> stonesToFlip;
        stonesToFlip = matchController.getUpdatedStones;
        for (MatchBoardSlot stoneToFlip: stonesToFlip) {
            if(stoneToFlip.getStoneColor() == Color.Black)
                flipper = imageViewBoard[stonesToFlip.getBoardPositionRow()]
                        [stonesToFlip.getBoardPositionColumn()].setImageResource
                        (R.drawable.black_to_white_flip);
            else
                flipper = imageViewBoard[stonesToFlip.getBoardPositionRow()]
                        [stonesToFlip.getBoardPositionColumn()].setImageResource
                        (R.drawable.white_to_black_flip);
            flipTheSlotAnimation = (AnimationDrawable)flipper.getDrawable();
            flipTheSlotAnimation.start();
        }
    }
    public void animation(final int id) {
/*        ObjectAnimator invisible = ObjectAnimator.ofFloat(id, "scaleX", 1f, 0f).setDuration(1000);
        final ObjectAnimator visible = ObjectAnimator.ofFloat(id, "scaleX", 0f, 1f).setDuration(1000);
        invisible.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                id.setImageResource(R.drawable.aw);
                visible.start();
            }
        });
        invisible.start();*/
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
