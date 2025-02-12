package com.example.week2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button dialogMB, toastMB;

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

        toastMB = (Button) findViewById(R.id.toastButton);
        dialogMB = (Button) findViewById(R.id.diaButton);

        toastMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToastMsg("toast button is clicked");
            }
        });

        dialogMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAndShowDialogBox("dialog button is clicked");
            }
        });
    }

    private void makeAndShowDialogBox(String dialogButtonIsClicked) {
        AlertDialog.Builder md = new AlertDialog.Builder(this);
        md.setTitle("CENG427");
        md.setMessage(dialogButtonIsClicked);
        md.setIcon(R.drawable.ic_launcher_background);

        md.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                displayToastMsg("Yes is clicked");
            }
        });

        md.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                displayToastMsg("No is clicked");
            }
        });

        md.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                displayToastMsg("Cancel is clicked");
            }
        });

        md.create();
        md.show();
    }

    private void displayToastMsg(String toastButtonIsClicked) {
//        Toast toast = Toast.makeText(getBaseContext(), toastButtonIsClicked, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.show();
        Toast.makeText(getBaseContext(), toastButtonIsClicked, Toast.LENGTH_SHORT).show();
    }


}