package edu.gatech.cs2340.rattitudem4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * displays the chart of ratReports
 */
public class RatChartActivity extends AppCompatActivity {

    private final Collection<Integer> monthRange = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_chart);
        Intent intent = getIntent();
        String startDateString = intent.getStringExtra("dateOne");
        String endDateString = intent.getStringExtra("dateTwo");
        getMonthRange(startDateString, endDateString);
        Map<Integer, Integer> months = populateChart(startDateString, endDateString);


        LineChart lineChart = findViewById(R.id.chart);

        List<Entry> entries = convertDataSetToEntry(months);

        LineDataSet dataSet = new LineDataSet(entries, "# of Rat Reports");

        LineData data = new LineData(dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); //

        dataSet.setDrawFilled(true);

        lineChart.setData(data);
        final int DURATION_MILL = 5000;
        lineChart.animateY(DURATION_MILL);


        lineChart.getDescription().setText("Rat Reports Per Month");
    }

    /**
     * Convert the set to entries in the chart
     * @param data  the map of data to convert to entries
     * @return returns list of entries to add to chart
     */
    private List<Entry> convertDataSetToEntry(Map<Integer, Integer> data) {
        List<Entry> entries = new ArrayList<>();

        for (int i : monthRange) {
            int month = i + 1;
            int value = 0;
            if (data.get(month) != null) {
                value = data.get(month);
            }
            entries.add(new Entry(month, value));
        }

        return entries;
    }


    /**
     * Button to return to Welcome Activity
     * @param view current view
     */
    public void back(View view) {
        finish();
    }

    private void getMonthRange(String start, String end) {

        String[] startDateParts = start.split("/");
        int startMonthInt = Integer.parseInt(startDateParts[0]);


        String[] endDateParts = end.split("/");
        int endMonthInt = Integer.parseInt(endDateParts[0]);

        for (int i = startMonthInt; i < (endMonthInt + 1); i++) {
            monthRange.add(i - 1);
        }
    }

    private Map<Integer,Integer> populateChart(String start, String end) {
        List<RatReport> reports = WelcomePageActivity.dbManager
                .getDateRange(start, end);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a", Locale.US);
        Map<Integer, Integer> months = new HashMap<>();

        for (RatReport r : reports) {
            String date = r.getDate();
            String[] dateParts = date.split("/");
            int month = Integer.parseInt(dateParts[0]);
            Integer oldCount = months.get(month);
            if ( oldCount == null ) {
                oldCount = 0;
            }
            months.put(month, oldCount + 1);
        }
        return months;
    }
}
