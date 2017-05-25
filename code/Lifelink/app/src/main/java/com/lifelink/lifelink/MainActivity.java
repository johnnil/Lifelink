package com.lifelink.lifelink;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    EditText colorInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        Button gotoCreate = (Button) findViewById(R.id.gotoCreate);
        gotoCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LobbyCreation.class);
                startActivity(intent);
            }
        });

        Button gotoJoin = (Button) findViewById(R.id.gotoJoin);
        gotoJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LobbyJoining.class);
                startActivity(intent);
            }
        });

        Button gotoSettings = (Button) findViewById(R.id.gotoSettings);
        gotoSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });

        //Saved preferences
        nameInput = (EditText) findViewById(R.id.nameInput);
        colorInput = (EditText) findViewById(R.id.colorInput);

        SharedPreferences playerProfile = getSharedPreferences("playerProfile", Context.MODE_PRIVATE);
        nameInput.setText(playerProfile.getString("name", "empty"));
        colorInput.setText(playerProfile.getString("color", "#ffffff")); //color white if nothing else
    }

    /**
     * Update and save user profile information (name and color)
     */
    public void updateProfile (View view){
        SharedPreferences playerProfile = getSharedPreferences("playerProfile", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = playerProfile.edit();
        editor.putString("name", nameInput.getText().toString());
        editor.putString("color", colorInput.getText().toString());
        editor.apply();

        Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
    }
}