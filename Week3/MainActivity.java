package com.example.week3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String tag = "Events";

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
        Log.d(tag, "in the onCreate event");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "in the onStart event");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "in the onRestart event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "in the onResume event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "in the onPause event");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "in the onStop event");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "in the onDestroy event");
        Toast.makeText(getBaseContext(), "I am destroyed!!", Toast.LENGTH_SHORT).show();
    }
}