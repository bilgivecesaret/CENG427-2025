package com.example.week13_4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
/*
 JSON stands for JavaScript Object Notation. It is an independent data
 exchange format and is the best alternative to XML. Android provides four
 different classes to manipulate JSON data. These classes are
 JSONArray, JSONObject, JSONStringer and JSONTokenizer.

 A JSON file consist of many components. The components of a JSON file are
 1. Array: In a JSON file , square bracket [ represents a JSON array
 2.	Objects: In a JSON file, curly bracket { represents a JSON object
 3. Key: A JSON object contains a key that is just a string. Pairs of key/value make up a JSON object
 4. Value: Each key has a value that could be string , integer or double e.t.c
 */

public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    private ListView lv;
    // URL to get contacts JSON
    private static String url = "https://raw.githubusercontent.com/AYBUcode/contacts-json/refs/heads/main/contacts.json";

    // JSON Node names
    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PHONE_HOME = "home";
    private static final String TAG_PHONE_OFFICE = "office";

    // contacts JSONArray
    private JSONArray contacts = null;

    // HashMap for ListView
    private ArrayList<HashMap<String, String>> contactList;

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

        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.lv);

        // Calling AsyncTask to get JSON
        new GetContacts().execute();

    }

    // AsyncTask class to get JSON by making HTTP call
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            //ServiceHandler mServiceHandler = new ServiceHandler();

            // Making a request to the URL and getting the response
            //String jsonStr = mServiceHandler.makeServiceCall(url, ServiceHandler.GET);

            // Reading the JSON file from the assets folder and storing it in a String
            //String jsonStr = loadFileFromAsset("contacts.json");
            ServiceHandler mServiceHandler = new ServiceHandler();
            String jsonStr = mServiceHandler.makeServiceCall(url, ServiceHandler.GET);

            // Optional: fallback to local file if network fails
            if (jsonStr == null) {
                Log.w("ServiceHandler", "Falling back to local contacts.json");
                jsonStr = loadFileFromAsset("contacts.json");
            }


            // Displaying the retrieved JSON object as a String via LogCat
            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_CONTACTS);

                    // looping through all Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String email = c.getString(TAG_EMAIL);
                        String address = c.getString(TAG_ADDRESS);
                        String gender = c.getString(TAG_GENDER);

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject(TAG_PHONE);
                        String mobile = phone.getString(TAG_PHONE_MOBILE);
                        String home = phone.getString(TAG_PHONE_HOME);
                        String office = phone.getString(TAG_PHONE_OFFICE);

                        // Temporary HashMap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // Adding each child node to HashMap key => value
                        contact.put(TAG_ID, id);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_EMAIL, email);
                        contact.put(TAG_PHONE_MOBILE, mobile);

                        // adding contact(a HashMap) to contactList (an ArrayList)
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
                Log.e("ServiceHandler", "Couldn't get any data from the url");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();

            // Updating parsed JSON data into ListView
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                    contactList, R.layout.list_item, new String[] { TAG_NAME,
                    TAG_EMAIL, TAG_PHONE_MOBILE }, new int[] {
                    R.id.name, R.id.email, R.id.mobile });

            lv.setAdapter(adapter);
        }

    }

    private String loadFileFromAsset(String fileName) {
        String file = null;
        try {

            InputStream is = getBaseContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            file = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return file;
    }

}