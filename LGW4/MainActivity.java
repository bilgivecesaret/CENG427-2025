package com.example.lgw4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private boolean chk1_state = false;
    private EditText et1;
    private Spinner spinner1;
    private SeekBar seek1;

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

        // Locking the orientation to Portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        et1 = (EditText) findViewById(R.id.et1);

        spinner1 = (Spinner) findViewById(R.id.spin1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int index, long l) {
                // Your code here
                makeAndShowDialogBox("You just selected an item from the Spinner");

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        seek1 = (SeekBar) findViewById(R.id.seek1);
        seek1.setOnSeekBarChangeListener(mySeekBarListener);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            displayToast("Button1 is pressed");
        } else if (view.getId() == R.id.btn2) {
            displayToast("Button2 is pressed");
        } else if (view.getId() == R.id.chk1) {
            chk1_state = !chk1_state;
            if (chk1_state) {
                makeAndShowDialogBox("Color Checkbox is selected");
            } else {
                makeAndShowDialogBox("Color Checkbox is deselected");
            }
        } else if (view.getId() == R.id.chk2) {
            displayToast("***************");
        } else if (view.getId() == R.id.rdb1) {
            displayToast("Option1 is selected");
        } else if (view.getId() == R.id.rdb2) {
            displayToast("Option2 is selected");
        }
    }

    private void displayToast(String msg) {

        Toast toast = Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT);
        // Appears in the center
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        // Appears in the top-left corner
        // toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        toast.show();

        // To use Toast in one go
        // Appear in the center bottom by default
        // Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void makeAndShowDialogBox(String message) {

        AlertDialog.Builder myDialogBox = new AlertDialog.Builder(this);

        // set message, title, and icon
        myDialogBox.setTitle("AlertDialog");
        myDialogBox.setMessage(message);
        myDialogBox.setIcon(R.drawable.ic_launcher_background);

        // Set three option buttons
        myDialogBox.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // whatever should be done when answering "YES" goes
                        // here

                    }
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

    private SeekBar.OnSeekBarChangeListener mySeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {

            //et1.setText("You just modified SeekBar value");
            et1.setText(String.valueOf(progress));
        } // end method onProgressChanged

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        } // end method onStartTrackingTouch

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        } // end method onStopTrackingTouch
    }; // end OnSeekBarChangeListener
}