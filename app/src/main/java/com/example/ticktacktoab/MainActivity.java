package com.example.ticktacktoab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean playerOneStarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerOneStarts = true;
    }

    public void playerStarts(View view) {
        playerOneStarts = !playerOneStarts;
    }

    public void buttonPressed(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        if (view.getTag().toString().equals("2")) {
            intent.putExtra("isOnePlayer", false);
        } else {
            intent.putExtra("isOnePlayer", true);
        }
        intent.putExtra("playerOneStarts", playerOneStarts);
        startActivity(intent);
    }
}
