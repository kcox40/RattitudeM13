package edu.gatech.cs2340.rattitudem4;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Carlos Priddy on 10/13/2017.
 * @author team 57
 * @version v1.0
 */

public class RatReportManager {
    private final List<RatReport> ratReports;
    private final DatabaseReference ratDBRef;
    private final Query ratQuery;
    private final Query lastItem;
    private int lastIndex;
    private RatReport lastReport;

    /**
     * manages the rat report data base
     */
    public RatReportManager() {
        //Get Database Reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ratDBRef = database.getReference();

        //Create internal database
        ratReports = new ArrayList<>();

        //Set Query objects
        final int NUM_OF_PULL = 500;
        ratQuery = ratDBRef.orderByKey().limitToLast(NUM_OF_PULL);
        lastItem = ratDBRef.orderByKey().limitToLast(1);

        //Initialize Query by attaching ValueEventListener
        queryInit();
    }
    /**
     * Populates the Rat report database by attaching a ValueEventListener to
     * the query
     */
    private void queryInit() {
        ratQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot reportSnapshot: dataSnapshot.getChildren()) {
                    RatReport report = new RatReport();
                    report.setCity((String) reportSnapshot.child("City").getValue());
                    report.setAddress((String) reportSnapshot.child("Incident Address").getValue());
                    report.setId(Integer
                            .parseInt((String) reportSnapshot.child("Unique Key").getValue()));
                    report.setBorough((String) reportSnapshot.child("Borough").getValue());
                    report.setDate((String) reportSnapshot.child("Created Date").getValue());
                    if (!"".equals(( reportSnapshot.child("Incident Zip").getValue()))) {
                        report.setIncidentZip(Integer.parseInt((String) reportSnapshot
                                .child("Incident Zip").getValue()));
                    }
                    report.setLocationType((String) reportSnapshot
                            .child("Address Type").getValue());
                    if (!"".equals(( reportSnapshot.child("Latitude").getValue()))) {
                        report.setLatitude(Double.parseDouble((String) reportSnapshot
                                .child("Latitude").getValue()));
                    }

                    if (!"".equals((reportSnapshot.child("Longitude").getValue()))) {
                        report.setLongitude(Double.parseDouble((String) reportSnapshot
                                .child("Longitude").getValue()));
                    }

                    ratReports.add(report);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lastItem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot reportSnapshot: dataSnapshot.getChildren()) {
                    RatReport report = new RatReport();
                    report.setCity((String) reportSnapshot.child("City").getValue());
                    report.setAddress((String) reportSnapshot.child("Incident Address").getValue());
                    report.setId(Integer.parseInt((String) reportSnapshot
                            .child("Unique Key").getValue()));
                    report.setBorough((String) reportSnapshot.child("Borough").getValue());
                    report.setDate((String) reportSnapshot.child("Created Date").getValue());
                    if (!"".equals(( reportSnapshot.child("Incident Zip").getValue()))) {
                        report.setIncidentZip(Integer.parseInt((String) reportSnapshot
                                .child("Incident Zip").getValue()));
                    }
                    report.setLocationType((String) reportSnapshot
                            .child("Address Type").getValue());
                    if (!"".equals((reportSnapshot.child("Latitude").getValue()))) {
                        report.setLatitude(Double.parseDouble((String) reportSnapshot
                                .child("Latitude").getValue()));
                    }

                    if (!"".equals((reportSnapshot.child("Longitude").getValue()))) {
                        report.setLongitude(Double.parseDouble((String) reportSnapshot
                                .child("Longitude").getValue()));
                    }

                    lastIndex = (Integer.parseInt(reportSnapshot.getKey()));
                    lastReport = report;
                }
                Log.d("Last Item", lastReport.toString() + " " + lastIndex);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Prints full string representations of all objects in the Query
     * prints to Log.d for debug purposes
     */
    public void printDatabase() {
        Log.d("Array Size", String.valueOf(ratReports.size()));
        for (RatReport report : ratReports) {
            Log.d("Printing", report.toString());
        }
    }

    /**
     * Returns a list of Rat Reports
     * @return A list of Rat Reports
     */
    public List<RatReport> getList() {
        return ratReports;
    }

    /**
     * Returns full string representations for all rat reports in Query
     * @return List of String representations for objects in internal database
     */
    public List<String> getStringList() {
        List<String> stringList = new ArrayList<>();
        for (RatReport report : ratReports) {
            stringList.add(report.toString());
        }
        return stringList;
    }

    /**
     * Returns a short string representation of objects in internal DB
     * @return short string representation
     */
    public Collection<String> getShortStringList() {
        Collection<String> shortList = new ArrayList<>();
        for (RatReport report : ratReports) {
            String line = "";
            line = line + report.getDate() + report.getBorough() +" " +  report.getId();
            shortList.add(line);
        }
        return shortList;
    }
    /** 
     * constructor for a new rat report  
     * @param date of the rat report 
     * @param locationType where the rat is 
     * @param incidentZip of the location 
     * @param address street address of the rat 
     * @param city of the rat sighting 
     * @param borough of rat sighting 
     * @param latitude of sighting 
     * @param longitude of sighting 
     * @return the most recent rat report
     */ 
    public int addNewRatReport(String date, String locationType,
                               int incidentZip, String address, String city,
                               String borough, Double latitude, Double longitude) {

        ratDBRef.child(String.valueOf(lastIndex + 1)).setValue(null);
        Map<String, Object> updates = new HashMap<>();
        updates.put(String.valueOf(lastIndex + 1) + "/Created Date", date);
        updates.put(String.valueOf(lastIndex + 1) + "/City", city);
        updates.put(String.valueOf(lastIndex + 1) + "/Incident Address", address);
        updates.put(String.valueOf(lastIndex + 1) + "/Unique Key",
                String.valueOf(lastReport.getId() + 1));
        updates.put(String.valueOf(lastIndex + 1) + "/Borough", borough);
        updates.put(String.valueOf(lastIndex + 1) + "/Incident Zip", String.valueOf(incidentZip));
        updates.put(String.valueOf(lastIndex + 1) + "/Address Type", locationType);
        updates.put(String.valueOf(lastIndex + 1) + "/Latitude", String.valueOf(latitude));
        updates.put(String.valueOf(lastIndex + 1) + "/Longitude", String.valueOf(longitude));
        ratDBRef.updateChildren(updates);
        return lastReport.getId() + 1;
    }
    /**
     * returns the most recent report
     * @return null
     */
    public RatReport getLastReport() {
        return null;
    }

    /**
     * Returns a list of rat reports for a specific date range EXCLUSIVE
     * @param beginning The beginning of the date range
     * @param ending The ending of the date range
     * @return a list of ratReports between the dates entered
     */
    public List<RatReport> getDateRange(String beginning, String ending) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US);
        Date startDate = null;
        Date endDate = null;
        Date dateOfReport = null;
        try {
            startDate = df.parse(beginning);
            endDate = df.parse(ending);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<RatReport> datedList = new ArrayList<>();

        for (RatReport r : ratReports) {
            try {
                dateOfReport = df.parse(r.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if ((dateOfReport != null) && (startDate != null)) {
                if (dateOfReport.after(startDate) && dateOfReport.before(endDate)) {
                    datedList.add(r);
                }
            }
        }

        return datedList;
    }
}

