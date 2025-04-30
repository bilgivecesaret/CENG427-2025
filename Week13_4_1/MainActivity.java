package com.example.week13_4_1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> countryNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryApi api = retrofit.create(CountryApi.class);
        api.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Country country : response.body()) {
                        countryNames.add(country.name.common);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            countryNames
                    );
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch countries", t);
            }
        });
    }

    // Retrofit API Interface
    public interface CountryApi {
        @GET("all")
        Call<List<Country>> getAllCountries();
    }

    // Country Model
    public static class Country {
        @SerializedName("name")
        public Name name;

        public static class Name {
            @SerializedName("common")
            public String common;
        }
    }
}