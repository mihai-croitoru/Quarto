package com.example.mihai.quarto;



import android.util.Log;

/**
 * PartGame.java This class of game parts which use in LogicData
 */
public class PartGame {
    private static final String TAG = "PartGameLogs";

    boolean a = false;

    public boolean isTrue() {
        // Log.d (TAG, "isTrue" );
        return a;
    }

    public void set(boolean b) {
        a = b;
    }

}
