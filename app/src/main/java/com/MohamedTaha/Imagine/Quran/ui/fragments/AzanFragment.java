package com.MohamedTaha.Imagine.Quran.ui.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.Quran.APIServices;
import com.MohamedTaha.Imagine.Quran.R;
import com.MohamedTaha.Imagine.Quran.helper.AppController;
import com.MohamedTaha.Imagine.Quran.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.Quran.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.Quran.helper.util.PlaybackStatus;
import com.MohamedTaha.Imagine.Quran.model.azan.Azan;
import com.MohamedTaha.Imagine.Quran.receiver.ConnectivityReceiver;
import com.MohamedTaha.Imagine.Quran.receiver.NoInternetReceiver;
import com.MohamedTaha.Imagine.Quran.service.MediaPlayerService;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MohamedTaha.Imagine.Quran.RetrofitClient.getRetrofit;
import static com.MohamedTaha.Imagine.Quran.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.BROADCAST_NOT_CONNECTION;
import static com.MohamedTaha.Imagine.Quran.service.MediaPlayerService.BROADCAST_NOT_INTERNET;
import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.FragmentListSoundLLControlMedia;
import static com.MohamedTaha.Imagine.Quran.ui.activities.ListSoundReader.ListSoundReaderLoadingIndicator;

/**
 * A simple {@link Fragment} subclass.
 */
public class AzanFragment extends Fragment {
    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";
   // String url = "https://muslimsalat.com/6.json?key=20d61be5c481c8dd4c72977a34526fb7";
   APIServices apiServices;

    String url = "http://api.aladhan.com/v1/calendar?latitude=24.7895324&longitude=46.7782341&method=4";
    @BindView(R.id.AzanFragment_TV_Elfagr)
    TextView AzanFragmentTVElfagr;
    @BindView(R.id.AzanFragment_TV_Elshroq)
    TextView AzanFragmentTVElshroq;
    @BindView(R.id.AzanFragment_TV_Alzohr)
    TextView AzanFragmentTVAlzohr;
    @BindView(R.id.AzanFragment_TV_AlAsr)
    TextView AzanFragmentTVAlAsr;
    @BindView(R.id.AzanFragment_TV_Almagrab)
    TextView AzanFragmentTVAlmagrab;
    @BindView(R.id.AzanFragment_TV_Alesha)
    TextView AzanFragmentTVAlesha;
    @BindView(R.id.AzanFragment_TV_City)
    TextView AzanFragmentTVCity;
    @BindView(R.id.AzanFragment_BT_GetLocation)
    Button AzanFragmentBTGetLocation;
    private FusedLocationProviderClient client;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 12;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private ConnectivityReceiver connectivityReceiver = null;
    private NoInternetReceiver noInternetReceiver = null;
    private static boolean isInternet = false;
    ProgressDialog pDialog ;
    boolean isConnected;
    public static boolean isInternet() {
        return isInternet;
    }

    public AzanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_azan, container, false);
        ButterKnife.bind(this, view);
        connectivityReceiver = new ConnectivityReceiver();
        noInternetReceiver = new NoInternetReceiver();
        apiServices = getRetrofit().create(APIServices.class);
        registerNoConnection();
        //Listen for not Internet
        registerNoInternet();
        //For check Location Enable or not
         pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();
         isConnected = NetworkConnection.networkConnectivity(getActivity());
        NoInternetConnection noInternetConnection = new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        if (!isConnected){
            //send BroadcastReceiver to the Service -> Not Connection
            Intent broadcastIntent = new Intent(BROADCAST_NOT_CONNECTION);
            getActivity().sendBroadcast(broadcastIntent);
            pDialog.dismiss();
        }
      else {
//            ProgressDialog pDialog = new ProgressDialog(getActivity());
//            pDialog.setMessage("Loading...");
//            pDialog.show();
            getLocationPermission();
           // getDeviceLocation();
        //    getTimeAzan();

        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connectivityReceiver != null) {
            getActivity().unregisterReceiver(connectivityReceiver);
        }
        if (noInternetReceiver != null) {
            getActivity().unregisterReceiver(noInternetReceiver);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                //If request is an celled, the result arrays are empty.
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getDeviceLocation();
                }
            }
        }
    }
private void getTimeAzan(String latitude, String longitude){

    Call<Azan> azanCall = apiServices.getAzan(latitude,longitude,4);
    azanCall.enqueue(new Callback<Azan>() {
        @Override
        public void onResponse(Call<Azan> call, Response<Azan> response) {
            Azan azan = response.body();
            try {
                if (azan.getCode() == 200){

                    //Set this data to textViews
                    AzanFragmentTVElfagr.setText(azan.getData().get(20).getTimings().getFajr());
                    AzanFragmentTVElshroq.setText(azan.getData().get(20).getTimings().getSunset());
                    AzanFragmentTVAlzohr.setText(azan.getData().get(20).getTimings().getDhuhr());
                    AzanFragmentTVAlAsr.setText(azan.getData().get(20).getTimings().getAsr());
                    AzanFragmentTVAlmagrab.setText(azan.getData().get(20).getTimings().getMaghrib());
                    AzanFragmentTVAlesha.setText(azan.getData().get(20).getTimings().getIsha());
                    AzanFragmentTVCity.setText(azan.getData().get(20).getDate().getReadable());
                }else {
                    Toast.makeText(getContext(), "No ", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                pDialog.hide();


            }
            pDialog.hide();

        }
        @Override
        public void onFailure(Call<Azan> call, Throwable t) {
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            pDialog.hide();

        }
    });

}
    private void getDeviceLocation() {
        /* Get the best and most recent location of the device, which may be null in rare
        cases when a location isnotavailable
         */
        //Construct a FusedLocationProviderClient
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        try {
            // if (mLocationPermissionsGranted) {
            Task location = client.getLastLocation();
            location.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // Toast.makeText(getActivity(), "find location!", Toast.LENGTH_SHORT).show();
                        //Set the map's camera position to the current location of the device.
                        //  mLastKnownLocation = task.getResult();
                        Location location = (Location) task.getResult();
                        double lat = location.getLatitude();
                        double lang = location.getLongitude();
                        getTimeAzan(String.valueOf(lat),String.valueOf(lang));
                        AzanFragmentTVCity.setText(": " + String.valueOf(lat) + String.valueOf(lang));
                    } else {


                        Toast.makeText(getActivity(), "Error" + location.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // }
        } catch (SecurityException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    private void registerNoConnection() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(MediaPlayerService.BROADCAST_NOT_CONNECTION);
        getActivity().registerReceiver(connectivityReceiver, filter);
    }

    private void registerNoInternet() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(BROADCAST_NOT_INTERNET);
        getActivity().registerReceiver(noInternetReceiver, filter);
    }

    public void getLocationPermission() {
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionChecker.checkSelfPermission(getActivity(), FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    PermissionChecker.checkSelfPermission(getActivity(), COURSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // mLocationPermissionsGranted = true;
                ActivityCompat.requestPermissions(getActivity(),
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            } else {

                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getDeviceLocation();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showGPSDosable();
                    }
                }
            }
        }

    }

    private void showGPSDosable() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage("GPS is disable in your device. would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page to Enable GPS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(callGPSSettingIntent, LOCATION_PERMISSION_REQUEST_CODE);
                        //  startActivity(callGPSSettingIntent);
                    }
                });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    @OnClick(R.id.AzanFragment_BT_GetLocation)
    public void onViewClicked() {
        isConnected = NetworkConnection.networkConnectivity(getActivity());
        if (!isConnected){
            //send BroadcastReceiver to the Service -> Not Connection
            Intent broadcastIntent = new Intent(BROADCAST_NOT_CONNECTION);
            getActivity().sendBroadcast(broadcastIntent);
        }else {
            getLocationPermission();

        }
    }
}




