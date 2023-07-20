package com.conways.linechart;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "zzzzz";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChart lineChart = (LineChart) this.findViewById(R.id.lineChart);
        BarChart barChart = (BarChart) this.findViewById(R.id.barChart);
        LineChart lineChart2 = (LineChart) this.findViewById(R.id.lineChart2);
        CandleChart candleChart = (CandleChart) this.findViewById(R.id.candleChart);
        SleepLineChart sleepLineChart = (SleepLineChart) this.findViewById(R.id.sleepLineChart);
        tv = (TextView) this.findViewById(R.id.textView);
        lineChart.setOnChartScrollChangedListener(new OnChartScrollChangedListener() {

            @Override
            public void onScrolling(int position, ChartModel chartModel) {
                tv.setText(String.valueOf(chartModel.getValue()));
            }

            @Override
            public void onPositionSelected(int position, ChartModel chartModel) {
                tv.setText(String.valueOf(chartModel.getValue()));
            }
        });

        barChart.setOnChartScrollChangedListener(new OnChartScrollChangedListener() {
            @Override
            public void onPositionSelected(int position, ChartModel chartModel) {
                tv.setText(String.valueOf(chartModel.getValue()));
            }

            @Override
            public void onScrolling(int position, ChartModel chartModel) {
                tv.setText(String.valueOf(chartModel.getValue()));
            }
        });

        List<ChartModel> prefix = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ChartModel chartModel = new ChartModel();
            chartModel.setIndex(String.valueOf(i));
            chartModel.setValue(0);
            prefix.add(chartModel);
        }

        List<ChartModel> list = new ArrayList<>();
        for (int i = 0; i < 48; i++) {
            ChartModel chartModel = new ChartModel();
            chartModel.setIndex(String.valueOf(i));
            chartModel.setTitle("date " + i);
            if (i % 2 == 0) {
                if (i == 2) {
                    chartModel.setValue(0);
                } else {
//                    chartModel.setValue(lineChart.getMax() / 5);
                    chartModel.setValue(40);
                }
            } else {
                chartModel.setValue(80);
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


        SleepModel sleepModel = new SleepModel();
        sleepModel.setStartTime("07:00");
        sleepModel.setEndTime("09:00");
        sleepModel.setList(list);
        sleepLineChart.updateData(sleepModel);


        List<CandleChartModel> candleChartModelList = new ArrayList<>();
        for (int i = 0; i < 288; i++) {
            CandleChartModel chartModel = new CandleChartModel();
            chartModel.setIndex(String.valueOf(i));
            if (i % 5 == 0) {
                chartModel.setLength((int) (candleChart.getMax() * 0.2));
                chartModel.setColor(Color.parseColor("#FFBAA76D"));
                chartModel.setType(CandleChartModel.Type.LOW);
            } else if (i % 4 == 0) {
                chartModel.setLength((int) (candleChart.getMax() * 0.3));
                chartModel.setColor(Color.parseColor("#FFFFEB3B"));
                chartModel.setType(CandleChartModel.Type.MEDIUM);
            } else if (i % 3 == 0) {
                chartModel.setLength((int) (candleChart.getMax() * 0.4));
                chartModel.setColor(Color.parseColor("#FFffffff"));
                chartModel.setMarkText("2");
                chartModel.setType(CandleChartModel.Type.HIGH);
            } else {
                chartModel.setLength((int) (candleChart.getMax() * 0.1));
                chartModel.setColor(Color.parseColor("#545353"));
                chartModel.setType(CandleChartModel.Type.INACTIVE);
            }
//            chartModel.setLength((int) (candleChart.getMax() * 0.4));
            candleChartModelList.add(chartModel);
        }

        candleChart.updateData(candleChartModelList);

    }

}
