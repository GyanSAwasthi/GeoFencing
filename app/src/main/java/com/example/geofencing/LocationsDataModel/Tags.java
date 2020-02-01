
package com.example.geofencing.LocationsDataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tags implements Serializable, Parcelable
{

    @SerializedName("alt_name")
    @Expose
    private String altName;
    @SerializedName("amenity")
    @Expose
    private String amenity;
    @SerializedName("building")
    @Expose
    private String building;
    @SerializedName("denomination")
    @Expose
    private String denomination;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("religion")
    @Expose
    private String religion;
    @SerializedName("wikipedia")
    @Expose
    private String wikipedia;
    public final static Creator<Tags> CREATOR = new Creator<Tags>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Tags createFromParcel(Parcel in) {
            return new Tags(in);
        }

        public Tags[] newArray(int size) {
            return (new Tags[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2879979756912893975L;

    protected Tags(Parcel in) {
        this.altName = ((String) in.readValue((String.class.getClassLoader())));
        this.amenity = ((String) in.readValue((String.class.getClassLoader())));
        this.building = ((String) in.readValue((String.class.getClassLoader())));
        this.denomination = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.religion = ((String) in.readValue((String.class.getClassLoader())));
        this.wikipedia = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tags() {
    }

    /**
     * 
     * @param amenity
     * @param name
     * @param altName
     * @param wikipedia
     * @param building
     * @param denomination
     * @param religion
     */
    public Tags(String altName, String amenity, String building, String denomination, String name, String religion, String wikipedia) {
        super();
        this.altName = altName;
        this.amenity = amenity;
        this.building = building;
        this.denomination = denomination;
        this.name = name;
        this.religion = religion;
        this.wikipedia = wikipedia;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public Tags withAltName(String altName) {
        this.altName = altName;
        return this;
    }

    public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public Tags withAmenity(String amenity) {
        this.amenity = amenity;
        return this;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Tags withBuilding(String building) {
        this.building = building;
        return this;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Tags withDenomination(String denomination) {
        this.denomination = denomination;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tags withName(String name) {
        this.name = name;
        return this;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Tags withReligion(String religion) {
        this.religion = religion;
        return this;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public Tags withWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(altName);
        dest.writeValue(amenity);
        dest.writeValue(building);
        dest.writeValue(denomination);
        dest.writeValue(name);
        dest.writeValue(religion);
        dest.writeValue(wikipedia);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "altName='" + altName + '\'' +
                ", amenity='" + amenity + '\'' +
                ", building='" + building + '\'' +
                ", denomination='" + denomination + '\'' +
                ", name='" + name + '\'' +
                ", religion='" + religion + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                '}';
    }
}
