package com.example.mihai.quarto;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 *
 * @author  implemented all possible actions in the game:
 */

public class RunGame  {
    private static final String TAG = "RunGamesLogs";

    public LogicData logicData;
    public static DrawData drawData;
    public static SetFigure setF;
    public static SetFigure board;
    public static PopWin popWin;
    private PopupDelegate delegate;

    public RunGame( PopupDelegate delegate) {
        logicData = new LogicData();
        drawData = new DrawData();
        popWin = new PopWin();
        setF = new SetFigure();
        this.delegate = delegate;
        board = new SetFigure();
        setF.createSetFigure();
        board.cleanSet();
    }

    public void setClick(int position) {
        /**
         * choice figure to opponent
         */
        if (logicData.isTrue(LogicData.choicePart)) {
            if (position == logicData.getFigurePositionToMove()) { // deselect
                Log.d(TAG, " uncheck selection");
                logicData.setPartGame(LogicData.choicePart);
                logicData.resetFigurePositionToMove();
                return;
            }

            else { // select
                Log.d(TAG, " select figure");
                logicData.addPartGame(LogicData.passPart);
                logicData.setFigurePositionToMove(position);
                logicData.resetFieldToMove();
                logicData.setPartGame(LogicData.passPart, LogicData.choicePart);
                return;
            }
        }
    }

    public void boardMove(int position) {
        /**
         * choice field to move
         */
        Log.d(TAG, " select field to place figure");
        if (logicData.isTrue(LogicData.movePart)) {
            if (logicData.getFigurePositionToMove() == -1) { // remove
                Log.d(TAG, "remove figure");
                board.put(position, board.pickUp(logicData.getFieldToMove()));
                logicData.setFieldToMove(position);
                return;
            }

            if (logicData.getFieldToMove() == -1) {// first move
                Log.d(TAG, " first move");
                logicData.setFieldToMove(position);
                board.put(position,
                        setF.pickUp(logicData.getFigurePositionToMove()));
                logicData.resetFigurePositionToMove();
                logicData.setPartGame(LogicData.movePart, LogicData.choicePart);
                return;
            }
        }
    }

    public void boardVin(int position) {
        /**
         * win combination choice
         */
        Log.d(TAG, " win combination choice");
        if (logicData.isTrue(LogicData.winPart)) {
            setWin(position);
            if (isWin()) {

                delegate.showWinPopUp();
                logicData.setPartGame(LogicData.restartPart);
                return;
            }
        }
    }

    public void btnClick() {
        /**
         * verify quarto/transfer the move- button click
         */
        Log.d(TAG, " Click Button");
        if (logicData.isTrue(LogicData.passPart)
                & logicData.isTrue(LogicData.choicePart)) { // pass
            Log.d(TAG, " Pass the move");
            logicData.setPartGame(LogicData.movePart);
            logicData.setPlayer((logicData.getPlayer() + 1) % 2); // nextPlayer
            return;
        }

        if (logicData.isTrue(LogicData.movePart)
                & logicData.isTrue(LogicData.choicePart)) {// win
            Log.d(TAG, " Game Won");
            logicData.setPartGame(LogicData.winPart);
            return;
        }
        if (logicData.isTrue(LogicData.winPart)) { // return to game
            Log.d(TAG, " Raturn to game");
            logicData.setPartGame(LogicData.choicePart, LogicData.movePart);
            logicData.resetWin();
            return;
        }

        if (logicData.isTrue(LogicData.restartPart)) {// restart
            Log.d(TAG, "Restart");
            logicData = new LogicData();
            setF = new SetFigure();
            board = new SetFigure();
            setF.createSetFigure();
            board.cleanSet();
        }
    }

    private void setWin(int position) {
        Log.d(TAG, "setVin");
        for (int n = 0; n <= 3; n++) { // remove position of win set
            if (logicData.getWin(n) == position) {
                logicData.setWin(n, -1);
                return;
            }
        }
        for (int n = 0; n <= 3; n++) { // assign position to win set
            if (logicData.getWin(n) == -1) {
                logicData.setWin(n, position);
                return;
            }
        }
    }


    private boolean isWin() {// check win set
        Log.d(TAG, "isWin");
        if (logicData.getWin(0) != -1 && logicData.getWin(1) != -1
                && logicData.getWin(2) != -1 && logicData.getWin(3) != -1
                && logicData.sortWin() && isVinLine() && isVictory() )
            return true  ;
        else
            return false;
    }



    private boolean isVinLine() {// check if it is a straight line
        Log.d(TAG, "setVinLine");
        int a = (logicData.getWin(3) - logicData.getWin(2)); // position difference between 3 and 2 element
        if (a == (logicData.getWin(2) - logicData.getWin(1))
                & a == (logicData.getWin(1) - logicData.getWin(0)))  // check if selected elements are in the same line
            switch (a) {
                case 4:  // vertical line
                    return true;
                case 1: // horizontal line
                    if (logicData.getWin(0) % 4 == 0) {
                        return true;
                    } else {
                        return false;
                    }
                case 5: // primary diagonal line
                    if (logicData.getWin(0) == 0) {
                        return true;
                    } else {
                        return false;
                    }
                case 3: // secondary diagonal line
                    if (logicData.getWin(0) == 3) {
                        return true;
                    } else {
                        return false;
                    }
            }
        return false;
    }

    private boolean isVictory() { // check if the items are victorious
        Log.d(TAG, "setVictory");
        int p1 = setF.getId(logicData.getWin(0));
        int p2 = setF.getId(logicData.getWin(1));
        int p3 = setF.getId(logicData.getWin(2));
        int p4 = setF.getId(logicData.getWin(3));
        int a = (~((p1 ^ p2) | (p1 ^ p3) | (p1 ^ p4)) << 28);
        if (a == 0)
            return false;
        else
            return true;
    }
}

