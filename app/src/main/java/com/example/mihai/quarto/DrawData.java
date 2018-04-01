package com.example.mihai.quarto;

import android.util.Log;
/**
 *
 * DrawData.java figures images pictures name must comply ordinal value in array
 * each figure characteristic represented by 0 or 1 in binary code pictures number
 */

public class DrawData {

    private static final String TAG = "Logs";
    static int visible[] = new int[17];
    static int unVisible[] = new int[17];

    public DrawData() {
        // Log.d (TAG, " Message :");

        for (int i = 0, fig = R.drawable.fig0000, unfig = R.drawable.unfig0000; i <= 15; i++, fig++, unfig++) {
            visible[i] = fig;       //figures starting with "fig" are usable/visible/accessible
            unVisible[i] = unfig;   //figures starting  with "unfig" are not accesible to the player until the next round/game or when selecting a other figure in the active game
        }
        visible[16] = R.drawable.fnll;
        unVisible[16] = R.drawable.fnll;
    }
}
