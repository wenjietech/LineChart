package com.conways.linechart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ScrollListener {

    private String TAG = "zzzzz";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChart lineChart = (LineChart) this.findViewById(R.id.lineChart);
        BarChart barChart = (BarChart) this.findViewById(R.id.barChart);
        LineChart lineChart2 = (LineChart) this.findViewById(R.id.lineChart2);
        lineChart.setScrollListener(this);
        tv = (TextView) this.findViewById(R.id.textView);

        List<ChartModel> prefix = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ChartModel chartModel = new ChartModel();
            chartModel.setIndex(String.valueOf(i));
            chartModel.setValue(0);
            prefix.add(chartModel);
        }

        List<ChartModel> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            ChartModel chartModel = new ChartModel();
            chartModel.setIndex(String.valueOf(i));
            if (i % 2 == 0) {
                if (i == 2) {
                    chartModel.setValue(0);
                } else {
                    chartModel.setValue(lineChart.getMax() / 5);
                }
            } else {
                chartModel.setValue(lineChart.getMax() * 4 / 5);
            }
            list.add(chartModel);
        }

        List<ChartModel> suffix = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ChartModel chartModel = new ChartModel();
            chartModel.setIndex(String.valueOf(i));
            chartModel.setValue(0);
            suffix.add(chartModel);
        }

        lineChart.updateData(list, prefix, suffix);
        barChart.updateData(list, prefix, suffix);
        lineChart2.updateData(list, prefix, suffix);
    }

    @Override
    public void scroll(ChartModel chartModel) {
        tv.setText(chartModel.getValue() + "");
    }
}
