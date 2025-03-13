package com.example.lgw5;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private ImageView imv1, imv2, imv3;
    private ValueAnimator colorAnim;

    private int imgNum = 3;
    private LinearLayout layout;

    // ARGB (Alpha, Red, Green and Blue)
    // If alpha value is 00 it means 100% transparent.
    // A alpha value of FF corresponds to opaque.
    private static final int RED = 0xffFF0000;
    private static final int BLUE = 0xff0000FF;

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

        imv1 = findViewById(R.id.imv1);
        imv2 = findViewById(R.id.imv2);
        imv3 = findViewById(R.id.imv3);

        tv = findViewById(R.id.tv1);

        layout = findViewById(R.id.main);

        // Code for TextView text color animation
        //colorAnim = ObjectAnimator.ofInt(tv, "backgroundColor",RED, BLUE);
        colorAnim = ObjectAnimator.ofInt(tv, "textColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.arrow_left) {
            if (imgNum == 1) {
                makeAndShowDialogBox("Already at the first image");
            } else {
                imgNum--;
                imv1.setVisibility(View.INVISIBLE);
                imv2.setVisibility(View.INVISIBLE);
                imv3.setVisibility(View.INVISIBLE);

                if (imgNum % 3 == 0) {
                    imv3.setVisibility(View.VISIBLE);
                } else if (imgNum % 3 == 1) {
                    imv1.setVisibility(View.VISIBLE);
                } else if (imgNum % 3 == 2) {
                    imv2.setVisibility(View.VISIBLE);
                }
            }
        } else if (view.getId() == R.id.arrow_right) {
            imgNum++;

            imv1.setVisibility(View.INVISIBLE);
            imv2.setVisibility(View.INVISIBLE);
            imv3.setVisibility(View.INVISIBLE);

            if (imgNum % 3 == 0) {
                imv3.setVisibility(View.VISIBLE);
            } else if (imgNum % 3 == 1) {
                imv1.setVisibility(View.VISIBLE);
            } else if (imgNum % 3 == 2) {
                imv2.setVisibility(View.VISIBLE);
            }
        } else if (view.getId() == R.id.btn1) {
            changeBackgroundColor();
        }
    }

    public void changeBackgroundColor() {
        int rand1, rand2, rand3;

        rand1 = (int) (Math.random() * 255);
        rand2 = (int) (Math.random() * 255);
        rand3 = (int) (Math.random() * 255);

        layout.setBackgroundColor(Color.rgb(rand1, rand2, rand3));
    }

    private void makeAndShowDialogBox(String message) {

        AlertDialog.Builder myDialogBox = new AlertDialog.Builder(this);

        // set message, title, and icon
        myDialogBox.setTitle("Warning");
        myDialogBox.setMessage(message);
        myDialogBox.setIcon(R.drawable.ic_launcher_background);

        // Set three option buttons
        myDialogBox.setPositiveButton("Close",
                (dialog, whichButton) -> {
                    // whatever should be done when answering "YES" goes
                    // here

                });

        // myDialogBox.setNegativeButton("NO",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        // myDialogBox.setNeutralButton("Cancel",
        // new DialogInterface.OnClickListener() {
        // public void onClick(DialogInterface dialog, int whichButton) {
        // // whatever should be done when answering "NO" goes here
        //
        // }
        // });

        myDialogBox.create();
        myDialogBox.show();
    }
}