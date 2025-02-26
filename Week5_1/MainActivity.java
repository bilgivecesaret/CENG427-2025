package com.example.week5_1;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Animation textBlink;
    private ImageView imv;
    private Button btn;
    private boolean spades = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = (TextView) findViewById(R.id.tv1);
        imv = (ImageView) findViewById(R.id.imv2);
        btn = (Button) findViewById(R.id.btn1);

        // Code for text blinking
        textBlink = new AlphaAnimation(0.0f, 1.0f);
        textBlink.setDuration(50);
        textBlink.setStartOffset(20);
        textBlink.setRepeatMode(Animation.REVERSE);
        textBlink.setRepeatCount(Animation.INFINITE);
        tv.startAnimation(textBlink);

    }

    public void onClick(View view) {
        spades = !spades;
        if (spades) {
            btn.setText("Show Clubs");
            imv.setVisibility(View.INVISIBLE);
        }
        else {
            btn.setText("Show Spades");
            imv.setVisibility(View.VISIBLE);
        }
    }
}