
package com.example.geofencing.LocationsDataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bounds implements Serializable, Parcelable
{

    @SerializedName("minlat")
    @Expose
    private Double minlat;
    @SerializedName("minlon")
    @Expose
    private Double minlon;
    @SerializedName("maxlat")
    @Expose
    private Double maxlat;
    @SerializedName("maxlon")
    @Expose
    private Double maxlon;
    public final static Creator<Bounds> CREATOR = new Creator<Bounds>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Bounds createFromParcel(Parcel in) {
            return new Bounds(in);
        }

        public Bounds[] newArray(int size) {
            return (new Bounds[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2647924420110739621L;

    protected Bounds(Parcel in) {
        this.minlat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.minlon = ((Double) in.readValue((Double.class.getClassLoader())));
        this.maxlat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.maxlon = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bounds() {
    }

    /**
     * 
     * @param minlon
     * @param maxlat
     * @param minlat
     * @param maxlon
     */
    public Bounds(Double minlat, Double minlon, Double maxlat, Double maxlon) {
        super();
        this.minlat = minlat;
        this.minlon = minlon;
        this.maxlat = maxlat;
        this.maxlon = maxlon;
    }

    public Double getMinlat() {
        return minlat;
    }

    public void setMinlat(Double minlat) {
        this.minlat = minlat;
    }

    public Bounds withMinlat(Double minlat) {
        this.minlat = minlat;
        return this;
    }

    public Double getMinlon() {
        return minlon;
    }

    public void setMinlon(Double minlon) {
        this.minlon = minlon;
    }

    public Bounds withMinlon(Double minlon) {
        this.minlon = minlon;
        return this;
    }

    public Double getMaxlat() {
        return maxlat;
    }

    public void setMaxlat(Double maxlat) {
        this.maxlat = maxlat;
    }

    public Bounds withMaxlat(Double maxlat) {
        this.maxlat = maxlat;
        return this;
    }

    public Double getMaxlon() {
        return maxlon;
    }

    public void setMaxlon(Double maxlon) {
        this.maxlon = maxlon;
    }

    public Bounds withMaxlon(Double maxlon) {
        this.maxlon = maxlon;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(minlat);
        dest.writeValue(minlon);
        dest.writeValue(maxlat);
        dest.writeValue(maxlon);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return "Bounds{" +
                "minlat=" + minlat +
                ", minlon=" + minlon +
                ", maxlat=" + maxlat +
                ", maxlon=" + maxlon +
                '}';
    }
}
