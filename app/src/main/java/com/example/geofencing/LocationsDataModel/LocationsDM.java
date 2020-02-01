
package com.example.geofencing.LocationsDataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationsDM implements Serializable, Parcelable
{

    @SerializedName("results")
    @Expose
    private Results results;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<LocationsDM> CREATOR = new Creator<LocationsDM>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LocationsDM createFromParcel(Parcel in) {
            return new LocationsDM(in);
        }

        public LocationsDM[] newArray(int size) {
            return (new LocationsDM[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1481288971172564072L;

    protected LocationsDM(Parcel in) {
        this.results = ((Results) in.readValue((Results.class.getClassLoader())));
        this.success = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public LocationsDM() {
    }

    /**
     * 
     * @param success
     * @param message
     * @param results
     */
    public LocationsDM(Results results, String success, String message) {
        super();
        this.results = results;
        this.success = success;
        this.message = message;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public LocationsDM withResults(Results results) {
        this.results = results;
        return this;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public LocationsDM withSuccess(String success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocationsDM withMessage(String message) {
        this.message = message;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(results);
        dest.writeValue(success);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return "LocationsDM{" +
                "results=" + results +
                ", success='" + success + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
