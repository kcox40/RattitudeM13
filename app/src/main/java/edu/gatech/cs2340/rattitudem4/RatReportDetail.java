package edu.gatech.cs2340.rattitudem4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
* @author team 57
* @version v1.0
* this class displays the rat report details
*/

public class RatReportDetail extends AppCompatActivity {

    private RatReport selectedRatReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_report_detail);
        Bundle bundle = getIntent().getExtras();
        String ratReport = "";
        if (bundle != null) {
            ratReport = bundle.getString("RatReport");
        }
        String reportId = "";
        if (!"".equals(ratReport) && (ratReport != null)) {
            reportId = ratReport.substring(ratReport.lastIndexOf(" ")+1);
        }
        List<RatReport> ratReports = WelcomePageActivity.dbManager.getList();
        for (RatReport r : ratReports) {
            if (Integer.toString(r.getId()).equals(reportId)) {
                selectedRatReport = r;
            }
        }
        setFeilds();


    }

    /**
     * Closes view to return back to OptionsActivity in the stack
     * @param view the current view (RatReportDetail)
     */
    public void backBtn(View view) {
        finish();
    }

    private void setFeilds() {
        TextView idView = findViewById(R.id.id);
        TextView dateView = findViewById(R.id.date);
        TextView locationView = findViewById(R.id.locationType);
        TextView zipView = findViewById(R.id.zip);
        TextView addressView = findViewById(R.id.address);
        TextView cityView = findViewById(R.id.city);
        TextView boroughView = findViewById(R.id.borough);
        TextView latitudeView = findViewById(R.id.latitude);
        TextView longitudeView = findViewById(R.id.longitude);

        String id = "ID: " + Integer.toString(selectedRatReport.getId());
        idView.setText(id);
        String date = "Date: " + selectedRatReport.getDate();
        dateView.setText(date);
        String location = "Location Type: " + selectedRatReport.getLocationType();
        locationView.setText(location);
        String zip = "Incident Zip: " + Integer.toString(selectedRatReport.getIncidentZip());
        zipView.setText(zip);
        String address = "Address: " + selectedRatReport.getAddress();
        addressView.setText(address);
        String city = "City: " + selectedRatReport.getCity();
        cityView.setText(city);
        String borough = "Borough: " + selectedRatReport.getBorough();
        boroughView.setText(borough);
        String latitude = "Latitude: " + Double.toString(selectedRatReport.getLatitude());
        latitudeView.setText(latitude);
        String longitude = "Longitude: " + Double.toString(selectedRatReport.getLongitude());
        longitudeView.setText(longitude);
    }
}
