package com.conways.linechart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        initCombineChart();


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
            if (i == 253) {
                chartModel.setLength((int) (candleChart.getMax() * 0.1));
                chartModel.setColor(Color.parseColor("#545353"));
                chartModel.setType(CandleChartModel.Type.INACTIVE);
                chartModel.setHighlight(true);
            } else {
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
            }
//            chartModel.setLength((int) (candleChart.getMax() * 0.4));
            candleChartModelList.add(chartModel);
        }

        candleChart.updateData(candleChartModelList);

    }


    private void initCombineChart(){
        CombineLineChart combineLineChart = (CombineLineChart) this.findViewById(R.id.combineChart);
        Button btnHigh = (Button) this.findViewById(R.id.btn_high);
        Button btnMed = (Button) this.findViewById(R.id.btn_med);
        Button btnLow = (Button) this.findViewById(R.id.btn_low);
        //For CombineLineChart
        CombineModel combineModel = new CombineModel();
        combineModel.setHigh(70);
        combineModel.setMedium(30);
        List<CombineModel.Section> sections = new ArrayList<>();
        CombineModel.Section section = new CombineModel.Section();
        section.setStart(0);
        section.setEnd(30);
        section.setColor(Color.parseColor("#C4A9F5"));
        section.setImageRes(R.drawable.icon_sleep);
        sections.add(section);

        section = new CombineModel.Section();
        section.setStart(40);
        section.setEnd(50);
        section.setColor(Color.parseColor("#00BCD4"));
        section.setImageRes(R.drawable.icon_sport);
        sections.add(section);

        section = new CombineModel.Section();
        section.setStart(60);
        section.setEnd(65);
        section.setColor(Color.parseColor("#00BCD4"));
        section.setImageRes(R.drawable.icon_sport);
        sections.add(section);

        combineModel.setSections(sections);
        List<CombineModel.Item> items = new ArrayList<>();
        for (int i = 0; i < 96; i++) {
            CombineModel.Item item = new CombineModel.Item();
            item.setIndex(i);
            if(i > 40 && i <60){
                item.setValue(0);
                if(i == 50){
                    item.setValue((int) (Math.random() * 100));
                }
            }else{
                item.setValue((int) (Math.random() * 100));
            }
            items.add(item);
//            Log.e("rrrr", " i=" + i + " value=" + item.getValue());
        }

        combineModel.setItems(items);
        combineLineChart.updateData(combineModel);

        btnHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> highlights = new ArrayList<>();
                highlights.add(10);
                highlights.add(11);
                highlights.add(12);
                highlights.add(13);
                highlights.add(43);
                highlights.add(44);
                highlights.add(50);
                highlights.add(74);
                highlights.add(75);
                combineLineChart.updateHighlight(highlights, Color.RED);
            }
        });
        btnMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> highlights = new ArrayList<>();
                highlights.add(20);
                highlights.add(21);
                highlights.add(22);
                highlights.add(23);
                highlights.add(53);
                highlights.add(54);
                combineLineChart.updateHighlight(highlights, Color.YELLOW);
            }
        });
        btnLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Integer> highlights = new ArrayList<>();
                highlights.add(70);
                highlights.add(71);
                highlights.add(72);
                highlights.add(73);
                highlights.add(74);
                combineLineChart.updateHighlight(highlights, Color.parseColor("#FF009688"));
            }
        });
    }
}
