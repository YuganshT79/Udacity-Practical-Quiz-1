package com.yuganshtyagi.udacitypracticalquiz1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.DeadObjectException;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText userName,userEmail,userAbout;
    Button submitBtn;
    SharedPreferences preferences;

    private static final String NAME_KEY = "name";
    private static final String EMAIL_KEY = "email";
    private static final String ABOUT_KEY = "about";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.username);
        userEmail = findViewById(R.id.user_email);
        userAbout = findViewById(R.id.about);

        if(savedInstanceState != null){
            userName.setText(savedInstanceState.getString(NAME_KEY));
            userEmail.setText(savedInstanceState.getString(EMAIL_KEY));
            userAbout.setText(savedInstanceState.getString(ABOUT_KEY));
        }

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        submitBtn = findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupSharedPreferences();
                userName.getText().clear();
                userEmail.getText().clear();
                userAbout.getText().clear();

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setupSharedPreferences(){
        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME_KEY,userName.getText().toString());
        editor.putString(EMAIL_KEY, userEmail.getText().toString());
        editor.putString(ABOUT_KEY, userAbout.getText().toString());
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String about = userAbout.getText().toString();

        outState.putString(NAME_KEY, name);
        outState.putString(EMAIL_KEY, email);
        outState.putString(ABOUT_KEY, about);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.details_menu){
            Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
