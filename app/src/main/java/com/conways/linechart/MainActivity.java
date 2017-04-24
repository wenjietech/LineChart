package com.conways.linechart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ScrollLisenter {

    private String TAG = "zzzzz";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChart lineChart = (LineChart) this.findViewById(R.id.lineChart);
        lineChart.setScrollLisenter(this);
        tv=(TextView)this.findViewById(R.id.textView);
    }

    @Override
    public void scroll(ChartModel chartModel) {
        tv.setText(chartModel.getValue()+"");
    }
}
