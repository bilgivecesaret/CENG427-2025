package com.example.week5_3;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Resources mResources;
    private Configuration mConfiguration;
    private int orientation;

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
        if (1 == Configuration.ORIENTATION_PORTRAIT)
            displayToast("Portrait mode");
        else
            displayToast("Landscape mode");
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn1) displayToast("Top left button is pressed");
        if (view.getId() == R.id.btn2) displayToast("Top right button is pressed");
        if (view.getId() == R.id.btn3) displayToast("Bottom left button is pressed");
        if (view.getId() == R.id.btn4) displayToast("Bottom right button is pressed");
        if (view.getId() == R.id.btn5) displayToast("Center button is pressed");
        if (view.getId() == R.id.btn6) displayToast("Top center button is pressed");
        if (view.getId() == R.id.btn7) displayToast("Bottom center button is pressed");
    }

    private void displayToast(String msg) {

        Toast toast = Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG);
        // Appears in the center
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        // Appears in the top-left corner
        // toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        toast.show();

        // To use Toast in one go
        // Appear in the center bottom by default
        // Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}