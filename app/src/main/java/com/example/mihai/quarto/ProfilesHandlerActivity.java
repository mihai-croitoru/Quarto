package com.example.mihai.quarto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.util.DisplayMetrics;

import java.util.List;

public class ProfilesHandlerActivity extends Activity {
    TextView textView;

    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles_handler);

         android.util.DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.85) ,(int) (height*.85));

        textView = (TextView) findViewById(R.id.textView1ProfileLayout);
        DatabaseHandler db = new DatabaseHandler(this);

        //inserting Profiles
        db.addProfile(new Profile("name1","surname1","profile1","pass1"));
        db.addProfile(new Profile("name2","surname2","profile2","pass2"));
        db.addProfile(new Profile("name3","surname3","profile3","pass3"));


        //reading and Dysplaing  all profiles
         List<Profile> profileList = db.getAllProfiles();

        for (Profile p : profileList){
            String log = "ID: " + p.getId() + ", Name: " + p.getName() +", Surname: " + p.getSurname() + ", Profile: " + p.getProfile() +"\n";
            text = text + log;
            }
            textView.setText(text);
    }
}
