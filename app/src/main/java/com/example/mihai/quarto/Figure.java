package com.example.mihai.quarto;

import android.util.Log;

/**
 * Figure.java : This class is for getting the id's of the figures which we play. Every numeral, in id binary
 * representation, presents figure characteristic.
 *
 */

public class Figure {
    private static final String TAG = "FigureLogs";

    int id;

    //public Figure() {}

    public Figure(int id) {
        this.id = id;
    }

    public int getId() {
        Log.d (TAG, "getId" );
        return id;
    }

}
