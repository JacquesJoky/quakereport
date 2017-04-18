package com.example.android.quakereport;

/**
 * Created by jacquesjoky on 1/23/17.
 */

public class Earthquake {

    /** Magnitude of the earthquake */
    private double mMagnitude;

    /** Location of the earthquake */
    private String mLocation;

    /** UNIX time of the earthquake */
    private long mTime;

    /** URL of the earthquake detailed USGS webpage*/
    private String mUrl;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude is the magnitude of the earthquake
     * @param location is the city location near the earthquake
     * @param time is the UNIX time the earthquake happened
     * @param url is the URL to the earthquake detailed USGS webpage
     */
    public Earthquake(double magnitude, String location, long time, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTime = time;
        this.mUrl = url;
    }

    /**
     * Returns the magnitude of the earthquake.
     * @return the magnitude of the earthquake
     */
    public double getMagnitude() {
        return mMagnitude;
    }

    /**
     * Returns the location of the earthquake
     * @return the location of the earthquake
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Returns the UNIX time of the earthquake
     * @return the UNIX time of the earthquake
     */
    public long getTime() {
        return mTime;
    }

    /**
     * Returns the URL to the earthquake detailed USGS webpage
     * @return the URL to the earthquake detailed USGS webpage
     */
    public String getUrl() {
        return mUrl;
    }
}
