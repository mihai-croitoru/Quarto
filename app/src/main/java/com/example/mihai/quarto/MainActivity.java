package com.example.mihai.quarto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * MainActivity.java game activity
 *
 */
public class MainActivity extends Activity implements OnClickListener,
        OnItemClickListener, NavigationView.OnNavigationItemSelectedListener, PopupDelegate {

    private static final String TAG = "MainActivityLogs";
    public static LogicData logicData;
    // int tempText;
    TextView text;
    GridView setGrid, boardGrid;
    Button btn;
    Button btnRules;
    Button PopUpButton;
    Button btnWin;
    public static RunGame run;
    ImageAdapter boardAdapter = new ImageAdapter(this, R.id.gridBoard);
    ImageAdapter setAdapter = new ImageAdapter(this, R.id.gridSet);
    private Toolbar supportActionBar;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        boardGrid = (GridView) findViewById(R.id.gridBoard);
        setGrid = (GridView) findViewById(R.id.gridSet);
        btn = (Button) findViewById(R.id.button1);
      //  btnRules = (Button) findViewById(R.id.buttonRules);
     //   btnWin = (Button) findViewById(R.id.winButton);
        text = (TextView) findViewById(R.id.instructions);
        //PopUpButton = findViewById(R.id.popupButton);
        //  LinearLayout r1 = findViewById(R.id.LinearLayoutWin);
        // final TextView tvWin = (TextView) findViewById(R.id.textWin);

        //--pasted
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        run = (RunGame) getLastNonConfigurationInstance();
        if (run == null)
            start();
        boardGrid.setOnItemClickListener(this);
        setGrid.setOnItemClickListener(this);
        btn.setOnClickListener(this);
//        btnRules.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, PopRules.class));
//                //PopupWindow popupWindow = new PopupWindow(MainActivity.this, (AttributeSet) btnRules);
//                //View view1 = findViewById(R.id.textRules);
//                //popupWindow.setContentView(view1);
//                //Intent intent;
//                //intent = new Intent(RulesActivity.class, this);
//                //startActivity(intent);
//                //startActivity(new Intent(RulesActivity.class, (MainActivity.this));
//            }
//        });

//        btnWin.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent( MainActivity.this, PopWin.class));
//               // tvWin.setBackgroundColor(Color.parseColor("#55FF0000"));
//            }
//        });

//        PopUpButton.setOnClickListener( new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                PopupMenu popup= new PopupMenu(MainActivity.this,PopUpButton);
//
//                popup.getMenuInflater().inflate(R.menu.popup_menu_rules,popup.getMenu());
//
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        Toast.makeText(MainActivity.this, "Click Item: " + menuItem.getTitle(),Toast.LENGTH_LONG).show();
//                        return true;
//                    }
//                });
//                popup.show();
//
//            }
//        });
      //  Log.d(TAG, "��� �������");
        f5();
      //  Log.d(TAG, "����������");
    }




    public void start() {
        Log.d(TAG, "start");
        run = new RunGame(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

        // Inflate the menu; this adds items to the action bar if it is present.
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.restart:
                start();
                f5();
                break;
            case R.id.rules:
                Intent intent = new Intent(this, RulesActivity.class);
                startActivity(intent);
                break;
            case   R.id.action_settings: {
                return true;
            }    
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_rules) {
            startActivity(new Intent(MainActivity.this, PopRules.class));


        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, Activity_options.class));

        }else if (id == R.id.nav_pop_win) {
            startActivity(new Intent( MainActivity.this, PopWin.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Log.d(TAG, "onItemClick");
        if (setGrid == parent && id != 16) { // choice figure
            run.setClick(position);
            f5();
        }

        if (boardGrid == parent & id == 16) {// move
            run.boardMove(position);
            f5();
        }

        if (boardGrid == parent && id != 16) { // choice win set
            run.boardVin(position);
            f5();
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        if (R.id.button1 == v.getId()) {
            run.btnClick();
            f5();


        }
//        if (R.id.buttonRules == v.getId()){
//        startActivity(new Intent(RulesActivity.class, (MainActivity.this));
//        }

    }

    //the onOnClick for rules page!
    public void yourMethodName(View v){
        startActivity(new Intent(MainActivity.this, RulesActivity.class));
    }

    
        void f5() { // refresh screen
        Log.d(TAG, "f5");
        setGrid.setAdapter(setAdapter);
        boardGrid.setAdapter(boardAdapter);
        text.setText(getString(run.logicData.getPlayer()) + ": "
                + getString(run.logicData.getInstruction()));
        btn.setText(run.logicData.getToDoText());
    }

    public Object onRetainNonConfigurationInstance() {
        return run;
    }

    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }

    @Override
    public void showWinPopUp() {
        startActivity(new Intent( MainActivity.this, PopWin.class));
    }
}
