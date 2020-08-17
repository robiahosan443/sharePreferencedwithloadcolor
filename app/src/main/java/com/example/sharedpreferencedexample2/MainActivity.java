package com.example.sharedpreferencedexample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int score = 0;
    private LinearLayout linearLayout;

    private TextView textView;
    private Button inceasebtn, decreasebtn, lastscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearlayoutId);
        inceasebtn = findViewById(R.id.increaseButtonId);
        decreasebtn = findViewById(R.id.decreaseButtonId);
        textView = findViewById(R.id.textviewId);
        lastscore = findViewById(R.id.lastscoreId);

        if (loadcolor()!=R.color.colorAccent){
            linearLayout.setBackgroundColor(loadcolor());
        }

        inceasebtn.setOnClickListener(this);
        decreasebtn.setOnClickListener(this);
        lastscore.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.increaseButtonId) {
            score = score + 10;
            textView.setText("score:" + score);
            SharedPreferences sharedPreferences = getSharedPreferences("storingscore", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("lastIncreasedScore", score);
            editor.commit();
        } else if (v.getId() == R.id.decreaseButtonId) {
            score = score - 10;
            textView.setText("score:" + score);
            SharedPreferences sharedPreferences = getSharedPreferences("storingscore", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("lastdecreasedscore", score);
            editor.commit();

        } else if (v.getId() == R.id.lastscoreId) {
            SharedPreferences sharedPreferences = getSharedPreferences("storingscore", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("lastIncreasedScore")) {
                int LastScore = sharedPreferences.getInt("lastIncreasedScore", 0);
                textView.setText("score:" + LastScore);
            } else if (sharedPreferences.contains("lastdecreasedscore")) {
                int LastScore = sharedPreferences.getInt("lastdecreasedscore", 0);
                textView.setText("score:" + LastScore);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.greencolorId) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.green));
            savecolor(getResources().getColor(R.color.green));
        }
        if (item.getItemId() == R.id.redcolorId) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.red));
            savecolor(getResources().getColor(R.color.red));
        }
        if (item.getItemId() == R.id.yellowcolorId) {
            linearLayout.setBackgroundColor(getResources().getColor(R.color.yellow));
            savecolor(getResources().getColor(R.color.yellow));
        }
        return super.onOptionsItemSelected(item);
    }

    private void savecolor(int color) {
        SharedPreferences sharedPreferences = getSharedPreferences("colordetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("selectedcolorkey", color);
        editor.commit();

    }
    private int loadcolor(){
        SharedPreferences sharedPreferences = getSharedPreferences("colordetails", Context.MODE_PRIVATE);
        int LoadColor=sharedPreferences.getInt("selectedcolorkey",getResources().getColor(R.color.colorPrimary));
        return LoadColor;
    }
}