package com.example.ticktacktoab;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Boolean isOnePlayer = getIntent().getBooleanExtra("isOnePlayer",true);
        TextView textView = (TextView) findViewById(R.id.gameView);
        if (isOnePlayer) {
            textView.setText("One Player");
        } else {
            textView.setText("Two Players");
        }
    }
}
