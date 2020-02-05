package com.example.geofencing.view;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.example.geofencing.Constants;
import com.example.geofencing.R;
import com.example.geofencing.presenter.MapPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * This class is the map class which displays the map.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback, IDisplayResult {
    /**
     * Google map reference.
     */
    private GoogleMap mMap;

    /**
     * ArrayList which contains the list of latitude and longitude.
     */
    ArrayList<LatLng> locations = new ArrayList();

    /**
     * Reference of the Presenter.
     */
    MapPresenter mapPresenter;

    /**
     * LatLng given by the user.
     */
    LatLng currentLocationLatLng;

    /**
     * Accuracy value given by user.
     */
    double accuracyValue;

    /**
     * Alert dialog which displays the response.
     */
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        builder = new AlertDialog.Builder(this);
        AssetManager assetManager = getAssets();
        mapPresenter = new MapPresenter(this);
        double latValue = getIntent().getDoubleExtra("latValue", 0.0);
        double lngValue = getIntent().getDoubleExtra("lngValue", 0.0);
        accuracyValue = getIntent().getDoubleExtra("accuracyValue", 0.0);
        currentLocationLatLng = new LatLng(latValue, lngValue);
        locations = mapPresenter.parseJson(mapPresenter.loadJsonFromAssets(assetManager), currentLocationLatLng);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapPresenter.drawGeoFence(mMap, locations);
        boolean isPresent = mapPresenter.isLocationWithInArea(currentLocationLatLng, locations, accuracyValue);
        mapPresenter.displayResponseDialog(isPresent);

    }

    @Override
    public void DisplayResponse(boolean isPresent) {
        if (isPresent) {
            displayDialog(Constants.DIALOG_SUCCESS_MESSAGE);
        } else {
            displayDialog(Constants.DIALOG_FAILURE_MESSAGE);

        }
    }

    /**
     * This method displays the response on the dialog.
     *
     * @param Message response message.
     */
    public void displayDialog(String Message) {
        builder.setMessage(Message)
                .setCancelable(false)
                .setPositiveButton(R.string.exit_map, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();

                    }
                })
                .setNegativeButton(R.string.dont_exit_map, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle(R.string.dialog_message);
        alert.show();
    }
}
