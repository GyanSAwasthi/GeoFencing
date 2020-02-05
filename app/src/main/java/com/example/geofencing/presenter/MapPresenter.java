package com.example.geofencing.presenter;

import android.content.res.AssetManager;
import android.graphics.Color;

import com.example.geofencing.LocationsDataModel.Geometry;
import com.example.geofencing.LocationsDataModel.LocationsDM;
import com.example.geofencing.view.IDisplayResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.android.PolyUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is presenter class contains all the business logic.
 */
public class MapPresenter {

    /**
     * Interface ref.
     */
    IDisplayResult displayResult;

    /**
     * Initialization of array list which contains list of lat and long.
     */
    ArrayList<LatLng> locations = new ArrayList();

    /**
     * Double variable which contains the distance of point from GeoFence.
     */
   // double distance;

    /**
     * Constructor of presenter.
     *
     * @param displayResult ref of interface.
     */
    public MapPresenter(IDisplayResult displayResult) {
        this.displayResult = displayResult;
    }

    /**
     * This method reads the json file from assets folder.
     *
     * @param assetManager AssetManger reads the assets folder
     * @return text of json file.
     */
    public String loadJsonFromAssets(AssetManager assetManager) {
        String jsonFileTxt = null;
        try {
            InputStream inputStream = assetManager.open("hub2.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();
            jsonFileTxt = new String(buffer, "UTF-8");
            System.out.println(jsonFileTxt);
        } catch (
                IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonFileTxt;
    }

    /**
     * This method read and parse the json.
     *
     * @param jsonFileTxt text of json file.
     * @param currentLocationLatLng latlong input is given by user.
     */
    public ArrayList<LatLng> parseJson(String jsonFileTxt, LatLng currentLocationLatLng) {
        try {

            JSONObject obj = new JSONObject(jsonFileTxt);
            Gson gson = new Gson();
            LocationsDM locationsDM = null;
            locationsDM = gson.fromJson(String.valueOf(obj), LocationsDM.class);
            List<com.example.geofencing.LocationsDataModel.Geometry> arrayGeometry = locationsDM.getResults().getGeometry().get(0);
            for (int i = 0; i < arrayGeometry.size(); i++) {
                Geometry geometry = arrayGeometry.get(i);
                double lat = geometry.getLat();
                double lon = geometry.getLon();
                locations.add(new LatLng(lat, lon));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locations;
    }

    /**
     * Calculates the distance from the given latitude and longitude.
     *
     * @param currentLocationLatLng latitude and longitude value given by the user
     * @param locationsList         List of all latitude and longitude values.
     * @param accuracyValue accuracy value.
     */
    public boolean isLocationWithInArea(LatLng currentLocationLatLng, ArrayList<LatLng> locationsList, double accuracyValue) {
       double distance = calculateDistance(currentLocationLatLng, locationsList);
        if (distance > accuracyValue) {
            return false;
        }
        return true;
    }

    /**
     * @param currentLocationLatLng current location latitude and longitude
     * @param locationsList         list of LatLng
     */
    public double calculateDistance(LatLng currentLocationLatLng, List<LatLng> locationsList) {
       double distance = -1;

        for (int i = 0; i < locationsList.size(); i++) {
            LatLng point = locationsList.get(i);

            int segmentPoint = i + 1;
            if (segmentPoint >= locationsList.size()) {
                segmentPoint = 0;
            }

            double currentDistance = PolyUtil.distanceToLine(currentLocationLatLng, point, locationsList.get(segmentPoint));
            if (distance == -1 || currentDistance < distance) {
                distance = currentDistance / 1000;
            }
        }
        System.out.println("distance : " + distance+" k.m");
        return distance;
    }

    /**
     * This method draw geo fence on google map.
     * @param mMap  google map
     * @param locationsList List Of latitude and longitude.
     */
    public void drawGeoFence(GoogleMap mMap, ArrayList<LatLng> locationsList) {
        PolylineOptions options = new PolylineOptions().width(5).color(Color.RED).geodesic(true);
        for (int z = 0; z < locationsList.size(); z++) {
            LatLng point = locationsList.get(z);
            options.add(point);
        }
        mMap.addPolyline(options);
        CameraPosition cameraPosition;
        cameraPosition = new CameraPosition.Builder().target(locationsList.get(0)).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    /**
     * Display the response that the given lat long are inside GeoFence.
     * @param isPresent boolean value of response.
     */
    public void displayResponseDialog(boolean isPresent) {
         displayResult.DisplayResponse(isPresent);
    }
}
