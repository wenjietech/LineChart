package com.conways.linechart;

/**
 * Created by Conways on 2017/4/24.
 */

public interface ScrollListener {

    void onPositionSelected(int position, ChartModel chartModel);

    void onScroll(float offset);

}
