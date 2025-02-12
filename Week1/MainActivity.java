package com.example.week1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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
    }

    public void converCurrencyToTurkishLira(View v){
        EditText dollarText = findViewById(R.id.dollarText);
        TextView liraTextView = findViewById(R.id.resultText);

        if(dollarText.getText().toString().isEmpty()){
            liraTextView.setText(R.string.no_value_message);
            return;
        }
        double exchangeRate = 36.05;
        double turkishLira = Double.parseDouble(dollarText.getText().toString()) * exchangeRate;
        liraTextView.setText(String.format(Locale.ENGLISH, "%.2f", turkishLira));

    }
}