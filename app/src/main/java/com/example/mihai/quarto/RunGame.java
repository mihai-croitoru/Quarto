package com.example.mihai.quarto;




import android.util.Log;

/**
 *
 * @author gregory implemented all possible actions in the game:
 */

public class RunGame {
    private static final String TAG = "RunGamesLogs";

    public LogicData logicData;
    public static DrawData drawData;
    public static SetFigure setF;
    public static SetFigure board;

    public RunGame() {
        logicData = new LogicData();
        drawData = new DrawData();

        setF = new SetFigure();
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
                Log.d(TAG, " ����� �����");
                logicData.setPartGame(LogicData.choicePart);
                logicData.resetFigurePositionToMove();
                return;
            }

            else { // select
                Log.d(TAG, " ������� ������");
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
        Log.d(TAG, " ����� ������ ��� ����");
        if (logicData.isTrue(LogicData.movePart)) {
            if (logicData.getFigurePositionToMove() == -1) { // remove
                Log.d(TAG, "����������");
                board.put(position, board.pickUp(logicData.getFieldToMove()));
                logicData.setFieldToMove(position);
                return;
            }

            if (logicData.getFieldToMove() == -1) {// first move
                Log.d(TAG, " ������ ���");
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
        Log.d(TAG, " ����� ���������� ����������");
        if (logicData.isTrue(LogicData.winPart)) {
            setWin(position);
            if (isWin()) {
                logicData.setPartGame(LogicData.restartPart);
                return;
            }
        }
    }

    public void btnClick() {
        /**
         * button click
         */
        Log.d(TAG, " ������ ������");
        if (logicData.isTrue(LogicData.passPart)
                & logicData.isTrue(LogicData.choicePart)) { // pass
            Log.d(TAG, " �������� ���");
            logicData.setPartGame(LogicData.movePart);
            logicData.setPlayer((logicData.getPlayer() + 1) % 2); // nextPlayer
            return;
        }

        if (logicData.isTrue(LogicData.movePart)
                & logicData.isTrue(LogicData.choicePart)) {// win
            Log.d(TAG, " ������");
            logicData.setPartGame(LogicData.winPart);
            return;
        }
        if (logicData.isTrue(LogicData.winPart)) { // return to game
            Log.d(TAG, " ���������");
            logicData.setPartGame(LogicData.choicePart, LogicData.movePart);
            logicData.resetVin();
            return;
        }

        if (logicData.isTrue(LogicData.restartPart)) {// restart
            Log.d(TAG, "�������");
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
            if (logicData.getVin(n) == position) {
                logicData.setVin(n, -1);
                return;
            }
        }
        for (int n = 0; n <= 3; n++) { // assign position to win set
            if (logicData.getVin(n) == -1) {
                logicData.setVin(n, position);
                return;
            }
        }
    }

    private boolean isWin() {// check win set
        Log.d(TAG, "isVin");
        if (logicData.getVin(0) != -1 && logicData.getVin(1) != -1
                && logicData.getVin(2) != -1 && logicData.getVin(3) != -1
                && logicData.sortVin() && isVinLine() && isVictory())
            return true;
        else
            return false;
    }

    private boolean isVinLine() {// check: is win set a line ?
        Log.d(TAG, "setVinLine");
        int a = (logicData.getVin(3) - logicData.getVin(2));
        if (a == (logicData.getVin(2) - logicData.getVin(1))
                & a == (logicData.getVin(1) - logicData.getVin(0)))
            switch (a) {
                case 4:
                    return true;
                case 1:
                    if (logicData.getVin(0) % 4 == 0) {
                        return true;
                    } else {
                        return false;
                    }
                case 5:
                    if (logicData.getVin(0) == 0) {
                        return true;
                    } else {
                        return false;
                    }
                case 3:
                    if (logicData.getVin(0) == 3) {
                        return true;
                    } else {
                        return false;
                    }
            }
        return false;
    }

    private boolean isVictory() { // check: selected figures overall parameter
        Log.d(TAG, "setVictory");
        int p1 = setF.getId(logicData.getVin(0));
        int p2 = setF.getId(logicData.getVin(1));
        int p3 = setF.getId(logicData.getVin(2));
        int p4 = setF.getId(logicData.getVin(3));
        int a = (~((p1 ^ p2) | (p1 ^ p3) | (p1 ^ p4)) << 28);
        if (a == 0)
            return false;
        else
            return true;
    }
}

