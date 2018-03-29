package com.example.mihai.quarto;



import java.util.Arrays;

import android.util.Log;



/**
 * LogicData.java All game data placed in this class.
 */

public class LogicData {
    private static final String TAG = "LogicDataLogs";

    public static PartGame passPart, choicePart, movePart, winPart,
            restartPart;
    public static int searchField = -1, searchFigurePosition = -1, player;
    public static int vin[] = new int[4];

    LogicData() {
        Log.d(TAG, "�����������");
        passPart = new PartGame();
        choicePart = new PartGame();
        movePart = new PartGame();
        winPart = new PartGame();
        restartPart = new PartGame();
        setPartGame(choicePart);
    }

    public int getToDoText() {
        Log.d(TAG, " getToDoText");
        if (isTrue(passPart))
            return R.string.btnPass;

        if (isTrue(choicePart))
            return R.string.btnVin;

        if (isTrue(movePart))
            return R.string.btnVin;

        if (isTrue(winPart))
            return R.string.btnReturn;

        if (isTrue(restartPart))
            return R.string.btnRestart;
        return R.string.hello_world;
    }

    public int getInstruction() {
        Log.d(TAG, "getInstruction");
        if (isTrue(passPart))
            return R.string.pass;

        if (isTrue(choicePart))
            return R.string.choiceFigure;

        if (isTrue(movePart))
            return R.string.move;

        if (isTrue(winPart))
            return R.string.vinLine;

        if (isTrue(restartPart))
            return R.string.congratulations;

        return R.string.hello_world;
    }

    public boolean isTrue(PartGame p) {
        Log.d(TAG, " isTrue");
        return p.isTrue();
    }

    void setPartGame(PartGame p) {
        Log.d(TAG, "setPartGame");
        passPart.set(false);
        choicePart.set(false);
        movePart.set(false);
        winPart.set(false);
        restartPart.set(false);
        p.set(true);
    }

    void setPartGame(PartGame p, PartGame p2) {
        Log.d(TAG, " setPartGame 2");
        setPartGame(p);
        p2.set(true);
    }

    void addPartGame(PartGame p) {
        p.set(true);
    }

    int getFieldToMove() {
        return searchField;
    }

    void setFieldToMove(int i) {
        searchField = i;
    }

    void resetFieldToMove() {
        searchField = -1;
    }

    void setFigurePositionToMove(int i) {
        Log.d(TAG, "searchFigurePosition = i" + i);
        searchFigurePosition = i;
    }

    public int getFigurePositionToMove() {
        return searchFigurePosition;
    }

    void resetFigurePositionToMove() {
        searchFigurePosition = -1;
    }

    void setVin(int i, int p) {
        vin[i] = p;
    }

    int getVin(int i) {
        return vin[i];
    }

    boolean sortVin() {
        Arrays.sort(vin);
        return true;
    }

    void resetVin() {
        vin[0] = -1;
        vin[1] = -1;
        vin[2] = -1;
        vin[3] = -1;
    }

    public int getPlayer() {
        switch (player) {
            case (0):
                return R.string.player1;
            case (1):
                return R.string.player2;
        }
        return R.string.hello_world;
    }

    void setPlayer(int p) {
        player = p;
    }

}
