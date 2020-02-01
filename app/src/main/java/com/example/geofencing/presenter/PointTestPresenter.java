package com.example.geofencing.presenter;

import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;

import com.example.geofencing.LocationsDataModel.Geometry;
import com.example.geofencing.LocationsDataModel.LocationsDM;
import com.example.geofencing.view.IDisplayResult;
import com.example.geofencing.view.PointTestActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This is presenter class contains all the business logic.
 */
public class PointTestPresenter {

    /**
     * Interface ref.
     */
    IDisplayResult displayResult;

    /**
     * Constructor of presenter.
     *
     * @param displayResult ref of interface.
     */
    public PointTestPresenter(IDisplayResult displayResult) {
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
     * @param jsonFileTxt           text of json file.
     * @param currentLocationLatLng latlong input is given by user.
     */
    public void parseJson(String jsonFileTxt, LatLng currentLocationLatLng) {
        try {
            ArrayList<LatLng> locations = new ArrayList();
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
            isLocationWithInArea(currentLocationLatLng, locations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the distacne from the given lat and long.
     *
     * @param currentLocationLatLng latitude and longitude value given by the user
     * @param locationsList         List of all latitude and longitude values.
     */
    private void isLocationWithInArea(LatLng currentLocationLatLng, ArrayList<LatLng> locationsList) {
        LatLng nearestPoint = findNearestPoint(currentLocationLatLng, locationsList);

        double distanceAccuracy = SphericalUtil.computeDistanceBetween(currentLocationLatLng, nearestPoint);
        if (distanceAccuracy > 60) {
            displayResult.DisplayResponse(false);
        } else {
            displayResult.DisplayResponse(true);
        }
    }

    /**
     * Calculates the nearest distance.
     *
     * @param currentLocationLatLng curent Latitude and longitude.
     * @param locationsList         List of latitude and longitude.
     * @return Minimum distance
     */
    private LatLng findNearestPoint(LatLng currentLocationLatLng, List<LatLng> locationsList) {
        double distance = -1;
        LatLng minimumDistancePoint = currentLocationLatLng;

        if (currentLocationLatLng == null || locationsList == null) {
            return minimumDistancePoint;
        }

        for (int i = 0; i < locationsList.size(); i++) {
            LatLng point = locationsList.get(i);

            int segmentPoint = i + 1;
            if (segmentPoint >= locationsList.size()) {
                segmentPoint = 0;
            }

            double currentDistance = PolyUtil.distanceToLine(currentLocationLatLng, point, locationsList.get(segmentPoint));
            if (distance == -1 || currentDistance < distance) {
                distance = currentDistance;
                minimumDistancePoint = findNearestPoint(currentLocationLatLng, point, locationsList.get(segmentPoint));
            }
        }

        return minimumDistancePoint;
    }

    /**
     * This calculate the nearest point and Based on `distanceToLine` method.
     *
     * @param p     LatLng.
     * @param start LatLng.
     * @param end   LatLng.
     * @return mimimum distance point.
     */
    private LatLng findNearestPoint(final LatLng p, final LatLng start, final LatLng end) {
        if (start.equals(end)) {
            return start;
        }

        final double s0lat = Math.toRadians(p.latitude);
        final double s0lng = Math.toRadians(p.longitude);
        final double s1lat = Math.toRadians(start.latitude);
        final double s1lng = Math.toRadians(start.longitude);
        final double s2lat = Math.toRadians(end.latitude);
        final double s2lng = Math.toRadians(end.longitude);

        double s2s1lat = s2lat - s1lat;
        double s2s1lng = s2lng - s1lng;
        final double u = ((s0lat - s1lat) * s2s1lat + (s0lng - s1lng) * s2s1lng)
                / (s2s1lat * s2s1lat + s2s1lng * s2s1lng);
        if (u <= 0) {
            return start;
        }
        if (u >= 1) {
            return end;
        }

        return new LatLng(start.latitude + (u * (end.latitude - start.latitude)),
                start.longitude + (u * (end.longitude - start.longitude)));
    }
}
