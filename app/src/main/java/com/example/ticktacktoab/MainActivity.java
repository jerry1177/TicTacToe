package com.example.ticktacktoab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPressed(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        if (view.getTag().toString().equals("2")) {
            intent.putExtra("yourBoolName", false);
            //Toast.makeText(this, "two Players", Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("yourBoolName", true);
        }
        startActivity(intent);
    }
}
