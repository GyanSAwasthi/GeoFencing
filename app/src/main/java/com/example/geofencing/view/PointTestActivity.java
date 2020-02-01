package com.example.geofencing.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.geofencing.R;
import com.example.geofencing.presenter.PointTestPresenter;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;

public class PointTestActivity extends AppCompatActivity implements IDisplayResult {
    EditText etLat, etLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_test);
        etLat = (EditText) findViewById(R.id.et_lat);
        etLong = (EditText) findViewById(R.id.et_long);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                readAndParseJsonFile();
            }
        });
    }

    /**
     * This method read and parse the json file.
     */
    private void readAndParseJsonFile() {
        AssetManager assetManager = getAssets();
        PointTestPresenter pointTestPresenter = new PointTestPresenter(this);
        Double latValue = new Double(etLat.getText().toString());
        Double lngValue = new Double(etLong.getText().toString());
        LatLng currentLocationLatLng = new LatLng(latValue, lngValue);
        pointTestPresenter.parseJson(pointTestPresenter.loadJsonFromAssets(assetManager), currentLocationLatLng);
    }

    @Override
    public void DisplayResponse(boolean isPresent) {
        if (isPresent) {
            Toast.makeText(this, "You are inside of GeoFence", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You are not inside of GeoFence", Toast.LENGTH_LONG).show();

        }
    }
}