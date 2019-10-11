package com.MohamedTaha.Imagine.Quran;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.check)
    Button check;
    boolean isInternet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }
    class noInternet extends AsyncTask<String, Void, String>{
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... urls) {
            String stringUrl = urls[0];
            String result;
            String inputLine;
            try {
                URL url = new URL(stringUrl);
                HttpURLConnection urlConnection =(HttpURLConnection)url.openConnection();
                //Set methods and timeouts
                urlConnection.setRequestMethod(REQUEST_METHOD);
                urlConnection.setReadTimeout(READ_TIMEOUT);
                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                urlConnection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(urlConnection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();

            }
            catch(IOException e){
                e.printStackTrace();
                result = null;

            }
            return result;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            if (aVoid == null){
                isInternet = false;
                Toast.makeText(Main2Activity.this,"No" +  isInternet, Toast.LENGTH_SHORT).show();
            }else {
                isInternet = true;
                Toast.makeText(Main2Activity.this, "Data" + isInternet, Toast.LENGTH_SHORT).show();

            }
        }
    }

    @OnClick(R.id.check)
    public void onViewClicked() {
        noInternet noInternet = new noInternet();
        noInternet.execute("http://clients3.google.com/generate_204");
     /*   NoInternetConnection  noInternetConnection=  new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        boolean isInternet = noInternetConnection.isInternet();*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isInternet){
                    Toast.makeText(getApplicationContext(), "There is data" + isInternet, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "There is not data" + isInternet, Toast.LENGTH_SHORT).show();

                }            }
        }, 2000);

          }
}
