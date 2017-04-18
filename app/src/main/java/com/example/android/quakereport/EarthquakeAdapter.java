package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jacquesjoky on 1/23/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        // Check if the existing view is being reused, otherwise inflate the view
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        // Get the {@link Earthquake} object locatted at this position of the list
        Earthquake currentEarthquake = getItem(position);

        // Get the magnitude of the current earthquake
        double magnitude = currentEarthquake.getMagnitude();
        // Create an appropriate formatter for the magnitude, i.e. with only one decimal
        DecimalFormat formatter = new DecimalFormat("0.0");
        // Format the magnitude
        String formattedMagnitude = formatter.format(magnitude);
        // Find the TextView in the earthquake_list_item.xmlst_item.xml layout with the ID magnitude
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Display the magnitude of the current earthquake in this TextView
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Get the location of the earthquake
        String location = currentEarthquake.getLocation();
        // Declare the variable for the location offset
        String locationOffset;
        // Declare the variable for the primary location
        String primaryLocaion;

        // if the location contains " of " as in "88km of Yelizovo, Russia"
        if(location.contains(LOCATION_SEPARATOR)) {
            // then the location is splitted in two parts around the separator " of "
            String [] parts = location.split(LOCATION_SEPARATOR);
            // the first part begins at the first index of the original string
            // and finishes after the separator " of "
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // the second part begins at the index after the separator
            // and finishes at the end of the original string
            primaryLocaion = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.location_offset_near_the);
            primaryLocaion = location;
        }

        // Find the TextView in the earthquake_list_item.xmlst_item.xml layout with the ID place
        TextView locationFirstLineTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Display the location of the current earthquake in this TextView
        locationFirstLineTextView.setText(locationOffset);

        // Find the TextView in the earthquake_list_item.xmlst_item.xml layout with the ID place
        TextView locationSecondLieTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Display the location of the current earthquake in this TextView
        locationSecondLieTextView.setText(primaryLocaion);

        // Create a date object from the the Unix time of the current earthquake
        Date date = new Date(currentEarthquake.getTime());

        // Find the TextView in the earthquake_list_iteme_list_item.xml layout with the ID time
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Create a date format object to display the date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        // Get the formated date as a string
        String formattedDate = dateFormatter.format(date);
        // Display the date of the current earthquake in this TextView
        dateTextView.setText(formattedDate);

        // Find the TextView in the earthquake_list_iteme_list_item.xml layout with the ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Create a date format object to display the date
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        // Get the formated date as a string
        String formattedTime = timeFormatter.format(date);
        // Display the date of the current earthquake in this TextView
        timeTextView.setText(formattedTime);

        // Return the list item view showing now the appropriate data
        return listItemView;
    }

    public int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
