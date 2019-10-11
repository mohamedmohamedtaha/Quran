package com.MohamedTaha.Imagine.Quran.helper.checkConnection;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NoInternetConnection extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
    private static boolean isInternet = false;

    public static boolean isInternet() {
        return isInternet;
    }

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
            }else {
                isInternet = true;
            }
        }
}
