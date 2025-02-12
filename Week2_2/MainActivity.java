package com.example.week2_2;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button b1, b2;
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
        //no need to typecast anymore
        b1 = (Button) findViewById(R.id.button1);
        //no need to typecast anymore
        b2 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(this::onClick);
        b2.setOnClickListener(this::onClick);

    }

    public void onClick(View view){
        if (view.getId() == R.id.button1){
            displayToast("Button1 is clicked");
        }
        if (view.getId() == R.id.button2){
            displayToast("Button2 is clicked");
        }
    }

    private void displayToast(String buttonIsClicked) {
       Toast.makeText(getBaseContext(), buttonIsClicked, Toast.LENGTH_SHORT)
                .show();

    }
}