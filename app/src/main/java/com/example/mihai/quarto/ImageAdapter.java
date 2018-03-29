package com.example.mihai.quarto;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 *
 *
 * ImageAdapter.java : This class is used as an Adapter of images from DrawData.class to gridView in MainActyvity.class
 * according to RunGame board and set
 *
 *
 */

public class ImageAdapter extends BaseAdapter {
    private static final String TAG = "ImageAdapterLogs";
    private Context mContext;
    int idn;

    public ImageAdapter(Context c, int grid) {
        mContext = c;
        idn = grid;
    }

    @Override
    public int getCount() {
        // Log.d (TAG, "image adapter getCount");
        switch (idn) {
            case R.id.gridBoard:
                return RunGame.board.length();
            case R.id.gridSet:
                return RunGame.setF.length();
        }
        return -1;
    }

    @Override
    public Object getItem(int position) {
        // Log.d (TAG, "image adapter getItem");
        return null;
    }

    @Override
    public long getItemId(int position) {
        // Log.d (TAG, "image adapter getItemId");
        switch (idn) {
            case R.id.gridBoard:
                return RunGame.board.getId(position);

            case R.id.gridSet:
                return RunGame.setF.getId(position);
        }
        return -1;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
                        // if it's not recycled, initialize some
                        // attributes
            imageView = new ImageView(mContext);
                        // imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                        // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        // imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        switch (idn) {
            case R.id.gridBoard:
                // if (logicData.)
                if (MainActivity.run.logicData.isTrue(LogicData.winPart)) {
                    if (LogicData.vin[0] == position | LogicData.vin[1] == position
                            | LogicData.vin[2] == position
                            | LogicData.vin[3] == position) {
                            // Log.d (TAG,
                            // "image adapter setImageResource(DrawData.visible[RunGame.board.getId(position "
                            // +position+" ) "+RunGame.board
                            // .getId(position)+ "] = " +
                            // DrawData.visible[RunGame.board.getId(position)] );

                        imageView.setImageResource(DrawData.visible[RunGame.board
                                .getId(position)]);
                        break;
                    } else {
                        // Log.d (TAG,
                        // "image adapter setImageResource(DrawData.unVisible[RunGame.board.getId(position "
                        // +position+" ) "+RunGame.board
                        // .getId(position)+ "] = " +
                        // DrawData.unVisible[RunGame.board.getId(position)] );

                        imageView.setImageResource(DrawData.unVisible[RunGame.board
                                .getId(position)]);
                        break;
                    }
                } else {
                    // Log.d (TAG,
                    // "image adapter ��� ������ setImageResource(DrawData.visible[RunGame.board.getId(position "
                    // +position+" ) "+RunGame.board
                    // .getId(position)+ "] = " +
                    // DrawData.visible[RunGame.board.getId(position)] );
                    imageView.setImageResource(DrawData.visible[RunGame.board
                            .getId(position)]);
                    break;
                }

            case R.id.gridSet:

                if (LogicData.searchFigurePosition != -1
                        & LogicData.searchFigurePosition != position)
                {
                    // Log.d (TAG,
                    // "image adapter  setImageResource(DrawData.unVisible[RunGame.setF.getId(position "
                    // +position+" ) "+RunGame.setF
                    // .getId(position)+ "] = " +
                    // DrawData.unVisible[RunGame.setF.getId(position)] );
                    imageView.setImageResource(DrawData.unVisible[RunGame.setF
                            .getId(position)]);

                } else {
                    imageView.setImageResource(DrawData.visible[RunGame.setF
                            .getId(position)]);
                    // Log.d (TAG,
                    // "image adapter  setImageResource(DrawData.visible[RunGame.setF.getId(position "
                    // +position+" ) "+RunGame.setF
                    // .getId(position)+ "] = " +
                    // DrawData.visible[RunGame.setF.getId(position)] );
                }
                break;
        }
        return imageView;
    }

}

