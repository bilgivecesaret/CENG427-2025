package com.example.lgw6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

    ListView listView;
    ArrayList<ListData> dataArray;
    AdapterView.AdapterContextMenuInfo info;
    private int currency = 0;
    private TextView tv,tvtitle1,tvtitle2;
    private EditText currency1EditText;
    private EditText currency2EditText;
    private ValueAnimator colorAnim;
    private static final int PINK = 0xffFFFF00;
    private static final int YELLOW = 0xffFA58F4;
    // Currency1 amount entered by the user
    private double currency1Amount = 0.0;
    // Currency2 amount entered by the user
    private double currency2Amount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hiding status bar using code
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        tvtitle1 = (TextView) findViewById(R.id.tvtitle1);
        tvtitle2 = (TextView) findViewById(R.id.tvtitle2);
        tv = (TextView) findViewById(R.id.currency2TextView);

        colorAnim = ObjectAnimator.ofInt(tvtitle1, "textColor", YELLOW, PINK);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        colorAnim = ObjectAnimator.ofInt(tvtitle2, "textColor", PINK, YELLOW);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        listView = (ListView) findViewById(R.id.listView);

        dataArray = new ArrayList<ListData>();

        // Single object
        ListData prepare_data;
        prepare_data = new ListData();
        prepare_data.setImage(R.drawable.usa);
        prepare_data.setCountry("USD");
        dataArray.add(prepare_data);

        prepare_data = new ListData();
        prepare_data.setImage(R.drawable.eu);
        prepare_data.setCountry("EUR");
        dataArray.add(prepare_data);

        prepare_data = new ListData();
        prepare_data.setImage(R.drawable.uk);
        prepare_data.setCountry("GBP");
        dataArray.add(prepare_data);

        prepare_data = new ListData();
        prepare_data.setImage(R.drawable.japan);
        prepare_data.setCountry("YEN");
        dataArray.add(prepare_data);

        listView.setAdapter(new CustomAdapter(this, dataArray));

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View view, int position,
                                    long id) {

                switch (position) {
                    case 0: // USD
                        currency = 0;
                        tv.setText("Amount (USD): ");
                        // tv.setText("Amount (\u0024): "); // TL unicode: \u20ba
                        break;
                    case 1: // EUR
                        currency = 1;
                        tv.setText("Amount (EUR): ");
                        // tv.setText("Amount (\u20ac): ");
                        break;
                    case 2: // GBP
                        currency = 2;
                        tv.setText("Amount (GBP): ");
                        // tv.setText("Amount (\u00a3): ");
                        break;
                    case 3: // YEN
                        currency = 3;
                        tv.setText("Amount (YEN): ");
                        // tv.setText("Amount (\u00a5): ");
                        break;
                }

                initialize();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }

        });

        currency1EditText = (EditText) findViewById(R.id.currency1EditText);
        currency1EditText.addTextChangedListener(currency1EditTextWatcher);

        currency2EditText = (EditText) findViewById(R.id.currency2EditText);
        currency2EditText.addTextChangedListener(currency2EditTextWatcher);
    }

    // Event-handling object that responds to currency1EditText's events
    private TextWatcher currency1EditTextWatcher = new TextWatcher() {
        // Called when the user enters a number
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // Convert billEditText's text to a double
            try {
                currency1Amount = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                currency1Amount = 0.0; // default if an exception occurs
            }

            // Update the second currency EditText
            currency2EditText
                    .removeTextChangedListener(currency2EditTextWatcher);
            update2();
            currency2EditText.addTextChangedListener(currency2EditTextWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }
    };

    // Event-handling object that responds to currency2EditText's events
    private TextWatcher currency2EditTextWatcher = new TextWatcher() {
        // Called when the user enters a number
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // Convert billEditText's text to a double
            try {
                currency2Amount = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                currency2Amount = 0.0; // default if an exception occurs
            }

            // Update the first currency EditText
            currency1EditText
                    .removeTextChangedListener(currency1EditTextWatcher);
            update1();
            currency1EditText.addTextChangedListener(currency1EditTextWatcher);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }
    };

    private void initialize() {
        currency1EditText.removeTextChangedListener(currency1EditTextWatcher);
        currency2EditText.removeTextChangedListener(currency2EditTextWatcher);

        currency1EditText.setText("");
        currency1EditText.setSelection(currency1EditText.getText().length());

        currency2EditText.setText("");
        currency2EditText.setSelection(currency2EditText.getText().length());

        currency1EditText.addTextChangedListener(currency1EditTextWatcher);
        currency2EditText.addTextChangedListener(currency2EditTextWatcher);
    }

    // Updates currency1 EditText
    private void update1() {
        if (!currency2EditText.getText().toString().isEmpty()) {
            if (currency == 0)
                currency1EditText.setText(String.format("%.2f",
                        fromUSD2TL(currency2Amount)));
            else if (currency == 1)
                currency1EditText.setText(String.format("%.2f",
                        fromEUR2TL(currency2Amount)));
            else if (currency == 2)
                currency1EditText.setText(String.format("%.2f",
                        fromGBP2TL(currency2Amount)));
            else if (currency == 3)
                currency1EditText.setText(String.format("%.2f",
                        fromYEN2TL(currency2Amount)));
        } else {
            currency1EditText
                    .removeTextChangedListener(currency1EditTextWatcher);

            currency1EditText.setText("");
            currency1EditText
                    .setSelection(currency1EditText.getText().length());

            currency1EditText.addTextChangedListener(currency1EditTextWatcher);

        }
    }

    // Updates the currency2 EditText
    private void update2() {

        if (!currency1EditText.getText().toString().isEmpty()) {
            if (currency == 0)
                currency2EditText.setText(String.format("%.2f",
                        fromTL2USD(currency1Amount)));
            else if (currency == 1)
                currency2EditText.setText(String.format("%.2f",
                        fromTL2EUR(currency1Amount)));
            else if (currency == 2)
                currency2EditText.setText(String.format("%.2f",
                        fromTL2GBP(currency1Amount)));
            else if (currency == 3)
                currency2EditText.setText(String.format("%.2f",
                        fromTL2YEN(currency1Amount)));
        } else {
            currency2EditText
                    .removeTextChangedListener(currency2EditTextWatcher);

            currency2EditText.setText("");
            currency2EditText
                    .setSelection(currency2EditText.getText().length());

            currency2EditText.addTextChangedListener(currency2EditTextWatcher);

        }
    }

    // Converting TL to USD
    private double fromTL2USD(double amount) {
        return (amount / 36.4);
    }

    // Converting USD to TL
    private double fromUSD2TL(double amount) {
        return (amount * 36.4);
    }

    // Converting TL to EUR
    private double fromTL2EUR(double amount) {
        return (amount / 38.9);
    }

    // Converting EUR to TL
    private double fromEUR2TL(double amount) {
        return (amount * 38.9);
    }

    // Converting TL to GBP
    private double fromTL2GBP(double amount) {
        return (amount / 44.6);
    }

    // Converting GBP to TL
    private double fromGBP2TL(double amount) {
        return (amount * 44.6);
    }

    // Converting TL to YEN
    private double fromTL2YEN(double amount) {
        return (amount / 0.24);
    }

    // Converting YEN to TL
    private double fromYEN2TL(double amount) {
        return (amount * 0.24);
    }
}
