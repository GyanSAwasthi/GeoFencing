
package com.example.geofencing.LocationsDataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Results implements Serializable, Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bounds")
    @Expose
    private Bounds bounds;
    @SerializedName("geometry")
    @Expose
    private List<List<Geometry>> geometry = new ArrayList<List<Geometry>>();
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("osm_type")
    @Expose
    private String osmType;
    @SerializedName("db_id")
    @Expose
    private String dbId;
    @SerializedName("hub_id")
    @Expose
    private String hubId;
    public final static Creator<Results> CREATOR = new Creator<Results>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        public Results[] newArray(int size) {
            return (new Results[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7921012619190006750L;

    protected Results(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.bounds = ((Bounds) in.readValue((Bounds.class.getClassLoader())));
        in.readList(this.geometry, (Geometry.class.getClassLoader()));
        this.tags = ((Tags) in.readValue((Tags.class.getClassLoader())));
        this.osmType = ((String) in.readValue((String.class.getClassLoader())));
        this.dbId = ((String) in.readValue((String.class.getClassLoader())));
        this.hubId = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Results() {
    }

    /**
     * 
     * @param hubId
     * @param dbId
     * @param bounds
     * @param geometry
     * @param osmType
     * @param id
     * @param type
     * @param tags
     */
    public Results(String type, Integer id, Bounds bounds, List<List<Geometry>> geometry, Tags tags, String osmType, String dbId, String hubId) {
        super();
        this.type = type;
        this.id = id;
        this.bounds = bounds;
        this.geometry = geometry;
        this.tags = tags;
        this.osmType = osmType;
        this.dbId = dbId;
        this.hubId = hubId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Results withType(String type) {
        this.type = type;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Results withId(Integer id) {
        this.id = id;
        return this;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Results withBounds(Bounds bounds) {
        this.bounds = bounds;
        return this;
    }

    public List<List<Geometry>> getGeometry() {
        return geometry;
    }

    public void setGeometry(List<List<Geometry>> geometry) {
        this.geometry = geometry;
    }

    public Results withGeometry(List<List<Geometry>> geometry) {
        this.geometry = geometry;
        return this;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Results withTags(Tags tags) {
        this.tags = tags;
        return this;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    public Results withOsmType(String osmType) {
        this.osmType = osmType;
        return this;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public Results withDbId(String dbId) {
        this.dbId = dbId;
        return this;
    }

    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }

    public Results withHubId(String hubId) {
        this.hubId = hubId;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(id);
        dest.writeValue(bounds);
        dest.writeList(geometry);
        dest.writeValue(tags);
        dest.writeValue(osmType);
        dest.writeValue(dbId);
        dest.writeValue(hubId);
    }

    public int describeContents() {
        return  0;
    }


    @Override
    public String toString() {
        return "Results{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", bounds=" + bounds +
                ", geometry=" + geometry +
                ", tags=" + tags +
                ", osmType='" + osmType + '\'' +
                ", dbId='" + dbId + '\'' +
                ", hubId='" + hubId + '\'' +
                '}';
    }
}
