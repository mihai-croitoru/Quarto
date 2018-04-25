package com.example.mihai.quarto;



        import android.util.Log;

/**
 * SetFigure.java full figure set and clean set for the game pick up and put/place figures
 */

public class SetFigure {
    private static final String TAG = "SetFigureLogs";
    Figure set[];

    static Figure nll = new Figure(16); // clear field

    SetFigure() {
        // Log.d (TAG, "setFigure constructor" );
        set = new Figure[16];
    }

    public int length() { // used for the ImageAdapter class
        // Log.d (TAG, " set length" );
        return set.length;
    }

    public int getId(int p) {// ImageAdapter need
        Log.d (TAG, "getId" );
        return set[p].getId();
    }

    public void createSetFigure() { // start set for the game
        Log.d (TAG, "createSetFigure" );
        for (int i = 0; i <= 15; i++) {
            set[i] = new Figure(i);
        }
        ;
    }

    public void cleanSet() { // clean/empty set
        Log.d (TAG, "cleanSet" );
        for (int i = 0; i <= 15; i++) {
            set[i] = nll;
        }
    }

    public Figure pickUp(int position) {// pick up figure from set
        // Log.d (TAG, "pickUp" );
        Figure tempF = set[position];
        set[position] = nll;
        return tempF;
    }

    public void put(int position, Figure f) {// put figure to set
        // Log.d (TAG, "put" );
        set[position] = f;
    }
}
