package com.example.geofencing.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.geofencing.R;

/**
 * This class takes the input from user.
 */
public class PointTestActivity extends AppCompatActivity {
    /**
     * Location permission.
     */
    public static final int PERMISSIONS_REQUEST_LOCATION = 99;

    /**
     * Edit text for lat,long and accuracy.
     */
    EditText etLat, etLong, etAccuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_test);

        /**
         * checks the permission.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        final Context context = this;
        etLat = (EditText) findViewById(R.id.et_lat);
        etLong = (EditText) findViewById(R.id.et_long);
        etAccuracy = (EditText) findViewById(R.id.et_aquracy);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etLat.getText().toString()) && !TextUtils.isEmpty(etLong.getText().toString())
                        && !TextUtils.isEmpty(etAccuracy.getText().toString())) {
                    openAndDisplayMap();
                } else {
                    Toast.makeText(context, "Please enter proper latitude, longitude and accuracy!", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    /**
     * This method read and parse the json file.
     */
    private void openAndDisplayMap() {
        try {

            Double latValue = new Double(etLat.getText().toString());
            Double lngValue = new Double(etLong.getText().toString());
            Double accuracyValue = new Double(etAccuracy.getText().toString());
            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra("latValue", latValue);
            intent.putExtra("lngValue", lngValue);
            intent.putExtra("accuracyValue", accuracyValue);
            startActivity(intent);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the location permission.
     *
     * @return
     */
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                    }
                } else {
                    Toast.makeText(this, "permission denied",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
