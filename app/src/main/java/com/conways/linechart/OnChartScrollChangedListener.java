package com.conways.linechart;

/**
 * Created by Conways on 2017/4/24.
 */

public interface OnChartScrollChangedListener {

    void onPositionSelected(int position, ChartModel chartModel);

    void onScrolling(int position, ChartModel chartModel);

}
